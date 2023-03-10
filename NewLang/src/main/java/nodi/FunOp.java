package main.java.nodi;
import main.java.nodi.expr.Expr;
import main.java.nodi.expr.IdInitObbOp;
import main.java.nodi.expr.IdInitOp;
import main.java.nodi.expr.Identifier;
import main.java.symbolTable.RowTable;
import main.java.symbolTable.SymbolTable;
import main.java.symbolTable.TypeField;
import main.java.visitors.Visitable;
import main.java.visitors.Visitor;

import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;

public class FunOp extends DefaultMutableTreeNode implements Visitable {

    private Identifier identificatore;
    private ArrayList<ParDeclOp> params;
    private String type;
    private BodyOp body;

    private SymbolTable symbolTable;



    public FunOp(Identifier identificatore, ArrayList<ParDeclOp> params, String type, BodyOp body){

        super("FunOp");

        super.add(identificatore);
        for (ParDeclOp parDecl: params)
            super.add(parDecl);
        super.add(new DefaultMutableTreeNode(type));
        super.add(body);


        this.identificatore=identificatore;
        this.params=params;
        this.type=type;
        this.body=body;

    }

    public FunOp(Identifier identificatore, String type, BodyOp body){

        super("FunOp");

        super.add(identificatore);
        super.add(new DefaultMutableTreeNode(type));
        super.add(body);


        this.identificatore=identificatore;
        this.params=null;
        this.type=type;
        this.body=body;

    }

    public Identifier getIdentificatore() { return identificatore; }

    public ArrayList<ParDeclOp> getParams() { return params; }

    public String getType() { return type; }

    public BodyOp getBody() { return body; }



    public void addParam(ParDeclOp param) {
        super.add(param);
        this.params.add(param);
    }

    public void addsParams(ArrayList<ParDeclOp> params) {
        for (ParDeclOp param: params)
            super.add(param);
        this.params.addAll(params);
    }

    public void setSymbolTable(SymbolTable symbolTable){this.symbolTable=symbolTable;}

    public SymbolTable getSymbolTable(){return this.symbolTable;}



    public String toString(){ return super.toString(); }

    @Override
    public Object accept(Visitor v) throws Exception {
        return v.visit(this);
    }
}
