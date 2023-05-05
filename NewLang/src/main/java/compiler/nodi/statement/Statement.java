package compiler.nodi.statement;

import compiler.visitors.Visitable;
import compiler.visitors.Visitor;

import javax.swing.tree.DefaultMutableTreeNode;

public class Statement extends DefaultMutableTreeNode implements Visitable {

    public Statement(String nodeName){
        super(nodeName);
    }

    @Override
    public String toString() { return super.toString(); }


    @Override
    public Object accept(Visitor v) throws Exception {
        return null;
    }
}
