package com.fges.book.repository;


import com.fges.book.entity.Book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book , Long>{

    Optional<Book> findBookByBookId(Long bookId);
    Optional<Book> findByName(String name);

    /*@Query("SELECT users_ids FROM book_users_ids where book_id = :bookId")

    List<Long> findUserIdsByBookId(Long bookId);*/

}
