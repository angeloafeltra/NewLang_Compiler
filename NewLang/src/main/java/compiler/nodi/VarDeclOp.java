package compiler.nodi;

import compiler.nodi.expr.Expr;
import compiler.visitors.Visitable;
import compiler.visitors.Visitor;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.ArrayList;

public class VarDeclOp extends DefaultMutableTreeNode implements Visitable {

    private String type;
    private ArrayList<Expr> exprList;



    public VarDeclOp(String type, ArrayList<Expr> exprList) {
        super("VarDeclOp");
        super.add(new DefaultMutableTreeNode(type));
        for(Expr expr: exprList)
            super.add(expr);
        this.type = type;
        this.exprList = exprList;
    }

    public String getType() { return type; }
    public ArrayList<Expr> getExprList() { return exprList; }

    public void setType(String type) { this.type=type;}

    public void addId(Expr id) { this.exprList.add(id); }
    public void addsId(ArrayList<Expr> idList) { this.exprList.addAll(idList); }


    @Override
    public Object accept(Visitor v) throws Exception {
        return v.visit(this);
    }
}
