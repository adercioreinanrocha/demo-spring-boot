package com.example.demo.endpoints.services;

import com.example.demo.endpoints.entity.Book;
import com.example.demo.endpoints.entity.Student;
import com.example.demo.endpoints.infrastructure.exceptions.EntityNotFoundException;
import com.example.demo.endpoints.repository.BookRepository;
import com.example.demo.endpoints.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    @Autowired
    private BookRepository bookRepository;


    public Student findById(Long id){
        Student student = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Student Not Found!"));
        List<Book> books = bookRepository.findBooks();
        student.setBooks(books);
        return student;
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
        List<Student> students = repository.findAll();
        List<Book> books = bookRepository.findBooks();
        students.forEach(student -> student.setBooks(books));
        return students;
    }
}
