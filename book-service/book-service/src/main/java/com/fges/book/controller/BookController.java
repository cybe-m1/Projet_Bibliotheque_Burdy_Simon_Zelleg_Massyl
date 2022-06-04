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
        log.info("Inside saveBook method of BookController");
        return bookService.saveBook(book);
    }
    @GetMapping
    public List<Book> getAll(){
        return bookService.getAll();
    }

    @PutMapping("/id/{bookId}")
    public Book bookPrint(@PathVariable("bookId") Long bookId, @RequestBody BookAssignRequestDto bookAssign) throws Exception {
        String bookCateg = bookService.getBookById(bookId).getCategory();
        log.info("Book Catagory is : {}", bookCateg);
        Long userId = bookAssign.getUserId();
        log.info("Id of user is : {}", userId);
        UserDTO user = restTemplate.getForObject("http://USER-SERVICE/api/id/" + userId, UserDTO.class);
        String userCateg = user.getAgeCategorie();
        log.info("User age catagory is : {}", userCateg);
        Integer userNumberOfBooks = restTemplate.getForObject("http://USER-SERVICE/api/number-of-books/" + userId, Integer.class);
        log.info("user with id : {} has {} number of books", bookAssign.getUserId(),userNumberOfBooks);
        boolean ahtorizeToGetBook = bookService.bookCategoriesCompare(userCateg, bookCateg);
        log.info("User : {} is athorized to print this book : {}", user.getUsername(), ahtorizeToGetBook);
        if(userNumberOfBooks < 3) {
            if(ahtorizeToGetBook) {
                Integer newNumberOfBooks = restTemplate.getForObject("http://USER-SERVICE/api/incr-number-of-books/" + userId, Integer.class);
                log.info("new number of books : {}", newNumberOfBooks);
                return bookService.bookPrint(bookId, userId);
            }
        }
        return bookService.getBookById(bookId);
    }

    @PutMapping("/return/id/{bookId}")
    public Book returnBook(@PathVariable("bookId") Long bookId ,@RequestBody BookAssignRequestDto bookAssign) throws Exception{
        Long userId = bookAssign.getUserId();
        UserDTO user = restTemplate.getForObject("http://USER-SERVICE/api/id/" + userId, UserDTO.class);
        restTemplate.getForObject("http://USER-SERVICE/api/decr-number-of-books/" + userId, Integer.class);
        Book book = bookService.getBookById(bookId);
        book.getUsersIds().remove(userId);
        return book;
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
