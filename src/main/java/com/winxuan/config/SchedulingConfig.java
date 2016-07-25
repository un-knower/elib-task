package com.winxuan.config;

/**
 * 定时任务配置类
 * Created by wangmingsen on 2016/7/5.
 */

import com.winxuan.support.KettleExecu;
import com.winxuan.support.MagazineSync;
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
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${kittle.kjb.file}")
    String kittleKjbFile;



    @Autowired
    MagazineSync magazineSync;
    @Autowired
    KettleExecu kettleExecu;
    /**
     * 中邮期刊数据同步
     */
    @Scheduled(cron = "0 0 0 0/2 * ?") // 每2天执行一次
    public void scheduler() {
        logger.info("中邮数据同步 start 》》》》》");
        magazineSync.callRpc();
        logger.info("中邮数据同步 end 》》》》》");
    }

    /**
     * 同步到统计平台
     */
    @Scheduled(cron = "0 0 0/2 * * ?") // 每1小时执行一次
    public void behaviorRecordJob() {
        logger.info("到统计平台开始同步》》》》》");
        kettleExecu.runJob(kittleKjbFile);
        logger.info("到统计平台记录同步完成》》》》》");
    }
}
