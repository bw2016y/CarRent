package org.teamwe.carrent.controller;

import org.springframework.web.bind.annotation.*;
import org.teamwe.carrent.controller.utils.Format;
import org.teamwe.carrent.service.CommentService;
import org.teamwe.carrent.utils.ReturnStatus;

import java.util.List;

/**
 * @author FDws
 * Created on 2018/6/18 13:36
 */

@RestController
public class CommentController {
    private CommentService service;

    @PostMapping("/comment")
    public Format newComment(@RequestParam String content,
                             @RequestParam String orderId) {
        return new Format().code(service.comment(orderId, content));
    }

    @GetMapping("/comment/{card}")
    public Format getComment(@PathVariable String card) {
        List<String[]> res = service.getComments(card);
        return new Format().code(ReturnStatus.SUCCESS).addData("contents", res);
    }
}
