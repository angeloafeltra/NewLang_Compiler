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

public class SemanticTestFunzioni {


    /**
     * Test dichiarazione multipla di una funzione.
     * Viene lanciata l'eccezione MultipleDeclaration
     */
    @Test
    public void tc_decFun1() throws FileNotFoundException {
        String path_test_file="test_sematicException_files/Funzioni/Test_Fun1";
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
            if(e instanceof Eccezioni.MultipleDeclaration) {
                catturata = true;
                Logger log = Logger.getLogger(this.getClass().getName());
                log.info("\n Eccezione MultipleDeclaration Catturata");
            }
        }

        Assert.assertTrue(catturata);

    }

    /**
     * Test in cui una funzione di tipo float restituisce una stringa.
     * Viene lanciata l'eccezione ReturnError
     */
    @Test
    public void tc_decFun2() throws FileNotFoundException {
        String path_test_file="test_sematicException_files/Funzioni/Test_Fun2";
        String separator = File.separator;
        File input = new File(
                System.getProperty("user.dir") +separator+"test_files"+ separator+ path_test_file);
        parser p = new parser(new Lexer(new FileReader(input)));
        boolean cattura=false;
        try {
            DefaultMutableTreeNode root = (DefaultMutableTreeNode) p.parse().value;
            //Genero le symbol table
            ((ProgramOp) root).accept(new SemanticVisitor1());
            ((ProgramOp) root).accept(new SemanticVisitor2());
        } catch (Exception e) {
            if(e instanceof Eccezioni.ReturnError)
            {
                cattura=true;
                Logger log = Logger.getLogger(this.getClass().getName());
                log.info("\n Eccezione ReturnError Catturata");
            }
        }

        Assert.assertTrue(cattura);
    }

    /**
     * Test in cui una funzione di tipo void ritorna un float.
     * Viene lanciata l'eccezione ReturnError.
     */
    @Test
    public void tc_decFun3() throws FileNotFoundException {
        String path_test_file="test_sematicException_files/Funzioni/Test_Fun3";
        String separator = File.separator;
        File input = new File(
                System.getProperty("user.dir") +separator+"test_files"+ separator+ path_test_file);
        parser p = new parser(new Lexer(new FileReader(input)));
        boolean cattura=false;
        try {
            DefaultMutableTreeNode root = (DefaultMutableTreeNode) p.parse().value;
            //Genero le symbol table
            ((ProgramOp) root).accept(new SemanticVisitor1());
            ((ProgramOp) root).accept(new SemanticVisitor2());
        } catch (Exception e) {
            if(e instanceof Eccezioni.ReturnError)
            {
                cattura=true;
                Logger log = Logger.getLogger(this.getClass().getName());
                log.info("\n Eccezione ReturnError Catturata");
            }
        }

        Assert.assertTrue(cattura);

    }

    /**
     * Test in cui una funzione di tipo float non ritorna nulla.
     * Viene lanciata l'eccezione ReturnError
     */
    @Test
    public void tc_decFun4() throws FileNotFoundException {
        String path_test_file="test_sematicException_files/Funzioni/Test_Fun4";
        String separator = File.separator;
        File input = new File(
                System.getProperty("user.dir") +separator+"test_files"+ separator+ path_test_file);
        parser p = new parser(new Lexer(new FileReader(input)));
        boolean cattura=false;
        try {
            DefaultMutableTreeNode root = (DefaultMutableTreeNode) p.parse().value;
            //Genero le symbol table
            ((ProgramOp) root).accept(new SemanticVisitor1());
            ((ProgramOp) root).accept(new SemanticVisitor2());
        } catch (Exception e) {
            if(e instanceof Eccezioni.ReturnError)
            {
                cattura=true;
                Logger log = Logger.getLogger(this.getClass().getName());
                log.info("\n Eccezione ReturnError Catturata");
            }
        }

        Assert.assertTrue(cattura);

    }

    /**
     * Test in cui una funzione di tipo float non possiede un return.
     * Viene lanciata l'eccezione ReturnError
     */
    @Test
    public void tc_decFun5() throws FileNotFoundException {
        String path_test_file="test_sematicException_files/Funzioni/Test_Fun5";
        String separator = File.separator;
        File input = new File(
                System.getProperty("user.dir") +separator+"test_files"+ separator+ path_test_file);
        parser p = new parser(new Lexer(new FileReader(input)));
        boolean cattura=false;
        try {
            DefaultMutableTreeNode root = (DefaultMutableTreeNode) p.parse().value;
            //Genero le symbol table
            ((ProgramOp) root).accept(new SemanticVisitor1());
            ((ProgramOp) root).accept(new SemanticVisitor2());
        } catch (Exception e) {
            if(e instanceof Eccezioni.ReturnError)
            {
                cattura=true;
                Logger log = Logger.getLogger(this.getClass().getName());
                log.info("\n Eccezione ReturnError Catturata");
            }
        }

        Assert.assertTrue(cattura);

    }

    /**
     * Test in cui viene dichiarata una variabile con lo stesso nome della funzione che la contiene.
     * Viene lanciata l'eccezione MultipleDeclaration.
     */
    @Test
    public void tc_decFun6() throws FileNotFoundException {
        String path_test_file="test_sematicException_files/Funzioni/Test_Fun6";
        String separator = File.separator;
        File input = new File(
                System.getProperty("user.dir") +separator+"test_files"+ separator+ path_test_file);
        parser p = new parser(new Lexer(new FileReader(input)));
        boolean cattura=false;
        try {
            DefaultMutableTreeNode root = (DefaultMutableTreeNode) p.parse().value;
            //Genero le symbol table
            ((ProgramOp) root).accept(new SemanticVisitor1());
            ((ProgramOp) root).accept(new SemanticVisitor2());
        } catch (Exception e) {
            if(e instanceof Eccezioni.MultipleDeclaration)
            {
                cattura=true;
                Logger log = Logger.getLogger(this.getClass().getName());
                log.info("\n Eccezione MultipleDeclaration Catturata");
            }
        }

        Assert.assertTrue(cattura);

    }


    /**
     * Test di una funzione che dichiara due volte lo stesso parametro.
     * Viene lanciata l'eccezione MultipleDeclaration
     */
    @Test
    public void tc_decFun7() throws FileNotFoundException {
        String path_test_file="test_sematicException_files/Funzioni/Test_Fun7";
        String separator = File.separator;
        File input = new File(
                System.getProperty("user.dir") +separator+"test_files"+ separator+ path_test_file);
        parser p = new parser(new Lexer(new FileReader(input)));
        boolean cattura=false;
        try {
            DefaultMutableTreeNode root = (DefaultMutableTreeNode) p.parse().value;
            //Genero le symbol table
            ((ProgramOp) root).accept(new SemanticVisitor1());
            ((ProgramOp) root).accept(new SemanticVisitor2());
        } catch (Exception e) {
            if(e instanceof Eccezioni.MultipleDeclaration)
            {
                cattura=true;
                Logger log = Logger.getLogger(this.getClass().getName());
                log.info("\n Eccezione MultipleDeclaration Catturata");
            }
        }
        Assert.assertTrue(cattura);

    }

    /**
     * Test di una funzione che possiede un parametro e una variabile nel body
     * con lo stesso identificativo.
     * Viene lanciata l'eccezione MultipleDeclaration.
     */
    @Test
    public void tc_decFun8() throws FileNotFoundException {
        String path_test_file="test_sematicException_files/Funzioni/Test_Fun8";
        String separator = File.separator;
        File input = new File(
                System.getProperty("user.dir") +separator+"test_files"+ separator+ path_test_file);
        parser p = new parser(new Lexer(new FileReader(input)));
        boolean cattura=false;
        try {
            DefaultMutableTreeNode root = (DefaultMutableTreeNode) p.parse().value;
            //Genero le symbol table
            ((ProgramOp) root).accept(new SemanticVisitor1());
            ((ProgramOp) root).accept(new SemanticVisitor2());
        } catch (Exception e) {
            if(e instanceof Eccezioni.MultipleDeclaration)
            {
                cattura=true;
                Logger log = Logger.getLogger(this.getClass().getName());
                log.info("\n Eccezione MultipleDeclaration Catturata");
            }
        }
        Assert.assertTrue(cattura);

    }


}