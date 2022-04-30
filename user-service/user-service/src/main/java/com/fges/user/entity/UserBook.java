package com.fges.user.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
public class UserBook {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId" , nullable = false)
    private User user;

    @Column(name = "bookId")
    private  Long bookId;

}
