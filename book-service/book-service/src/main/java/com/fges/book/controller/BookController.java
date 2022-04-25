package com.fges.book.controller;


import com.fges.book.VO.ResponseTemplateVO;
import com.fges.book.entity.Book;
import com.fges.book.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/books")
@Slf4j
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping("/")
    public Book saveBook( @RequestBody Book book){
        //log.info("Dans saveBook de BookController");
        return bookService.saveBook(book);
    }

    @GetMapping("/{id}")
    public Book findBookById(@PathVariable("id") Long bookId ){
        //log.info("Dans  findBookById de BookController");
        return bookService.findBookById(bookId);
    }

    // @GetMapping("/{id}")
    // public ResponseTemplateVO getBookWithCategory(@PathVariable("id") Long bookId){
    //      //log.info("Dans  getBookWithCategory de BookController")
    //      return bookService.getBookWithCategory(bookId);
    // }
}
