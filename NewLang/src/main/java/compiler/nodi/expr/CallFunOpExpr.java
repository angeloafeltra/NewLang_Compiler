package compiler.nodi.expr;

import compiler.visitors.Visitable;
import compiler.visitors.Visitor;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.ArrayList;
import java.util.List;

public class CallFunOpExpr extends Expr implements Visitable {

    private Identifier identifier;
    private ArrayList<Expr> listExpr;

    public CallFunOpExpr(Identifier identifier){
        super("CallFunOpExpr");
        super.add(identifier);

        this.identifier=identifier;
        this.listExpr=null;
    }

    public CallFunOpExpr(Identifier identifier, List<Expr> listExpr){
        super("CallFunOpExpr");
        super.add(identifier);
        for(Expr expr:listExpr){
            super.add(expr);
            String mode=expr.getMode();
            super.add(new DefaultMutableTreeNode(mode));
        }

        this.listExpr= (ArrayList<Expr>) listExpr;
        this.identifier=identifier;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public ArrayList<Expr> getListExpr() {return listExpr;}


    public void addExpr(Expr expr){
        super.add(expr);
        super.add(new DefaultMutableTreeNode(expr.getMode()));
        this.listExpr.add(expr);
    }

    public void addsListExpr(List<Expr> listExpr){
        for(Expr expr: listExpr){
            super.add(expr);
            super.add(new DefaultMutableTreeNode(expr.getMode()));
        }
        this.listExpr.addAll(listExpr);
    }

    @Override
    public void setTipoEspressione(String type) {super.setTipoEspressione(type);}

    @Override
    public String getTipoEspressione() {return super.getTipoEspressione();}

    @Override
    public String toString() {return super.toString();}

    @Override
    public Object accept(Visitor v) throws Exception {
        return v.visit(this);
    }
}
