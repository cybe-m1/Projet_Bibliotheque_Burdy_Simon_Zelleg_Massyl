package com.fges.book.entity;

import javax.persistence.*;

import com.fges.user.entity.User;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bookId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String category;

    @ManyToMany(mappedBy = "books")
    private List<User> users;

}
