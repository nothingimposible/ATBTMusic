package com.qst.atbtmusic.controller;

import com.qst.atbtmusic.mapper.CommentMapper;
import com.qst.atbtmusic.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommentController {
    @Autowired
    CommentMapper commentMapper;

    @RequestMapping("/comment")
    public void comment(Comment comment){
        commentMapper.comment(comment);
    }

}
