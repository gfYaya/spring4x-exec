package com.smart.quartz;

import java.util.Map;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;
import org.springframework.context.ApplicationContext;

public class MyJob implements StatefulJob {
	public void execute(JobExecutionContext jctx) throws JobExecutionException {
	    //size存储在JobDetail的JobDataMap中,count存储在Trigger的JobDataMap中
//		Map dataMap = jctx.getJobDetail().getJobDataMap(); //size能读到,但是count始终为null
		Map dataMap = jctx.getTrigger().getJobDataMap();  //count始终是10,但是size为null
		String size =(String)dataMap.get("size");
		//可以通过键值直接获取ctx,我只想说好tm神奇,JobDataMap里面还包装这个东西
        ApplicationContext ctx = (ApplicationContext)dataMap.get("applicationContext");
        System.out.println("size:"+size);
        dataMap.put("size",size+"0");
        
        String count =(String)dataMap.get("count");
        System.out.println("count:"+count);
	}
}
