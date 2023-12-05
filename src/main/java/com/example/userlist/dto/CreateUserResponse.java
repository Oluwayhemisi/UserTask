package com.example.userlist.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserResponse {
    private String message;
    private String email;
    private String userName;
    private String phoneNumber;
    private Boolean status;
}
