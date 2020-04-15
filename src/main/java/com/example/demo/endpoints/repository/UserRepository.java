package com.example.demo.endpoints.repository;

import com.example.demo.endpoints.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String userName);
}
