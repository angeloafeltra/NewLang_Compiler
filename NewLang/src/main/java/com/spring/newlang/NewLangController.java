package com.spring.newlang;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class NewLangController {


    @RequestMapping(
            value="/compile",
            method = RequestMethod.GET
    )
    public String compile(@RequestBody Map<String,String> json){
        return "Hello World From Spring Boot2";
    }

    @RequestMapping("/listenCall")
    public String listenCall() {
        return "Messaggio Ricevuto e ricambiato";
    }

}
