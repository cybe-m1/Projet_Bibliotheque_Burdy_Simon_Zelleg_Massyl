package com.fges.book.entity;

import javax.persistence.*;

import lombok.*;

import java.util.List;

@Entity(name = "book")
@Data
@NoArgsConstructor
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bookId;
    private String name;
    private String category;


    @OneToMany(mappedBy = "book")
    private List<BookUser> users;
}
