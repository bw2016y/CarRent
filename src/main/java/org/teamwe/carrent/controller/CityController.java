package org.teamwe.carrent.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.teamwe.carrent.controller.utils.Format;
import org.teamwe.carrent.service.CityService;
import org.teamwe.carrent.utils.ReturnStatus;

/**
 * @author FDws
 * Created on 2018/6/20 11:58
 */

@RestController
public class CityController {
    private CityService service;

    @GetMapping("/city")
    public Format getCities() {
        return new Format().code(ReturnStatus.SUCCESS).addData("city", service.getCity());
    }

    @PostMapping("/city")
    public Format addCity(@RequestParam String name,
                          @RequestParam String location) {
        return new Format().code(service.addCity(name, location));
    }

    @PostMapping("/city")
    public Format update(@RequestParam String name,
                         @RequestParam String location) {
        return new Format().code(service.updateCity(name, location));
    }
}
