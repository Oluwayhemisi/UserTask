package com.example.userlist.service;

import com.example.userlist.dto.CreateUserRequest;
import com.example.userlist.dto.CreateUserResponse;
import com.example.userlist.exceptions.UserException;
import com.example.userlist.model.User;

import java.util.List;

public interface UserService {
    CreateUserResponse createUser(CreateUserRequest createUserRequest) throws UserException;

    List<CreateUserResponse> getAllUsers();
    User findUserByEmail(String email) throws UserException;

}
