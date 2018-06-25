package org.teamwe.carrent.controller.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.teamwe.carrent.CarRentApplication;

import static org.junit.Assert.*;

/**
 * @author FDws
 * Created on 2018/6/20 15:03
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CarRentApplication.class)
public class AlipayConfigTest {

    @Value("${project.controller.domain}")
    private String url;

    @Value("#{'${project.controller.allowed.origins}'.split(', *')}")
    private String[] domainFront;

    @Autowired
    private Environment env;

    @Test
    public void test() {
        System.out.println(url + ":" + env.getProperty("local.server.port"));
        System.out.println(url);
    }
}