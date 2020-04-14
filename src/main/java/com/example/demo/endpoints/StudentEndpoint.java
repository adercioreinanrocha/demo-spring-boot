package com.example.demo.endpoints;

import com.example.demo.entity.Student;
import com.example.demo.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rs/students", produces = MediaType.APPLICATION_JSON_VALUE)
public class StudentEndpoint {

    @Autowired
    private StudentService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Student> findAll(){
        return service.findAll();
    }

    @GetMapping(value = "/{id}")
    public Student findById(@PathVariable Long id){
        return service.findById(id);
    }

    @PostMapping
    public Student save(@RequestBody Student student){
        return service.save(student);
    }

    @DeleteMapping(value = "/{id}")
    public Student save(@PathVariable Long id){
        return service.delete(id);
    }
}
