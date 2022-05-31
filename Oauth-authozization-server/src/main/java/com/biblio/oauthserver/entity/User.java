package com.biblio.oauthserver.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
    private String password;
    private Integer age;
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


