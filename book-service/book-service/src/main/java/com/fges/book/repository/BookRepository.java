package com.fges.book.repository;


import com.fges.book.entity.Book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book , Long>{

    Optional<Book> findBookByBookId(Long bookId);
    Optional<Book> findByName(String name);


    @Query(value = "SELECT * FROM BOOK  INNER JOIN BOOK_USER  ON BOOK.BOOK_ID = BOOK_USER.BOOK_ID WHERE BOOK_USER.USER_ID = :userId " , nativeQuery = true)
    List<Book> findBooksByUserId(@Param("userId") Long userId);

    /*boolean existsByUserId(Long userId);*/
}
