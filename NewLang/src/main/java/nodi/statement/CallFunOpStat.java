package main.java.nodi.statement;

import main.java.nodi.expr.Expr;
import main.java.nodi.expr.Identifier;
import main.java.visitors.Visitable;
import main.java.visitors.Visitor;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.ArrayList;

public class CallFunOpStat extends Statement implements Visitable {

    private Identifier identifier;
    private ArrayList<Expr> listExpr;

    public CallFunOpStat(Identifier identifier){
        super("CallFunOpStmt");
        super.add(identifier);

        this.identifier=identifier;
        this.listExpr=null;
    }

    public CallFunOpStat(Identifier identifier, ArrayList<Expr> listExpr){
        super("CallFunOpStmt");
        super.add(identifier);
        for(Expr expr:listExpr){
            super.add(expr);
            String mode=expr.getMode();
            super.add(new DefaultMutableTreeNode(mode));
        }

        this.identifier=identifier;
        this.listExpr=listExpr;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public ArrayList<Expr> getListExpr() {return listExpr;}

    public void addExpr(Expr expr){
        super.add(expr);
        super.add(new DefaultMutableTreeNode(expr.getMode()));
        this.listExpr.add(expr);
    }

    public void addsListExpr(ArrayList<Expr> listExpr){
        for(Expr expr: listExpr){
            super.add(expr);
            super.add(new DefaultMutableTreeNode(expr.getMode()));
        }
        this.listExpr.addAll(listExpr);
    }

    public String toString() {return super.toString();}

    @Override
    public Object accept(Visitor v) throws Exception {
        return v.visit(this);
    }
}
