package compiler.nodi.expr;

import compiler.visitors.Visitable;
import compiler.visitors.Visitor;

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
