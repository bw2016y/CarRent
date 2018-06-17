package org.teamwe.carrent.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.teamwe.carrent.controller.utils.Format;
import org.teamwe.carrent.service.EngineerService;
import org.teamwe.carrent.utils.ReturnStatus;
import org.teamwe.carrent.utils.StringUtil;

import java.util.Arrays;

/**
 * @author FDws
 * Created on 2018/6/17 20:19
 */

@RestController
public class EngineerController {
    private EngineerService service;

    @GetMapping("/engineer")
    public Format getEngineerList() {
        return new Format().code(ReturnStatus.SUCCESS).addData("engineers", Arrays.asList(service.getEngineer()));
    }

    @PutMapping("/engineer")
    public Format check(@RequestParam String email) {
        if (!StringUtil.isLegalMail(email.trim())) {
            return new Format().code(ReturnStatus.FAILURE).message(StringUtil.ILLEGAL_EMAIL);
        }
        int res = service.check(email.trim());
        return new Format().code(res);
    }
}
