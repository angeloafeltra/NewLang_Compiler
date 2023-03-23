package compiler.nodi.expr;

import compiler.visitors.Visitable;
import compiler.visitors.Visitor;

public class ConstOp extends Expr implements Visitable {

    private String lessema;
    private String typeConst;

    public ConstOp(String typeConst, String lessema){
        super(typeConst+": "+lessema);
        this.typeConst=typeConst;
        this.lessema=lessema;
    }

    public String getLessema() {
        return lessema;
    }

    public String getTypeConst() {
        return typeConst;
    }

    public void setTipoEspressione(String type) {super.setTipoEspressione(type);}

    public String getTipoEspressione() {return super.getTipoEspressione();}

    public String toString() {return super.toString();}

    @Override
    public Object accept(Visitor v) throws Exception {
        return v.visit(this);
    }
}
