package com.fges.book.service;




import com.fges.book.VO.Category;
import com.fges.book.VO.ResponseTemplateVO;
import com.fges.book.entity.Book;
import com.fges.book.repository.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j

public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private RestTemplate restTemplate;

    public Book saveBook(Book book) {
        //log.info("Dans le saveBook de BookService");
        return bookRepository.save(book);
    }

    public Book findBookById(Long bookId) {
         //log.info("Dans le  findBookById de BookService");
        return bookRepository.findByBookId(bookId);
    }

    // public ResponseTemplateVO getBookWithCategory(Long bookId) {
    //      //log.info("Dans le  getBookWithCategory de BookService");
    //     ResponseTemplateVO vo  = new ResponseTemplateVO();
    //     Book book  = bookRepository.findByBookId(bookId);
    //     Category category = restTemplate.getForObject("http://localhost:9001/books/"+Book.getCategory()   ,Category.class);

    //     vo.setBook(book);
    //     vo.setCategory(category);

    //     return vo;

    // }
}


