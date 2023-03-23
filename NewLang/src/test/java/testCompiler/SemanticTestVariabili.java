package testCompiler;

import compiler.nodi.ProgramOp;
import compiler.Lexer;
import compiler.parser;
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


public class SemanticTestVariabili {


    /**
     * Test dichiarazione multipla di una variabile nello stesso scope.
     * Viene lanciata l'eccezione MultipleDeclaration.
     */
    @Test
    public void tc_var1() throws FileNotFoundException {
        String path_test_file="test_sematicException_files/Variabili/Test_Var1";
        String separator = File.separator;
        File input = new File(
                System.getProperty("user.dir") +separator+"test_files"+ separator+ path_test_file);
        parser p = new parser(new Lexer(new FileReader(input)));

        boolean catturata=false;

        try {
            DefaultMutableTreeNode root = (DefaultMutableTreeNode) p.parse().value;

            ((ProgramOp) root).accept(new SemanticVisitor1());
            ((ProgramOp) root).accept(new SemanticVisitor2());
        } catch (Exception e) {
            if(e instanceof Eccezioni.MultipleDeclaration)
            {
                catturata=true;
                Logger log = Logger.getLogger(this.getClass().getName());
                log.info("\n Eccezione MultipleDeclaration Catturata");
            }
        }

        Assert.assertTrue(catturata);
    }

    /**
     * Test in cui viene dichiarato un integer inizializzato con una stringa.
     * Viene lanciata l'eccezione InizializationError
     */
    @Test
    public void tc_var2() throws FileNotFoundException {
        String path_test_file="test_sematicException_files/Variabili/Test_Var2";
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
            if (e instanceof Eccezioni.InizializationError)
            {
                catturata=true;
                Logger log = Logger.getLogger(this.getClass().getName());
                log.info("\n Eccezione InizializationError Catturata");
            }
        }
        Assert.assertTrue(catturata);

    }

    /**
     * Test in cui viene dichiarato ua variabile assegnandogli una variabile non
     * dichiarata nel programma.
     * Viene lanciata l'eccezione NoDeclarationError
     */
    @Test
    public void tc_var3() throws FileNotFoundException {
        String path_test_file="test_sematicException_files/Variabili/Test_Var3";
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
            if(e instanceof Eccezioni.NoDeclarationError)
            {
                catturata=true;
                Logger log = Logger.getLogger(this.getClass().getName());
                log.info("\n Eccezione NoDeclarationError Catturata");
            }
        }

        Assert.assertTrue(catturata);

    }


}