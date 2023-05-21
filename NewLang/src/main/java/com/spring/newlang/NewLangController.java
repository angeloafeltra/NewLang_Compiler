package com.spring.newlang;

import compiler.NewLang;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;




@RestController
public class NewLangController {


    public static final String CONST_ERROR="error";
    public static final String CONST_FILE="file";
    public static final String CONST_FILE_NAME="fileName";


    private SecureRandom random = new SecureRandom();

    @PostMapping(path="/compile")
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

            //eseguibile.delete() && gcc.delete()
            ArrayList<File> filesToDelete=new ArrayList<>(){
                {
                    add(eseguibile);
                    add(gcc);
                }
            };
            if( deleteFile(filesToDelete)){
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
            ArrayList<File> filesToDelete=new ArrayList<>(){
                {
                    add(gcc);
                }
            };
            if(deleteFile(filesToDelete)) { System.err.println("Errore nel eliminazione del file"); }

            Map<String, byte[]> json2 = new HashMap<>();
            json2.put(CONST_FILE, new byte[0]);
            json2.put(CONST_ERROR, "Errore compilazione".getBytes());
            return json2;
        }

    }

    @GetMapping(path="/testListen")
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

    private boolean deleteFile(ArrayList<File> filesToDelete) {
        for (File file:filesToDelete) {
            try {
                Files.delete(Path.of(file.getPath()));
            } catch (IOException e) {
                return false;
            }
        }
        return true;
    }

}
