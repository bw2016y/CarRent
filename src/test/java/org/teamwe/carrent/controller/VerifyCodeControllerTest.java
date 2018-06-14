package org.teamwe.carrent.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.teamwe.carrent.CarRentApplication;

/**
 * @author FDws
 * Created on 2018/6/14 15:41
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CarRentApplication.class)
@WebAppConfiguration
public class VerifyCodeControllerTest {
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void genCode() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/validate")).andReturn();
    }
}