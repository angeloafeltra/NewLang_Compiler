package compiler.nodi.statement;

import compiler.nodi.expr.Expr;
import compiler.visitors.Visitable;
import compiler.visitors.Visitor;

public class ReturnOp extends Statement implements Visitable {

    private Expr expr;

    public ReturnOp(){
        super("ReturnOp");
        this.expr=null;
    }

    public ReturnOp(Expr expr){
        super("ReturnOp");
        super.add(expr);
        this.expr=expr;
    }

    public Expr getExpr() {
        return expr;
    }

    public String toString() {return super.toString();}

    @Override
    public Object accept(Visitor v) throws Exception {
        return v.visit(this);
    }
}
