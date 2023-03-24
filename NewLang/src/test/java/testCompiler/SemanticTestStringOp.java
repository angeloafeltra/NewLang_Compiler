package testCompiler;

import compiler.Lexer;
import compiler.parser;
import compiler.nodi.ProgramOp;
import compiler.visitors.semanticVisitor.Eccezioni;
import compiler.visitors.semanticVisitor.SemanticVisitor1;
import compiler.visitors.semanticVisitor.SemanticVisitor2;
import org.junit.Assert;
import org.junit.jupiter.api.Test;;


import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.logging.Logger;

public class SemanticTestStringOp {

    /**
     * Test in cui viene eseguita l'operazione di concanetazione tra due integer.
     * Lancio l'eccezione StringOpError
     */
    @Test
    public void tc_StrOp1() throws FileNotFoundException {
        String path_test_file="test_sematicException_files/StrOp/Test_StrOp1";
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
            if(e instanceof Eccezioni.StringOpError)
            {
                catturata=true;
                Logger log = Logger.getLogger(this.getClass().getName());
                log.info("\n Eccezione StringOpError Catturata");
            }
        }

        Assert.assertTrue(catturata);
    }

    /**
     * Test in cui viene eseguita l'operazione di concanetazione tra due float.
     * Lancio l'eccezione StringOpError
     */
    @Test
    public void tc_StrOp2() throws FileNotFoundException {
        String path_test_file="test_sematicException_files/StrOp/Test_StrOp2";
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
            if(e instanceof Eccezioni.StringOpError)
            {
                catturata=true;
                Logger log = Logger.getLogger(this.getClass().getName());
                log.info("\n Eccezione StringOpError Catturata");
            }
        }

        Assert.assertTrue(catturata);
    }

    /**
     * Test in cui viene eseguita l'operazione di concanetazione tra due char.
     * Lancio l'eccezione StringOpError
     */
    @Test
    public void tc_StrOp3() throws FileNotFoundException {
        String path_test_file="test_sematicException_files/StrOp/Test_StrOp3";
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
            if(e instanceof Eccezioni.StringOpError)
            {
                catturata=true;
                Logger log = Logger.getLogger(this.getClass().getName());
                log.info("\n Eccezione StringOpError Catturata");
            }
        }

        Assert.assertTrue(catturata);
    }

    /**
     * Test in cui viene eseguita l'operazione di concanetazione tra due boolean.
     * Lancio l'eccezione StringOpError
     */
    @Test
    public void tc_StrOp4() throws FileNotFoundException {
        String path_test_file="test_sematicException_files/StrOp/Test_StrOp4";
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
            if(e instanceof Eccezioni.StringOpError)
            {
                catturata=true;
                Logger log = Logger.getLogger(this.getClass().getName());
                log.info("\n Eccezione StringOpError Catturata");
            }
        }

        Assert.assertTrue(catturata);
    }
}