package com.fges.book.controller;

import com.fges.book.BookNotFoundException;
import com.fges.book.entity.Book;
import com.fges.book.service.BookService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@Slf4j
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping
    public Book saveBook( @RequestBody Book book){
        log.info("Inside saveBook method of BookController");
        return bookService.saveBook(book);
    }
    @GetMapping
    public List<Book> getAll(){
        return bookService.getAll();
    }

    @GetMapping("/id/{bookId}")
    public Book getBookById(@PathVariable("bookId") Long bookId ) throws Exception{
        log.info("Inside findBookById method of BookController");
        return bookService.getBookById(bookId);
    }

    @GetMapping("/bookName/{bookName}")
    public Book getBookByName(@PathVariable String bookName) throws Exception {
        return bookService.getBookByName(bookName);
    }

    @PutMapping
    public Book update(@RequestBody Book book) throws BookNotFoundException {
        return bookService.updateBook(book);
    }

    @DeleteMapping(value="/delete/{bookId}")
    public Book delete(@PathVariable Long bookId) throws Exception {
        Book toDelete = bookService.getBookById(bookId);
        bookService.deleteBookById(bookId);
        return toDelete;
    }

}
