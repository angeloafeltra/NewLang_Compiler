package compiler.nodi.expr;

import compiler.visitors.Visitable;
import compiler.visitors.Visitor;

public class IdInitOp extends Expr implements Visitable {

    private Identifier id;
    private Expr expr;

    public IdInitOp(Identifier id, Expr expr){
        super("IdInitOp");

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
