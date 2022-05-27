package com.fges.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
    private String name;
    private String email;
    private Integer age;
    private String password;
    private String matchingPassword;
}
