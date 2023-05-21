package com.example;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GenExecutable {


    public static void createFile(String fileName,byte[] fileContent) throws IOException {
        Path filePath= Paths.get("fileConvertiti/"+fileName+".out");
        Files.createFile(filePath);

        System.out.println(fileName);
        Set<PosixFilePermission> perms = new HashSet<>();
        perms.add(PosixFilePermission.OWNER_READ);
        perms.add(PosixFilePermission.OWNER_WRITE);
        perms.add(PosixFilePermission.OWNER_EXECUTE);
        perms.add(PosixFilePermission.GROUP_READ);
        perms.add(PosixFilePermission.GROUP_WRITE);
        perms.add(PosixFilePermission.GROUP_EXECUTE);
        perms.add(PosixFilePermission.OTHERS_READ);
        perms.add(PosixFilePermission.OTHERS_WRITE);
        perms.add(PosixFilePermission.OTHERS_EXECUTE);

        // Imposta i permessi di accesso sul file

        File fileConvertito=new File(filePath.toUri());
        try{
            OutputStream os=new FileOutputStream(fileConvertito);
            os.write(fileContent);
            os.close();
        }
        catch (Exception e){
            System.out.println("Exception: "+ e);
        }

        Files.setPosixFilePermissions(filePath, perms);
        //printPosixPermissions(filePath);
    }

    private static void printPosixPermissions(Path path) throws IOException {
        Map<String, Object> attributes = Files.readAttributes(path, "posix:*");
        attributes.entrySet()
                .forEach(e -> System.out.printf("%s = %s%n", e.getKey(), e.getValue()));
    }
}
