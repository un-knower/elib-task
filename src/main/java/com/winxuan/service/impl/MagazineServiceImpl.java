package com.winxuan.service.impl;

import com.winxuan.entity.Magazine;
import com.winxuan.repository.MagazineRepository;
import com.winxuan.service.MagazineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

/**
 * elibaio
 *
 * @Description:描述
 * @author: wangmingsen
 * @date:2016/5/18
 * @version: v1.0
 */
@Service("magazineService")
public class MagazineServiceImpl implements MagazineService {
    @Autowired
    MagazineRepository magazineRepository;

    @Override
    public Magazine save(Magazine magazine) {
        if(magazine != null){
            Date date = new Date();
            magazine.setCreateTime(date);
        }
        return magazineRepository.save(magazine);
    }

    @Override
    public Magazine update(Magazine magazine) {
        Date date = new Date();
        magazine.setUpdateTime(date);
        return magazineRepository.saveAndFlush(magazine);
    }

    @Override
    public Magazine findOne(Long id) {
        return magazineRepository.findOne(id);
    }

    @Override
    public void delete(Magazine magazine) {
        Date date = new Date();
        magazine.setUpdateTime(date);
        magazineRepository.saveAndFlush(magazine);
    }

    @Override
    public Magazine findByName(String name) {
        return magazineRepository.findByName(name);
    }

    @Override
    public List<Magazine> findAll() {
        return magazineRepository.findAll();
    }
}
