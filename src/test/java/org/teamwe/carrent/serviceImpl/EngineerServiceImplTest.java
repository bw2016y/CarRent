package org.teamwe.carrent.serviceImpl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class EngineerServiceImplTest {

    @Autowired
    private EngineerServiceImpl engineerService;
    @Test
    public void getEngineer() {
        engineerService.getEngineer();

    }

    @Test
    public void check() {
        engineerService.check("new9.com");
    }
}