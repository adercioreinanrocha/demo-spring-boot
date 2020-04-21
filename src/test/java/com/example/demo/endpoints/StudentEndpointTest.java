package com.example.demo.endpoints;

import com.example.demo.endpoints.entity.Book;
import com.example.demo.endpoints.entity.Student;
import com.example.demo.endpoints.repository.BookRepository;
import com.example.demo.endpoints.services.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = DemoApplication.class)
@AutoConfigureMockMvc
@Sql(value = "/schema.sql", executionPhase = BEFORE_TEST_METHOD)
@Sql(scripts = "/before-test.sql", executionPhase = BEFORE_TEST_METHOD)
public class StudentEndpointTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private StudentService studentService;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void getStudentTest() throws Exception {
        //TODO gerar o token  POST /authorization body=usuario e senha. Retornar token
        String body = "{\"userName\": \"user\",\"password\": \"user\"}";
        MvcResult resultAuthentication = mvc.perform(MockMvcRequestBuilders
                .post("/authenticate")
                .contentType("application/json")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(body))
                .andExpect(status().isOk())
                .andReturn();

        String token = resultAuthentication.getResponse().getContentAsString();

        //TODO obter o token e repassar no HEADER Authorization para chamado do StudentEndpointss

        String expected = "{\n" +
                "    \"id\": 1001,\n" +
                "    \"name\": \"Jose\",\n" +
                "    \"passportNumber\": \"A39448\",\n" +
                "    \"books\": [\n" +
                "        {\n" +
                "            \"id\": 1,\n" +
                "            \"name\": \"Book 1\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 2,\n" +
                "            \"name\": \"Book 2\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        given(bookRepository.findBooks()).willReturn(getBooks());

        MvcResult resultStudent = mvc.perform(MockMvcRequestBuilders
                .get("/rs/students")
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().json(expected))
                .andReturn();

    }

    private List<Book> getBooks() {
        List<Book> books = new ArrayList<>();
        books.add(new Book(1L, "Book 1"));
        books.add(new Book(2L, "Book 2"));
        return books;
    }
}
