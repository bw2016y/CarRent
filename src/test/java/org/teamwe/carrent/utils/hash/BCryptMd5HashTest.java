package org.teamwe.carrent.utils.hash;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.*;

/**
 * @author FDws
 * Created on 2018/6/11 23:24
 */

public class BCryptMd5HashTest {
    private Hash hash = null;

    @Before
    public void init() {
        try {
            hash = new BCryptMd5Hash();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void hashPassword() {
        String pass = "123456";
        String data = hash.hashPassword(pass);
        System.out.println(data);
        String done = hash.hashPassword("456", data);
        System.out.println(done);
    }

    @Test
    public void hashPassword1() {
    }

    @Test
    public void hashNormal() {
        assert hash != null;
        String[] pass = {"1", "2", "3", "4", "abc"};
        String[] result = {"c4ca4238a0b923820dcc509a6f75849b", "c81e728d9d4c2f636f067f89cc14862c",
                "eccbc87e4b5ce2fe28308fd9f2a7baf3", "a87ff679a2f3e71d9181a67b7542122c", "900150983cd24fb0d6963f7d28e17f72"};
        for (int i = 0; i < pass.length; i++) {
            assertEquals(hash.hashNormal(pass[i]), result[i]);
        }
    }

    @Test
    public void hashNormal1() {
    }

    @Test
    public void genSalt() {
        assertNotNull(hash.genSalt());
    }

    @Test
    public void hashFile() {
    }

    @Test
    public void hashByte(){
        String demo = "123";
        System.out.println(hash.hashBytes(demo.getBytes()));
        System.out.println(hash.hashNormal(demo));
    }

    @Test
    public void genRandomChar() {
        System.out.println(hash.genRandomChar(20));
        System.out.println(hash.genRandomChar(20));
    }
}