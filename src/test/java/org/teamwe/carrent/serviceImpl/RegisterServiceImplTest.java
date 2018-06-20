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
    private RegisterService registerService;


    @Test
    public void register() {

        registerService.register("hs","1553741667@qq.com","1234","xxx","image",2,"12345678");


    }

    @Test
    public void active() {
        registerService.active("DLG8euEtP2mJm4gH3gO7");
    }


}