package com.winxuan.web;

import com.winxuan.support.KettleExecu;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangmingsen on 2016/7/5.
 */
@RestController
public class App {
    public static final Log LOG = LogFactory.getLog(App.class);

    @Value("${kittle.kjb.file}")
    String kittleKjbFile;
    @Value("${kittle.ktr.file.shutubooksync}")
    String shutuBookSync;
    @Value("${kittle.ktr.file.jiuyuebooksync}")
    String jiuyueBookSync;
    @Autowired
    KettleExecu kettleExecu;

    @RequestMapping("/")
    String home() {
        return "hello world";
    }


    /**
     * 数图BOOK同步
     * @return
     */
    @RequestMapping("shutu/book/sync")
    String shutuBookSync() {
        try {
            LOG.info("shutu book sync start");
            kettleExecu.runTrans(shutuBookSync);
            LOG.info("shutu book sync end");
            return "shutu book sync ok";
        } catch (Exception e) {
            return "shutu book sync fail";
        }

    }

    /**
     * 九月BOOK同步
     * @return
     */

    @RequestMapping("jiuyue/book/sync")
    String jiuyueBookSync() {
        try {
            LOG.info("jiuyue book sync start");
            kettleExecu.runTrans(jiuyueBookSync);
            LOG.info("jiuyue book sync end");
            return "jiuyue book sync ok";
        } catch (Exception e) {
            return "jiuyue book sync fail";
        }
    }

    /**
     * 其他行为数据同步
     * @return
     */
    @RequestMapping("/sync")
    String sync() {
        try {
            LOG.info("sync start");
            kettleExecu.runJob(kittleKjbFile);
            LOG.info("sync accomplish");
            return "sync ok";
        } catch (Exception e) {
            return "sync fail";
        }
    }


}
