package com.spring.newlang;

import compiler.NewLang;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;



@RestController
public class NewLangController {


    public static final String CONST_ERROR="error";
    public static final String CONST_FILE="file";
    public static final String CONST_FILE_NAME="fileName";
    static private String targetDirectory = "test_files" + File.separator + "c_out" + File.separator;

    private SecureRandom random = new SecureRandom();

    @RequestMapping(path="/compile", method = RequestMethod.POST)
    public Map<String,byte[]> compileFileNewLang(@RequestBody Map<String,byte[]> json) throws IOException {

        byte[] fileContent= json.get(CONST_FILE);
        String fileName=generateRandomFileName(12);

        NewLang newLang=new NewLang();

        File eseguibile=newLang.compile(fileContent,fileName);
        File gcc=new File("test_files" + File.separator + "c_out" + File.separator + fileName +".c");


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


            if(eseguibile.delete() && gcc.delete()){
                Map<String, byte[]> json2 = new HashMap<>();
                json2.put(CONST_FILE, executableBytes);
                json2.put(CONST_ERROR,"Compilazione corretta".getBytes());
                json2.put(CONST_FILE_NAME,fileName.getBytes());
                return json2;
            }else{
                Map<String, byte[]> json2 = new HashMap<>();
                json2.put(CONST_FILE, new byte[0]);
                json2.put(CONST_ERROR, "Errore cancellazione".getBytes());
                return json2;
            }

        }else{
            if(!gcc.delete()) { System.out.println("Errore nel eliminazione del file"); }

            Map<String, byte[]> json2 = new HashMap<>();
            json2.put(CONST_FILE, new byte[0]);
            json2.put(CONST_ERROR, "Errore compilazione".getBytes());
            return json2;
        }

    }

    @RequestMapping(path="/testListen", method = RequestMethod.GET)
    public String listenMessage() {
        return "Messaggio Ricevuto e ricambiato";
    }


    private String generateRandomFileName(int lunghezza){

        String alphaNumericStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz0123456789";
        StringBuilder s=new StringBuilder();

        for(int i=0;i<lunghezza;i++){
            int ch = random.nextInt(0,alphaNumericStr.length());
            s.append(alphaNumericStr.charAt(ch));
        }

        return s.toString();
    }

}
