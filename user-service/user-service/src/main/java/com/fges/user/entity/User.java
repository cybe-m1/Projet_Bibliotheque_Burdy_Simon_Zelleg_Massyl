package com.fges.user.entity;

import javax.persistence.*;

import lombok.*;

import java.util.Collection;
import java.util.List;

@Entity(name= "user")
@Data
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    private String name;
    private String email;
    private Integer age;

    @OneToMany(mappedBy = "user")
    private List<UserBook> books;
}
