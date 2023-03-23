package com.example.newlangui;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
public class NewLangController {


    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        // Inserire qui la logica per gestire il file ricevuto
        // Ad esempio, salvare il file sul disco
        // Si può usare file.getOriginalFilename() per ottenere il nome del file originale
        System.out.println(file);
        if (!file.isEmpty()){
            return ResponseEntity.ok("File caricato con successo");
        }else {
            // Restituire una risposta di successo
            return ResponseEntity.ok("File non caricato");
        }
    }


    @RequestMapping("/sendMessageContainer2")
    public String sendMessage() {
        System.out.println("RICEVUTO");
        final String uri = "http://localhost:8080/listenCall";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        if(result.equals("Messaggio Ricevuto e ricambiato"))
            return "Collegamento funzionante";
        else
            return "Collegamento non funzionante";
    }

}