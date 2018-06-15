package org.teamwe.carrent.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.teamwe.carrent.controller.utils.Format;
import org.teamwe.carrent.entity.User;
import org.teamwe.carrent.service.InformationService;
import org.teamwe.carrent.utils.ReturnStatus;
import org.teamwe.carrent.utils.StringUtil;

/**
 * @author FDws
 * Created on 2018/6/15 16:29
 */
@RestController
public class InformationController {

    private InformationService service;

    @GetMapping("/user/{email}")
    public Format getInfo(@PathVariable String email) {
        if (!StringUtil.isLegalMail(email.trim()))
            return new Format().code(ReturnStatus.FAILURE).message("Illegal email");
        User user = service.info(email.trim());
        if (user == null) {
            return new Format().code(ReturnStatus.FAILURE).message("No such user");
        } else {
            return new Format().code(ReturnStatus.SUCCESS).
                    addData("user", user);
        }
    }

    @PutMapping("/user")
    public void change(@RequestParam String email,
                       @RequestParam(required = false) String password,
                       @RequestParam(required = false) String name,
                       @RequestParam(required = false) MultipartFile file){
        // TODO change user information
    }
}
