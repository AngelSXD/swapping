package com.sxd.swapping;

import com.sxd.swapping.controller.FirstController;
import com.sxd.swapping.service.BaseService;
import com.sxd.swapping.service.impl.BaseServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
//编写测试类的时候，如果明确这个测试类会用到哪几个 Bean，则可以在 classes 属性处指定，之后启动测试类的时候，就只会加载需要的 Bean 到上下文中，从而加快启动速度
//否则 只使用@SpringBootTest注解即可
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes={BaseServiceImpl.class,FirstController.class})
public class SwappingApplicationTests {

    @Autowired
    FirstController firstController;

    @Test
    public void contextLoads() {
        firstController.myTestController();
    }

}
