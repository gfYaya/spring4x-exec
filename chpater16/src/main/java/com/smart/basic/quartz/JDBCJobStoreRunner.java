package com.smart.basic.quartz;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

//http://www.quartz-scheduler.org/documentation/quartz-1.x/tutorials/TutorialLesson09#TutorialLesson9-JDBCJobStore
//从删除文档来看,表应该是固定的
public class JDBCJobStoreRunner {
	public static void main(String args[]) {
		try {
			SchedulerFactory schedulerFactory = new StdSchedulerFactory();
			Scheduler scheduler = schedulerFactory.getScheduler();
			//获取调度器中所有的触发器组
			String[] triggerGroups = scheduler.getTriggerGroupNames();
			for (int i = 0; i < triggerGroups.length; i++) {
				//重新恢复在tgroup1组中名为trigger1_1的触发器运行
				String[] triggers = scheduler.getTriggerNames(triggerGroups[i]);
				for (int j = 0; j < triggers.length; j++) {
					Trigger tg = scheduler.getTrigger(triggers[j], triggerGroups[i]);
					if (tg instanceof SimpleTrigger && tg.getFullName().equals("tgroup1.trigger1_1")) {//根据名称判断
//						((SimpleTrigger) tg).setRepeatCount(100);
						scheduler.rescheduleJob(triggers[j], triggerGroups[i],tg);//运行恢复
					}
				}
			}
			scheduler.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
