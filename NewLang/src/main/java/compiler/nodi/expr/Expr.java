package compiler.nodi.expr;

import compiler.visitors.Visitable;
import compiler.visitors.Visitor;

import javax.swing.tree.DefaultMutableTreeNode;

public class Expr extends DefaultMutableTreeNode implements Visitable {

    private String mode=null;

    private String tipoEspressione;

    public Expr(String nodeName) {
        super(nodeName);
    }

    public String getMode() {
        return this.mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getTipoEspressione() {
        return this.tipoEspressione;
    }

    public void setTipoEspressione(String tipoEspressione){
        this.tipoEspressione=tipoEspressione;
    }

    public String toString() {return super.toString();}

    @Override
    public Object accept(Visitor v) throws Exception {
        return v.visit(this);
    }
}
