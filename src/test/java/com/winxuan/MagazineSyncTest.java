package com.winxuan;

import com.winxuan.support.MagazineSync;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import java.io.IOException;

/**
 * Created by wangmingsen on 2016/7/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoApplication.class)
@WebAppConfiguration
public class MagazineSyncTest {

    @Autowired
    MagazineSync magazineSync;


    @Test
    public void test() throws IOException {

    }



}