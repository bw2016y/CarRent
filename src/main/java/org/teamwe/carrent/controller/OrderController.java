package org.teamwe.carrent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.teamwe.carrent.controller.utils.Format;
import org.teamwe.carrent.controller.utils.SessionAttr;
import org.teamwe.carrent.entity.Order;
import org.teamwe.carrent.entity.User;
import org.teamwe.carrent.service.OrderService;
import org.teamwe.carrent.utils.ReturnStatus;
import org.teamwe.carrent.utils.StringUtil;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author FDws
 * Created on 2018/6/17 21:06
 */

@RestController
public class OrderController {
    private final OrderService service;

    @Autowired
    public OrderController(OrderService service) {
        this.service = service;
    }

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

    @GetMapping("/user/{email:.+}/order")
    public Format getOrders(@PathVariable String email) {
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
    public Format cancelOrder(@PathVariable String orderId, HttpSession session) {
        Order o = service.getById(orderId);
        if (o == null ||
                session.getAttribute(SessionAttr.USER_ID) == null ||
                !session.getAttribute(SessionAttr.USER_ID).equals(o.getEmail())) {
            return new Format().code(ReturnStatus.FAILURE).message("Illegal user");
        }
        return new Format().code(service.deleteOrder(orderId));
    }

    @DeleteMapping("/order/{id}/time")
    public Format endOrder(@PathVariable String id) {
        return new Format().code(service.endOrder(id));
    }

    @GetMapping("/order/{id}")
    public Format getOrder(@PathVariable String id, HttpSession session) {
        Order o = service.getById(id);
        if (o == null) {
            return new Format().code(ReturnStatus.FAILURE).message("No such of order : " + id);
        }
        String type = "" + session.getAttribute(SessionAttr.USER_TYPE);
        String email = "" + session.getAttribute(SessionAttr.USER_ID);

        if (String.valueOf(User.ENGENEER).equals(type)
                || email.equals(o.getEmail())) {
            return new Format().code(ReturnStatus.SUCCESS).addData("order", o);
        }
        return new Format().code(ReturnStatus.FAILURE).message("No such of order : " + id);
    }
}
