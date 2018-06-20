package org.teamwe.carrent.controller;

import org.springframework.web.bind.annotation.*;
import org.teamwe.carrent.controller.utils.Format;
import org.teamwe.carrent.entity.Order;
import org.teamwe.carrent.service.OrderService;
import org.teamwe.carrent.utils.ReturnStatus;
import org.teamwe.carrent.utils.StringUtil;

import java.util.List;

/**
 * @author FDws
 * Created on 2018/6/17 21:06
 */

@RestController
public class OrderController {

    private OrderService service;

    @PostMapping("/order")
    public Format makeOrder(@RequestParam String email,
                            @RequestParam String card,
                            @RequestParam long timebegin,
                            @RequestParam long timeend) {
        if (!StringUtil.isLegalMail(email.trim())) {
            return new Format().code(ReturnStatus.FAILURE).message(StringUtil.ILLEGAL_EMAIL);
        }

        int res = service.makeOrder(email.trim(), card, timebegin, timeend);
        return new Format().code(res);
    }

    @GetMapping("/user/{email}/order")
    public Format getOrder(@PathVariable String email) {
        if (!StringUtil.isLegalMail(email.trim())) {
            return new Format().code(ReturnStatus.FAILURE).message(StringUtil.ILLEGAL_EMAIL);
        }
        List<Order> res = service.getOrders(email.trim());
        return new Format().code(ReturnStatus.SUCCESS).addData("orders", res);
    }

    @PutMapping("/order")
    public Format finishOrder(@RequestParam String id,
                              @RequestParam int level) {
        return new Format().code(service.finishOrder(id, level));
    }

    @DeleteMapping("/order/{orderId}")
    public Format cancelOrder(@PathVariable String orderId) {
        return new Format().code(service.deleteOrder(orderId));
    }

    @DeleteMapping("/order/{id}/time")
    public Format endOrder(@PathVariable String id) {
        return new Format().code(service.endOrder(id));
    }
}
