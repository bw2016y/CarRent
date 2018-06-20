package org.teamwe.carrent.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.teamwe.carrent.CarRentApplication;
import org.teamwe.carrent.controller.utils.FileUtil;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * @author FDws
 * Created on 2018/6/20 8:47
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CarRentApplication.class)
public class ProjectConfigTest {
    @Autowired
    FileUtil fu;

    @Autowired
    ProjectConfig config;

    @Test
    public void getAllowedMethods() {
        System.out.println(fu.getMaxFileSize());
        System.out.println(fu.getImageParent().getAbsolutePath());

        System.out.println(Arrays.toString(config.getAllowedOrigins()));
        System.out.println(config.getAllowedOrigins().length);
        System.out.println(Arrays.toString(config.getAllowedMethods()));
        System.out.println(config.getAllowedMethods().length);
    }

    @Test
    public void getAllowedOrigins() {
    }
}