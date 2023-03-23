package testCompiler;

import compiler.Lexer;
import compiler.parser;
import compiler.nodi.ProgramOp;
import compiler.visitors.semanticVisitor.Eccezioni;
import compiler.visitors.semanticVisitor.SemanticVisitor1;
import compiler.visitors.semanticVisitor.SemanticVisitor2;
import org.junit.Assert;
import org.junit.Test;

import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.logging.Logger;

public class SemanticTestUnaryOp {

    /**
     * Test in cui si nega una stringa.
     * Viene lanciata l'eccezione MinusOpError
     */
    @Test
    public void tc_MinusOp1() throws FileNotFoundException {
        String path_test_file="test_sematicException_files/UnaryOp/Test_MinusOp1";
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
            if(e instanceof Eccezioni.MinusOpError)
            {
                catturata=true;
                Logger log = Logger.getLogger(this.getClass().getName());
                log.info("\n Eccezione MinusOpError Catturata");
            }
        }

        Assert.assertTrue(catturata);
    }

    /**
     * Test in cui si nega un char.
     * Viene lanciata l'eccezione MinusOpError
     */
    @Test
    public void tc_MinusOp2() throws FileNotFoundException {
        String path_test_file="test_sematicException_files/UnaryOp/Test_MinusOp2";
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
            if(e instanceof Eccezioni.MinusOpError)
            {
                catturata=true;
                Logger log = Logger.getLogger(this.getClass().getName());
                log.info("\n Eccezione MinusOpError Catturata");
            }
        }

        Assert.assertTrue(catturata);
    }

    /**
     * Test in cui si nega un boolean.
     * Viene lanciata l'eccezione MinusOpError
     */
    @Test
    public void tc_MinusOp3() throws FileNotFoundException {
        String path_test_file="test_sematicException_files/UnaryOp/Test_MinusOp3";
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
            if(e instanceof Eccezioni.MinusOpError)
            {
                catturata=true;
                Logger log = Logger.getLogger(this.getClass().getName());
                log.info("\n Eccezione MinusOpError Catturata");
            }
        }

        Assert.assertTrue(catturata);
    }


    /**
     * Test in cui eseguo il not ad un intero
     * Viene lanciata l'eccezione NotOpError
     */
    @Test
    public void tc_NotOp1() throws FileNotFoundException {
        String path_test_file="test_sematicException_files/UnaryOp/Test_NotOp1";
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
            if(e instanceof Eccezioni.NotOpError)
            {
                catturata=true;
                Logger log = Logger.getLogger(this.getClass().getName());
                log.info("\n Eccezione NotOpError Catturata");
            }
        }

        Assert.assertTrue(catturata);
    }

    /**
     * Test in cui eseguo il not ad un float
     * Viene lanciata l'eccezione NotOpError
     */
    @Test
    public void tc_NotOp2() throws FileNotFoundException {
        String path_test_file="test_sematicException_files/UnaryOp/Test_NotOp2";
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
            if(e instanceof Eccezioni.NotOpError)
            {
                catturata=true;
                Logger log = Logger.getLogger(this.getClass().getName());
                log.info("\n Eccezione NotOpError Catturata");
            }
        }

        Assert.assertTrue(catturata);
    }

    /**
     * Test in cui eseguo il not ad un char
     * Viene lanciata l'eccezione NotOpError
     */
    @Test
    public void tc_NotOp3() throws FileNotFoundException {
        String path_test_file="test_sematicException_files/UnaryOp/Test_NotOp3";
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
            if(e instanceof Eccezioni.NotOpError)
            {
                catturata=true;
                Logger log = Logger.getLogger(this.getClass().getName());
                log.info("\n Eccezione NotOpError Catturata");
            }
        }

        Assert.assertTrue(catturata);
    }

    /**
     * Test in cui eseguo il not ad una stringa
     * Viene lanciata l'eccezione NotOpError
     */
    @Test
    public void tc_NotOp4() throws FileNotFoundException {
        String path_test_file="test_sematicException_files/UnaryOp/Test_NotOp4";
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
            if(e instanceof Eccezioni.NotOpError)
            {
                catturata=true;
                Logger log = Logger.getLogger(this.getClass().getName());
                log.info("\n Eccezione NotOpError Catturata");
            }
        }

        Assert.assertTrue(catturata);
    }
}