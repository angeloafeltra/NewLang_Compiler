package main.java.utilsClass;

import main.java.nodi.FunOp;
import main.java.nodi.VarDeclOp;

import java.util.ArrayList;

public class Decl {

    private ArrayList<VarDeclOp> varDeclList;
    private ArrayList<FunOp> funOpList;

    public Decl(){
        this.varDeclList=new ArrayList<VarDeclOp>();
        this.funOpList=new ArrayList<FunOp>();
    }

    public Decl(ArrayList<VarDeclOp> varDeclList, ArrayList<FunOp> funOpList){
        this.varDeclList=new ArrayList<VarDeclOp>();
        this.funOpList=new ArrayList<FunOp>();
        this.varDeclList.addAll(varDeclList);
        this.funOpList.addAll(funOpList);
    }

    public void addVarDecl(VarDeclOp varDecl) {this.varDeclList.add(varDecl);}
    public void addsVarDeclList(ArrayList<VarDeclOp> varDeclList) {this.varDeclList.addAll(varDeclList);}

    public void addFunOp(FunOp funOp) {this.funOpList.add(funOp);}
    public void addsFunOpList(ArrayList<FunOp> funOpList) {this.funOpList.addAll(funOpList);}


    public ArrayList<VarDeclOp> getVarDeclList() {return this.varDeclList;}
    public ArrayList<FunOp> getFunOpList() {return this.funOpList;}


}
