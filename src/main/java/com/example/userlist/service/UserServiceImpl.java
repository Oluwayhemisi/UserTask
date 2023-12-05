package com.example.userlist.service;

import com.example.userlist.dto.CreateUserRequest;
import com.example.userlist.dto.CreateUserResponse;
import com.example.userlist.exceptions.UserException;
import com.example.userlist.model.User;
import com.example.userlist.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{


    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    @Override
    public CreateUserResponse createUser(CreateUserRequest createUserRequest) throws UserException {
        Optional<User> user = userRepository.findByEmail(createUserRequest.getEmail());
        if (user.isPresent()){
            throw new UserException("User Already Exist",404);
        }
        User user1 = new User();
        user1.setUserName(createUserRequest.getUserName());
        user1.setEmail(createUserRequest.getEmail());
        user1.setPhoneNumber(createUserRequest.getPhoneNumber());
        user1.setStatus(createUserRequest.getStatus());

        User savedUser = userRepository.save(user1);
        return modelMapper.map(savedUser,CreateUserResponse.class);



    }

    @Override
    public List<CreateUserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> {
            CreateUserResponse userDto = new CreateUserResponse();
            BeanUtils.copyProperties(user, userDto);
            return userDto;
        }).collect(Collectors.toList());
    }

    @Override
    public User findUserByEmail(String email) throws UserException {
        return userRepository.findByEmail(email).orElseThrow(()-> new UserException("User email not found",404));

    }
}
