package org.teamwe.carrent.controller.utils;

import org.junit.Test;
import org.teamwe.carrent.entity.User;


/**
 * @author FDws
 * Created on 2018/6/14 21:11
 */

public class ParamValidateTest {

    @Test
    public void name() {
        String[] result = new String[1];
        boolean r = new ParamValidate(result)
                .name("   fds   ").email("hello@123.0")
                .license("123123123123123155", true)
                .password("hello").type("1", User.COMMEN_USER, User.ENGENEER).
                        validate();
        assert !r;
        System.out.println(result[0]);
    }
}