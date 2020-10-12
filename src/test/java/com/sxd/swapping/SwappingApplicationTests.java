package com.sxd.swapping;

import com.sxd.swapping.controller.FirstController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SwappingApplicationTests {

    @Autowired
    FirstController firstController;

    @Test
    public void contextLoads() {
        firstController.myTestController();
    }

}
