package compiler.symbolTable;

import compiler.visitors.semanticVisitor.Eccezioni;

import java.util.ArrayList;

public class SymbolTable {

    private SymbolTable father;
    private String scope;
    private ArrayList<RowTable> listRow;
    private static boolean shadowing=true;


    public SymbolTable(){
        father=null;
        listRow=new ArrayList<RowTable>();
    }

    public SymbolTable(SymbolTable father, ArrayList<RowTable> listRow,String scope){
        this.father=father;
        this.listRow=listRow;
        this.scope=scope;

    }

    public void addRow(RowTable row) throws Exception {

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

    public ArrayList<RowTable> getListRow() {return this.listRow;}

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
            String typeOutput=typeFieldFunction.getOutputParam().get(0);
            return typeOutput;
        }else{
            return null;
        }



    }

    @Override
    public String toString() {
        String str="SymbolTable: "+scope+"\n";
        if(father!=null) str=str+"Father: "+father.getScope()+"\n";
        for (RowTable row:listRow)
            str=str+row.toString()+"\n";
        return str;
    }

}
