package compiler.nodi;

import compiler.nodi.expr.Identifier;
import compiler.visitors.Visitable;
import compiler.visitors.Visitor;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.ArrayList;
import java.util.List;

public class ParDeclOp extends DefaultMutableTreeNode implements Visitable {

    private String typeStream;
    private String type;
    private ArrayList<Identifier> idList;


    public ParDeclOp(String typeStream, String type, List<Identifier> idList) {
        super("ParDeclOp");
        super.add(new DefaultMutableTreeNode(typeStream));
        super.add(new DefaultMutableTreeNode(type));
        for (Identifier id: idList)
            super.add(id);



        this.typeStream = typeStream;
        this.type = type;
        this.idList = (ArrayList<Identifier>) idList;
    }

    public String getTypeStream() { return typeStream; }
    public String getType() { return type; }
    public List<Identifier> getIdList() { return idList; }

    public void addId(Identifier id) {
        super.add(id);
        this.idList.add(id);
    }
    public void addAllId(List<Identifier> idList) {
        for(Identifier id: idList)
            super.add(id);
        this.idList.addAll(idList); }

    @Override
    public String toString() { return super.toString(); }

    @Override
    public Object accept(Visitor v) throws Exception {
        return v.visit(this);
    }
}
