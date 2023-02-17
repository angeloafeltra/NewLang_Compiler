package main.java.nodi.statement;

import main.java.nodi.BodyOp;
import main.java.nodi.VarDeclOp;
import main.java.nodi.expr.ConstOp;
import main.java.nodi.expr.IdInitOp;
import main.java.symbolTable.SymbolTable;
import main.java.visitors.Visitable;
import main.java.visitors.Visitor;

public class ForOp extends Statement implements Visitable {

    private IdInitOp id;
    private ConstOp cons;
    private BodyOp bodyOp;

    private SymbolTable symbolTable;

    public ForOp(IdInitOp id,ConstOp cons,BodyOp bodyOp){
        super("ForOp");
        super.add(id);
        super.add(cons);
        super.add(bodyOp);

        this.id=id;
        this.cons=cons;
        this.bodyOp=bodyOp;
    }

    public IdInitOp getId() {
        return id;
    }

    public ConstOp getCons() {
        return cons;
    }

    public BodyOp getBodyop() {
        return bodyOp;
    }

    public SymbolTable getSymbolTable() {return this.symbolTable;}

    public void setSymbolTable(SymbolTable symbolTable) {this.symbolTable=symbolTable;}

    public String toString() {return super.toString();}

    @Override
    public Object accept(Visitor v) throws Exception {
        return v.visit(this);
    }
}
