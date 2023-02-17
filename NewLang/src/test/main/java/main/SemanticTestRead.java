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

public class SemanticTestRead {


    /**
     * Test di un operazione di read che utilizza una variabile non dichiarata.
     * Lancio l'eccezione NoDeclarationError.
     */
    @Test
    public void test_readOp1() throws FileNotFoundException {
        String path_test_file="test_sematicException_files/Read/Test_ReadOp1";
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


}