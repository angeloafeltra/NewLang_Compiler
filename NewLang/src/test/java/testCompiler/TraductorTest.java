package testCompiler;

import compiler.Lexer;
import compiler.parser;
import compiler.nodi.ProgramOp;
import compiler.visitors.TranslatorVisitor;
import compiler.visitors.semanticVisitor.SemanticVisitor1;
import compiler.visitors.semanticVisitor.SemanticVisitor2;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import javax.swing.tree.DefaultMutableTreeNode;
import java.io.*;

import static org.junit.Assert.assertEquals;

public class TraductorTest {



    public void newLang2C(parser p){
        try {
            DefaultMutableTreeNode root = (DefaultMutableTreeNode) p.parse().value;
            //Genero le symbol table
            ((ProgramOp) root).accept(new SemanticVisitor1());
            ((ProgramOp) root).accept(new SemanticVisitor2());
            ((ProgramOp) root).accept(new TranslatorVisitor());


            ProcessBuilder builder = new ProcessBuilder("/bin/bash", "-c", "cd "+System.getProperty("user.dir")+"&& cd test_files && cd c_out && gcc " + TranslatorVisitor.FILE_NAME);
            builder.redirectErrorStream(true);
            Process process = builder.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while (true) {
                line = r.readLine();
                if (line == null) {
                    break;
                }
                System.out.println(line);
            }

        } catch (Exception e) {

        }
    }

    @Test
    public void tc_tradVar() throws IOException {
        String path_test_file="test_translator/TestDichiarazioniVariabili";
        String separator = File.separator;
        File input = new File(
                System.getProperty("user.dir") +separator+"test_files"+ separator+ path_test_file);
        parser p = new parser(new Lexer(new FileReader(input)));

        newLang2C(p);


        String path_output="c_out/c_gen.c";
        String path_oracolo="test_translator/c_testDichiarazioniVariabili.c";
        File output=new File(System.getProperty("user.dir") +separator+"test_files"+ separator+ path_output);
        File oracolo=new File(System.getProperty("user.dir") +separator+"test_files"+ separator+ path_oracolo);

        assertEquals("The files differ!",
                FileUtils.readFileToString(output, "utf-8"),
                FileUtils.readFileToString(oracolo, "utf-8"));


    }

    @Test
    public void tc_tradOpBin() throws IOException {
        String path_test_file="test_translator/TestOperazioniBinarie";
        String separator = File.separator;
        File input = new File(
                System.getProperty("user.dir") +separator+"test_files"+ separator+ path_test_file);
        parser p = new parser(new Lexer(new FileReader(input)));

        newLang2C(p);


        String path_output="c_out/c_gen.c";
        String path_oracolo="test_translator/c_testOperazioniBinarie.c";
        File output=new File(System.getProperty("user.dir") +separator+"test_files"+ separator+ path_output);
        File oracolo=new File(System.getProperty("user.dir") +separator+"test_files"+ separator+ path_oracolo);

        assertEquals("The files differ!",
                FileUtils.readFileToString(output, "utf-8"),
                FileUtils.readFileToString(oracolo, "utf-8"));


    }

    @Test
    public void tc_tradOpUnarie() throws IOException {
        String path_test_file="test_translator/TestOperazioniUnarie";
        String separator = File.separator;
        File input = new File(
                System.getProperty("user.dir") +separator+"test_files"+ separator+ path_test_file);
        parser p = new parser(new Lexer(new FileReader(input)));

        newLang2C(p);


        String path_output="c_out/c_gen.c";
        String path_oracolo="test_translator/c_testOperazioniUnarie.c";
        File output=new File(System.getProperty("user.dir") +separator+"test_files"+ separator+ path_output);
        File oracolo=new File(System.getProperty("user.dir") +separator+"test_files"+ separator+ path_oracolo);

        assertEquals("The files differ!",
                FileUtils.readFileToString(output, "utf-8"),
                FileUtils.readFileToString(oracolo, "utf-8"));

    }

    @Test
    public void tc_traExempleProgram() throws IOException {
        String path_test_file="test_translator/TestExempleProgram.txt";
        String separator = File.separator;
        File input = new File(
                System.getProperty("user.dir") +separator+"test_files"+ separator+ path_test_file);
        parser p = new parser(new Lexer(new FileReader(input)));

        newLang2C(p);


        String path_output="c_out/c_gen.c";
        String path_oracolo="test_translator/c_testExempleProgram.c";
        File output=new File(System.getProperty("user.dir") +separator+"test_files"+ separator+ path_output);
        File oracolo=new File(System.getProperty("user.dir") +separator+"test_files"+ separator+ path_oracolo);

        assertEquals("The files differ!",
                FileUtils.readFileToString(output, "utf-8"),
                FileUtils.readFileToString(oracolo, "utf-8"));

    }

    @Test
    public void tc_tradNewLangExemple() throws IOException {
        String path_test_file="test_translator/TestNewLangExemple";
        String separator = File.separator;
        File input = new File(
                System.getProperty("user.dir") +separator+"test_files"+ separator+ path_test_file);
        parser p = new parser(new Lexer(new FileReader(input)));

        newLang2C(p);


        String path_output="c_out/c_gen.c";
        String path_oracolo="test_translator/c_testNewLangExemple.c";
        File output=new File(System.getProperty("user.dir") +separator+"test_files"+ separator+ path_output);
        File oracolo=new File(System.getProperty("user.dir") +separator+"test_files"+ separator+ path_oracolo);

        assertEquals("The files differ!",
                FileUtils.readFileToString(output, "utf-8"),
                FileUtils.readFileToString(oracolo, "utf-8"));

    }
}