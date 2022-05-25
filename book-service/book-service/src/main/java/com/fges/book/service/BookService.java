package com.fges.book.service;
import com.fges.book.BookNotFoundException;
import com.fges.book.entity.Book;
import com.fges.book.repository.BookRepository;
import com.fges.book.repository.BookUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service

public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookUserRepository bookUserRepository;

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }


    public List<Book> getAll(){
        return bookRepository.findAll();
    }

    public Book getBookByName(String name) throws Exception{
        return bookRepository.findByName(name).orElseThrow(() -> new Exception("Book not found"));
    }
    
    public Book getBookById(Long bookId) throws Exception{
        return bookRepository.findBookByBookId(bookId).orElseThrow(() -> new Exception("Book not found"));
    }

    public Book updateBook(Book book) throws BookNotFoundException {
        if(bookRepository.existsById(book.getBookId())) {
            return bookRepository.save(book);
        }
        throw new BookNotFoundException("Book does not exist ...");
    }

    public void deleteBookById(Long bookId) throws BookNotFoundException {
        if(bookRepository.existsById(bookId)) {
            bookRepository.deleteById(bookId);
        } else {
            throw new BookNotFoundException("Book does not exist ...");
        }
    }


   public List<Book> findBooksByUserId(Long userId)  throws BookNotFoundException{
        if(bookUserRepository.existsByUserId(userId)){
            return bookUserRepository.findBooksByUserId(userId);
        }else{
            throw new BookNotFoundException("Book does not exist ...");
        }



    }
}


