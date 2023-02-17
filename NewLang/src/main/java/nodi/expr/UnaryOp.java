package main.java.nodi.expr;

import main.java.visitors.Visitable;
import main.java.visitors.Visitor;

public class UnaryOp extends Expr implements Visitable {

    private Expr expr;
    private String type;
    private String typeResult;

    public UnaryOp(String type, Expr expr){
        super(type);
        super.add(expr);

        this.type=type;
        this.expr=expr;
    }

    public Expr getExpr() {
        return expr;
    }

    public String getType() {
        return type;
    }

    public void setTipoEspressione(String type) {super.setTipoEspressione(type);}

    public String getTipoEspressione() {return super.getTipoEspressione();}

    public Object accept(Visitor v) throws Exception {
        return (v.visit(this));
    }

    public String toString() { return super.toString(); }
}
