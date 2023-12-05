package com.example.userlist.controller;


import com.example.userlist.dto.ApiResponse;
import com.example.userlist.dto.CreateUserRequest;
import com.example.userlist.dto.CreateUserResponse;
import com.example.userlist.exceptions.UserException;
import com.example.userlist.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("create/")
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest createUserRequest) throws UserException {
        CreateUserResponse createCustomerResponse = userService.createUser(createUserRequest);
        ApiResponse apiResponse = ApiResponse.builder()
                .message("Transaction successful")
                .status("success")
                .data(createCustomerResponse)
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<?> getUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }
}
