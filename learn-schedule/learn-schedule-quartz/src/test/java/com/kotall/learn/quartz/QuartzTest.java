package com.kotall.learn.quartz;

import com.kotall.learn.quartz.job.HelloJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * desc:
 *
 * @author zpwang
 * @date 2019/11/21 13:18
 * @since 1.0.0
 */
public class QuartzTest {
    public static void main(String[] args) {
        try {
            Scheduler schedule = StdSchedulerFactory.getDefaultScheduler();

            schedule.start();

            JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
                    .withIdentity("job1", "group1")
                    .build();

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("trigger1", "group1")
                    .startNow()
                    //.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).repeatForever())
                    //.withSchedule(CronScheduleBuilder.cronSchedule("0/10 * * * * ? *"))
                    .withSchedule(CalendarIntervalScheduleBuilder.calendarIntervalSchedule().withInterval(5, DateBuilder.IntervalUnit.SECOND))
                    .build();

            schedule.scheduleJob(jobDetail, trigger);
            //schedule.shutdown();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
