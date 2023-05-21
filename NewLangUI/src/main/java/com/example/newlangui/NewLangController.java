package com.example.newlangui;

import com.example.FileDownloadUtil;
import com.example.GenExecutable;
import jakarta.jws.WebParam;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.result.view.RedirectView;


import java.io.IOException;

import java.util.HashMap;

import java.util.Map;




@Controller
public class NewLangController {


    @GetMapping("/")
    public String loadHomepage(ModelMap model){
        model.addAttribute("error","");
        return "homepage";
    }


    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, ModelMap model) throws IOException {
        //Controllo che il file non è vuto
        if (!file.isEmpty()){
            System.out.println(file.getOriginalFilename());
            byte[] fileContent=file.getBytes(); //Contenuto del file
            byte[] fileName = file.getOriginalFilename().getBytes(); //Nome del file


            Map<String, byte[]> json = new HashMap<String, byte[]>();
            json.put("file", fileContent);
            json.put("fileName",fileName);


            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Map<String, byte[]>> requestEntity = new HttpEntity<Map<String, byte[]>>(json, headers);
            ResponseEntity<Map<String, byte[]>> response = restTemplate.exchange("http://back-end:8080/compile", HttpMethod.POST, requestEntity, new ParameterizedTypeReference<Map<String, byte[]>>() {});
            System.out.println("Status code: " + response.getStatusCode());
            Map<String,byte[]> jsonResponse=response.getBody();
            String error=new String(jsonResponse.get("error"));

            if(error.equals("Compilazione corretta")){
                //Salvo il file
                GenExecutable.createFile(new String(jsonResponse.get("fileName")),jsonResponse.get("file"));
                model.addAttribute("fileName",new String(jsonResponse.get("fileName")));
                return "downloadPage";
            }else{
                if(error.equals("Errore cancellazione")){
                    model.addAttribute("error","Errore interno del compilatore riprovare più tardi.");
                    return "homepage";
                }else{
                    model.addAttribute("error","C'è un errore nello script, controlla che la sintassi e la semantica sia stata rispettata.");
                    return "homepage";
                }
            }

        }else {
            model.addAttribute("error","File non caricato.");
            return "homepage";
        }
    }

    @GetMapping("/downloadFile/{fileCode}")
    public ResponseEntity<?> downloadFile(@PathVariable("fileCode") String fileCode) {
        FileDownloadUtil downloadUtil = new FileDownloadUtil();

        Resource resource = null;
        try {
            resource = downloadUtil.getFileAsResource(fileCode);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }

        if (resource == null) {
            return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
        }

        String contentType = "application/octet-stream";
        String headerValue = "attachment; filename=\"" + resource.getFilename() + "\"";

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                .body(resource);
    }


    @RequestMapping("/testConnection")
    public String sendMessage(ModelMap model) {
        final String uri = "http://back-end:8080/testListen";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        if(result.equals("Messaggio Ricevuto e ricambiato"))
            model.addAttribute("result","Connessione OK");
        else
            model.addAttribute("result","Connessione Non Funzionante");
        return "testPage";
    }

}
