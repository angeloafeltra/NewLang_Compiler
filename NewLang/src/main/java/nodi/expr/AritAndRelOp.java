package main.java.nodi.expr;

import main.java.visitors.Visitable;
import main.java.visitors.Visitor;

public class AritAndRelOp extends Expr implements Visitable {

    private String typeOp;
    private Expr expr1;
    private Expr expr2;

    public AritAndRelOp(String typeOp, Expr expr1, Expr expr2){
        super(typeOp);
        super.add(expr1);
        super.add(expr2);

        this.typeOp=typeOp;
        this.expr1=expr1;
        this.expr2=expr2;
    }

    public String getTypeOp() {
        return typeOp;
    }

    public Expr getExpr1() {
        return expr1;
    }

    public Expr getExpr2() {
        return expr2;
    }

    public void setTipoEspressione(String type) {super.setTipoEspressione(type);}

    public String getTipoEspressione() {return super.getTipoEspressione();}

    public String toString() {return super.toString();}

    @Override
    public Object accept(Visitor v) throws Exception {
        return v.visit(this);
    }
}
