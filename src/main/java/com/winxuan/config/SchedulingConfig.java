package com.winxuan.config;

/**
 * 定时任务配置类
 * Created by wangmingsen on 2016/7/5.
 */

import com.winxuan.support.KettleExecu;
import com.winxuan.support.MagazineSync;
import com.winxuan.web.App;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling // 启用定时任务
public class SchedulingConfig {
    public static final Log LOG = LogFactory.getLog(SchedulingConfig.class);

    @Value("${kittle.kjb.file}")
    String kittleKjbFile;
    @Value("${kittle.kjb.file.booksync}")
    String bookSync;



    @Autowired
    MagazineSync magazineSync;
    @Autowired
    KettleExecu kettleExecu;
    /**
     * 中邮期刊数据同步
     */
    @Scheduled(cron = "0 0 0 0/2 * ?") // 每2天执行一次
    public void scheduler() {
        LOG.info("中邮 data sync start");
        magazineSync.callRpc();
        LOG.info("中邮 data sync accomplish");
    }

    /**
     * 同步到统计平台
     */
    @Scheduled(cron = "0 0 0/4 * * ?") // 每4小时执行一次
    public void behaviorRecordJob() {
        LOG.info("sync start ");
        kettleExecu.runJob(kittleKjbFile);
        LOG.info(" sync accomplish");
    }
    /**
     * 数图和九月 Book同步到统计平台
     */
 /*   @Scheduled(cron = "0 0 0/4 * * ?")*/ // 每4小时执行一次
    public void bookJobSync() {
        LOG.info("Book data sync start");
        kettleExecu.runJob(bookSync);
        LOG.info("Book data sync accomplish");
    }
}
