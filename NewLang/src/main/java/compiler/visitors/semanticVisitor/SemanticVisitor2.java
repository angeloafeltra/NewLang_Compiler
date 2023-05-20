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

    private static final String INTEGER="integer";
    private static final String FLOAT="float";
    private static final String CHAR="char";
    private static final String STRING="string";
    private static final String BOOLEAN="boolean";
    private static final String VOID="void";
    private SymbolTable currentScope;

    private static final String[][] combinazioniAritOp= { {INTEGER, INTEGER, INTEGER},
                                                        {INTEGER, FLOAT, FLOAT},
                                                        {FLOAT, INTEGER, FLOAT},
                                                        {FLOAT, FLOAT, FLOAT} };

    private static final String[][] combinazioniStrOp= { {STRING, STRING, STRING},
                                                        {STRING, FLOAT,STRING},
                                                        {STRING,INTEGER,STRING},
                                                        {STRING, CHAR, STRING},
                                                        {INTEGER,STRING,STRING},
                                                        {FLOAT,STRING,STRING},
                                                        {CHAR,STRING,STRING},
                                                        {BOOLEAN,STRING,STRING},
                                                        {STRING,BOOLEAN,STRING}};

    private static final String[][] combinazioniBooleanOp= { {BOOLEAN, BOOLEAN, BOOLEAN} };

    private static final String[][] combinazioniRelOp= { {INTEGER, INTEGER, BOOLEAN},
                                                        {INTEGER, FLOAT,BOOLEAN},
                                                        {FLOAT, INTEGER, BOOLEAN},
                                                        {FLOAT, FLOAT, BOOLEAN},
                                                        {STRING,STRING,BOOLEAN},
                                                        {CHAR,CHAR,BOOLEAN}};

    private static final String[][] combinazioniMinus= { {INTEGER, INTEGER},
                                                        {FLOAT, FLOAT} };


    private static final String[][] combinazioniNot= { {BOOLEAN, BOOLEAN} };

    private static final String[][] compatibilita= {{INTEGER,INTEGER},
                                                    {FLOAT,FLOAT},
                                                    {FLOAT,INTEGER},
                                                    {CHAR,CHAR},
                                                    {STRING,STRING},
                                                    {BOOLEAN,BOOLEAN},
                                                    {VOID,VOID}};

    private boolean returnPresente;


    @Override
    public Object visit(ProgramOp programOp) throws Exception {

        currentScope=programOp.getSymbolTable();
        if(programOp.getVarDeclList()!=null){
            for (VarDeclOp variable: programOp.getVarDeclList())
                variable.accept(this);
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
        if(returnPresente==false && !funOp.getType().equals(VOID))
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
            for(VarDeclOp variable:bodyOp.getListVar())
                variable.accept(this);
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

        RowTable result=currentScope.lookup(callFunOpStat.getIdentifier().getLessema());
        if (result==null) throw new Eccezioni.NoDeclarationError();//La funzione non è stata dichairata

        TypeField.TypeFieldFunction dichiarazione= (TypeField.TypeFieldFunction) result.getType();
        ArrayList<String> inputParam= (ArrayList<String>) dichiarazione.getInputParam();//Ottengo il tipo dei parametri d'input della funzione
        String[] typeStream=result.getPropreties().split(",");//Ottengo il type stream dei parametri

        int numeroParamteri=inputParam.size();
        int numeroParametriChiamata= callFunOpStat.getListExpr()!=null ? callFunOpStat.getListExpr().size() : 0;


        if(numeroParamteri!=numeroParametriChiamata) throw new Eccezioni.CallFunNumParamError(); //Il Numero di parametri non coincide

        //Verifico che il tipo coincide
        for(int i=0;i<numeroParametriChiamata;i++){
            Expr expr=(callFunOpStat.getListExpr().get(i));
            String tipo= (String) expr.accept(this);
            checkRiduzioni(inputParam.get(i),tipo);
            //Aggiungo lo stream dei parametri al ast
            if (expr.getMode()==null)
                expr.setMode(typeStream[i]);
        }
        return null;
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
        if(!typeE1.equals(INTEGER) || !typeE2.equals(INTEGER))
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
        if (!tipo.equals(BOOLEAN))
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

        if(lancioEccezione) {
            throw new Eccezioni.ReturnError();
        }

        
        return null;
    }

    @Override
    public Object visit(Statement statement) throws Exception {

        if(statement instanceof AssignOp){
            return statement.accept(this);
        }
        if(statement instanceof CallFunOpStat){
            return statement.accept(this);
        }
        if(statement instanceof ForOp){
            return statement.accept(this);
        }
        if(statement instanceof IfStatOp){
            return statement.accept(this);
        }
        if(statement instanceof ReadOp){
            return statement.accept(this);
        }
        if(statement instanceof WhileOp){
            return statement.accept(this);
        }
        if(statement instanceof WriteOp){
            return statement.accept(this);
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
        if (!condition.equals(BOOLEAN))
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
        if(typeOp.equals("AddOp") || typeOp.equals("DiffOp") || typeOp.equals("MulOp") || typeOp.equals("DivOp") ||
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


        if(typeOp.equals("GTOp") || typeOp.equals("GEOp") || typeOp.equals("LTOp") || typeOp.equals("LEOp") ||
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

        if(typeOp.equals("AndOp") || typeOp.equals("OrOp")){
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
        if(result==null) throw new Eccezioni.NoDeclarationError(); //La funzione non è stata dichairata

        TypeField.TypeFieldFunction dichiarazione =(TypeField.TypeFieldFunction) result.getType();
        ArrayList<String> inputParam= (ArrayList<String>) dichiarazione.getInputParam();
        String[] typeStream=result.getPropreties().split(",");
        int numeroParamteri=inputParam.size();
        int numeroParametriChiamata= callFunOpExpr.getListExpr()!=null ? callFunOpExpr.getListExpr().size() : 0;

        if(numeroParamteri!=numeroParametriChiamata) throw new Eccezioni.CallFunNumParamError(); //Il Numero di parametri non coincide

        //Verifico che il tipo coincide
        for(int i=0;i<numeroParametriChiamata;i++){
            Expr expr=(callFunOpExpr.getListExpr().get(i));
            //Controllo se ce qualche riduzione
            String tipo= (String) expr.accept(this);
            checkRiduzioni(inputParam.get(i),tipo);
            //Aggiungo lo stream
            if (expr.getMode()==null)
                expr.setMode(typeStream[i]);

        }
        if(dichiarazione.getOutputParam()!=null && !dichiarazione.getOutputParam().isEmpty()) {
            callFunOpExpr.setTipoEspressione(dichiarazione.getOutputParam().get(0));
            return dichiarazione.getOutputParam().get(0);
        }else
            return null;

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
            constOp.setTipoEspressione(BOOLEAN);
            return BOOLEAN;
        }
        if (type.equals("integer_const")) {
            constOp.setTipoEspressione(INTEGER);
            return INTEGER;
        }
        if (type.equals("real_const")) {
            constOp.setTipoEspressione(FLOAT);
            return FLOAT;
        }
        if (type.equals("string_const")) {
            constOp.setTipoEspressione(STRING);
            return STRING;
        }
        if (type.equals("char_const")) {
            constOp.setTipoEspressione(CHAR);
            return CHAR;
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

    private void checkRiduzioni(String tipo1,String tipo2) throws Eccezioni.CallFunTypeParamError {
        boolean isCompatibile=false;
        for(String[] riduzione: compatibilita){
            if(tipo1.equals(riduzione[0])&&tipo2.equals(riduzione[1]))
                isCompatibile=true;
        }
        if(!isCompatibile) throw new Eccezioni.CallFunTypeParamError();
    }




}
