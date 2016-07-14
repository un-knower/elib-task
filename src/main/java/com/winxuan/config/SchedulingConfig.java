package com.winxuan.config;

/**
 * 定时任务配置类
 * Created by wangmingsen on 2016/7/5.
 */

import com.winxuan.support.MagazineSync;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling // 启用定时任务
public class SchedulingConfig {
    private final Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    MagazineSync magazineSync;

    @Scheduled(cron = "0 0 0 0/2 * ?") // 每2天执行一次
    public void scheduler() {
        logger.info(">>>>>>>>>>>>> Start timing task ! ... ");
        magazineSync.callRpc();
    }
    @Scheduled(cron = "0 0/2 * * * ?") // 每2分钟执行一次
    public void partScheduler() {
        logger.info(">>>>>>>>>>>>> elib_sync 程序正常运行 ... ");
    }
}
