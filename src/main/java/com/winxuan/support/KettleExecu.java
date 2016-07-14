package com.winxuan.support;


import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.exception.KettleXMLException;
import org.pentaho.di.core.util.EnvUtil;
import org.pentaho.di.job.Job;
import org.pentaho.di.job.JobMeta;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;
import org.springframework.stereotype.Component;

/**
 * Created by wangmingsen on 2016/7/13.
 */
@Component
public class KettleExecu {
    public static void runJob(String jobname) {
        try {
            KettleEnvironment.init();
            // jobname 是Job脚本的路径及名称
            JobMeta jobMeta = new JobMeta(jobname, null);
            Job job = new Job(null, jobMeta);
            // 向Job 脚本传递参数，脚本中获取参数值：${参数名}
            // job.setVariable(paraname, paravalue);

            job.start();
            job.waitUntilFinished();
            if (job.getErrors() > 0) {
                System.out.println("decompress fail!");
            }
        } catch (KettleException e) {
            System.out.println(e);
        }
    }

    // 调用Transformation示例：
    public static void runTrans(String filename) {
        try {
            KettleEnvironment.init();
            EnvUtil.environmentInit();
            TransMeta transMeta = new TransMeta(filename);
            Trans trans = new Trans(transMeta);
           // trans.execute(null);
            trans.prepareExecution(null);
            trans.startThreads();
            trans.waitUntilFinished();
            if (trans.getErrors() != 0) {
                System.out.println("Error");
            }
        } catch (KettleXMLException e) {
            e.printStackTrace();
        } catch (KettleException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String filename = "F:\\kittle\\test.ktr";
        runTrans(filename);

    }


}
