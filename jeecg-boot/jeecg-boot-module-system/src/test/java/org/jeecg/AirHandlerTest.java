package org.jeecg;

import org.jeecg.modules.gas.AirHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class AirHandlerTest {
    @Resource
    private AirHandler airHandler;

    @Test
    public void test() {
        airHandler.handle(new byte[]{1, 35, 64, 0, 0, 8, -128, 0, 0, 8, -128, 0, 0, 8, -128, 0, 0, 8, -128, 0, 0, 8, -128, 0, 0, 8, -128, 0, 0, 8, -128, 0, 0, 8, -128, 0, 0, 23, -128, 0, 4, 23, -128, 0, 0, 8, -123, 0, 0, 8, -123, 0, 0, 8, -123, 0, 0, 8, -123, 0, 0, 8, -123, 0, 0, 8, -123, 15, 27});
    }
}
