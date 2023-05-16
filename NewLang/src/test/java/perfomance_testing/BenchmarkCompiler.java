package perfomance_testing;

import compiler.NewLang;
import org.openjdk.jmh.annotations.*;

import java.io.*;
import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

public class BenchmarkCompiler {

    @State(Scope.Benchmark)
    public static class FileToCompile{

        public byte[] fileContent;
        public String fileName;
        private SecureRandom random = new SecureRandom();
        @Setup
        public void setup(){

            //Leggo un file
            String separator = File.separator;
            String path_test_file="NewLangExemple";
            File input_file = new File(
                    System.getProperty("user.dir") + separator + "test_files" + separator + path_test_file);
            byte[] buffer = new byte[1024];
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            try (InputStream input = new FileInputStream(input_file)) {
                int n = 0;
                while ((n = input.read(buffer)) != -1) {
                    output.write(buffer, 0, n);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            fileContent= output.toByteArray();

            //Genero il nome del file
            String alphaNumericStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz0123456789";
            StringBuilder s=new StringBuilder();

            for(int i=0;i<15;i++){
                int ch = random.nextInt(0,alphaNumericStr.length());
                s.append(alphaNumericStr.charAt(ch));
            }

            fileName=s.toString();

        }

        public byte[] getFileContent() {return fileContent;}
        public String getFileName() {return fileName;}
    }



    @Benchmark
    @Fork(3)
    @Warmup(iterations = 3)
    @Measurement(iterations = 6)
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void measureAvgTimeCompiler(FileToCompile file) {
        NewLang newLang=new NewLang();
        newLang.compile(file.getFileContent(),file.getFileName());
    }

    @Benchmark
    @Fork(3)
    @Warmup(iterations = 3)
    @Measurement(iterations = 6)
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void measureThrptCompiler(FileToCompile file) {
        NewLang newLang=new NewLang();
        newLang.compile(file.getFileContent(),file.getFileName());
    }


    @Benchmark
    @Fork(3)
    @Warmup(iterations = 3)
    @Measurement(iterations = 6)
    @BenchmarkMode(Mode.SampleTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void measureSampleCompiler(FileToCompile file) {
        NewLang newLang=new NewLang();
        newLang.compile(file.getFileContent(),file.getFileName());
    }


    @Benchmark
    @Fork(3)
    @Warmup(iterations = 3)
    @Measurement(iterations = 6)
    @BenchmarkMode(Mode.SingleShotTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void measureSingleShotCompiler(FileToCompile file) {
        NewLang newLang=new NewLang();
        newLang.compile(file.getFileContent(),file.getFileName());
    }

}
