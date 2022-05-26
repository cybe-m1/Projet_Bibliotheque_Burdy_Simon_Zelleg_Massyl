package com.fges.book.service;
import com.fges.book.BookNotFoundException;
import com.fges.book.entity.Book;
import com.fges.book.entity.UserDTO;
import com.fges.book.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public Book saveBook(Book book) {
        log.info("Inside saveBook of BookService");
        return bookRepository.save(book);
    }

    public Book bookPrint(Book book, Long userId){
        book.getUsersIds().add(userId);
        return bookRepository.save(book);
    }
    public Book bookPrint(Long bookId, Long userId) throws Exception {
        return bookPrint(getBookById(bookId), userId);
    }

    /*public List<UserDTO> getUsersByBookId(Long bookId){
        Optional<List<Long>> userIds = bookRepository.getUserIdsByBookId(bookId);
        //requete de batard avec le rest Template
        return;
    }*/
    public List<Book> getAll(){
        return bookRepository.findAll();
    }

    public Book getBookByName(String name) throws Exception{
        return bookRepository.findByName(name).orElseThrow(() -> new Exception("Book not found"));
    }
    
    public Book getBookById(Long bookId) throws Exception{
        log.info("Inside getBookById of BookService");
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
    
}


