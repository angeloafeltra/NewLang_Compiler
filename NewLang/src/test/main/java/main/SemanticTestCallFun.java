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

public class SemanticTestCallFun {


    /**
     * Test in cui viene chiamata una funzione con un numero di parametri errato.
     * Viene lanciata l'eccezione CallFunNumParamError.
     */
    @Test
    public void tc_callFun1() throws FileNotFoundException {
        String path_test_file="test_sematicException_files/CallFun/Test_callFun1";
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
            if(e instanceof Eccezioni.CallFunNumParamError) {
                catturata = true;
                Logger log = Logger.getLogger(this.getClass().getName());
                log.info("\n Eccezione CallFunNumParamError Catturata");
            }
        }

        Assert.assertTrue(catturata);

    }

    /**
     * Test in cui viene chiamata una funzione passando parametri errati.
     * Viene lanciata l'eccezione CallFunTypeParamError
     */
    @Test
    public void t_callFun2() throws FileNotFoundException {
        String path_test_file="test_sematicException_files/CallFun/Test_callFun2";
        String separator = File.separator;
        File input = new File(
                System.getProperty("user.dir") +separator+"test_files"+ separator+ path_test_file);
        parser p = new parser(new main.java.Lexer(new FileReader(input)));
        boolean cattura=false;
        try {
            DefaultMutableTreeNode root = (DefaultMutableTreeNode) p.parse().value;
            //Genero le symbol table
            ((ProgramOp) root).accept(new SemanticVisitor1());
            ((ProgramOp) root).accept(new SemanticVisitor2());
        } catch (Exception e) {
            if(e instanceof Eccezioni.CallFunTypeParamError)
            {
                cattura=true;
                Logger log = Logger.getLogger(this.getClass().getName());
                log.info("\n Eccezione CallFunTypeParamError Catturata");
            }
        }

        Assert.assertTrue(cattura);
    }

    /**
     * Test in cui viene chiamata una funzione non dichiarata
     * Viene lanciata l'eccezione NoDeclarationError
     */
    @Test
    public void t_callFun3() throws FileNotFoundException {
        String path_test_file="test_sematicException_files/CallFun/Test_callFun3";
        String separator = File.separator;
        File input = new File(
                System.getProperty("user.dir") +separator+"test_files"+ separator+ path_test_file);
        parser p = new parser(new main.java.Lexer(new FileReader(input)));
        boolean cattura=false;
        try {
            DefaultMutableTreeNode root = (DefaultMutableTreeNode) p.parse().value;
            //Genero le symbol table
            ((ProgramOp) root).accept(new SemanticVisitor1());
            ((ProgramOp) root).accept(new SemanticVisitor2());
        } catch (Exception e) {
            if(e instanceof Eccezioni.NoDeclarationError)
            {
                cattura=true;
                Logger log = Logger.getLogger(this.getClass().getName());
                log.info("\n Eccezione NoDeclarationError Catturata");
            }
        }

        Assert.assertTrue(cattura);

    }

}