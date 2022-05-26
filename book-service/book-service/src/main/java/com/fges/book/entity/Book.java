package com.fges.book.entity;

import javax.persistence.*;

import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bookId;
    private String name;
    private String category;
    @ElementCollection
    List<Long> usersIds; // ids, liste vide à la creation
}
