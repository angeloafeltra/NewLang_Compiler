package perfomance_testing;

import compiler.NewLang;
import org.openjdk.jmh.annotations.*;

import java.io.*;
import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

public class BenchmarkCompiler {

    @State(Scope.Benchmark)
    public static class FileToCompile{

        private SecureRandom random = new SecureRandom();
        @Setup
        public void setup(){}


        public byte[] getFileContent(String path_test_file) {
            byte[] fileContent;
            String separator = File.separator;
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

            return fileContent;
        }



        public String getFileName() {
            String alphaNumericStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz0123456789";
            StringBuilder s=new StringBuilder();

            for(int i=0;i<15;i++) {
                int ch = random.nextInt(0, alphaNumericStr.length());
                s.append(alphaNumericStr.charAt(ch));
            }
            return s.toString();
        }

    }



    @Benchmark
    @Fork(3)
    @Warmup(iterations = 2)
    @Measurement(iterations = 6)
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void measureAvgTimeCompilerFile1Line(FileToCompile file) throws IOException {
        NewLang newLang=new NewLang();
        newLang.compile(file.getFileContent("/test_script_performance_testing/Test_1Line.txt"),file.getFileName());
    }

    @Benchmark
    @Fork(3)
    @Warmup(iterations = 2)
    @Measurement(iterations = 6)
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void measureAvgTimeCompilerFile10Line(FileToCompile file) throws IOException {
        NewLang newLang=new NewLang();
        newLang.compile(file.getFileContent("/test_script_performance_testing/Test_10Line.txt"),file.getFileName());
    }

    @Benchmark
    @Fork(3)
    @Warmup(iterations = 2)
    @Measurement(iterations = 6)
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void measureAvgTimeCompilerFile100Line(FileToCompile file) throws IOException {
        NewLang newLang=new NewLang();
        newLang.compile(file.getFileContent("/test_script_performance_testing/Test_100Line.txt"),file.getFileName());
    }



    @Benchmark
    @Fork(3)
    @Warmup(iterations = 2)
    @Measurement(iterations = 6)
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void measureAvgTimeCompilerFile500Line(FileToCompile file) throws IOException {
        NewLang newLang=new NewLang();
        newLang.compile(file.getFileContent("/test_script_performance_testing/Test_500Line.txt"),file.getFileName());
    }




    @Benchmark
    @Fork(3)
    @Warmup(iterations = 2)
    @Measurement(iterations = 6)
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void measureAvgTimeCompilerFile750Line(FileToCompile file) throws IOException {
        NewLang newLang=new NewLang();
        newLang.compile(file.getFileContent("/test_script_performance_testing/Test_750Line.txt"),file.getFileName());
    }



    @Benchmark
    @Fork(3)
    @Warmup(iterations = 2)
    @Measurement(iterations = 6)
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void measureAvgTimeCompilerFile1000Line(FileToCompile file) throws IOException {
        NewLang newLang=new NewLang();
        newLang.compile(file.getFileContent("/test_script_performance_testing/Test_1000Line.txt"),file.getFileName());
    }

    @Benchmark
    @Fork(3)
    @Warmup(iterations = 2)
    @Measurement(iterations = 6)
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void measureAvgTimeCompilerFile2000Line(FileToCompile file) throws IOException {
        NewLang newLang=new NewLang();
        newLang.compile(file.getFileContent("/test_script_performance_testing/Test_2000Line.txt"),file.getFileName());
    }



    @Benchmark
    @Fork(3)
    @Warmup(iterations = 2)
    @Measurement(iterations = 6)
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void measureAvgTimeCompilerFile50Line(FileToCompile file) throws IOException {
        NewLang newLang=new NewLang();
        newLang.compile(file.getFileContent("/test_script_performance_testing/Test_50Line.txt"),file.getFileName());
    }

    @Benchmark
    @Fork(3)
    @Warmup(iterations = 2)
    @Measurement(iterations = 6)
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void measureAvgTimeCompilerFile250Line(FileToCompile file) throws IOException {
        NewLang newLang=new NewLang();
        newLang.compile(file.getFileContent("/test_script_performance_testing/Test_250Line.txt"),file.getFileName());
    }




    /*
    @Benchmark
    @Fork(3)
    @Warmup(iterations = 3)
    @Measurement(iterations = 6)
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void measureThrptCompiler(FileToCompile file) throws IOException {
        NewLang newLang=new NewLang();
        newLang.compile(file.getFileContent(),file.getFileName());
    }
    */




}
