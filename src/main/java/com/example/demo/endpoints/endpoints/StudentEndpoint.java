package com.example.demo.endpoints.endpoints;

import com.example.demo.endpoints.services.StudentService;
import com.example.demo.endpoints.entity.Student;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rs/students", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "Student")
public class StudentEndpoint {

    @Autowired
    private StudentService service;

    @ApiOperation(value = "Obter todos os estudantes")
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
