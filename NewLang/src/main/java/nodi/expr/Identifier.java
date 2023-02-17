package main.java.nodi.expr;

import main.java.visitors.Visitable;
import main.java.visitors.Visitor;

import javax.swing.tree.DefaultMutableTreeNode;

public class Identifier extends Expr implements Visitable {

    private String lessema;

    public Identifier(String lessema){
        super("ID: "+lessema);
        this.lessema=lessema;
    }

    public String getLessema(){ return this.lessema; }

    public void setLessema(String lessema) {this.lessema=lessema;}
    public void setTipoEspressione(String type) {super.setTipoEspressione(type);}

    public String getTipoEspressione() {return super.getTipoEspressione();}

    public Object accept(Visitor v) throws Exception {
        return (v.visit(this));
    }

    public String toString() {return super.toString();}

}
