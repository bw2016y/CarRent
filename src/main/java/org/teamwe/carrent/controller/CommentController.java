package org.teamwe.carrent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.teamwe.carrent.controller.utils.Format;
import org.teamwe.carrent.controller.utils.SessionAttr;
import org.teamwe.carrent.entity.Order;
import org.teamwe.carrent.service.CommentService;
import org.teamwe.carrent.service.OrderService;
import org.teamwe.carrent.utils.ReturnStatus;
import org.teamwe.carrent.utils.StringUtil;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author FDws
 * Created on 2018/6/18 13:36
 */

@RestController
public class CommentController {
    private final CommentService service;
    private final OrderService orderService;

    @Autowired
    public CommentController(CommentService service, OrderService orderService) {
        this.service = service;
        this.orderService = orderService;
    }

    @PostMapping("/comment")
    public Format newComment(@RequestParam String content,
                             @RequestParam String orderId,
                             HttpSession session) {
        String email = "" + session.getAttribute(SessionAttr.USER_ID);
        Order o = orderService.getById(orderId);
        if (o == null || !email.equals(o.getEmail())) {
            return new Format().code(ReturnStatus.FAILURE).message(StringUtil.NO_SUCH_USER);
        }
        return new Format().code(service.comment(orderId, content));
    }

    @GetMapping("/comment/{card}")
    public Format getComment(@PathVariable String card) {
        List<String[]> res = service.getComments(card);
        return new Format().code(ReturnStatus.SUCCESS).addData("contents", res);
    }
}
