package com.example.demo.endpoints.repository;

import com.example.demo.endpoints.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
