package main.java.main;

import main.java.parser;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.io.*;

public class TesterParser {
    public static void main(String[] args) throws FileNotFoundException {
        JTree tree;
        String separator = File.separator;
        File input = new File(
                System.getProperty("user.dir") +separator+"test_files"+ separator+ args[0]);
        parser p = new parser(new main.java.Lexer(new FileReader(input)));
        try {
            //p.debug_parse();

            DefaultMutableTreeNode root = (DefaultMutableTreeNode) p.parse().value;
            tree=new JTree(root);

            JFrame framePannello=new JFrame();
            framePannello.setSize(400, 400);
            JScrollPane treeView = new JScrollPane(tree);
            framePannello.add(treeView);
            framePannello.setVisible(true);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}