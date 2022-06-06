package com.fges.comment.controller;

import java.util.List;
import java.util.Optional;

import com.fges.comment.CommentNotFoundException;
import com.fges.comment.UserOrBookNotFoundException;
import com.fges.comment.entity.BookDTO;
import com.fges.comment.entity.Comment;
import com.fges.comment.entity.UserDTO;
import com.fges.comment.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping
    public Comment saveComment(@RequestBody Comment comment) throws UserOrBookNotFoundException {
        //check if book and user exists before creating comment
        Long bookId = comment.getBookId();
        Long userId = comment.getUserId();
        BookDTO bookMatching = restTemplate.getForObject("http://BOOK-SERVICE/books/id/"+ bookId, BookDTO.class);
        UserDTO userMatching = restTemplate.getForObject("http://USER-SERVICE/api/id/"+ userId, UserDTO.class);
        if((bookMatching instanceof BookDTO) && (userMatching instanceof UserDTO)) {
            return commentService.saveComment(comment);
        }
        else {
            //throw new UserOrBookNotFoundException("User or Book dont exist");
            return null;
        }
    }

    @GetMapping
    public List<Comment> getAll(){
        return commentService.getAll();
    }

    @GetMapping("/id/{commentId}")
    public Comment getCommentById(@PathVariable("commentId") Long commentId ) throws Exception{
        return commentService.getCommentById(commentId);
    }

    @GetMapping("/bookId/{bookId}")
    public List<Comment> getCommentsByBookId(@PathVariable("bookId") Long bookId) throws Exception{
        return commentService.getCommentsByBookId(bookId);
    }

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