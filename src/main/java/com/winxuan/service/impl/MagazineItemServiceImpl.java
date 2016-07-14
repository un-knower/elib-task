package com.winxuan.service.impl;

import com.winxuan.entity.MagazineItem;
import com.winxuan.repository.MagazineItemRepository;
import com.winxuan.service.MagazineItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;

/**
 * elibaio
 *
 * @Description:描述
 * @author: wangmingsen
 * @date:2016/5/18
 * @version: v1.0
 */
@Service("MagazineItemService")
public class MagazineItemServiceImpl implements MagazineItemService {

    @Autowired
    MagazineItemRepository magazineItemRepository;

    @Override
    public MagazineItem save(MagazineItem magazineItem) {
        Date date = new Date();
        magazineItem.setCreateTime(date);
        return magazineItemRepository.save(magazineItem);
    }

    @Override
    public MagazineItem update(MagazineItem magazineItem) {

        return magazineItemRepository.saveAndFlush(magazineItem);
    }

    @Override
    public MagazineItem findOne(Long id) {
        return magazineItemRepository.findOne(id);
    }

    @Override
    public void delete(MagazineItem magazineItem) {

        magazineItemRepository.saveAndFlush(magazineItem);
    }

    @Override
    public MagazineItem findByItemId(Integer itemId) {
        return magazineItemRepository.findByItemId(itemId);
    }
}
