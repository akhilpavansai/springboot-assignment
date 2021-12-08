package com.shop.supermarket.entity;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @NotEmpty(message = "password cannot be empty")
    @Size(min = 4,message = "password should be min length 4")
    private String password;

    @NotEmpty(message = "email cannot be empty")
    private String email;

    private String phoneNumber;

    private String address;

}
