package compiler.nodi.statement;

import compiler.nodi.BodyOp;
import compiler.nodi.expr.Expr;
import compiler.symbolTable.SymbolTable;
import compiler.visitors.Visitable;
import compiler.visitors.Visitor;

public class IfStatOp extends Statement implements Visitable {

    private Expr expr;
    private BodyOp bodyThen;
    private BodyOp bodyElse;

    private SymbolTable symbolTableThen;

    private SymbolTable symbolTableElse;

    public IfStatOp(Expr expr, BodyOp bodyThen, BodyOp bodyElse){
        super("IfStatOp");
        super.add(expr);
        super.add(bodyThen);
        super.add(bodyElse);

        this.expr=expr;
        this.bodyThen=bodyThen;
        this.bodyElse=bodyElse;
    }

    public IfStatOp(Expr expr, BodyOp bodyThen){
        super("IfStatOp");
        super.add(expr);
        super.add(bodyThen);

        this.expr=expr;
        this.bodyElse = null;
        this.bodyThen = bodyThen;
    }

    public Expr getExpr() {
        return expr;
    }

    public BodyOp getBodyThen() {
        return bodyThen;
    }

    public BodyOp getBodyElse() {
        return bodyElse;
    }

    public SymbolTable getSymbolTableThen() {return this.symbolTableThen;}

    public void setSymbolTableThen(SymbolTable symbolTable) {this.symbolTableThen=symbolTable;}

    public SymbolTable getSymbolTableElse() {return this.symbolTableElse;}

    public void setSymbolTableElse(SymbolTable symbolTable) {this.symbolTableElse=symbolTable;}

    public String toString() {return super.toString();}

    @Override
    public Object accept(Visitor v) throws Exception {
        return v.visit(this);
    }
}
