package com.example.demo.services;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    public Student findById(Long id){
        Optional<Student> student = repository.findById(id);
        return student.get();
    }

    public Student save(Student student){
        return repository.save(student);
    }

    public Student delete(Long id){
        Optional<Student> studentOptional = repository.findById(id);
        studentOptional.ifPresent(student -> repository.delete(student));
        return studentOptional.get();
    }

    public List<Student> findAll(){
        return repository.findAll();
    }
}
