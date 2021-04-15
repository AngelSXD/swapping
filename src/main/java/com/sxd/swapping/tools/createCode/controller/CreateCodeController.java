package com.sxd.swapping.tools.createCode.controller;

import com.sxd.swapping.mybatis.vo.UniVerResponse;
import com.sxd.swapping.tools.createCode.util.CreateCodeUtil;
import com.sxd.swapping.utils.HashMapLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("createCode")
@RestController
public class CreateCodeController {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    public static final String TEN_CODE_GLOBAL_KEY_IN_REDIS = "PISEN-CLOUD-LUNA-SECURITY-CODE-TEN-GLOBAL:";



    @RequestMapping("getCode")
    public UniVerResponse<String>  createCode(){

        String uid = "test";
        //使用hashmap实现同步锁
        //这里uid可以是
        // 1>使用者的uid,标明 同一个使用者同一时间只能有一个获取码的任务
        // 2>任务单的uid,标明 一个使用者如果有不同的任务单,可以保证一个用户的多个任务单的每一个任务单只能有一个获取码的任务

        synchronized(HashMapLock.getLock(uid)){

            UniVerResponse<String> res = new UniVerResponse<>();
            //区分用户的基础序列值
            String serialNumber = "";

            //1.例如:user.id是数据库自增的
            //2.例如本user想要下载防伪码,那么先取出他的id
            //3.例如id = 10L 或者用户id可以是1000L
            Long id = 99L;
            String str2 = CreateCodeUtil.fmtStringAddZero(id,3,"0");

            //使用redis的增量方法  达到每次用户调用这个获取码  都会次数+1
            ValueOperations<String, String> opv = stringRedisTemplate.opsForValue();
            //[key:value]  [PISEN-CLOUD-LUNA-SECURITY-CODE-TEN-GLOBAL:099   :   次数]
            String str1 = opv.increment(TEN_CODE_GLOBAL_KEY_IN_REDIS + str2, 1).toString();

            str1 = CreateCodeUtil.fmtStringAddZero(str1,5,"0");

            serialNumber = str1 + str2;
            //生成 20个不重复的code
            List<String> codeList = CreateCodeUtil.getCode(serialNumber,2000);
            for (String s : codeList) {
                System.out.println(s);
            }

            res.beTrue("成功");
            return  res;
        }
    }



}
