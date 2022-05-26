package com.fges.user.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
