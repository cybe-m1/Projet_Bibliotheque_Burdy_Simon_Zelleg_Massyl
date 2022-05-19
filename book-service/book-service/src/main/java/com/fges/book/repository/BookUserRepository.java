package com.fges.book.repository;


import com.fges.book.entity.Book;
import com.fges.book.entity.BookUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookUserRepository extends JpaRepository<BookUser, Long> {
    boolean existsByUserId(Long userId);

    List<Book> findBooksByUserId(Long userId);
}
