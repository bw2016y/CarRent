package org.teamwe.carrent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.teamwe.carrent.controller.utils.FileUtil;
import org.teamwe.carrent.controller.utils.Format;
import org.teamwe.carrent.controller.utils.ParamValidate;
import org.teamwe.carrent.entity.User;
import org.teamwe.carrent.service.InformationService;
import org.teamwe.carrent.utils.ReturnStatus;
import org.teamwe.carrent.utils.StringUtil;
import org.teamwe.carrent.utils.hash.Hash;

/**
 * @author FDws
 * Created on 2018/6/15 16:29
 */
@RestController
public class InformationController {

    private final InformationService service;
    private final Hash hash;
    private final FileUtil fu;

    @Autowired
    public InformationController(FileUtil fu, Hash hash, InformationService service) {
        this.fu = fu;
        this.hash = hash;
        this.service = service;
    }

    @GetMapping("/user/{email}")
    public Format getInfo(@PathVariable String email) {
        if (!StringUtil.isLegalMail(email.trim()))
            return new Format().code(ReturnStatus.FAILURE).message(StringUtil.ILLEGAL_EMAIL);
        User user = service.info(email.trim());
        if (user == null) {
            return new Format().code(ReturnStatus.FAILURE).message(StringUtil.NO_SUCH_USER);
        } else {
            user.setPassword("");
            return new Format().code(ReturnStatus.SUCCESS).
                    addData("user", user);
        }
    }

    @PutMapping("/user")
    public Format change(@RequestParam String email,
                         @RequestParam(required = false) String password,
                         @RequestParam(required = false) String name,
                         @RequestParam(required = false) MultipartFile file) {

        if (!StringUtil.isLegalMail(email.trim())) {
            return new Format().code(ReturnStatus.FAILURE).message(StringUtil.ILLEGAL_EMAIL);
        }
        User u = service.info(email.trim());
        if (u == null) {
            return new Format().code(ReturnStatus.FAILURE).message(StringUtil.NO_SUCH_USER);
        }

        boolean flag = false;

        if (password != null && new ParamValidate(null).password(password).validate()) {
            u.setPassword(hash.hashPassword(password.trim()));
            flag = true;
        }
        if (name != null && new ParamValidate(null).name(name).validate()) {
            u.setName(name);
            flag = true;
        }
        if (file != null && new ParamValidate(null).file(file, fu.getMaxFileSize(), true).validate()) {
            String fn = fu.saveImage(file);
            u.setHead(fn);
            flag = true;
        }
        if (flag) {
            int res = service.changeInfo(u);
            return new Format().code(res);
        }

        return new Format().code(ReturnStatus.SUCCESS);
    }

    @DeleteMapping("/user/{email}")
    public Format delete(@PathVariable String email) {
        if (!StringUtil.isLegalMail(email.trim())) {
            return new Format().code(ReturnStatus.FAILURE).message(StringUtil.ILLEGAL_EMAIL);
        }
        int res = service.delete(email.trim());
        return new Format().code(res);
    }

    @GetMapping("/password/{email}")
    public Format forgetPassword(@PathVariable String email) {
        if (!StringUtil.isLegalMail(email.trim())) {
            return new Format().code(ReturnStatus.FAILURE).message(StringUtil.ILLEGAL_EMAIL);
        }
        int res = service.forgetPassword(email.trim());
        return new Format().code(res);
    }

    @PutMapping("/password")
    public Format resetPassword(@RequestParam String hash, @RequestParam String password) {
        String[] msg = new String[1];
        if (new ParamValidate(msg).password(password).validate()) {
            int res = service.resetPassword(hash, password);
            return new Format().code(res);
        }
        return new Format().code(ReturnStatus.FAILURE).message(msg[0]);
    }
}
