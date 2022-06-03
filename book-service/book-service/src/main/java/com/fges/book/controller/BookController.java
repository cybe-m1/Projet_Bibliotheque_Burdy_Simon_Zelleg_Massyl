package com.fges.book.controller;

import com.fges.book.BookNotFoundException;
import com.fges.book.UserIdNotFound;
import com.fges.book.entity.Book;
import com.fges.book.entity.BookAssignRequestDto;
import com.fges.book.entity.UserDTO;
import com.fges.book.service.BookService;


//import lombok.extern.slf4j.XSlf4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
@Slf4j
//@XSlf4j
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/users")
    public List<Object> getUsers(){
        Object[] users = restTemplate.getForObject("http://USER-SERVICE/users", Object[].class);
        return Arrays.asList(users);
    }

    @PostMapping
    public Book saveBook( @RequestBody Book book){
        //log.info("Inside saveBook method of BookController");
        return bookService.saveBook(book);
    }
    @GetMapping
    public List<Book> getAll(){
        return bookService.getAll();
    }

    @PutMapping("/id/{bookId}")
    public Book bookPrint(@PathVariable("bookId") Long bookId, @RequestBody BookAssignRequestDto bookAssign) throws Exception {
        //Requete sur le service user -> ajoute +1 a l'attribut numberOfBooks du user
        Integer countNumberOfBooks = restTemplate.getForObject("http://USER-SERVICE/api/number-of-books/" + bookAssign.getUserId(), Integer.class);
        log.info("user with id : {} has {} number of books", bookAssign.getUserId(),countNumberOfBooks);
        if(countNumberOfBooks < 3) {
            Integer newNumberOfBooks = restTemplate.getForObject("http://USER-SERVICE/api/incr-number-of-books/" + bookAssign.getUserId(), Integer.class);
            log.info("new number of books : {}", bookAssign.getUserId());
            return bookService.bookPrint(bookId, bookAssign.getUserId());
        }
        return bookService.getBookById(bookId);
    }

    @GetMapping("/id/{bookId}/users")
    public Object[] getUsersByBookId(@PathVariable("bookId") Long bookId) throws Exception, UserIdNotFound {
        List<Long> userIds = bookService.getBookById(bookId).getUsersIds();
        if (!userIds.isEmpty()) {
            String userIdsParsed = userIds.stream().map(Object::toString)
                    .collect(Collectors.joining(","));
            log.info("userIdParsed : {}", userIdsParsed);
            return restTemplate.getForObject("http://USER-SERVICE/api/userIds/" + userIdsParsed, Object[].class);
        } else {
            throw new UserIdNotFound("No User for this Book !");
        }
    }


    @GetMapping("/id/{bookId}")
    public Book getBookById(@PathVariable("bookId") Long bookId ) throws Exception{
        //log.info("Inside findBookById method of BookController");
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
