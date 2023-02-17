package main.java.nodi.statement;


import main.java.nodi.BodyOp;
import main.java.nodi.expr.Expr;
import main.java.symbolTable.SymbolTable;
import main.java.visitors.Visitable;
import main.java.visitors.Visitor;

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
