package main.java.main;

import main.java.nodi.ProgramOp;
import main.java.parser;
import main.java.visitors.semanticVisitor.Eccezioni;
import main.java.visitors.semanticVisitor.SemanticVisitor1;
import main.java.visitors.semanticVisitor.SemanticVisitor2;
import org.junit.Assert;
import org.junit.Test;

import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.logging.Logger;

public class SemanticTestBooleanOp {

    /**
     * Test in cui viene eseguito l'and tra integer e bool.
     * Viene lanciata l'eccezione BooleanOpError.
     */
    @Test
    public void tc_booleanOp1() throws FileNotFoundException {
        String path_test_file="test_sematicException_files/BooleanOp/Test_BooleanOp1";
        String separator = File.separator;
        File input = new File(
                System.getProperty("user.dir") +separator+"test_files"+ separator+ path_test_file);
        parser p = new parser(new main.java.Lexer(new FileReader(input)));

        boolean catturata=false;
        try {
            DefaultMutableTreeNode root = (DefaultMutableTreeNode) p.parse().value;

            //Genero le symbol table
            ((ProgramOp) root).accept(new SemanticVisitor1());
            ((ProgramOp) root).accept(new SemanticVisitor2());
        } catch (Exception e) {
            if(e instanceof Eccezioni.BooleanOpError)
            {
                catturata=true;
                Logger log = Logger.getLogger(this.getClass().getName());
                log.info("\n Eccezione BooleanOpError Catturata");
            }
        }

        Assert.assertTrue(catturata);
    }

    /**
     * Test in cui viene eseguito l'and tra float e bool.
     * Viene lanciata l'eccezione BooleanOpError Catturata
     */
    @Test
    public void tc_booleanOp2() throws FileNotFoundException {
        String path_test_file="test_sematicException_files/BooleanOp/Test_BooleanOp2";
        String separator = File.separator;
        File input = new File(
                System.getProperty("user.dir") +separator+"test_files"+ separator+ path_test_file);
        parser p = new parser(new main.java.Lexer(new FileReader(input)));

        boolean catturata=false;
        try {
            DefaultMutableTreeNode root = (DefaultMutableTreeNode) p.parse().value;

            //Genero le symbol table
            ((ProgramOp) root).accept(new SemanticVisitor1());
            ((ProgramOp) root).accept(new SemanticVisitor2());
        } catch (Exception e) {
            if(e instanceof Eccezioni.BooleanOpError)
            {
                catturata=true;
                Logger log = Logger.getLogger(this.getClass().getName());
                log.info("\n Eccezione BooleanOpError Catturata");
            }
        }

        Assert.assertTrue(catturata);
    }

    /**
     * Test in cui viene eseguito l'and tra char e boolean.
     * Viene lanciata l'eccezione BooleanOpError
     */
    @Test
    public void tc_booleanOp3() throws FileNotFoundException {
        String path_test_file="test_sematicException_files/BooleanOp/Test_BooleanOp3";
        String separator = File.separator;
        File input = new File(
                System.getProperty("user.dir") +separator+"test_files"+ separator+ path_test_file);
        parser p = new parser(new main.java.Lexer(new FileReader(input)));

        boolean catturata=false;
        try {
            DefaultMutableTreeNode root = (DefaultMutableTreeNode) p.parse().value;

            //Genero le symbol table
            ((ProgramOp) root).accept(new SemanticVisitor1());
            ((ProgramOp) root).accept(new SemanticVisitor2());
        } catch (Exception e) {
            if(e instanceof Eccezioni.BooleanOpError)
            {
                catturata=true;
                Logger log = Logger.getLogger(this.getClass().getName());
                log.info("\n Eccezione BooleanOpError Catturata");
            }
        }

        Assert.assertTrue(catturata);
    }

    /**
     * Test in cui viene eseguito l'and tra string e boolean.
     * Viene lanciata l'eccezione BooleanOpError
     */
    @Test
    public void tc_booleanOp4() throws FileNotFoundException {
        String path_test_file="test_sematicException_files/BooleanOp/Test_BooleanOp4";
        String separator = File.separator;
        File input = new File(
                System.getProperty("user.dir") +separator+"test_files"+ separator+ path_test_file);
        parser p = new parser(new main.java.Lexer(new FileReader(input)));

        boolean catturata=false;
        try {
            DefaultMutableTreeNode root = (DefaultMutableTreeNode) p.parse().value;

            //Genero le symbol table
            ((ProgramOp) root).accept(new SemanticVisitor1());
            ((ProgramOp) root).accept(new SemanticVisitor2());
        } catch (Exception e) {
            if(e instanceof Eccezioni.BooleanOpError)
            {
                catturata=true;
                Logger log = Logger.getLogger(this.getClass().getName());
                log.info("\n Eccezione BooleanOpError Catturata");
            }
        }

        Assert.assertTrue(catturata);
    }
}