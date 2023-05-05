package compiler.nodi.expr;

import compiler.visitors.Visitable;
import compiler.visitors.Visitor;

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

    @Override
    public Object accept(Visitor v) throws Exception {
        return (v.visit(this));
    }

    @Override
    public String toString() {return super.toString();}

}