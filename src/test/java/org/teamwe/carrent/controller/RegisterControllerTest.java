package org.teamwe.carrent.controller;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.teamwe.carrent.CarRentApplication;
import org.teamwe.carrent.controller.utils.FileUtil;
import org.teamwe.carrent.controller.utils.Format;


/**
 * @author FDws
 * Created on 2018/6/15 8:58
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CarRentApplication.class)
@WebAppConfiguration
public class RegisterControllerTest {
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;
    @Autowired
    private FileUtil fu;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void register() throws Exception {
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/user")
                .param("name", "test")
                .param("email", "1234@123.com")
                .param("password", "pass123")
                .param("code", "1234")
                .param("license", "123123123123123159")
                .param("type", "1")
                .param("phone", "12312312312")).andDo(MockMvcResultHandlers.print()).andReturn();
        System.out.println(result.getAsyncResult());
        System.out.println(fu.getMaxFileSize());
    }
}