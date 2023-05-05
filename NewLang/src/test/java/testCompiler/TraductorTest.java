package testCompiler;

import compiler.Lexer;
import compiler.parser;
import compiler.nodi.ProgramOp;
import compiler.visitors.TranslatorVisitor;
import compiler.visitors.semanticVisitor.SemanticVisitor1;
import compiler.visitors.semanticVisitor.SemanticVisitor2;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;;

import javax.swing.tree.DefaultMutableTreeNode;
import java.io.*;

import static org.junit.Assert.assertEquals;

public class TraductorTest {


    public void newLang2C(parser p) {
        try {
            DefaultMutableTreeNode root = (DefaultMutableTreeNode) p.parse().value;
            //Genero le symbol table
            ((ProgramOp) root).accept(new SemanticVisitor1());
            ((ProgramOp) root).accept(new SemanticVisitor2());
            ((ProgramOp) root).accept(new TranslatorVisitor());


            ProcessBuilder builder = new ProcessBuilder("/bin/bash", "-c", "cd " + System.getProperty("user.dir") + "&& cd test_files && cd c_out && gcc c_gen.c");
            builder.redirectErrorStream(true);
            Process process = builder.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while (true) {
                line = r.readLine();
                if (line == null) {
                    break;
                }
                //System.out.println(line);
            }

        } catch (Exception e) {

        }
    }


    @ParameterizedTest
    @CsvSource({
            "test_translator/TestDichiarazioniVariabili, test_translator/c_testDichiarazioniVariabili.c",
            "test_translator/TestOperazioniBinarie, test_translator/c_testOperazioniBinarie.c",
            "test_translator/TestOperazioniUnarie, test_translator/c_testOperazioniUnarie.c",
            "test_translator/TestExempleProgram.txt, test_translator/c_testExempleProgram.c",
            "test_translator/TestNewLangExemple, test_translator/c_testNewLangExemple.c"
    })
    public void tc_generalTrad(String path_test_file, String path_oracolo) throws IOException {
        String separator = File.separator;
        File input = new File(
                System.getProperty("user.dir") + separator + "test_files" + separator + path_test_file);
        parser p = new parser(new Lexer(new FileReader(input)));

        newLang2C(p);


        String path_output = "c_out/c_gen.c";
        File output = new File(System.getProperty("user.dir") + separator + "test_files" + separator + path_output);
        File oracolo = new File(System.getProperty("user.dir") + separator + "test_files" + separator + path_oracolo);

        assertEquals("The files differ!",
                FileUtils.readFileToString(output, "utf-8"),
                FileUtils.readFileToString(oracolo, "utf-8"));


    }


}