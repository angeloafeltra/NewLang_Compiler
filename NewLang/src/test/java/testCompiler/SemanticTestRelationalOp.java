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

public class SemanticTestRelationalOp {


    /**
     * Test in cui viene eseguita un operazione relazionale tra integer e char.
     * Lancio l'eccezione RelationalOpError
     */
    @Test
    public void tc_RelOp1() throws FileNotFoundException {
        String path_test_file="test_sematicException_files/RelationalOp/Test_RelOp1";
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
            if(e instanceof Eccezioni.RelationalOpError)
            {
                catturata=true;
                Logger log = Logger.getLogger(this.getClass().getName());
                log.info("\n Eccezione RelationalOpError Catturata");
            }
        }

        Assert.assertTrue(catturata);
    }

    /**
     * Test in cui viene eseguita un operazione relazionale tra integer e string.
     * Lancio l'eccezione RelationalOpError
     */
    @Test
    public void tc_RelOp2() throws FileNotFoundException {
        String path_test_file="test_sematicException_files/RelationalOp/Test_RelOp2";
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
            if(e instanceof Eccezioni.RelationalOpError)
            {
                catturata=true;
                Logger log = Logger.getLogger(this.getClass().getName());
                log.info("\n Eccezione RelationalOpError Catturata");
            }
        }

        Assert.assertTrue(catturata);
    }

    /**
     * Test in cui viene eseguita un operazione relazionale tra integer e boolean.
     * Lancio l'eccezione RelationalOpError
     */
    @Test
    public void tc_RelOp3() throws FileNotFoundException {
        String path_test_file="test_sematicException_files/RelationalOp/Test_RelOp3";
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
            if(e instanceof Eccezioni.RelationalOpError)
            {
                catturata=true;
                Logger log = Logger.getLogger(this.getClass().getName());
                log.info("\n Eccezione RelationalOpError Catturata");
            }
        }

        Assert.assertTrue(catturata);
    }

    /**
     * Test in cui viene eseguita un operazione relazionale tra string e char.
     * Lancio l'eccezione RelationalOpError
     */
    @Test
    public void tc_RelOp4() throws FileNotFoundException {
        String path_test_file="test_sematicException_files/RelationalOp/Test_RelOp4";
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
            if(e instanceof Eccezioni.RelationalOpError)
            {
                catturata=true;
                Logger log = Logger.getLogger(this.getClass().getName());
                log.info("\n Eccezione RelationalOpError Catturata");
            }
        }

        Assert.assertTrue(catturata);
    }


    /**
     * Test in cui viene eseguita un operazione relazionale tra char e float.
     * Lancio l'eccezione RelationalOpError
     */
    @Test
    public void tc_RelOp5() throws FileNotFoundException {
        String path_test_file = "test_sematicException_files/RelationalOp/Test_RelOp5";
        String separator = File.separator;
        File input = new File(
                System.getProperty("user.dir") +separator+"test_files"+ separator+ path_test_file);
        parser p = new parser(new Lexer(new FileReader(input)));

        boolean catturata = false;
        try {
            DefaultMutableTreeNode root = (DefaultMutableTreeNode) p.parse().value;

            //Genero le symbol table
            ((ProgramOp) root).accept(new SemanticVisitor1());
            ((ProgramOp) root).accept(new SemanticVisitor2());
        } catch (Exception e) {
            if (e instanceof Eccezioni.RelationalOpError) {
                catturata = true;
                Logger log = Logger.getLogger(this.getClass().getName());
                log.info("\n Eccezione RelationalOpError Catturata");
            }
        }
    }

    /**
     * Test in cui viene eseguita un operazione relazionale tra char e boolean.
     * Lancio l'eccezione RelationalOpError
     */
    @Test
    public void tc_RelOp6() throws FileNotFoundException {
        String path_test_file="test_sematicException_files/RelationalOp/Test_RelOp6";
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
            if(e instanceof Eccezioni.RelationalOpError)
            {
                catturata=true;
                Logger log = Logger.getLogger(this.getClass().getName());
                log.info("\n Eccezione RelationalOpError Catturata");
            }
        }

        Assert.assertTrue(catturata);
    }


    /**
     * Test in cui viene eseguita un operazione relazionale tra boolean e boolean.
     * Lancio l'eccezione RelationalOpError
     */
    @Test
    public void tc_RelOp7() throws FileNotFoundException {
        String path_test_file="test_sematicException_files/RelationalOp/Test_RelOp7";
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
            if(e instanceof Eccezioni.RelationalOpError)
            {
                catturata=true;
                Logger log = Logger.getLogger(this.getClass().getName());
                log.info("\n Eccezione RelationalOpError Catturata");
            }
        }

        Assert.assertTrue(catturata);
    }

}