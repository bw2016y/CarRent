package org.teamwe.carrent.controller;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.teamwe.carrent.controller.utils.Format;
import org.teamwe.carrent.controller.utils.ParamValidate;
import org.teamwe.carrent.controller.utils.VerifyCodeImage;
import org.teamwe.carrent.service.RegisterService;
import org.teamwe.carrent.utils.ReturnStatus;
import org.teamwe.carrent.utils.hash.Hash;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.concurrent.Callable;

/**
 * Register Function Controller
 *
 * @author FDws
 * Created on 2018/6/14 15:24
 */
@RestController
public class RegisterController {
    private RegisterService service;
    private Environment env;
    private Hash hash;

    public Callable<Format> register(@RequestParam String name,
                                     @RequestParam String email,
                                     @RequestParam String password,
                                     @RequestParam String license,
                                     @RequestParam MultipartFile file,
                                     @RequestParam String type,
                                     @RequestParam String phone,
                                     @RequestParam String code,
                                     HttpSession session) {

        Object c = session.getAttribute(VerifyCodeImage.NAME);
        if (c == null || !c.equals(code.trim())) {
            return () -> new Format().code(ReturnStatus.FAILURE).message("Code is error");
        }

        String[] msg = new String[1];
        if (new ParamValidate(msg).name(name).email(email).password(password).
                license(license, false).type(type).phone(phone, false).validate()) {
            return () -> {
                // TODO 完成注册功能
                File parent = new File(env.getProperty("path.image", System.getProperty("user.home")));
                return null;
            };
        }

        return () -> new Format().code(0);
    }

    private String suffix(String name) {
        for (int i = name.length() - 1; i >= 0; i--) {
            if (name.charAt(i) == '.') {
                return name.substring(i + 1, name.length());
            }
        }
        return "";
    }
}
