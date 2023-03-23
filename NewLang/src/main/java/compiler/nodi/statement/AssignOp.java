package compiler.nodi.statement;

import compiler.nodi.expr.Expr;
import compiler.nodi.expr.Identifier;
import compiler.visitors.Visitable;
import compiler.visitors.Visitor;

import java.util.ArrayList;

public class AssignOp extends Statement implements Visitable  {

    private ArrayList<Identifier> listId;
    private ArrayList<Expr> listExpr;

    public AssignOp(ArrayList<Identifier> listId, ArrayList<Expr> listExpr){
        super("AssignOp");
        for(Identifier id: listId)
            super.add(id);
        for(Expr exp: listExpr)
            super.add(exp);

        this.listId=listId;
        this.listExpr=listExpr;
    }

    public ArrayList<Identifier> getListId() {
        return listId;
    }

    public ArrayList<Expr> getListExpr() {
        return listExpr;
    }

    public void addId(Identifier id){
        super.add(id);
        this.listId.add(id);
    }

    public void addsListId(ArrayList<Identifier> listId){
        for(Identifier id: listId)
            super.add(id);
        this.listId.addAll(listId);
    }

    public void addExpr(Expr exp){
        super.add(exp);
        this.listExpr.add(exp);
    }

    public void addsListExp(ArrayList<Expr> listExpr){
        for(Expr exp:listExpr)
            super.add(exp);
        this.listExpr.addAll(listExpr);
    }

    public String toString() {return super.toString();}

    @Override
    public Object accept(Visitor v) throws Exception {
        return v.visit(this);
    }
}
