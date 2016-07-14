package com.winxuan.repository;

import com.winxuan.entity.MagazineItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * elibaio
 *
 * @Description:分期刊操作
 * @author: wangmingsen
 * @date:2016/5/18
 * @version: v1.0
 */
public interface MagazineItemRepository extends JpaRepository<MagazineItem, Long>, JpaSpecificationExecutor<MagazineItem> {
    @Query()
    MagazineItem findByItemId(Integer itemId);
}
