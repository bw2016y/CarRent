package org.teamwe.carrent.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.teamwe.carrent.service.CarService;

import java.util.Arrays;
import java.util.List;

/**
 * @author FDws
 * Created on 2018/6/17 20:25
 */

@RestController
public class CarController {
    private CarService service;

    // TODO Talk to add new get request
    @GetMapping("/images/:card")
    public List<String> getCarImages(@PathVariable String card) {
        return Arrays.asList(service.get_car_imgs(card));
    }

    @GetMapping("/type")
    public List<Integer> getTypes() {
        return service.getType();
    }
}
