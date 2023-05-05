package testCompiler;

import compiler.Lexer;
import compiler.parser;
import compiler.nodi.ProgramOp;
import compiler.visitors.semanticVisitor.SemanticVisitor1;
import compiler.visitors.semanticVisitor.SemanticVisitor2;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.logging.Logger;

public class SemanticTest {


    @Test
    public void tc_correct1() throws FileNotFoundException {
        String path_test_file="NewLangExemple";
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
            //Logger log = Logger.getLogger(this.getClass().getName());
            //log.info("\n Nessuna Eccezione Catturata");
        } catch (Exception e) {
            catturata=true;
        }

        Assert.assertFalse(catturata);

    }

    @Test
    public void tc_correct2() throws FileNotFoundException {
        String path_test_file="ExempleProgram.txt";
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
            //Logger log = Logger.getLogger(this.getClass().getName());
            //log.info("\n Nessuna Eccezione Catturata");
        } catch (Exception e) {

        }

        Assert.assertFalse(catturata);
    }

}