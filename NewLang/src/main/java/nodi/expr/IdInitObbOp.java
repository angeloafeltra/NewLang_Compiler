package main.java.nodi.expr;

import main.java.visitors.Visitable;
import main.java.visitors.Visitor;

import java.util.ArrayList;

public class IdInitObbOp extends Expr implements Visitable {

    private Identifier id;
    private Expr expr;

    public IdInitObbOp(Identifier id, Expr expr){
        super("IdInitObbOp");

        super.add(id);
        super.add(expr);
        this.id=id;
        this.expr=expr;
    }

    public Identifier getId() {
        return id;
    }

    public Expr getExpr() {
        return expr;
    }

    public Object accept(Visitor v) throws Exception {
        return (v.visit(this));
    }

    public String toString() {return super.toString();}

}