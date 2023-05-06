package compiler.utilsClass;

import compiler.nodi.FunOp;
import compiler.nodi.VarDeclOp;

import java.util.ArrayList;
import java.util.List;

public class Decl {

    private ArrayList<VarDeclOp> varDeclList;
    private ArrayList<FunOp> funOpList;

    public Decl(){
        this.varDeclList=new ArrayList<>();
        this.funOpList=new ArrayList<>();
    }

    public Decl(List<VarDeclOp> varDeclList, List<FunOp> funOpList){
        this.varDeclList=new ArrayList<>();
        this.funOpList=new ArrayList<>();
        this.varDeclList.addAll(varDeclList);
        this.funOpList.addAll(funOpList);
    }

    public void addVarDecl(VarDeclOp varDecl) {this.varDeclList.add(varDecl);}
    public void addsVarDeclList(List<VarDeclOp> varDeclList) {this.varDeclList.addAll(varDeclList);}

    public void addFunOp(FunOp funOp) {this.funOpList.add(funOp);}
    public void addsFunOpList(List<FunOp> funOpList) {this.funOpList.addAll(funOpList);}


    public ArrayList<VarDeclOp> getVarDeclList() {return this.varDeclList;}
    public ArrayList<FunOp> getFunOpList() {return this.funOpList;}


}
