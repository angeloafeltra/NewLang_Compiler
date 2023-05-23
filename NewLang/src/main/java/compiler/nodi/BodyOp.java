package compiler.nodi;

import compiler.nodi.statement.Statement;
import compiler.visitors.Visitable;
import compiler.visitors.Visitor;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.ArrayList;
import java.util.List;

public class BodyOp  extends DefaultMutableTreeNode implements Visitable {

    private ArrayList<VarDeclOp> listVar;
    private ArrayList<Statement> listStatement;



    public BodyOp(List<VarDeclOp> listVar, List<Statement> listStatement) {
        super("BodyOp");


        if (listVar!=null) {
            for (VarDeclOp variable : listVar)
                super.add(variable);
            this.listVar = (ArrayList<VarDeclOp>) listVar;
        }else{
            this.listVar=null;
        }

        if (listStatement!=null) {
            this.listStatement = (ArrayList<Statement>) listStatement;
            for(Statement statement: listStatement)
                super.add(statement);
        }else{
            this.listStatement=null;
        }
    }


    public BodyOp() {
        super("BodyOp");
        this.listVar = null;
        this.listStatement = null;
    }

    public List<VarDeclOp> getListVar() { return this.listVar; }
    public List<Statement> getListStatement() { return this.listStatement; }


    public void addVar(VarDeclOp variable) {
        super.add(variable);
        this.listVar.add(variable);
    }
    public void addsListVar(List<VarDeclOp> listVar) {
        for(VarDeclOp variable:listVar)
            super.add(variable);
        this.listVar.addAll(listVar);
    }

    public void addStatement(Statement stm) {
        super.add(stm);
        this.listStatement.add(stm);
    }

    public void addStatements(List<Statement> stms) {
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
