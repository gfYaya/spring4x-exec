package com.smart.basic.quartz;
import java.util.Date;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;

//public class SimpleJob implements Job{

//及时使用了StatefulJob,也无法做到阻塞式的.
//http://www.quartz-scheduler.org/documentation/quartz-1.x/tutorials/TutorialLesson03#TutorialLesson3-StatefulJob
public class SimpleJob implements StatefulJob {
	//实现Job接口
	public void execute(JobExecutionContext jobCtx) throws JobExecutionException {
		if(jobCtx.getTrigger().getName().equals("trigger1_1")){
			try {
				System.out.println("trigger1_1 starting");
				Thread.sleep(10000);
				System.out.println("trigger1_1 go to end");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(jobCtx.getTrigger().getName() + " triggered. time is:" + (new Date()));
	}
}
