package compiler;

import compiler.nodi.ProgramOp;

import compiler.visitors.TranslatorVisitor;
import compiler.visitors.semanticVisitor.SemanticVisitor1;
import compiler.visitors.semanticVisitor.SemanticVisitor2;

import javax.swing.tree.DefaultMutableTreeNode;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NewLang {

    private BufferedReader r;

    public File compile(byte[] fileContent,String fileName) throws IOException {

        parser p = new parser(new Lexer(new InputStreamReader(new ByteArrayInputStream(fileContent), StandardCharsets.UTF_8)));
        Logger logger = Logger.getLogger(NewLang.class.getName());
        try {
            DefaultMutableTreeNode root = (DefaultMutableTreeNode) p.parse().value;

            ((ProgramOp) root).accept(new SemanticVisitor1());
            ((ProgramOp) root).accept(new SemanticVisitor2());
            ((ProgramOp) root).accept(new TranslatorVisitor(fileName+".c"));


            ProcessBuilder builder = new ProcessBuilder("/bin/bash", "-c", "cd "+
                                                        System.getProperty("user.dir")+
                                                        "&& cd test_files && cd c_out && gcc " +
                                                        fileName +
                                                        ".c -lm -o" +
                                                        fileName +".out");
            builder.redirectErrorStream(true);
            Process process = builder.start();
            r = new BufferedReader(new InputStreamReader(process.getInputStream(),StandardCharsets.UTF_8));
            String line;

            while (true) {
                line = r.readLine();
                if (line == null) {
                    break;
                }
                logger.log(Level.ALL,line);
            }

            return new File("test_files" + File.separator + "c_out" + File.separator + fileName +".out");

        } catch (Exception e) {
            logger.log(Level.WARNING, e.getMessage());
            return null;
        } finally {
            r.close();
        }

    }

}