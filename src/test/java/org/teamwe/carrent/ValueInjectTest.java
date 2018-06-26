package org.teamwe.carrent;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.util.Arrays;

/**
 * @author FDws
 * Created on 2018/6/20 15:08
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CarRentApplication.class)
public class ValueInjectTest {
    @Value("${file.image-path}")
    private String imagePath;

    @Value("${file.max-file-size}")
    private long maxFileSize;

    @Value("${project.controller.allowed.origins}")
    private String[] origins;
    @Value("${project.controller.allowed.methods}")
    private String[] methods;
    @Value("${project.controller.domain}")
    private String domain;
    @Value("${project.controller.verify.interval:1000}")
    private int interval;
    @Value("${project.controller.verify.width:200}")
    private int width;
    @Value("${project.controller.verify.height:60}")
    private int height;
    @Value("${project.controller.verify.number:6}")
    private int number;

    @Value("#{'${project.controller.allowed.origins}'.split(', *')}")
    private String[] front;


    @Test
    public void test() {
        System.out.println(imagePath);
        System.out.println(maxFileSize);
        System.out.println(Arrays.toString(origins));
        System.out.println(Arrays.toString(methods));
        System.out.println(domain);
        System.out.println(interval);
        System.out.println(width);
        System.out.println(height);
        System.out.println(number);
        System.out.println(front[0]);
    }

    @Test
    public void test1() {
//        User u = new User();
//        u.setEmail("123@qq.com");
//        u.setCredit(123);
//        u.setHead("fjdlsfkdsa");
//        u.setLicence("234232");
//        u.setPassword("password");
//        String key = System.currentTimeMillis() + "";
//
//        redis.opsForValue().set(key, u);
//
//        System.out.println(redis.opsForValue().get(key));
    }
}
