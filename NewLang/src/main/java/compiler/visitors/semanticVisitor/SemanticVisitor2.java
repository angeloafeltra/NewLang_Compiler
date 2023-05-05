package compiler.visitors.semanticVisitor;

import compiler.nodi.*;
import compiler.nodi.expr.*;
import compiler.nodi.statement.*;
import compiler.symbolTable.RowTable;
import compiler.symbolTable.SymbolTable;
import compiler.symbolTable.TypeField;
import compiler.visitors.Visitor;

import java.util.ArrayList;

/**
 * Type Checking e Type System
 */
public class SemanticVisitor2 implements Visitor {

    private SymbolTable currentScope;

    private static final String[][] combinazioniAritOp= { {"integer", "integer", "integer"},
                                                        {"integer", "float", "float"},
                                                        {"float", "integer", "float"},
                                                        {"float", "float", "float"} };

    private static final String[][] combinazioniStrOp= { {"string", "string", "string"},
                                                        {"string", "float","string"},
                                                        {"string","integer","string"},
                                                        {"string", "char", "string"},
                                                        {"integer","string","string"},
                                                        {"float","string","string"},
                                                        {"char","string","string"},
                                                        {"boolean","string","string"},
                                                        {"string","boolean","string"}};

    private static final String[][] combinazioniBooleanOp= { {"boolean", "boolean", "boolean"} };

    private static final String[][] combinazioniRelOp= { {"integer", "integer", "boolean"},
                                                        {"integer", "float", "boolean"},
                                                        {"float", "integer", "boolean"},
                                                        {"float", "float", "boolean"},
                                                        {"string","string","boolean"},
                                                        {"char","char","boolean"}};

    private static final String[][] combinazioniMinus= { {"integer", "integer"},
                                                        {"float", "float"} };


    private static final String[][] combinazioniNot= { {"boolean", "boolean"} };

    private static final String[][] compatibilita= {{"integer","integer"},
                                                    {"float","float"},
                                                    {"float","integer"},
                                                    {"char","char"},
                                                    {"string","string"},
                                                    {"boolean","boolean"},
                                                    {"void","void"}};

    private boolean returnPresente;


    @Override
    public Object visit(ProgramOp programOp) throws Exception {

        currentScope=programOp.getSymbolTable();
        if(programOp.getVarDeclList()!=null){
            for (VarDeclOp var: programOp.getVarDeclList())
                var.accept(this);
        }


        if(programOp.getFunOpList()!=null){
            for(FunOp fun: programOp.getFunOpList())
                fun.accept(this);
        }

        programOp.getMain().accept(this);

        return null;
    }

    @Override
    /**
     *  Se il tipo dell'identificatore e il tipo dell'espressione coincidono
     *  non coincidono lancio un eccezione InizializationError
     */
    public Object visit(VarDeclOp varDecl) throws Exception {
        String tipoIdentificatore="";
        String tipo="";
        for (Expr expr:varDecl.getExprList()){

            if (expr instanceof IdInitOp){
                tipoIdentificatore= (String) ((IdInitOp) expr).getId().accept(this);
                tipo= (String) expr.accept(this);
                boolean isCompatibile=false;
                for (String[] comp: compatibilita) {
                    if (tipoIdentificatore.equals(comp[0]) && tipo.equals(comp[1])) {
                        isCompatibile=true;
                    }
                }
                if(!isCompatibile) {
                    throw new Eccezioni.InizializationError();
                }
            }
        }

        return null;
    }

    @Override
    /**
     * Se la funzione possiede un tipo diverso da void e non possiede un return
     * lancio l'eccezione ReturnError
     */
    public Object visit(FunOp funOp) throws Exception {


        currentScope=funOp.getSymbolTable();
        returnPresente=false;
        funOp.getBody().accept(this); //Lancio i controlli nel body
        if(returnPresente==false && !funOp.getType().equals("void"))
            throw new Eccezioni.ReturnError();
        returnPresente=false;
        currentScope=funOp.getSymbolTable().getFather();

        return null;
    }

    @Override
    public Object visit(ParDeclOp parDeclOp) throws Exception {

        return null;
    }

    @Override
    /**
     *  Lanco il controllo delle variabili e gli statement nel body
     */
    public Object visit(BodyOp bodyOp) throws Exception {
        //Se il body contiene delle variabili, controllo le variabili
        if(bodyOp.getListVar()!=null){
            for(VarDeclOp var:bodyOp.getListVar())
                var.accept(this);
        }


        if(bodyOp.getListStatement()!=null){
            for(Statement stm:bodyOp.getListStatement())
                stm.accept(this);
        }

        return null;
    }

    @Override
    /**
     * Se il numero di identificatori non coincide col numero di espressioni
     * lancio l'eccezione AssignError
     * Se il tipo degli identificatori non coincide col tipo delle espressioni
     * lancio l'eccezione TypeAssignError
     */
    public Object visit(AssignOp assignOp) throws Exception {

        ArrayList<Identifier> listaId=assignOp.getListId();
        ArrayList<Expr> listaExpr=assignOp.getListExpr();

        //Verifico che il numero di identificatori coincide con numero di espressioni
        if(listaId.size()==listaExpr.size()){
            //Controllo che il tipo degli identificatori combacia con quello delle espressioni
            for(int i=0;i<listaId.size();i++){
                String typeId= (String) listaId.get(i).accept(this);
                String typeExpr= (String) listaExpr.get(i).accept(this);
                //Controllo se ho qualche assegnazione float<-int
                boolean isCompatibile=false;
                for(String[] riduzione: compatibilita){
                    if(typeId.equals(riduzione[0]) && typeExpr.equals(riduzione[1]))
                        isCompatibile=true;
                }
                if(!isCompatibile)
                    //Tipo Differente
                    throw new Eccezioni.TypeAssignError();
            }
        }else{
            //Numero identificatori diverso dal numero di espressioni
            throw new Eccezioni.AssignError();
        }

        return null;
    }

    @Override
    /**
     * Se il numero di parametri nella chiamata non coincide col numero di parametri
     * nella dichiarazione di funzione lancio l'errore CallFunNumParamError
     * Se il tipo di parametri nella chiamata non coincide col tipo dei parametri nella
     * dichiarazione di funzione lancio l'errore CallFunTypeParamError()
     *
     */
    public Object visit(CallFunOpStat callFunOpStat) throws Exception {

        //Ottengo la dichiarazione della funzione dal type env
        RowTable result=currentScope.lookup(callFunOpStat.getIdentifier().getLessema());
        if(result==null){ //Controllo se la funzione e presente nel typ env
            throw new Eccezioni.NoDeclarationError(); //La funzione non è stata dichairata
        }else{
            TypeField.TypeFieldFunction dichiarazione= (TypeField.TypeFieldFunction) result.getType();
            //Ottengo il tipo dei parametri d'input della funzione
            ArrayList<String> inputParam=dichiarazione.getInputParam();
            //Ottengo il type stream dei parametri
            String[] typeStream=result.getPropreties().split(",");


            int numeroParamteri=inputParam.size();
            int numeroParametriChiamata=0;

            //Verifico che il numero di parametri coincide
            if(callFunOpStat.getListExpr()!=null)
                numeroParametriChiamata=callFunOpStat.getListExpr().size();
            if(numeroParamteri!=numeroParametriChiamata){
                throw new Eccezioni.CallFunNumParamError(); //Il Numero di parametri non coincide
            }else{
                //Verifico che il tipo coincide
                for(int i=0;i<numeroParametriChiamata;i++){
                    Expr expr=(callFunOpStat.getListExpr().get(i));
                    String tipo= (String) expr.accept(this);
                    //Controllo se ce qualche riduzione
                    boolean isCompatibile=false;
                    for(String[] riduzione: compatibilita){
                        if(inputParam.get(i).equals(riduzione[0])&&tipo.equals(riduzione[1]))
                            isCompatibile=true;
                    }
                    if(!isCompatibile) {
                        throw new Eccezioni.CallFunTypeParamError();
                    }else{
                        //Aggiungo lo stream dei parametri al ast
                        if (expr.getMode()==null)
                            expr.setMode(typeStream[i]);

                    }
                }
                return null;
            }
        }

    }

    @Override
    /**
     * Se il min e il max del for non sono integer lancio l'eccezione
     * ForExpressionTypeError
     */
    public Object visit(ForOp forOp) throws Exception {

        //Verifico che il limite minimo e massimo (e1 ed e2) sono constanti intere
        String typeE1= (String) forOp.getId().getExpr().accept(this);
        String typeE2=(String) forOp.getCons().accept(this);
        if(!typeE1.equals("integer") || !typeE2.equals("integer"))
            throw new Eccezioni.ForExpressionTypeError();

        //Entro nello scope
        currentScope=forOp.getSymbolTable();
        forOp.getBodyop().accept(this);
        //Esco dallo scope
        currentScope=forOp.getSymbolTable().getFather();

        return null;
    }

    @Override
    /**
     * Se l'espressione nella condizione non e un booleano
     * lancio l'eccezione ConditionNotValid
     */
    public Object visit(IfStatOp ifStatOp) throws Exception {

        //Ottengo il tipo del espressione
        String tipo= (String) ifStatOp.getExpr().accept(this);
        if (!tipo.equals("boolean"))
            throw new Eccezioni.ConditionNotValid();

        //BodyThen
        //Entro nello scope
        currentScope=ifStatOp.getSymbolTableThen();
        //Ottengo il tipo del body
        ifStatOp.getBodyThen().accept(this);
        //Esco dallo scope
        currentScope=ifStatOp.getSymbolTableThen().getFather();

        //BodyElse
        if(ifStatOp.getBodyElse()!=null) {
            //Entro nello scope
            currentScope=ifStatOp.getSymbolTableElse();
            ifStatOp.getBodyElse().accept(this);
            //Esco dallo sope
            currentScope=ifStatOp.getSymbolTableElse().getFather();
        }
        return null;
    }

    @Override

    public Object visit(ReadOp readOp) throws Exception {

        //Controllo se gli identificatori usati,sono stati dichiarati
        for (Identifier id:readOp.getListId()){
            id.accept(this);
        }
        return null;
    }

    @Override
    /**
     * Se il tipo di return non coincide con il tipo della funzione
     * che lo possiede lancio l'eccezione ReturnError
     */
    public Object visit(ReturnOp returnOp) throws Exception {
        String tipo="void";
        returnPresente=true;
        //Ottengo il tipo dell'espressione l'espressione
        if(returnOp.getExpr()!=null) {
            tipo = (String) returnOp.getExpr().accept(this);
        }

        //Verifico che il tipo del return coincide con quello della funzione
        String tipoFunzione= currentScope.getTypeFun();
        boolean lancioEccezione=true;
        for (String[] comp:compatibilita) {
            if (tipoFunzione.equals(comp[0]) && tipo.equals(comp[1])){
                lancioEccezione=false;
            }
        }

        if(lancioEccezione==true) {
            throw new Eccezioni.ReturnError();
        }

        
        return null;
    }

    @Override
    public Object visit(Statement statement) throws Exception {

        if(statement instanceof AssignOp){
            return ((AssignOp) statement).accept(this);
        }
        if(statement instanceof CallFunOpStat){
            return ((CallFunOpStat) statement).accept(this);
        }
        if(statement instanceof ForOp){
            return ((ForOp) statement).accept(this);
        }
        if(statement instanceof IfStatOp){
            return ((IfStatOp) statement).accept(this);
        }
        if(statement instanceof ReadOp){
            return ((ReadOp) statement).accept(this);
        }
        if(statement instanceof WhileOp){
            return ((WhileOp) statement).accept(this);
        }
        if(statement instanceof WriteOp){
            return ((WriteOp) statement).accept(this);
        }


        return null;
    }

    @Override
    /**
     * Se la condizione di while non è un booleano lancio l'errore
     * conditionNotValid
     */
    public Object visit(WhileOp whileOp) throws Exception {

        //Ottengo il tipo dell'espressione nella condizione
        String condition= (String) whileOp.getExpr().accept(this);
        if (!condition.equals("boolean"))
            throw new Eccezioni.ConditionNotValid();

        //Entro nello scope
        currentScope=whileOp.getSymbolTable();
        whileOp.getBody().accept(this);
        //Esco dallo scope
        currentScope=whileOp.getSymbolTable().getFather();

        return null;
    }

    @Override
    public Object visit(WriteOp writeOp) throws Exception {

        for(Expr expr:writeOp.getListExpr()) {
            expr.accept(this);

        }

        return null;
    }

    @Override
    /**
     * Regola di inferenza:
     *  Gamma implica che l'operazione op1 tra l'espressione e1 e l'espressione e2 è di tipo tau se,
     *  gamma implica che l'espressione e1 è di tipo tau1,
     *  gamma implica che l'espressione e2 è di tipo tau2 e
     *  optype2(op2,tau1,tau2) è uguale a tau.
     *
     *  Tabella optype2: combinazioniAritOp, combinazioniStrOp, combinazioniBooleanOp, combinazioniRelOp
     */
    public Object visit(AritAndRelOp aritAndRelOp) throws Exception {
        //Ottengo il tipo dell'espressione1
        String typeExpr1= (String) aritAndRelOp.getExpr1().accept(this);
        //Ottengo il tipo dell'espressione2
        String typeExpr2= (String) aritAndRelOp.getExpr2().accept(this);

        //Ottengo il tipo di operazione
        String typeOp=aritAndRelOp.getTypeOp();
        if(typeOp.equals("AddOp") || typeOp.equals("DiffOp") || typeOp.equals("MulOp") || typeOp.equals("DivOp") |
            typeOp.equals("PowOp")){
           //Si tratta di un operazione aritmetica

            for (String[] combinazione:combinazioniAritOp) {
                if (typeExpr1.equals(combinazione[0]) && typeExpr2.equals(combinazione[1])){
                    aritAndRelOp.setTipoEspressione(combinazione[2]);
                    return combinazione[2];
                }
            }

           throw new Eccezioni.ArithmeticOpError();
        }


        if(typeOp.equals("GTOp") || typeOp.equals("GEOp") || typeOp.equals("LTOp") || typeOp.equals("LEOp") |
                typeOp.equals("EQOp") || typeOp.equals("NEOp")){
            //Si tratta di un operazione relazionale
            for (String[] combinazione:combinazioniRelOp) {
                if (typeExpr1.equals(combinazione[0]) && typeExpr2.equals(combinazione[1])) {
                    aritAndRelOp.setTipoEspressione(combinazione[2]);
                    return combinazione[2];
                }
            }
            throw new Eccezioni.RelationalOpError();
        }

        if(typeOp.equals("StrCatOp")){
            //Si tratta di un operazione su stringhe
            for (String[] combinazione:combinazioniStrOp) {
                if (typeExpr1.equals(combinazione[0]) && typeExpr2.equals(combinazione[1])) {
                    aritAndRelOp.setTipoEspressione(combinazione[2]);
                    return combinazione[2];
                }
            }
            throw new Eccezioni.StringOpError();
        }

        if(typeOp.equals("AndOp") | typeOp.equals("OrOp")){
            //Si tratta di un operazione su stringhe
            for (String[] combinazione:combinazioniBooleanOp) {
                if (typeExpr1.equals(combinazione[0]) && typeExpr2.equals(combinazione[1])) {
                    aritAndRelOp.setTipoEspressione(combinazione[2]);
                    return combinazione[2];
                }
            }

            throw new Eccezioni.BooleanOpError();
        }

        return new Eccezioni.BinaryOpNotValid();
    }

    @Override
    /**
     * Se il numero di parametri nella chiamata non coincide col numero di parametri
     * nella dichiarazione di funzione lancio l'errore CallFunNumParamError
     * Se il tipo di parametri nella chiamata non coincide col tipo dei parametri nella
     * dichiarazione di funzione lancio l'errore CallFunTypeParamError()
     *
     */
    public Object visit(CallFunOpExpr callFunOpExpr) throws Exception {


        //Verifico che il numero di parametri nella chiamata coincide
        RowTable result=currentScope.lookup(callFunOpExpr.getIdentifier().getLessema());
        if(result==null){
            throw new Eccezioni.NoDeclarationError(); //La funzione non è stata dichairata
        }else{
            TypeField.TypeFieldFunction dichiarazione =(TypeField.TypeFieldFunction) result.getType();
            ArrayList<String> inputParam=dichiarazione.getInputParam();
            String[] typeStream=result.getPropreties().split(",");
            int numeroParamteri=inputParam.size();
            int numeroParametriChiamata=0;
            if(callFunOpExpr.getListExpr()!=null)
                numeroParametriChiamata=callFunOpExpr.getListExpr().size();

            if(numeroParamteri!=numeroParametriChiamata){
                throw new Eccezioni.CallFunNumParamError(); //Il Numero di parametri non coincide
            }else{
                //Verifico che il tipo coincide
                for(int i=0;i<numeroParametriChiamata;i++){
                    Expr expr=(callFunOpExpr.getListExpr().get(i));
                    //Controllo se ce qualche riduzione
                    String tipo= (String) expr.accept(this);
                    boolean isCompatibile=false;
                    for(String[] riduzione: compatibilita){
                        if(inputParam.get(i).equals(riduzione[0])&&tipo.equals(riduzione[1]))
                            isCompatibile=true;
                    }
                    if(!tipo.equals(inputParam.get(i))) {
                        throw new Eccezioni.CallFunTypeParamError();
                    }else{
                        //Aggiungo lo stream
                        if (expr.getMode()==null)
                            expr.setMode(typeStream[i]);

                    }
                }
                if(dichiarazione.getOutputParam()!=null && dichiarazione.getOutputParam().size()>0) {
                    callFunOpExpr.setTipoEspressione(dichiarazione.getOutputParam().get(0));
                    return dichiarazione.getOutputParam().get(0);
                }else
                    return null;
            }
        }

    }

    @Override
    /**
     * Regola di inferenza:
     *  Gamma implica che integer_const è uguale a integer
     *  Gamma implica che real_const è uguale a float
     *  Gamma implica che string_const è uguale a string
     *  Gamma implica che char_const è uguale a char
     *  Gamma implica che true è uguale a boolean
     *  Gamma implica che false è uguale a boolean
     */
    public Object visit(ConstOp constOp) throws Exception {
        String type = constOp.getTypeConst();
        if (type.equals("boolean_const")) {
            constOp.setTipoEspressione("boolean");
            return "boolean";
        }
        if (type.equals("integer_const")) {
            constOp.setTipoEspressione("integer");
            return "integer";
        }
        if (type.equals("real_const")) {
            constOp.setTipoEspressione("float");
            return "float";
        }
        if (type.equals("string_const")) {
            constOp.setTipoEspressione("string");
            return "string";
        }
        if (type.equals("char_const")) {
            constOp.setTipoEspressione("char");
            return "char";
        }

        return null;
    }

    @Override
    public Object visit(Expr expr) throws Exception {
        String type = null;
        if(expr instanceof IdInitOp)
            type= (String) ((IdInitOp) expr).accept(this);
        if(expr instanceof ConstOp)
            type= (String) ((ConstOp) expr).accept(this);
        if(expr instanceof AritAndRelOp)
            type= (String) ((AritAndRelOp) expr).accept(this);
        if(expr instanceof UnaryOp)
            type= (String) ((UnaryOp) expr).accept(this);
        if(expr instanceof Identifier)
            type= (String) ((Identifier) expr).accept(this);
        if(expr instanceof CallFunOpExpr)
            type= (String) ((CallFunOpExpr) expr).accept(this);

        return type;
    }

    @Override
    /**
     * Regola di inferenza:
     *  Gamma implica che l'identificatore ha tipo tau se la lookup
     *  dell'identificatore in gamma restituisce come tipo tau
     */
    public Object visit(Identifier identifier) throws Exception {


        RowTable result=currentScope.lookup(identifier.getLessema());
        if(result==null) {
            System.out.println(identifier.getLessema());
            throw new Eccezioni.NoDeclarationError();
        }else{

            if(result.getType() instanceof TypeField.TypeFieldFunction)
                return ((TypeField.TypeFieldFunction) result.getType()).getOutputParam().get(0);
            else {
                identifier.setTipoEspressione(((TypeField.TypeFieldVar) result.getType()).getType());
                return ((TypeField.TypeFieldVar) result.getType()).getType();
            }

        }

    }

    @Override
    public Object visit(IdInitObbOp idInitObbOp) throws Exception {
        return null;
    }

    @Override
    public Object visit(IdInitOp idInitOp) throws Exception {
        String type=(String) idInitOp.getExpr().accept(this);
        return type;
    }

    @Override
    /**
     * Regola di inferenza:
     *  Gamma implica che l'oerazione op1 sull'espressione e è di tipo tau se, gamma implica che e è di tipo tau1
     *  e optype1(op1,tau1) è tau.
     *
     *  Tabella optype1: combinazioniMinus, combinazioniNot
     */
    public Object visit(UnaryOp unaryOp) throws Exception {
        //Ottengo il tipo dell'espressione
        String typeExpr= (String) unaryOp.getExpr().accept(this);
        //Ottengo il tipo di operazione
        String typeOp=unaryOp.getType();
        if (typeOp.equals("UminusOp")){
            for(String[] combinazione: combinazioniMinus){
                if(typeExpr.equals(combinazione[0])) {
                    unaryOp.setTipoEspressione(combinazione[1]);
                    return combinazione[1];
                }
            }
            throw new Eccezioni.MinusOpError();

        }
        if (typeOp.equals("NotOp")){
            for(String[] combinazione: combinazioniNot){
                if(typeExpr.equals(combinazione[0])){
                    unaryOp.setTipoEspressione(combinazione[1]);
                    return combinazione[1];
                }
            }
            throw new Eccezioni.NotOpError();
        }

        throw new Eccezioni.UnaryOpNotValid();
    }
}
