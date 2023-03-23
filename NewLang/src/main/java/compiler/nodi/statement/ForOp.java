package compiler.nodi.statement;

import compiler.nodi.BodyOp;
import compiler.nodi.expr.ConstOp;
import compiler.nodi.expr.IdInitOp;
import compiler.symbolTable.SymbolTable;
import compiler.visitors.Visitable;
import compiler.visitors.Visitor;

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
