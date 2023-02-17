package main.java.main;

public class SemanticTestFor {

    /**
    @Test
    public void testExceptionForDeclarationNotValid() throws FileNotFoundException {
        String path_test_file="test_sematicException_files/For/Test20";
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
            if(e instanceof Eccezioni.ForDeclarationNotValid)
            {
                catturata=true;
                Logger log = Logger.getLogger(this.getClass().getName());
                log.info("\n Eccezione ForDeclarationNotValid Catturata");
            }
        }

        Assert.assertTrue(catturata);
    }
     */



}