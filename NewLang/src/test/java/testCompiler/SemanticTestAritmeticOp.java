package testCompiler;

import compiler.Lexer;
import compiler.parser;
import compiler.nodi.ProgramOp;
import compiler.visitors.semanticVisitor.Eccezioni;
import compiler.visitors.semanticVisitor.SemanticVisitor1;
import compiler.visitors.semanticVisitor.SemanticVisitor2;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.logging.Logger;

class SemanticTestAritmeticOp {



    @ParameterizedTest
    @CsvSource({
            "test_sematicException_files/ArithmeticOp/Test_AritOp1",
            "test_sematicException_files/ArithmeticOp/Test_AritOp2",
            "test_sematicException_files/ArithmeticOp/Test_AritOp3",
            "test_sematicException_files/ArithmeticOp/Test_AritOp4",
            "test_sematicException_files/ArithmeticOp/Test_AritOp5",
            "test_sematicException_files/ArithmeticOp/Test_AritOp6"
    })
    void tc_aritOpGeneral(String path_test_file) throws FileNotFoundException {
        String separator = File.separator;
        File input = new File(
                System.getProperty("user.dir") +separator+"test_files"+ separator+ path_test_file);
        parser p = new parser(new Lexer(new FileReader(input)));

        boolean catturata=false;
        try {
            DefaultMutableTreeNode root = (DefaultMutableTreeNode) p.parse().value;

            //Genero le symbol table
            ((ProgramOp) root).accept(new SemanticVisitor1());
            ((ProgramOp) root).accept(new SemanticVisitor2());
        } catch (Exception e) {
            if(e instanceof Eccezioni.ArithmeticOpError)
            {
                catturata=true;
                //Logger log = Logger.getLogger(this.getClass().getName());
                //log.info("\n Eccezione ArithmeticOpError Catturata");
            }
        }

        Assert.assertTrue(catturata);
    }

}