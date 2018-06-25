package org.teamwe.carrent.utils.serviceUtil;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.teamwe.carrent.entity.User;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ResisTestTest {

    @Autowired
    private ResisTest resisTest;
    @Test
    public void saveUserO() {
        User user = new User();
        user.setName("韩硕");
        resisTest.saveUserO("123",user);


    }

    @Test
    public void getUserO() {
        System.out.println(resisTest.getUserO("123"));
    }

    @Test
    public void getUser(){
        System.out.println(resisTest.isExist("123"));
    }

    @Test
    public void test(){
        System.out.println(ResisTest.password);
    }
}