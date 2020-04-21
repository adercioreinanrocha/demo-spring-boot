package com.example.demo.endpoints.repository;

import com.example.demo.endpoints.entity.Book;
import com.example.demo.endpoints.infrastructure.exceptions.IntegrationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Repository
public class BookRepository {

    @Autowired
    private RestTemplate restTemplate;

    public List<Book> findBooks(){
        final String uri = "http://www.mocky.io/v2/5e98e32a3500006b00c4868c"; // SUCESSO
        //final String uri = "http://www.mocky.io/v2/5e9e50a13400009ab46eec4a"; // ERROR
        try {

            ResponseEntity<List<Book>>  responseEntity = restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<List<Book>>() {
            });
            if(responseEntity.getStatusCode().equals(HttpStatus.OK)){
                return responseEntity.getBody();
            }
        }catch(HttpStatusCodeException exception){
            throw new IntegrationException("Error na integracao com servico: " + uri + "; statusCode: " + exception.getStatusCode());
        }
        return Collections.emptyList();
    }
}
