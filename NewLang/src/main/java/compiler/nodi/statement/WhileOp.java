package compiler.nodi.statement;


import compiler.nodi.BodyOp;
import compiler.nodi.expr.Expr;
import compiler.symbolTable.SymbolTable;
import compiler.visitors.Visitable;
import compiler.visitors.Visitor;

public class WhileOp extends Statement implements Visitable {


    private Expr expr;
    private BodyOp body;

    private SymbolTable symbolTable;

    public WhileOp(Expr expr, BodyOp body){
        super("WhileOp");
        super.add(expr);
        super.add(body);

        this.expr=expr;
        this.body=body;
    }

    public Expr getExpr() {
        return expr;
    }

    public BodyOp getBody() {
        return body;
    }

    public void setSymbolTable(SymbolTable symbolTable) {this.symbolTable=symbolTable;}

    public SymbolTable getSymbolTable() {return this.symbolTable;}

    public Object accept(Visitor v) throws Exception {
        return (v.visit(this));
    }

    public String toString() {return super.toString();}
}
