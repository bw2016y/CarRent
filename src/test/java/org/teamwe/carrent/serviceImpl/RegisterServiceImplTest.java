package org.teamwe.carrent.serviceImpl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.teamwe.carrent.service.RegisterService;
import org.teamwe.carrent.utils.hash.BCryptMd5HashTest;

import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.*;


@SpringBootTest
@RunWith(SpringRunner.class)
public class RegisterServiceImplTest {

    @Autowired
    private RegisterServiceImpl registerService;


    @Test
    public void register() {

        registerService.register("hs","155374166@qq.com","123456789","xxx","image",2,"12345678");


    }

    @Test
    public void active() {
        registerService.active("YoyZ75fRcxWccDuxh3Mp");
    }

    @Test
    public void test(){
        System.out.println(registerService.host);
    }





}