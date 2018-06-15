package org.teamwe.carrent.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.teamwe.carrent.controller.utils.Format;
import org.teamwe.carrent.controller.utils.ParamValidate;
import org.teamwe.carrent.controller.utils.VerifyCodeImage;
import org.teamwe.carrent.entity.User;
import org.teamwe.carrent.service.LoginService;
import org.teamwe.carrent.utils.ReturnStatus;

import javax.servlet.http.HttpSession;

/**
 * @author FDws
 * Created on 2018/6/13 9:53
 */
@RestController
public class LoginController {

    private LoginService service;

    @PostMapping("/session")
    public Format login(@RequestParam String email,
                        @RequestParam String password,
                        @RequestParam String code,
                        HttpSession session) {
        Object c = session.getAttribute(VerifyCodeImage.NAME);
        if (c == null || !c.equals(code)) {
            return new Format().code(ReturnStatus.FAILURE).message("Code is Illegal");
        }

        String[] msg = new String[1];
        if (new ParamValidate(msg).email(email).password(password).validate()) {
            String[] re = service.login(email.trim(), password.trim());
            if (re.length == 3) {

                addFlag(session, email.trim());
                removeCode(session);

                return new Format().code(ReturnStatus.SUCCESS)
                        .addData("name", re[0])
                        .addData("file", re[1])
                        .addData("type", re[2]);
            } else if (re.length == 1) {
                return new Format().code(ReturnStatus.FAILURE).message(re[0]);
            }
        }
        return new Format().code(ReturnStatus.FAILURE).message(msg[0]);
    }

    @DeleteMapping("/session")
    public void logout(HttpSession session) {
        session.removeAttribute(User.class.getName());
    }

    private void addFlag(HttpSession session, String email) {
        session.setAttribute(User.class.getName(), email);
    }

    private void removeCode(HttpSession session) {
        session.removeAttribute(VerifyCodeImage.NAME);
        session.removeAttribute(VerifyCodeImage.TIME);
    }
}
