package perfomance_testing;

import compiler.Lexer;
import compiler.nodi.ProgramOp;
import compiler.parser;
import compiler.visitors.semanticVisitor.SemanticVisitor1;
import org.openjdk.jmh.annotations.*;

import javax.swing.tree.DefaultMutableTreeNode;
import java.io.*;
import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

public class BenchmarkSemanticVisitor1 {

    @State(Scope.Benchmark)
    public static class FileToCompile{

        private byte[] fileContent;
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
    public void measureAvgTimeCompiler(BenchmarkCompiler.FileToCompile file) throws Exception {
        parser p = new parser(new Lexer(new InputStreamReader(new ByteArrayInputStream(file.getFileContent()))));
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) p.parse().value;
        ((ProgramOp) root).accept(new SemanticVisitor1());
    }

    
}
