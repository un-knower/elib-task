package com.winxuan.support;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.winxuan.entity.Magazine;
import com.winxuan.entity.MagazineItem;
import com.winxuan.service.MagazineItemService;
import com.winxuan.service.MagazineService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * Created by wangmingsen on 2016/7/4.
 */
@Component
public class MagazineSync {

    public static final Log LOG = LogFactory.getLog(MagazineSync.class);
    public static final String IDENTIFYKEY = "37cba4406591402dbe0ab99a9557c9ea";
    public static final String APPKEY = "b61a51f2dacb81a4bdedcacca74af4e7";
    public static final String URL = "http://api.183read.cc/open_api/rest.php?act=?1&param=?2";


    public static final String PAGE_NUM = "1";
    public static final String PAGE_LIMIT = "10";

    @Autowired
    MagazineItemService magazineItemService;
    @Autowired
    MagazineService magazineService;

    String className = null;

    StringBuffer sb = new StringBuffer();


    /**
     * 调用接口同步
     */
    public void callRpc() {
        long startTime = System.currentTimeMillis();
        JSONObject result = magazineClass();
        if (statusCheck(result)) {
            LOG.info("获取分类成功!");
            JSONArray classArray = result.getJSONArray("class");
            classArray.forEach(o -> {
                JSONObject aClass = (JSONObject) o;
                String classId = aClass.getString("class_id");
                className = aClass.getString("class_name");
                String coverImage = aClass.getString("cover_image");
                classItems(classId, PAGE_NUM);
            });
        }
        long endTime = System.currentTimeMillis();

        LOG.info("程序耗时" + (endTime - startTime));

    }

    /**
     * 循环遍历数据
     *
     * @param classId
     * @param pageNum
     */
    void classItems(String classId, String pageNum) {
        if (!StringUtils.isEmpty(classId)) {
            //分类下的期刊
            JSONObject classItems = magazineClassList(classId, pageNum);
            if (statusCheck(classItems)) {
                LOG.info("获取分类列表数据成功!");
                //页数
                JSONObject pageInfo = classItems.getJSONObject("page_info");
                //总期刊列表
                JSONArray magazineList = classItems.getJSONArray("magazine_list");
                final int[] totalItemNum = {0};
                final int[] currentPageNum = {0};
                final String[] pageN = {"1"};
                magazineList.forEach(o -> {
                    JSONObject ja = (JSONObject) o;
                    //总期刊ID
                    String mid = ja.getString("magazine_id");
                    //总期刊名称
                    String mName = ja.getString("magazine_name");
                    sb.append(mName).append("\r\n");
                    do {

                        //总期刊下的子期刊列表
                        JSONObject magazineItems = magazineItems(mid, pageN[0]);
                        if (statusCheck(magazineItems)) {
                            JSONObject itemPageInfo = magazineItems.getJSONObject("page_info");
                            //历史期刊
                            JSONArray historyItemList = magazineItems.getJSONArray("history_item_list");
                            historyItemList.forEach(ob -> {
                                JSONObject jsonObject = (JSONObject) ob;
                                Integer itemId = jsonObject.getIntValue("item_id");
                                String itemName = jsonObject.getString("item_name");
                                String volume = jsonObject.getString("volume");
                                Integer magazineId = jsonObject.getIntValue("magazine_id");
                                String coverImage = jsonObject.getString("cover_image");
                                MagazineItem magazineItem = magazineItemService.findByItemId(itemId);
                                if (null == magazineItem) {
                                    LOG.info("add magazineItem -> itemId:" + itemId + " >>>>> itemName:" + itemName);
                                    MagazineItem item = new MagazineItem();
                                    item.setItemId(itemId);
                                    item.setName(volume);
                                    item.setItemMagazineId(magazineId);
                                    item.setCoverImage(coverImage);
                                    item.setResType(2);
                                    Magazine magazine = null;
                                    magazine = magazineService.findByName(itemName);
                                    if (null == magazine) {
                                        magazine = new Magazine();
                                        magazine.setName(itemName);
                                        magazine.setSource(2);
                                        magazineService.save(magazine);
                                    }
                                    item.setMagazine(magazine);
                                    magazineItemService.save(item);
                                    LOG.info("add succeed!");

                                }

                            });
                            totalItemNum[0] = itemPageInfo.getIntValue("total_page_num");
                            currentPageNum[0] = itemPageInfo.getIntValue("current_page_num");
                            pageN[0] = String.valueOf(currentPageNum[0] + 1);
                        }


                    } while (totalItemNum[0] > currentPageNum[0]);
                });
                totalItemNum[0] = pageInfo.getIntValue("total_page_num");
                currentPageNum[0] = pageInfo.getIntValue("current_page_num");
                if (totalItemNum[0] > currentPageNum[0]) {
                    classItems(classId, String.valueOf(currentPageNum[0] + 1));
                }
            }
        }
    }

    /**
     * 专题分类获取
     *
     * @return JSONObject
     * /*
     */
     JSONObject magazineClass() {
        JSONObject param = new JSONObject();
        param.put("identify_key", IDENTIFYKEY);
        param.put("app_key", APPKEY);
        String remoteUrl = URL.replace("?1", "magazine.class.get")
                .replace("?2", param.toString());
        JSONObject jsonObj = JSONObject.parseObject(sendGet(remoteUrl));
        return jsonObj.getJSONObject("result");


    }

    /**
     * 专题分类列表获取
     *
     * @return JSONObject
     */
     JSONObject magazineClassList(String classId, String pageNum) {
        JSONObject param = new JSONObject();
        param.put("identify_key", IDENTIFYKEY);
        param.put("app_key", APPKEY);
        param.put("class_id", classId);
        param.put("page_num", pageNum);
        param.put("page_limit", PAGE_LIMIT);
        String remoteUrl = URL.replace("?1", "magazine.class.items.get")
                .replace("?2", param.toString());
        JSONObject jsonObj = JSONObject.parseObject(sendGet(remoteUrl));
        return jsonObj.getJSONObject("result");


    }


    /**
     * 专题分刊列表获取
     * s
     *
     * @return JSONObject
     */
    JSONObject magazineItems(String magazineId, String pageNum) {
        JSONObject param = new JSONObject();
        param.put("identify_key", IDENTIFYKEY);
        param.put("app_key", APPKEY);
        param.put("magazine_id", magazineId);
        param.put("page_num", pageNum);
        param.put("page_limit", PAGE_LIMIT);
        String remoteUrl = URL.replace("?1", "magazine.history.items.get")
                .replace("?2", param.toString());
        JSONObject jsonObj = JSONObject.parseObject(sendGet(remoteUrl));
        return jsonObj.getJSONObject("result");
    }


    /**
     * 检查返回状态
     *
     * @param result
     * @return boolean
     */
    boolean statusCheck(JSONObject result) {
        JSONObject statusInfo = result.getJSONObject("status_info");
        String statusCode = statusInfo.getString("status_code");
        if (statusCode.equals("100")) {
            return true;
        } else {
            LOG.error(statusCode + statusInfo);
            return false;
        }
    }

    public static String sendGet(String URL) {
        String result = "";
        BufferedReader in = null;
        try {
            String URLNameString = URL;
            URL realUrl = new URL(URLNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("accept-language", "zh-CN,zh;q=0.8");
            connection.setRequestProperty("content-type", "application/json");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                LOG.error(e2.getMessage());
            }
        }
        return result;
    }

}
