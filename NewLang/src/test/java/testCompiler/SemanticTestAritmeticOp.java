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

public class
SemanticTestAritmeticOp {

    /**
     * Test in cui sommo una stringa con un intero.
     * Viene lanciata l'eccezione ArithmeticOpError
     */
    @Test
    public void tc_aritOp1() throws FileNotFoundException {
        String path_test_file="test_sematicException_files/ArithmeticOp/Test_AritOp1";
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
                Logger log = Logger.getLogger(this.getClass().getName());
                log.info("\n Eccezione ArithmeticOpError Catturata");
            }
        }

        Assert.assertTrue(catturata);
    }

    /**
     * Test in cui sommo una stringa con un float.
     * Viene lanciata l'eccezione ArithmeticOpError
     */
    @Test
    public void tc_aritOp2() throws FileNotFoundException {
        String path_test_file="test_sematicException_files/ArithmeticOp/Test_AritOp2";
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
                Logger log = Logger.getLogger(this.getClass().getName());
                log.info("\n Eccezione ArithmeticOpError Catturata");
            }
        }

        Assert.assertTrue(catturata);
    }

    /**
     * Test in cui sommo un char ed un integer.
     * Viene lanciata l'eccezione ArithmeticOpError
     */
    @Test
    public void tc_aritOp3() throws FileNotFoundException {
        String path_test_file="test_sematicException_files/ArithmeticOp/Test_AritOp3";
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
                Logger log = Logger.getLogger(this.getClass().getName());
                log.info("\n Eccezione ArithmeticOpError Catturata");
            }
        }

        Assert.assertTrue(catturata);
    }

    /**
     * Test in cui viene sommato un char ed un float.
     * Viene lanciata l'eccezione ArithmeticOpError
     */
    @Test
    public void tc_aritOp4() throws FileNotFoundException {
        String path_test_file="test_sematicException_files/ArithmeticOp/Test_AritOp4";
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
                Logger log = Logger.getLogger(this.getClass().getName());
                log.info("\n Eccezione ArithmeticOpError Catturata");
            }
        }

        Assert.assertTrue(catturata);
    }

    /**
     * Test in cui viene sommato un intero con un booleano.
     * Viene lanciata l'eccezione ArithmeticOpError
     */
    @Test
    public void tc_aritOp5() throws FileNotFoundException {
        String path_test_file="test_sematicException_files/ArithmeticOp/Test_AritOp5";
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
                Logger log = Logger.getLogger(this.getClass().getName());
                log.info("\n Eccezione ArithmeticOpError Catturata");
            }
        }

        Assert.assertTrue(catturata);
    }

    /**
     * Test in cui viene sommato un char ed un booleano.
     * Viene lanciata l'eccezione ArithmeticOpError
     * @throws FileNotFoundException
     */
    @Test
    public void tc_aritOp6() throws FileNotFoundException {
        String path_test_file="test_sematicException_files/ArithmeticOp/Test_AritOp6";
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
                Logger log = Logger.getLogger(this.getClass().getName());
                log.info("\n Eccezione ArithmeticOpError Catturata");
            }
        }

        Assert.assertTrue(catturata);
    }
}