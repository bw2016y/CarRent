package org.teamwe.carrent.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.teamwe.carrent.controller.utils.FileUtil;
import org.teamwe.carrent.controller.utils.Format;
import org.teamwe.carrent.controller.utils.ParamValidate;
import org.teamwe.carrent.controller.utils.VerifyCodeImage;
import org.teamwe.carrent.service.RegisterService;
import org.teamwe.carrent.utils.ReturnStatus;
import org.teamwe.carrent.utils.StringUtil;
import org.teamwe.carrent.utils.hash.Hash;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.net.URLEncoder;
import java.util.concurrent.Callable;

/**
 * Register Function Controller
 *
 * @author FDws
 * Created on 2018/6/14 15:24
 */
@RestController
public class RegisterController {
    private static Logger log = LoggerFactory.getLogger(RegisterController.class);

    private RegisterService service;

    private final FileUtil fileUtil;

    @Autowired
    public RegisterController(FileUtil fu) {
        this.fileUtil = fu;
    }

    @PostMapping("/user")
    public Callable<Format> register(@RequestParam(required = false) String name,
                                     @RequestParam(required = false) String email,
                                     @RequestParam(required = false) String password,
                                     @RequestParam(required = false) String license,
                                     @RequestParam(required = false) MultipartFile file,
                                     @RequestParam(required = false) String type,
                                     @RequestParam(required = false) String phone,
                                     @RequestParam(required = false) String code,
                                     HttpSession session) {

        Object c = session.getAttribute(VerifyCodeImage.NAME);
        if (c == null || !c.equals(code.trim())) {
            return () -> new Format().code(ReturnStatus.FAILURE).message("Code is error");
        }

        String[] msg = new String[1];
        if (new ParamValidate(msg).name(name).email(email).password(password).
                license(license, false).type(type).phone(phone, false).validate()) {
            return () -> {
                String fileName = fileUtil.saveImage(file);
                String r = service.register(name.trim(),
                        email.trim(), password.trim(),
                        license.trim(), fileName,
                        Integer.parseInt(type.trim()), phone.trim());

                if (StringUtil.nullOrEmpty(r)) {
                    return new Format().code(ReturnStatus.SUCCESS);
                }
                return new Format().code(ReturnStatus.FAILURE).message(r);
            };
        }

        return () -> new Format().code(1);
    }
}
