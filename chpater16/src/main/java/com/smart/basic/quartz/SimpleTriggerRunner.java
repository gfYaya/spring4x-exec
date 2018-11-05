/*
 * Created on Sep 21, 2006
 * 
 * This class is to run a scheduler with SimpleTrigger
 */
package com.smart.basic.quartz;
import java.util.Date;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;

public class SimpleTriggerRunner {
	public static void main(String args[]) {
		try {
			//创建一个JobDetial实例,指定SimpleJob
			JobDetail jobDetail = new JobDetail("job1_1", "jgroup1", SimpleJob.class);
			//通过SimpleTrigger定义调度规则:马上启动,每2秒运行一次,运行100次
			SimpleTrigger simpleTrigger = new SimpleTrigger("trigger1_1", "tgroup1");
			simpleTrigger.setStartTime(new Date());
//			simpleTrigger.setRepeatInterval(2000);
//			simpleTrigger.setRepeatCount(100);
			simpleTrigger.setRepeatInterval(2000);
			simpleTrigger.setRepeatCount(1);
            //通过SechdulerFactory获取一个调度器实例
			SchedulerFactory schedulerFactory = new StdSchedulerFactory();
			Scheduler scheduler = schedulerFactory.getScheduler();
			scheduler.scheduleJob(jobDetail, simpleTrigger);//注册并进行调度
			scheduler.start();//启用调度

			System.out.println("--------------------------------");
			//创建一个JobDetial实例,指定SimpleJob
			JobDetail jobDetail2 = new JobDetail("job1_2", "jgroup1", SimpleJob.class);
			//通过SimpleTrigger定义调度规则:马上启动,每2秒运行一次,运行100次
			SimpleTrigger simpleTrigger2 = new SimpleTrigger("trigger1_2", "tgroup1");
			simpleTrigger2.setStartTime(new Date());
			simpleTrigger2.setRepeatInterval(2000);
			simpleTrigger2.setRepeatCount(100);
			scheduler.scheduleJob(jobDetail2, simpleTrigger2);//注册并进行调度
			scheduler.start();//启用调度
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
