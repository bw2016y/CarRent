package org.teamwe.carrent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.teamwe.carrent.controller.utils.FileUtil;
import org.teamwe.carrent.controller.utils.Format;
import org.teamwe.carrent.controller.utils.ParamValidate;
import org.teamwe.carrent.controller.utils.VerifyCodeImage;
import org.teamwe.carrent.entity.User;
import org.teamwe.carrent.service.RegisterService;
import org.teamwe.carrent.utils.ReturnStatus;
import org.teamwe.carrent.utils.StringUtil;

import javax.servlet.http.HttpSession;
import java.util.Optional;
import java.util.concurrent.Callable;

/**
 * Register Function Controller
 *
 * @author FDws
 * Created on 2018/6/14 15:24
 */
@RestController
public class RegisterController {

    private final RegisterService service;

    private final FileUtil fileUtil;

    @Autowired
    public RegisterController(FileUtil fu, RegisterService service) {
        this.fileUtil = fu;
        this.service = service;
    }

    @PostMapping("/user")
    public Callable<Format> register(@RequestParam String name,
                                     @RequestParam String email,
                                     @RequestParam String password,
                                     @RequestParam(required = false) String license,
                                     @RequestParam(required = false) MultipartFile file,
                                     @RequestParam String type,
                                     @RequestParam(required = false) String phone,
                                     @RequestParam String code,
                                     HttpSession session) {

        Object c = session.getAttribute(VerifyCodeImage.NAME);
        session.removeAttribute(VerifyCodeImage.NAME);
        if (c == null || !c.equals(code.trim().toLowerCase())) {
            return () -> new Format().code(ReturnStatus.FAILURE).message("Code is error");
        }

        String[] msg = new String[1];
        if (new ParamValidate(msg).name(name).email(email).password(password).
                license(license, false).file(file, fileUtil.getMaxFileSize(), false)
                .type(type, User.COMMEN_USER, User.ENGENEER).phone(phone, false).validate()) {

            return () -> {
                String fileName;
                if (file == null || file.isEmpty()) {
                    fileName = "";
                } else {
                    fileName = fileUtil.saveImage(file);
                }
                String r = service.register(name.trim(),
                        email.trim(), password.trim(),
                        Optional.ofNullable(license).orElse("").trim(),
                        fileName,
                        Integer.parseInt(type.trim()),
                        Optional.ofNullable(phone).orElse("").trim());

                if (StringUtil.nullOrEmpty(r)) {
                    return new Format().code(ReturnStatus.SUCCESS);
                }
                return new Format().code(ReturnStatus.FAILURE).message(r);
            };
        }

        return () -> new Format().code(1).message(msg[0]);
    }

    @GetMapping("/active/{id}")
    public Format active(@PathVariable String id) {
        if (StringUtil.nullOrEmpty(id)) {
            return new Format().code(ReturnStatus.FAILURE).message("Id is illegal");
        }
        String msg = service.active(id);
        if (StringUtil.nullOrEmpty(msg)) {
            return new Format().code(ReturnStatus.SUCCESS);
        }
        return new Format().code(ReturnStatus.FAILURE).message(msg);
    }
}
