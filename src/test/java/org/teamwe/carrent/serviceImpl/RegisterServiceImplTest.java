package org.teamwe.carrent.serviceImpl;

import org.junit.Test;
import org.teamwe.carrent.utils.hash.BCryptMd5HashTest;

import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.*;

public class RegisterServiceImplTest {
    RegisterServiceImpl registerService;

    {
        try {
            registerService = new RegisterServiceImpl();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void register() {

        registerService.register("hs","1553741667@qq.com","1234","xxx","image",2,"12345678");




    }

    @Test
    public void active() {
    }
}