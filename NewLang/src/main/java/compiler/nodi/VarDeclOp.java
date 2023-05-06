package compiler.nodi;

import compiler.nodi.expr.Expr;
import compiler.visitors.Visitable;
import compiler.visitors.Visitor;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.ArrayList;
import java.util.List;

public class VarDeclOp extends DefaultMutableTreeNode implements Visitable {

    private String type;
    private ArrayList<Expr> exprList;



    public VarDeclOp(String type, List<Expr> exprList) {
        super("VarDeclOp");
        super.add(new DefaultMutableTreeNode(type));
        for(Expr expr: exprList)
            super.add(expr);
        this.type = type;
        this.exprList = (ArrayList<Expr>) exprList;
    }

    public String getType() { return type; }
    public List<Expr> getExprList() { return exprList; }

    public void setType(String type) { this.type=type;}

    public void addId(Expr id) { this.exprList.add(id); }
    public void addsId(List<Expr> idList) { this.exprList.addAll(idList); }


    @Override
    public Object accept(Visitor v) throws Exception {
        return v.visit(this);
    }
}
