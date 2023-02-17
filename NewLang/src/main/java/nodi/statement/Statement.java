package main.java.nodi.statement;

import main.java.visitors.Visitable;
import main.java.visitors.Visitor;

import javax.swing.tree.DefaultMutableTreeNode;

public class Statement extends DefaultMutableTreeNode implements Visitable {

    public Statement(String nodeName){
        super(nodeName);
    }

    public String toString() { return super.toString(); }


    @Override
    public Object accept(Visitor v) throws Exception {
        return null;
    }
}
