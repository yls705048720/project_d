/**
 * 
 */
package com.yls.bus.sys.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 没有持久化 服务器启动开始
 * @author Lian Shan Yang
 *
 */
@Component
public class TaskExample {
	
	/**
	 * 缺点
	 * 
	 * 1、该种方法，是将定时任务序列化到内存当中的。也就是说，当系统重新部署，需要重启服务器时，该定时任务会从内存中清除（几乎是废话
	 * ，应用服务器都停了，定时任务还怎么能够在内存当中。。），当应用服务器重新启动后，定时任务才会重新载入内存当中。
	 * 所以说，这种定时任务，应用服务器停止时间如果比较长的话，则当日此期间未执行的定时任务，就没有机会执行了。
	 * 
	 * 2、执行执行并不是非常准确的（即执行时间不准确）。也就是说定点儿执行的定时任务，到了时间可能仍然没有执行。
	 * 这是为什么呢？定时任务，说白了，就是一个进程。应用服务器启动时，定时任务进入内存，并进入阻塞状态。这时，
	 * 我们可以理解为有一个timer不断去扫面执行时间。当到执行时间是，经过内存调度，即将执行的定时任务进入就绪状态，
	 * 等待CPU进行调度，注意它并不会阻塞其他线程的调度，而是将自己置于就绪状态等待CPU空闲时调度。所以说，如果CPU一直忙碌的话，
	 * 就会发生定时任务到时间仍未执行的情况
	 */
	
    /** 
     * cron表达式：* * * * * *（共6位，使用空格隔开，具体如下）  
     * cron表达式：*(秒0-59) *(分钟0-59) *(小时0-23) *(日期1-31) *(月份1-12或是JAN-DEC) *(星期1-7或是SUN-SAT)  
     * 注意： 30 * * * * * 表示每分钟的第30秒执行，而（*斜杠30）表示每30秒执行 
     *  
     * */  
    @Scheduled(cron="*/30 * * * * *")  
    public void firstTask(){  
        System.out.println("==============it is first task!时间："+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));  
    }  
}
