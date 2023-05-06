package compiler.nodi.statement;

import compiler.nodi.expr.Expr;
import compiler.nodi.expr.Identifier;
import compiler.visitors.Visitable;
import compiler.visitors.Visitor;

import java.util.ArrayList;
import java.util.List;

public class ReadOp extends Statement implements Visitable {

    private ArrayList<Identifier> listId;
    private Expr expr;

    public ReadOp(List<Identifier> listId, Expr expr){
        super("ReadOp");
        for (Identifier id:listId)
            super.add(id);

        super.add(expr);

        this.listId= (ArrayList<Identifier>) listId;
        this.expr=expr;
    }

    public ReadOp(List<Identifier> listId){
        super("ReadOp");
        for (Identifier id:listId)
            super.add(id);


        this.listId= (ArrayList<Identifier>) listId;
        this.expr=null;
    }

    public List<Identifier> getListId() { return this.listId; }
    public Expr getExpr() { return this.expr; }

    public void addId(Identifier id) {
        super.add(id);
        this.listId.add(id);
    }

    public void addsListId(List<Identifier> listId) {
        for (Identifier id:listId)
            super.add(id);
        this.listId.addAll(listId);
    }

    @Override
    public String toString() { return super.toString(); }

    @Override
    public Object accept(Visitor v) throws Exception {
        return v.visit(this);
    }
}
