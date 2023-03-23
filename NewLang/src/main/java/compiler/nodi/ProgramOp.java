package compiler.nodi;

import compiler.symbolTable.SymbolTable;
import compiler.visitors.Visitable;
import compiler.visitors.Visitor;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.ArrayList;

public class ProgramOp extends DefaultMutableTreeNode implements Visitable {

    private ArrayList<VarDeclOp> varDeclList;
    private ArrayList<FunOp> funOpList;
    private FunOp main;
    private SymbolTable symbolTable;


    public ProgramOp(ArrayList<VarDeclOp> varDeclList, FunOp main, ArrayList<FunOp> funOpList){

        super("ProgramOp");
        for (VarDeclOp varDecl: varDeclList)
            super.add(varDecl);

        for (FunOp funOp: funOpList)
            super.add(funOp);

        main.setUserObject("Main");
        super.add(main);

        this.varDeclList = varDeclList;
        this.funOpList = funOpList;
        this.main=main;


    }

    public ProgramOp(FunOp main, ArrayList<FunOp> funOpList){

        super("ProgramOp");

        for (FunOp funOp: funOpList)
            super.add(funOp);

        main.setUserObject("Main");
        super.add(main);

        this.varDeclList =null;
        this.funOpList = funOpList;
        this.main=main;

    }

    public void addVarDec(VarDeclOp varDec) {
        super.add(varDec);
        this.varDeclList.add(varDec);
    }
    public void addFunOp(FunOp funOp) {
        super.add(funOp);
        this.funOpList.add(funOp);
    }

    public void addsVarDec(ArrayList<VarDeclOp> varDecList) {
        for (VarDeclOp varDecOp: varDecList) {
            super.add(varDecOp);
            this.varDeclList.add(varDecOp);
        }
    }

    public void addsFunOp(ArrayList<FunOp> funOpList) {
        for (FunOp funOp: funOpList) {
            super.add(funOp);
            this.funOpList.add(funOp);
        }
    }

    public ArrayList<VarDeclOp> getVarDeclList() { return this.varDeclList; }
    public FunOp getMain() { return this.main; }

    public ArrayList<FunOp> getFunOpList() {return this.funOpList;}

    public void setSymbolTable(SymbolTable symbolTable){this.symbolTable=symbolTable;}

    public SymbolTable getSymbolTable(){return this.symbolTable;}




    public String toString() {return super.toString();}


    @Override
    public Object accept(Visitor v) throws Exception {
        return v.visit(this);
    }
}
