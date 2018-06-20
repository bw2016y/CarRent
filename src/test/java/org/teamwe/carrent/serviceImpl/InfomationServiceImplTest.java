package org.teamwe.carrent.serviceImpl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.teamwe.carrent.entity.User;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class InfomationServiceImplTest {

    @Autowired
    private  InfomationServiceImpl infomationService;
    @Test
    public void info() {
        infomationService.info("1553741667@qq.com");
    }

    @Test
    public void changeInfo() {
        User user = infomationService.info("1553741667@qq.com");

        user.setName("韩硕");
        user.setStatus(0);

        infomationService.changeInfo(user);
    }

    @Test
    public void delete() {
        infomationService.delete("1553741667@qq.com");
    }

    @Test
    public void forgetPassword() {
        infomationService.forgetPassword("1553741667@qq.com");
    }

    @Test
    public void resetPassword() {
        infomationService.resetPassword("P872FkmUrQH5jeTGUucN","123456789");
    }
}