package org.teamwe.carrent.controller;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.teamwe.carrent.controller.utils.Format;
import org.teamwe.carrent.service.RegisterService;

/**
 * Register Function Controller
 *
 * @author FDws
 * Created on 2018/6/14 15:24
 */
@RestController
public class RegisterController {
    private RegisterService service;

    public Format register(@RequestParam String name,
                           @RequestParam String email,
                           @RequestParam String password,
                           @RequestParam String license,
                           @RequestParam MultipartFile file,
                           @RequestParam String type,
                           @RequestParam String phone,
                           @RequestParam String code) {

        return Format.getInstance().code(0);
    }
}
