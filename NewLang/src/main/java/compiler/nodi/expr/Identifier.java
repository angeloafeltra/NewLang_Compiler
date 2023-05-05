package compiler.nodi.expr;

import compiler.visitors.Visitable;
import compiler.visitors.Visitor;

public class Identifier extends Expr implements Visitable {

    private String lessema;

    public Identifier(String lessema){
        super("ID: "+lessema);
        this.lessema=lessema;
    }

    public String getLessema(){ return this.lessema; }

    public void setLessema(String lessema) {this.lessema=lessema;}
    @Override
    public void setTipoEspressione(String type) {super.setTipoEspressione(type);}

    @Override
    public String getTipoEspressione() {return super.getTipoEspressione();}
    @Override
    public Object accept(Visitor v) throws Exception {
        return (v.visit(this));
    }
    @Override
    public String toString() {return super.toString();}

}
