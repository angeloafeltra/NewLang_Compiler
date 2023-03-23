package compiler;

import compiler.nodi.ProgramOp;

import compiler.visitors.TranslatorVisitor;
import compiler.visitors.semanticVisitor.SemanticVisitor1;
import compiler.visitors.semanticVisitor.SemanticVisitor2;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.io.*;

public class NewLang {
    public static void main(String[] args) throws FileNotFoundException {
        JTree tree;
        String separator = File.separator;
        File input = new File(
                System.getProperty("user.dir") +separator+"test_files"+ separator+ args[0]);
        parser p = new parser(new Lexer(new FileReader(input)));
        try {
            DefaultMutableTreeNode root = (DefaultMutableTreeNode) p.parse().value;

            ((ProgramOp) root).accept(new SemanticVisitor1());
            ((ProgramOp) root).accept(new SemanticVisitor2());
            ((ProgramOp) root).accept(new TranslatorVisitor());


            ProcessBuilder builder = new ProcessBuilder("/bin/bash", "-c", "cd "+System.getProperty("user.dir")+"&& cd test_files && cd c_out && gcc " + TranslatorVisitor.FILE_NAME);
            builder.redirectErrorStream(true);
            Process process = builder.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while (true) {
                line = r.readLine();
                if (line == null) {
                    break;
                }
                System.out.println(line);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}