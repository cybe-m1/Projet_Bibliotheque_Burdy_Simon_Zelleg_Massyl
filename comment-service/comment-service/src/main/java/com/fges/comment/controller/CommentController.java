package com.fges.comment.controller;

import java.util.List;

import com.fges.comment.CommentNotFoundException;
import com.fges.comment.entity.Comment;
import com.fges.comment.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping
    public Comment saveComment( @RequestBody Comment comment){
        return commentService.saveComment(comment);
    }
    @GetMapping
    public List<Comment> getAll(){
        return commentService.getAll();
    }

    @GetMapping("/id/{commentId}")
    public Comment getCommentById(@PathVariable("commentId") Long commentId ) throws Exception{
        return commentService.getCommentById(commentId);
    }

    //@GetMapping("/commentTitle/{commentName}")
    //public Comment getCommentByName(@PathVariable String commentTitle) throws Exception {
      //  return commentService.getCommentByTitle(commentTitle);
    //}

    @PutMapping
    public Comment update(@RequestBody Comment comment) throws CommentNotFoundException {
        return commentService.updateComment(comment);
    }

    @DeleteMapping(value="/delete/{commentId}")
    public Comment delete(@PathVariable Long commentId) throws Exception {
        Comment toDelete = commentService.getCommentById(commentId);
        commentService.deleteCommentById(commentId);
        return toDelete;
    }

}
