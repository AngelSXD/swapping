package com.sxd.swapping.schedule;

import com.sxd.swapping.service.HuaYangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AutoUpdateDateSchedule {

    @Autowired
    HuaYangService service;

    //每一分钟执行一次
//    @Scheduled(cron = "0 0/1 * * * ?")
    public void dealUpdateTask(){
        System.out.println("定时更新数据库操作--->开始");

        //只做最简单的 业务操作
        service.scheduleUpdate();

        System.out.println("定时更新数据库操作--->结束");
    }

}
