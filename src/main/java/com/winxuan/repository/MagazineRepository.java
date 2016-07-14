package com.winxuan.repository;


import com.winxuan.entity.Magazine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.List;

/**
 * elibaio
 *
 * @Description:总期刊操作
 * @author: wangmingsen
 * @date:2016/5/18
 * @version: v1.0
 */
public interface MagazineRepository extends JpaRepository<Magazine, Long>, JpaSpecificationExecutor<Magazine> {
    Magazine findByName(String name);
    List<Magazine> findAll();
}
