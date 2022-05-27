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
    private boolean enabled = false;
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
