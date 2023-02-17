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

public class SemanticTestAssegnazione {

    /**
     * Test in eseguo un assegnazione con un identificatore non dichiarato.
     * Viene lanciata l'eccezione NoDeclaration Error
     */
    @Test
    public void tc_assOp1() throws FileNotFoundException {
        String path_test_file="test_sematicException_files/Assegnazioni/Test_AssOp3";
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
            if(e instanceof Eccezioni.NoDeclarationError)
            {
                catturata=true;
                Logger log = Logger.getLogger(this.getClass().getName());
                log.info("\n Eccezione NoDeclarationError Catturata");
            }
        }

        Assert.assertTrue(catturata);
    }

    /**
     * Test di un assegnazione in cui il numero d'identificatori non coincide
     * col numero di espressioni.
     * Viene lanciata l'eccezione AssignError Error
     */
    @Test
    public void tc_assOp2() throws FileNotFoundException {
        String path_test_file="test_sematicException_files/Assegnazioni/Test_AssOp1";
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
            if(e instanceof Eccezioni.AssignError)
            {
                catturata=true;
                Logger log = Logger.getLogger(this.getClass().getName());
                log.info("\n Eccezione AssignError Catturata");
            }
        }

        Assert.assertTrue(catturata);
    }


    /**
     * Test di un assegnazione in cui il tipo degli identificatori non coincide
     * col numero di espressioni.
     * Viene lanciata l'eccezione AssignError Error
     */
    @Test
    public void tc_assOp3() throws FileNotFoundException {
        String path_test_file="test_sematicException_files/Assegnazioni/Test_AssOp2";
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

            if(e instanceof Eccezioni.TypeAssignError)
            {
                catturata=true;
                Logger log = Logger.getLogger(this.getClass().getName());
                log.info("\n Eccezione TypeAssignError Catturata");
            }
        }

        Assert.assertTrue(catturata);
    }


}