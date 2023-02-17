package main.java.nodi.statement;

import main.java.nodi.expr.Expr;
import main.java.visitors.Visitable;
import main.java.visitors.Visitor;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.ArrayList;

public class WriteOp extends Statement implements Visitable {

    private String type;
    private ArrayList<Expr> listExpr;

    public WriteOp(String type,ArrayList<Expr> listExpr){
        super("WriteOp");
        super.add(new DefaultMutableTreeNode(type));
        for (Expr exp:listExpr)
            super.add(exp);

        this.type=type;
        this.listExpr=listExpr;

    }

    public String getType() {
        return type;
    }

    public ArrayList<Expr> getListExpr() {
        return listExpr;
    }

    public void addExp(Expr exp) {
        super.add(exp);
        this.listExpr.add(exp);
    }

    public void addsListExpr(ArrayList<Expr> listExpr){
        for(Expr exp:listExpr)
            super.add(exp);
        this.listExpr.addAll(listExpr);
    }

    public Object accept(Visitor v) throws Exception {
        return (v.visit(this));
    }

    public String toString() {return super.toString();}
}
