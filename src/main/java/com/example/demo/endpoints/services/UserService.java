package com.example.demo.endpoints.services;

import com.example.demo.endpoints.entity.User;
import com.example.demo.endpoints.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User findByUserName(String userName){
        return repository.findByUserName(userName);
    }
}
