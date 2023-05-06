package compiler.visitors.semanticVisitor;

import compiler.nodi.*;
import compiler.nodi.expr.*;
import compiler.nodi.statement.*;
import compiler.symbolTable.RowTable;
import compiler.symbolTable.SymbolTable;
import compiler.symbolTable.TypeField;
import compiler.visitors.Visitor;

import java.util.ArrayList;
import java.util.Collection;

/**
 *  Generazione Scope
 */
public class SemanticVisitor1 implements Visitor {

    private SymbolTable padre=null;

    @Override
    /**
     * Genero lo scope della root, inserendo variabili e funzioni
     */
    public Object visit(ProgramOp programOp) throws Exception {

        programOp.setSymbolTable(new SymbolTable());
        SymbolTable symbolTable=programOp.getSymbolTable();
        symbolTable.setScope("Root");
        symbolTable.setFather(null);
        padre=symbolTable;

        //Se sono presenti variabili le aggiungo allo scope
        if(programOp.getVarDeclList()!=null){
            ArrayList<RowTable> listaVar;
            for(VarDeclOp variable:programOp.getVarDeclList()){
                listaVar= (ArrayList<RowTable>) variable.accept(this);
                for(RowTable row :listaVar){
                    symbolTable.addRow(row);
                }
            }
        }


        //Se sono presenti funzioni le aggiungo allo scope
        if(programOp.getFunOpList()!=null){
            for(FunOp fun:programOp.getFunOpList()){
                String identificatore=fun.getIdentificatore().getLessema();
                TypeField.TypeFieldFunction typeField=new TypeField.TypeFieldFunction();
                StringBuilder typeStream=new StringBuilder();
                if(fun.getParams()!=null){
                    ArrayList<String[]> listaParametri=new ArrayList<>();
                    for(ParDeclOp param: fun.getParams())
                        listaParametri.addAll((Collection<? extends String[]>) param.accept(this));

                    for(String[] parametro:listaParametri){
                        typeField.addInputParam(parametro[1]);
                        typeStream.append(parametro[2]+",");
                    }
                }
                typeField.addOutputParam(fun.getType());
                symbolTable.addRow(new RowTable(identificatore,FunOp.class,typeField,typeStream.toString()));
            }
        }

        //Aggiungo la funzione main allo scope
        String identificatore=programOp.getMain().getIdentificatore().getLessema();
        TypeField.TypeFieldFunction typeField=new TypeField.TypeFieldFunction();
        StringBuilder typeStream=new StringBuilder();
        if(programOp.getMain().getParams()!=null){
            ArrayList<String[]> listaParametri=new ArrayList<>();
            for(ParDeclOp param: programOp.getMain().getParams())
                listaParametri.addAll((Collection<? extends String[]>) param.accept(this));

            for(String[] parametro:listaParametri){
                typeField.addInputParam(parametro[1]);
                typeStream.append(parametro[2]+",");
            }
        }

        if (typeStream.toString().endsWith(","))
            typeStream= new StringBuilder(typeStream.toString().substring(0, typeStream.length() - 1));

        typeField.addOutputParam(programOp.getMain().getType());
        symbolTable.addRow(new RowTable(identificatore,FunOp.class,typeField,typeStream.toString()));



        //Lancio la generazione degli scope per le funzioni
        if(programOp.getFunOpList()!=null) {
            for (FunOp fun : programOp.getFunOpList()) {
                padre=programOp.getSymbolTable();
                fun.accept(this);
            }
        }

        padre=programOp.getSymbolTable();
        programOp.getMain().accept(this);



        return null;
    }

    @Override
    /**
     * Genero una RowTable per ogni variabile
     */
    public Object visit(VarDeclOp varDecl) throws Exception {

        ArrayList<RowTable> listVar=new ArrayList<>();

        String identificatore="";
        String tipo="";
        for (Expr expr:varDecl.getExprList()){
            if (expr instanceof Identifier){
                identificatore= ((Identifier) expr).getLessema();
                tipo=varDecl.getType(); //Il tipo della variabile corrispone con quello della dichiarazione
            }
            if (expr instanceof IdInitOp){
                identificatore=((IdInitOp) expr).getId().getLessema();
                tipo= varDecl.getType(); //Il tipo della variabile corrispone con quello della dichiarazione
            }
            if (expr instanceof  IdInitObbOp){
                identificatore=((IdInitObbOp) expr).getId().getLessema();
                tipo= (String) ((ConstOp)((IdInitObbOp) expr).getExpr()).accept(this); //Il tipo viene inferito
                //Aggiorno il tipo nel nodo
                varDecl.setType(tipo);
            }
            listVar.add(new RowTable(identificatore,VarDeclOp.class,new TypeField.TypeFieldVar(tipo),""));
        }

        return listVar;
    }

    @Override
    /**
     * Genero lo scope della funzione, aggiungendo i parametri
     */
    public Object visit(FunOp funOp) throws Exception {

        funOp.setSymbolTable(new SymbolTable());
        SymbolTable symbolTable=funOp.getSymbolTable();
        symbolTable.setFather(padre);
        symbolTable.setScope(funOp.getIdentificatore().getLessema());

        //Se sono presenti parametri li aggiungo allo scope
        if(funOp.getParams()!=null){
            ArrayList<String[]> listaParametri=new ArrayList<>();
            for(ParDeclOp param: funOp.getParams()){
                listaParametri.addAll((Collection<? extends String[]>) param.accept(this));
            }

            for (String[] param:listaParametri)
                symbolTable.addRow(new RowTable(param[0],ParDeclOp.class,new TypeField.TypeFieldVar(param[1]),param[2]));
        }



        if(funOp.getBody()!=null){
            padre=funOp.getSymbolTable();
            funOp.getBody().accept(this);
        }



        return null;
    }

    @Override
    public Object visit(ParDeclOp parDeclOp) {

        ArrayList<String[]> listParametri=new ArrayList<>();
        for (Identifier id:parDeclOp.getIdList()){
            listParametri.add(new String[]{id.getLessema(),parDeclOp.getType(),parDeclOp.getTypeStream()});
        }

        return listParametri;
    }

    @Override
    /**
     * Aggiungo le variabili allo scope del body
     */
    public Object visit(BodyOp bodyOp) throws Exception {

        if(bodyOp.getListVar()!=null){
            ArrayList<RowTable> listaVar;
            for(VarDeclOp variable:bodyOp.getListVar()){
                listaVar= (ArrayList<RowTable>) variable.accept(this);
                for(RowTable row :listaVar){
                    padre.addRow(row);
                }
            }
        }


        if(bodyOp.getListStatement()!=null){
            for (Statement stat:bodyOp.getListStatement()){
                if(stat instanceof WhileOp)
                    //Genero lo scope di While
                    stat.accept(this);
                if(stat instanceof ForOp)
                    //Genero lo scope di For
                    stat.accept(this);
                //Genero lo scope di If
                if(stat instanceof IfStatOp)
                    stat.accept(this);
            }
        }

        return null;
    }

    @Override
    public Object visit(AssignOp assignOp) {
        return null;
    }

    @Override
    public Object visit(CallFunOpStat callFunOpStat) {
        return null;
    }

    @Override
    //Genero la symbol table per il For
    public Object visit(ForOp forOp) throws Exception {

        forOp.setSymbolTable(new SymbolTable());
        SymbolTable symbolTable=forOp.getSymbolTable();
        symbolTable.setScope("For");
        symbolTable.setFather(padre);


        String identificatore=forOp.getId().getId().getLessema();
        symbolTable.addRow(new RowTable(identificatore,VarDeclOp.class,new TypeField.TypeFieldVar("integer"),""));


        if(forOp.getBodyop()!=null) {
            padre = forOp.getSymbolTable();
            forOp.getBodyop().accept(this);
        }
        
        return null;
    }

    @Override
    //Genero la symbol table per l'if
    public Object visit(IfStatOp ifStatOp) throws Exception {

        ifStatOp.setSymbolTableThen(new SymbolTable());
        SymbolTable symbolTableThen=ifStatOp.getSymbolTableThen();
        symbolTableThen.setScope("If-Then");
        symbolTableThen.setFather(padre);
        ifStatOp.setSymbolTableElse(new SymbolTable());
        SymbolTable symbolTableElse=ifStatOp.getSymbolTableElse();
        symbolTableElse.setScope("If-Else");
        symbolTableElse.setFather(padre);


        if(ifStatOp.getBodyThen()!=null) {
            padre = ifStatOp.getSymbolTableThen();
            ifStatOp.getBodyThen().accept(this);
        }

        //Symbol Table Else
        if(ifStatOp.getBodyElse()!=null) {
            padre=ifStatOp.getSymbolTableElse();
            ifStatOp.getBodyElse().accept(this);
        }

        return null;
    }

    @Override
    public Object visit(ReadOp readOp) {
        return null;
    }

    @Override
    public Object visit(ReturnOp returnOp) {
        return null;
    }

    @Override
    public Object visit(Statement statement) {
        return null;
    }

    @Override
    //Genero la symbol table per il while
    public Object visit(WhileOp whileOp) throws Exception {

        whileOp.setSymbolTable(new SymbolTable());
        SymbolTable symbolTable=whileOp.getSymbolTable();
        symbolTable.setScope("While");
        symbolTable.setFather(padre);


        if(whileOp.getBody()!=null) {
            padre = whileOp.getSymbolTable();
            whileOp.getBody().accept(this);
        }

        return null;
    }

    @Override
    public Object visit(WriteOp writeOp) {
        return null;
    }

    @Override
    public Object visit(AritAndRelOp aritAndRelOp) throws Exception {

        aritAndRelOp.getExpr1().accept(this);
        aritAndRelOp.getExpr2().accept(this);
        return null;
    }

    @Override
    public Object visit(CallFunOpExpr callFunOpExpr) {
        return null;
    }

    @Override
    public Object visit(ConstOp constOp) {
        String type = constOp.getTypeConst();
        if (type.equals("boolean_const"))
            return "boolean";
        if (type.equals("integer_const"))
            return "integer";
        if (type.equals("real_const"))
            return "float";
        if (type.equals("string_const"))
            return "string";
        if (type.equals("char_const"))
            return "char";
        return null;
    }

    @Override
    public Object visit(Expr expr) throws Exception {
        return null;
    }

    @Override
    public Object visit(Identifier identifier) throws Exception {
        return null;
    }

    @Override
    public Object visit(IdInitObbOp idInitObbOp) {
        return null;
    }

    @Override
    public Object visit(IdInitOp idInitOp) throws Exception {
        return null;
    }

    @Override
    public Object visit(UnaryOp unaryOp) throws Exception {
        return null;
    }
}
