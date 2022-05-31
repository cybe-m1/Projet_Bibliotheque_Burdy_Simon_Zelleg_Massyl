package com.fges.user.entity;

import javax.persistence.*;

import lombok.*;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    private String name;
    private String email;
    private Integer age;
    @Column(length = 60)
    private String password;
    private String role;
    private boolean enabled = false;
    @Enumerated(EnumType.STRING)
    @Column(name="auth_provider")
    private AuthentificationProvider authProvider;
    //private Integer number_of_books = 0;
    public String getAgeCategorie(){
        String categ = "";
        if(this.age <= 13){
            categ = "children";
        }
        else if (this.age <= 18){
            categ =  "teenage";
        }
        else categ =  "adult";
        return categ;
    }
}
