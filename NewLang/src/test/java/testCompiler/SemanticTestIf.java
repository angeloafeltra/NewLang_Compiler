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

public class SemanticTestIf {

    /**
     * Test di un if che ha come condizione una somma aritmetica.
     * Lancio l'eccezione ConditionNotValid
     */
    @Test
    public void tc_if1() throws FileNotFoundException {
        String path_test_file="test_sematicException_files/if/Test_If1";
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
            if(e instanceof Eccezioni.ConditionNotValid)
            {
                catturata=true;
                //Logger log = Logger.getLogger(this.getClass().getName());
                //log.info("\n Eccezione ConditionNotValid Catturata");
            }

        }

        Assert.assertTrue(catturata);
    }

    /**
     * Test di un if che ha nella condizione una variabile non dichiarata.
     * Lancio l'eccezione NoDeclarationError
     */
    @Test
    public void tc1_if2() throws FileNotFoundException {
        String path_test_file="test_sematicException_files/if/Test_If2";
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
                //Logger log = Logger.getLogger(this.getClass().getName());
                //log.info("\n Eccezione NoDeclarationError Catturata");
            }
        }

        Assert.assertTrue(catturata);
    }


}