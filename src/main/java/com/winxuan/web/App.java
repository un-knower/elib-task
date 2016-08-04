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
    @Value("${kittle.kjb.file.booksync}")
    String bookSync;


    @Autowired
    KettleExecu kettleExecu;

    @RequestMapping("/")
    String home() {
        return "hello world";
    }



    @RequestMapping("/book/sync")
    String bookSync() {
        LOG.info("book sync start");
        kettleExecu.runJob(bookSync);
        LOG.info("book sync end");
        return "book sync ok";
    }

    @RequestMapping("/sync")
    String sync() {
        LOG.info("sync start");
        kettleExecu.runJob(kittleKjbFile);
        LOG.info("sync accomplish");
        return "sync ok";
    }


}
