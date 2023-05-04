package com.example.finalexam;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestController {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void get(){
        String name = "le";
        List resp = restTemplate.getForObject("http://localhost:"+port+"/comment?name="+name,List.class);
        if (resp.size() == 0) {
            assertThrows(NullPointerException.class,()-> {
                resp.get(0).toString();
            },"ISBN Exception error was expected");
        }
    }

    @Test
    public void addNewCommentTest(){
        Comments c = new Comments();
        c.setName("test");
        c.setEmail("test@utcc.ac.th");
        c.setText("eiei");

        ResponseEntity<Comments> re = restTemplate.postForEntity("http://localhost:"+port+"/newComments",c,Comments.class);
        Comments resp = re.getBody();
        assertEquals(re.getStatusCode(), HttpStatus.CREATED);
        assertEquals(resp.getEmail(),"test@utcc.ac.th");
    }

}
