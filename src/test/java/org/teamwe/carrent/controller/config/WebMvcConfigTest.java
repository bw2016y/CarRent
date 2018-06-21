package org.teamwe.carrent.controller.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.teamwe.carrent.CarRentApplication;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * @author FDws
 * Created on 2018/6/20 14:44
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CarRentApplication.class)
public class WebMvcConfigTest {
    @Autowired
    WebMvcConfig config;

    @Test
    public void getAllowedMethods() {
        System.out.println(Arrays.toString(config.getAllowedMethods()));
        System.out.println(Arrays.toString(config.getAllowedOrigins()));
    }

    @Test
    public void getAllowedOrigins() {
    }
}