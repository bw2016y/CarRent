package org.teamwe.carrent.serviceImpl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.teamwe.carrent.entity.Order;
import org.teamwe.carrent.service.CommentService;

import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CommentServiceImplTest {

    @Autowired
    private CommentServiceImpl commentService;
    @Test
    public void comment() {
        commentService.comment("4","very good car ");
    }

    @Test
    public void getComments() {
        List<String[]> list = commentService.getComments("陕V123456");

        for (Iterator<String[]> iterator = list.iterator();iterator.hasNext();){
            String[] comment = iterator.next();
            for (String s : comment){
                System.out.println(s);
            }
        }
    }
}