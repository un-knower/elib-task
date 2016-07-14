package com.winxuan.service;


import com.winxuan.entity.Magazine;
import java.util.List;

/**
 * elibaio
 *
 * @Description:总期刊服务
 * @author: wangmingsen
 * @date:2016/5/18
 * @version: v1.0
 */
public interface MagazineService extends BaseService<Magazine> {
	Magazine findByName(String name);
	List<Magazine> findAll();
}
