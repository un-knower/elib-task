package com.winxuan.service;


import com.winxuan.entity.MagazineItem;

/**
 * elibaio
 *
 * @Description:子期刊服务
 * @author: wangmingsen
 * @date:2016/5/18
 * @version: v1.0
 */

public interface MagazineItemService extends BaseService<MagazineItem> {

    MagazineItem findByItemId(Integer itemId);
}
