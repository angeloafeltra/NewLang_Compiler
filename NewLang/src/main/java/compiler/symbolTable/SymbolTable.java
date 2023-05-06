package compiler.symbolTable;

import compiler.visitors.semanticVisitor.Eccezioni;

import java.util.ArrayList;
import java.util.List;

public class SymbolTable {

    private SymbolTable father;
    private String scope;
    private ArrayList<RowTable> listRow;
    private static boolean shadowing=true;


    public SymbolTable(){
        father=null;
        listRow=new ArrayList<>();
    }

    public SymbolTable(SymbolTable father, List<RowTable> listRow, String scope){
        this.father=father;
        this.listRow= (ArrayList<RowTable>) listRow;
        this.scope=scope;

    }

    public void addRow(RowTable row) throws Eccezioni.MultipleDeclaration {

        if (shadowing) {
            if (probe(row.getSymbol()) || (row.getSymbol().equals(scope) && !row.getSymbol().equals("Root")))
                throw new Eccezioni.MultipleDeclaration();
            else
                listRow.add(row);
        } else {
            SymbolTable symbolTable = this;
            while (symbolTable != null) {
                if (symbolTable.probe(row.getSymbol()) || (row.getSymbol().equals(symbolTable.scope) && !row.getSymbol().equals("Root")))
                    throw new Eccezioni.MultipleDeclaration();
                else
                    symbolTable = symbolTable.getFather();
            }
            listRow.add(row);
        }
    }


    public boolean probe(String x){
        for(RowTable r:this.listRow)
            if(r.getSymbol().equals(x))
               return true;
        return false;
    }


    public RowTable lookup(String id){
        SymbolTable symbolTable=this;
        while (symbolTable!=null){
            for(RowTable r:symbolTable.listRow) {
                if (r.getSymbol().equals(id))
                    return r;
            }
            symbolTable=symbolTable.father;
        }
        return null;
    }

    public void setFather(SymbolTable father){
        this.father=father;
    }

    public SymbolTable getFather() {return this.father;}

    public List<RowTable> getListRow() {return this.listRow;}

    public void setScope(String scope){
        this.scope=scope;
    }

    public String getScope(){return scope;}


    public String getTypeFun(){
        SymbolTable currentScope=this;
        SymbolTable padre=currentScope.getFather();
        while (!padre.getScope().equals("Root")) {
            currentScope=padre;
            padre=currentScope.getFather();
        }

        //Mi trovo con la currentScope nella funzione che contiene il return
        //Mi trovo col padre dell currentScope nella Root
        String scopeName=currentScope.getScope(); //Coincide col nome della funzione
        RowTable row=padre.lookup(scopeName); //Se la lookup fallisce ho un errore in sematica 1
        if(row!=null){
            TypeField.TypeFieldFunction typeFieldFunction=(TypeField.TypeFieldFunction) row.getType();
            return typeFieldFunction.getOutputParam().get(0);
        }else{
            return null;
        }



    }

    @Override
    public String toString() {
        StringBuilder str=new StringBuilder();
        str.append("SymbolTable: "+scope+"\n");
        if(father!=null) {
            str.append("Father: "+father.getScope()+"\n");
        }
        for (RowTable row:listRow) {
            str.append(row.toString()+"\n");
        }
        return str.toString();
    }

}
