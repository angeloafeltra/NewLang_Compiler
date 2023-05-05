package com.spring.newlang;

import compiler.NewLang;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;



@RestController
public class NewLangController {


    public static final String CONST_ERROR="error";
    public static final String CONST_FILE="file";
    public static final String CONST_FILE_NAME="fileName";

    private Random random = new Random();

    @RequestMapping("/compile")
    public Map<String,byte[]> compileFileNewLang(@RequestBody Map<String,byte[]> json) throws IOException {
        byte[] fileContent= json.get(CONST_FILE);
        String fileName=new String(json.get(CONST_FILE_NAME));
        int index = fileName.indexOf(".");
        if(index>0)
            fileName=fileName.substring(0,index);

        //Aggiungo un numero random al file name

        int number= random.nextInt(1000,2000);
        fileName=fileName+Integer.toString(number);

        NewLang newLang=new NewLang();
        File eseguibile=newLang.compile(fileContent,fileName);


        if(eseguibile!=null){
            byte[] buffer = new byte[1024];
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            try (InputStream input = new FileInputStream(eseguibile)) {
                int n = 0;
                while ((n = input.read(buffer)) != -1) {
                    output.write(buffer, 0, n);
                }
            }
            byte[] executableBytes = output.toByteArray();
            File gcc=new File("test_files" + File.separator + "c_out" + File.separator + fileName +".c");

            if(eseguibile.delete() && gcc.delete()){
                Map<String, byte[]> json2 = new HashMap<String, byte[]>();
                json2.put(CONST_FILE, executableBytes);
                json2.put(CONST_ERROR,"Compilazione corretta".getBytes());
                json2.put(CONST_FILE_NAME,fileName.getBytes());
                return json2;
            }else{
                Map<String, byte[]> json2 = new HashMap<String, byte[]>();
                json2.put(CONST_FILE, new byte[0]);
                json2.put(CONST_ERROR, "Errore cancellazione".getBytes());
                return json2;
            }

        }else{
            File gcc=new File("test_files" + File.separator + "c_out" + File.separator + fileName +".c");
            if(!gcc.delete()) { System.out.println("Errore nel eliminazione del file"); }

            Map<String, byte[]> json2 = new HashMap<String, byte[]>();
            json2.put(CONST_FILE, new byte[0]);
            json2.put(CONST_ERROR, "Errore compilazione".getBytes());
            return json2;
        }

    }

    @RequestMapping("/testListen")
    public String listenMessage() {
        return "Messaggio Ricevuto e ricambiato";
    }

}
