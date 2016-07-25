package com.winxuan.support;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.exception.KettleXMLException;
import org.pentaho.di.job.Job;
import org.pentaho.di.job.JobMeta;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by wangmingsen on 2016/7/13.
 */
@Component
public class KettleExecu {
    @Value("${kittle.filePath}")
    String filePath;

    public static final Log LOG = LogFactory.getLog(KettleExecu.class);
    public  void runJob(String file) {
        System.out.println(filePath);
        try {
            KettleEnvironment.init();
            // jobname 是Job脚本的路径及名称
            JobMeta jobMeta = new JobMeta(file, null);
            Job job = new Job(null, jobMeta);
            // 向Job 脚本传递参数，脚本中获取参数值：${参数名}
            // job.setVariable(paraname, paravalue);

            job.setVariable("file_path",filePath);
            job.start();
            job.waitUntilFinished();
            if (job.getErrors() > 0) {
                LOG.error("decompress fail!");
            }
        } catch (KettleException e) {
            System.out.println(e);
        }
    }

    // 调用Transformation：
    public void runTrans(String file) {
        try {
            KettleEnvironment.init();
            TransMeta transMeta = new TransMeta(file);
            Trans trans = new Trans(transMeta);
            trans.setVariable("file_path",filePath);
            trans.prepareExecution(null);
            trans.startThreads();
            trans.waitUntilFinished();
            if (trans.getErrors() != 0) {

            }
        } catch (KettleXMLException e) {
            LOG.error(e);
        } catch (KettleException e) {
            LOG.error(e);
        }
    }



}
