package compiler.nodi;

import compiler.nodi.statement.Statement;
import compiler.visitors.Visitable;
import compiler.visitors.Visitor;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.ArrayList;

public class BodyOp  extends DefaultMutableTreeNode implements Visitable {

    private ArrayList<VarDeclOp> listVar;
    private ArrayList<Statement> listStatement;



    public BodyOp(ArrayList<VarDeclOp> listVar, ArrayList<Statement> listStatement) {
        super("BodyOp");


        if (listVar!=null) {
            for (VarDeclOp variable : listVar)
                super.add(variable);
            this.listVar = listVar;
        }else{
            this.listVar=null;
        }

        if (listStatement!=null) {
            this.listStatement = listStatement;
            for(Statement statement: listStatement)
                super.add(statement);
        }else{
            this.listStatement=null;
        }
    }


    public BodyOp() {
        super("BodyOp");
        for(VarDeclOp variable: listVar)
            super.add(variable);
        this.listVar = null;
        this.listStatement = null;
    }

    public ArrayList<VarDeclOp> getListVar() { return this.listVar; }
    public ArrayList<Statement> getListStatement() { return this.listStatement; }


    public void addVar(VarDeclOp variable) {
        super.add(variable);
        this.listVar.add(variable);
    }
    public void addsListVar(ArrayList<VarDeclOp> listVar) {
        for(VarDeclOp variable:listVar)
            super.add(variable);
        this.listVar.addAll(listVar);
    }

    public void addStatement(Statement stm) {
        super.add(stm);
        this.listStatement.add(stm);
    }

    public void addStatements(ArrayList<Statement> stms) {
        for(Statement stm:stms)
            super.add(stm);
        this.listStatement.addAll(stms);
    }

    @Override
    public String toString() { return super.toString(); }

    @Override
    public Object accept(Visitor v) throws Exception {
        return v.visit(this);
    }
}
