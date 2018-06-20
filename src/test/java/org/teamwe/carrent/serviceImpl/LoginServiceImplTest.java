package org.teamwe.carrent.serviceImpl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class LoginServiceImplTest {

    @Autowired LoginServiceImpl loginService;

    @Test
    public  void login(){
        loginService.login("1553741667@qq.com","123456789");
    }
}
