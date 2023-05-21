package compiler.visitors;

import compiler.nodi.*;
import compiler.nodi.expr.*;
import compiler.nodi.statement.*;
import compiler.symbolTable.RowTable;
import compiler.symbolTable.SymbolTable;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static compiler.visitors.ScriptConstant.SUPPORT_FUN_INTEGER_TO_STR;
import static compiler.visitors.ScriptConstant.SUPPORT_FUN_REAL_TO_STR;
import static compiler.visitors.ScriptConstant.SUPPORT_FUN_CHAR_TO_STR;
import static compiler.visitors.ScriptConstant.SUPPORT_FUN_BOOL_TO_STR;
import static compiler.visitors.ScriptConstant.SUPPORT_FUN_STR_CONCAT;
import static compiler.visitors.ScriptConstant.SUPPORT_FUN_READ_STR;
import static compiler.visitors.ScriptConstant.SUPPORT_FUN_STR_TO_BOOL;
import static compiler.visitors.ScriptConstant.LIBRERIE;
import static compiler.visitors.ScriptConstant.PROTOTIPI_FUN_SUP;

public class TranslatorVisitor implements Visitor{

    public static final String DIR_TEST_FILES="test_files";
    public static final String DIR_FILE_C="c_out";
    public static final String ALTERNATIV_MAIN="main2";
    public static final String CONST_STRING="string";
    public static final String CONST_INTEGER="integer";
    public static final String CONST_FLOAT="float";
    public static final String CONST_BOOLEAN="boolean";
    public static final String CONST_CHAR="char";
    public static final String CONST_ARITMETICA="aritmetica";
    public static final String CONST_RELAZIONALE="relazionale";
    public static final String CONST_BOOLEANA="booleana";
    public static final String CONST_STRCAT="strcat";
    public static final String CONST_INTEGER_TO_STR="integer_to_str(";
    public static final String CONST_REAL_TO_STR="real_to_str(";
    public static final String CONST_CHAR_TO_STR="char_to_str(";
    public static final String CONST_BOOL_TO_STR="bool_to_str(";

    public  String FILE_NAME;
    private  FileWriter fileWriter;
    private boolean main=false;
    private SymbolTable currentScope;

    private static final String[][] CONV_TYPE_OP= { {"AddOp", "+"},
                                                    {"DiffOp", "-"},
                                                    {"MulOp", "*"},
                                                    {"DivOp", "/"},
                                                    {"PowOp", "pow"},
                                                    {"GTOp", ">"},
                                                    {"GEOp", ">="},
                                                    {"LTOp", "<"},
                                                    {"LEOp", "<="},
                                                    {"NEOp", "!="},
                                                    {"EQOp", "=="},
                                                    {"StrCatOp", CONST_STRCAT},
                                                    {"AndOp", "&&"},
                                                    {"OrOp", "||"},
                                                    {"UminusOp", "-1*"},
                                                    {"NotOp", "!"}};


    public TranslatorVisitor(String filNameToConvert){
        FILE_NAME=filNameToConvert;
    }
    public TranslatorVisitor(){this.FILE_NAME="c_gen.c";}


    @Override
    public Object visit(ProgramOp programOp) throws Exception {
        currentScope=programOp.getSymbolTable();

        if(!inizializeFileWriter()) return null;
        fileWriter.write(LIBRERIE);// Inserisco le librerie
        fileWriter.write("\n");

        //Inserisco i prototipi delle funzioni
        fileWriter.write("//Prototipi\n");
        fileWriter.write(PROTOTIPI_FUN_SUP);
        generateListPrototipi((ArrayList<FunOp>) programOp.getFunOpList(),programOp.getMain());
        fileWriter.write("\n");


        //Inserisco le variabili
        fileWriter.write("//Variabili Globali\n");
        generaVariabiliGlobali((ArrayList<VarDeclOp>) programOp.getVarDeclList());
        fileWriter.write("\n");


        generaMain(programOp.getMain());//Inserisco il main
        generaFunzioni((ArrayList<FunOp>) programOp.getFunOpList(),programOp.getMain(),currentScope); //Inserisco le funzioni


        fileWriter.write(SUPPORT_FUN_INTEGER_TO_STR);
        fileWriter.write(SUPPORT_FUN_REAL_TO_STR);
        fileWriter.write(SUPPORT_FUN_CHAR_TO_STR);
        fileWriter.write(SUPPORT_FUN_BOOL_TO_STR);
        fileWriter.write(SUPPORT_FUN_STR_CONCAT);
        fileWriter.write(SUPPORT_FUN_READ_STR);
        fileWriter.write(SUPPORT_FUN_STR_TO_BOOL);
        fileWriter.close();
        return null;
    }

    @Override
    public Object visit(VarDeclOp varDecl) throws Exception {
        String tipo=convertType(varDecl.getType());

        for(Expr expr: varDecl.getExprList()){
            if(expr instanceof IdInitObbOp){
                String espressione= (String)((IdInitObbOp)expr).accept(this);
                fileWriter.write(espressione+";\n");
            }
            if(expr instanceof  IdInitOp) {
                String espressione= (String)((IdInitOp)expr).accept(this);
                fileWriter.write(tipo+" "+ espressione+";\n");
            }
        }


        return null;
    }


    @Override
    public Object visit(FunOp funOp) throws Exception {
        String id=funOp.getIdentificatore().getLessema();
        String tipo=convertType(funOp.getType());
        fileWriter.write(tipo+" "+id+ "(");

        if(funOp.getParams()!=null && !funOp.getParams().isEmpty()) {
            ArrayList<String> parms = new ArrayList<>(); //Lista di parametri
            for(ParDeclOp param:funOp.getParams()){
                parms.addAll((Collection<? extends String>) param.accept(this));
            }
            StringBuilder parametri=new StringBuilder();
            for(String par:parms){
                parametri.append(par+", ");
            }
            parametri= new StringBuilder(parametri.toString().substring(0, parametri.length() - 2));
            fileWriter.write(parametri.toString());
        }

        fileWriter.write("){\n");

        //Inserisco il body
        funOp.getBody().accept(this);

        fileWriter.write("}\n");
        return null;
    }

    @Override
    public Object visit(ParDeclOp parDeclOp) throws Exception {
        ArrayList<String> params=new ArrayList<>();
        String tipo=convertType(parDeclOp.getType());

        String typeStream=parDeclOp.getTypeStream();
        if(typeStream.equals("out"))
            typeStream="*";
        else
            typeStream="";
        for (Identifier id: parDeclOp.getIdList())
            params.add(tipo + " " + typeStream + id.getLessema());

        return params;
    }



    @Override
    public Object visit(BodyOp bodyOp) throws Exception {
        generaVariabiliGlobali((ArrayList<VarDeclOp>) bodyOp.getListVar());

        if(bodyOp.getListStatement()!=null){
            Collections.reverse(bodyOp.getListStatement());
            for(Statement stmt: bodyOp.getListStatement())
                if(!(main && stmt instanceof ReturnOp)) stmt.accept(this);
        }
        return null;
    }

    @Override
    public Object visit(AssignOp assignOp) throws Exception {
        ArrayList<Identifier> listaId=assignOp.getListId();
        ArrayList<Expr> listExpr=assignOp.getListExpr();
        for(int i=0;i<listaId.size();i++){
            String identificatore= (String) listaId.get(i).accept(this);
            String espressione= (String) listExpr.get(i).accept(this);

            if(listaId.get(i).getMode()!=null && listaId.get(i).getMode().equals("out"))
                fileWriter.write("*"+identificatore+"="+espressione+";\n");
            else
                fileWriter.write(identificatore+"="+espressione+";\n");
        }

        return null;
    }

    @Override
    public Object visit(CallFunOpStat callFunOpStat) throws Exception {
        String id=callFunOpStat.getIdentifier().getLessema();
        if(id.equals("main"))
            id=ALTERNATIV_MAIN;
        StringBuilder espressione=new StringBuilder();
        fileWriter.write(id+"(");

        if(callFunOpStat.getListExpr()!=null) {
            for (Expr expr : callFunOpStat.getListExpr()){
                String tipoChiamata="";
                if(expr.getMode().equals("out"))
                    tipoChiamata="&";
                espressione.append(tipoChiamata+(String) expr.accept(this));
                espressione.append(",");
            }
            fileWriter.write(espressione.toString().substring(0,espressione.length()-1));
        }else{
            fileWriter.write(espressione.toString());
        }
        fileWriter.write(");\n");
        return null;

    }

    @Override
    public Object visit(ForOp forOp) throws Exception {
        currentScope=forOp.getSymbolTable();
        String init=(String) forOp.getId().accept(this); //Esempio: i<<1
        String variable=forOp.getId().getId().getLessema(); //Esempio i
        String ind1=((ConstOp)forOp.getId().getExpr()).getLessema();
        String ind2= (String) forOp.getCons().accept(this);
        if(Integer.valueOf(ind1)<=Integer.valueOf(ind2))
            fileWriter.write("for(int "+init+";"+variable+"<="+ind2+";"+variable+"++){\n");
        else
            fileWriter.write("for(int "+init+";"+variable+">="+ind2+";"+variable+"--){\n");
        forOp.getBodyop().accept(this);
        fileWriter.write("}\n");
        currentScope=forOp.getSymbolTable().getFather();
        return null;
    }

    @Override
    public Object visit(IfStatOp ifStatOp) throws Exception {
        String condition= (String) ifStatOp.getExpr().accept(this);
        fileWriter.write("if("+condition+"){\n");
        currentScope=ifStatOp.getSymbolTableThen();
        ifStatOp.getBodyThen().accept(this);
        currentScope=ifStatOp.getSymbolTableThen().getFather();
        fileWriter.write("}else{\n");
        if(ifStatOp.getBodyElse()!=null) {
            currentScope=ifStatOp.getSymbolTableElse();
            ifStatOp.getBodyElse().accept(this);
            currentScope=ifStatOp.getSymbolTableElse().getFather();
        }
        fileWriter.write("}\n");
        return null;
    }

    @Override
    public Object visit(ReadOp readOp) throws Exception {

        String espressione="";
        if(readOp.getExpr()!=null) {
            espressione = (String) readOp.getExpr().accept(this);
        }

        if(!espressione.equals(""))
            fileWriter.write("printf("+espressione+");\n"); //Stampo il messaggio della read

        for(Identifier id:readOp.getListId()){
            switch (id.getTipoEspressione()){
                case CONST_INTEGER:
                    fileWriter.write("scanf(\"%d\",&" + id.getLessema() + ");\n");
                    break;
                case CONST_FLOAT:
                    fileWriter.write("scanf(\"%f\",&" + id.getLessema() + ");\n");
                    break;
                case CONST_CHAR:
                    fileWriter.write("scanf(\"%c\",&" + id.getLessema() + ");\n");
                    break;
                case  CONST_STRING:
                    fileWriter.write(id.getLessema() + "=read_str();\n");
                    break;
                case CONST_BOOLEAN:
                    fileWriter.write("scanf(\"%b\",&" + id.getLessema() + ");\n");
                    break;
                default:
            }
        }

        return null;
    }

    @Override
    public Object visit(ReturnOp returnOp) throws Exception {

        if(returnOp.getExpr()!=null) {
            String espressione = (String) returnOp.getExpr().accept(this);
            fileWriter.write("return " + espressione + ";\n");
        }else{
            fileWriter.write("return;\n");
        }

        return null;
    }

    @Override
    public Object visit(Statement statement) throws Exception {

        if(statement instanceof AssignOp){
            statement.accept(this);
        }
        if(statement instanceof CallFunOpStat){
            statement.accept(this);
        }
        if(statement instanceof ForOp){
            statement.accept(this);
        }
        if(statement instanceof IfStatOp){
            statement.accept(this);
        }
        if(statement instanceof ReadOp){
            statement.accept(this);
        }
        if(statement instanceof WhileOp){
            statement.accept(this);
        }
        if(statement instanceof WriteOp){
            statement.accept(this);
        }

        if (statement instanceof ReturnOp){
            statement.accept(this);
        }

        return null;
    }

    @Override
    public Object visit(WhileOp whileOp) throws Exception {

        currentScope=whileOp.getSymbolTable();
        String condition= (String) whileOp.getExpr().accept(this);
        fileWriter.write("while("+condition+"){\n");
        whileOp.getBody().accept(this);
        fileWriter.write("}\n");
        currentScope=whileOp.getSymbolTable().getFather();


        return null;
    }

    @Override
    public Object visit(WriteOp writeOp) throws Exception {

        for (Expr expr : writeOp.getListExpr()) {
            if(writeOp.getType().equals("writeln"))
                fileWriter.write("printf(\"%s\\n\",");
            else
                fileWriter.write("printf(\"%s\",");
            String espressione= (String) expr.accept(this);
            if(expr.getTipoEspressione().equals(CONST_INTEGER))
                espressione=CONST_INTEGER_TO_STR+espressione+")";
            if(expr.getTipoEspressione().equals(CONST_FLOAT))
                espressione=CONST_REAL_TO_STR+espressione+")";
            if(expr.getTipoEspressione().equals(CONST_CHAR))
                espressione=CONST_CHAR_TO_STR+espressione+")";
            if(expr.getTipoEspressione().equals(CONST_BOOLEAN))
                espressione=CONST_BOOL_TO_STR+espressione+")";
            fileWriter.write(espressione+");\n");
        }

        return null;
    }

    @Override
    public Object visit(AritAndRelOp aritAndRelOp) throws Exception {

        String[][] typeOpBinaria= { {"AddOp", CONST_ARITMETICA,"+"},
                {"DiffOp", CONST_ARITMETICA,"-"},
                {"MulOp", CONST_ARITMETICA,"*"},
                {"DivOp", CONST_ARITMETICA,"/"},
                {"PowOp", "potenza","pow"},
                {"GTOp", CONST_RELAZIONALE,">"},
                {"GEOp", CONST_RELAZIONALE,">="},
                {"LTOp", CONST_RELAZIONALE,"<"},
                {"LEOp", CONST_RELAZIONALE,"<="},
                {"NEOp", CONST_RELAZIONALE,"!="},
                {"EQOp", CONST_RELAZIONALE,"=="},
                {"StrCatOp", CONST_STRCAT,CONST_STRCAT},
                {"AndOp", CONST_BOOLEANA,"&&"},
                {"OrOp", CONST_BOOLEANA,"||"}};

        String tipoOperazione1="";
        String tipoOperazione2="";
        for (String[] op :typeOpBinaria){
            if (aritAndRelOp.getTypeOp().equals(op[0])){
                tipoOperazione1=op[1];
                tipoOperazione2=op[2];
            }
        }

        switch (tipoOperazione1){
            case CONST_ARITMETICA:
                return generaOpAritmetica(aritAndRelOp.getExpr1(),aritAndRelOp.getExpr2(),tipoOperazione2);
            case CONST_RELAZIONALE:
                return generaOpRelazionale(aritAndRelOp.getExpr1(),aritAndRelOp.getExpr2(),tipoOperazione2);
            case CONST_STRCAT:
                return generaOpStrCat(aritAndRelOp.getExpr1(),aritAndRelOp.getExpr2(),tipoOperazione2);
            case CONST_BOOLEANA:
                return generaOpBooleana(aritAndRelOp.getExpr1(),aritAndRelOp.getExpr2(),tipoOperazione2);
            case "potenza":
                return generaOpPow(aritAndRelOp.getExpr1(),aritAndRelOp.getExpr2(),tipoOperazione2);
            default:
                return "";
        }
    }


    @Override
    public Object visit(CallFunOpExpr callFunOpExpr) throws Exception {
        String id=callFunOpExpr.getIdentifier().getLessema();
        if(id.equals("main"))
            id=ALTERNATIV_MAIN;
        StringBuilder espressione=new StringBuilder();
        espressione.append(id+"(");
        if(callFunOpExpr.getListExpr()!=null) {
            for (Expr expr : callFunOpExpr.getListExpr()){
                String tipoChiamata="";
                if(expr.getMode().equals("out"))
                    tipoChiamata="&";
                espressione.append(tipoChiamata+(String) expr.accept(this));
                espressione.append(",");
            }
            espressione= new StringBuilder(espressione.toString().substring(0, espressione.length() - 1));
        }

        espressione.append(")");
        return espressione.toString();
    }

    @Override
    public Object visit(ConstOp constOp) throws Exception {

        String constante="";
        if(constOp.getTypeConst().equals("integer_const")){
            constante=constOp.getLessema();
        }
        if(constOp.getTypeConst().equals("real_const")){
            constante=constOp.getLessema();
        }
        if(constOp.getTypeConst().equals("char_const")){
            constante="'"+constOp.getLessema()+"'";
        }
        if(constOp.getTypeConst().equals("string_const")){
            constante="\""+constOp.getLessema()+"\"";
        }
        if(constOp.getTypeConst().equals("boolean_const")){
            constante=constOp.getLessema();
        }

        return constante;
    }

    @Override
    public Object visit(Expr expr) throws Exception {

        String espressione = "";
        if(expr instanceof ConstOp)
            espressione= (String) ((ConstOp) expr).accept(this);
        if(expr instanceof AritAndRelOp)
            espressione= (String) ((AritAndRelOp) expr).accept(this);
        if(expr instanceof UnaryOp)
            espressione= (String) ((UnaryOp) expr).accept(this);
        if(expr instanceof Identifier)
            espressione= (String) ((Identifier) expr).accept(this);
        if(expr instanceof CallFunOpExpr)
            espressione= (String) ((CallFunOpExpr) expr).accept(this);

        return espressione;

    }

    @Override
    public Object visit(Identifier identifier) throws Exception {
        String espression=identifier.getLessema();
        RowTable result=currentScope.lookup(espression);
        if(result.getPropreties().equals("out"))
            espression='*'+espression;

        return espression;
    }

    @Override
    public Object visit(IdInitObbOp idInitObbOp) throws Exception {
        String identificatore=idInitObbOp.getId().getLessema();
        String type="";

        if(((ConstOp) idInitObbOp.getExpr()).getTypeConst().equals("integer_const"))
            type="int";
        if(((ConstOp) idInitObbOp.getExpr()).getTypeConst().equals("real_const"))
            type=CONST_FLOAT;
        if(((ConstOp) idInitObbOp.getExpr()).getTypeConst().equals("char_const"))
            type = "char";
        if(((ConstOp) idInitObbOp.getExpr()).getTypeConst().equals("string_const"))
            type = "char*";
        if(((ConstOp) idInitObbOp.getExpr()).getTypeConst().equals("boolean_const"))
            type="bool";

        String const_value= (String) idInitObbOp.getExpr().accept(this);
        return type+" "+identificatore+"="+const_value;
    }

    @Override
    public Object visit(IdInitOp idInitOp) throws Exception {
        String identificatore=idInitOp.getId().getLessema();
        String espressione= (String) idInitOp.getExpr().accept(this);
        return identificatore+"="+espressione;
    }

    @Override
    public Object visit(UnaryOp unaryOp) throws Exception {

        String espressione="";
        //Ottengo il'espressione
        String exp1= (String) unaryOp.getExpr().accept(this);
        String typeOp=unaryOp.getType();
        String operazione=convertTypeOp(typeOp);
        if (typeOp.equals("UminusOp") || typeOp.equals("NotOp")){
            espressione=operazione+"("+exp1+")";
        }

        return espressione;
    }

    private String convertType(String type){
        if(type.equals(CONST_INTEGER)) type="int";
        if(type.equals(CONST_BOOLEAN)) type="bool";
        if(type.equals(CONST_STRING)) type="char*";
        return type;
    }

    private String generaPrototipo(FunOp fun) throws Exception {
        String tipoFunzione=convertType(fun.getType()); //Tipo della funzione
        String nomeFunzione=fun.getIdentificatore().getLessema(); //Nome della funzione
        StringBuilder parametri=new StringBuilder(); //Parametri Funzione
        if(fun.getParams()!=null && !fun.getParams().isEmpty()) {
            ArrayList<String> parms = new ArrayList<>(); //Lista di parametri
            for(ParDeclOp param:fun.getParams()){
                parms.addAll((Collection<? extends String>) param.accept(this));
            }
            for(String par:parms){
                parametri.append(par+", ");
            }
            parametri= new StringBuilder(parametri.toString().substring(0, parametri.length() - 2));
        }

        return tipoFunzione+" "+nomeFunzione+ "("+parametri.toString()+");\n";
    }

    private String convertTypeOp(String typeOp){

        String tipoOprazione="";
        for (String [] conversione:CONV_TYPE_OP){
            if(typeOp.equals(conversione[0])) tipoOprazione=conversione[1];
        }
        return tipoOprazione;
    }



    private boolean inizializeFileWriter() throws IOException {
        File FILE;
        // Inizializzo il file di scrittura
        if (!(new File(DIR_TEST_FILES + File.separator + DIR_FILE_C + File.separator)).exists()) {
            Files.createDirectory(Paths.get(DIR_TEST_FILES + File.separator + DIR_FILE_C + File.separator));
            FILE = new File(DIR_TEST_FILES + File.separator + DIR_FILE_C + File.separator + FILE_NAME);
            if(!FILE.createNewFile()) return false;
        }else{
            FILE = new File(DIR_TEST_FILES + File.separator + DIR_FILE_C + File.separator + FILE_NAME);
        }
        fileWriter = new FileWriter(FILE);
        return true;
    }

    private void generateListPrototipi(ArrayList<FunOp> listFun,FunOp main) throws Exception {
        if(listFun!=null){
            for(FunOp fun: listFun)
                fileWriter.write(generaPrototipo(fun));
            if (main.getIdentificatore().getLessema().equals("main"))
                main.getIdentificatore().setLessema(ALTERNATIV_MAIN);
            fileWriter.write(generaPrototipo(main)); //Prototipo Main
        }
    }

    private void generaVariabiliGlobali(ArrayList<VarDeclOp> listVar) throws Exception {

        if (listVar != null) {
            for (VarDeclOp variable : listVar) {
                String tipo = convertType(variable.getType());
                for (Expr expr : variable.getExprList())
                    if (expr instanceof Identifier)
                        fileWriter.write(tipo + " " + ((Identifier) expr).getLessema() + ";\n");

            }
            for (VarDeclOp variable : listVar) variable.accept(this);
        }
    }

    private void generaMain(FunOp main_fun) throws IOException {
        fileWriter.write("int main (int argc, char *argv[]){\n");
        fileWriter.write(main_fun.getIdentificatore().getLessema()+"(");
        //Se la funzione di start possiede dei parametri, li recupero da argv
        if(main_fun.getParams()!=null){
            fileWriter.write(generaParametriMain(main_fun));
        }

        fileWriter.write(");\n");
        fileWriter.write("return 0;\n");
        fileWriter.write("}\n");
    }

    private String generaParametriMain(FunOp main_fun){
        int i=0;
        StringBuilder parametri=new StringBuilder();
        for(ParDeclOp param: main_fun.getParams()){
            String tipo=param.getType();
            for(Identifier id:param.getIdList()){
                i++;
                switch (tipo) {
                    case CONST_STRING:
                        parametri.append("argv["+i+"],");
                        break;
                    case CONST_INTEGER:
                        parametri.append("atoi(argv["+i+"]),");
                        break;
                    case CONST_FLOAT:
                        parametri.append("atof(argv["+i+"]),");
                        break;
                    case CONST_BOOLEAN:
                        parametri.append("str_to_bool(argv["+i+"]),");
                        break;
                    case CONST_CHAR:
                        parametri.append("*argv["+i+"],");
                        break;
                    default:
                }
            }
        }
        if(!parametri.toString().equals(""))
            parametri= new StringBuilder(parametri.toString().substring(0, parametri.length() - 1));
        return parametri.toString();
    }

    private void generaFunzioni(ArrayList<FunOp> list_fun,FunOp main_fun,SymbolTable scope) throws Exception {
        //Inserisco le funzioni
        if(list_fun!=null){
            for(FunOp fun: list_fun){
                currentScope=fun.getSymbolTable();
                fun.accept(this);
                fileWriter.write("\n");
                currentScope=scope;
            }
            currentScope=main_fun.getSymbolTable();
            main_fun.accept(this);
            currentScope=scope;
        }
    }


    private String generaOpAritmetica(Expr expr1,Expr expr2,String tipoOperazione) throws Exception {
        String expr1_str= (String) expr1.accept(this);
        String expr2_str= (String) expr2.accept(this);
        return expr1_str+tipoOperazione+expr2_str;
    }

    private String generaOpRelazionale(Expr expr1,Expr expr2, String tipoOperazione) throws Exception {
        String expr1_str= (String) expr1.accept(this);
        String expr2_str= (String) expr2.accept(this);

        if (expr1.getTipoEspressione().equals(CONST_STRING) || expr1.getTipoEspressione().equals("char")){
            if(expr1.getTipoEspressione().equals(CONST_CHAR))
                expr1_str=CONST_CHAR_TO_STR+expr1_str+")";
            if(expr2.getTipoEspressione().equals(CONST_CHAR))
                expr2_str=CONST_CHAR_TO_STR+expr2_str+")";
            return "strcmp("+expr1_str+","+expr2_str+")"+tipoOperazione+"0";
        }else{
            return expr1_str + tipoOperazione + expr2_str;
        }
    }

    private String generaOpBooleana(Expr expr1,Expr expr2,String tipoOperazione) throws Exception {
        String expr1_str= (String) expr1.accept(this);
        String expr2_str= (String) expr2.accept(this);
        return expr1_str+tipoOperazione+expr2_str;
    }

    private String generaOpStrCat(Expr expr1,Expr expr2,String tipoOperazione) throws Exception {
        String expr1_str= (String) expr1.accept(this);
        String expr2_str= (String) expr2.accept(this);

        switch (expr1.getTipoEspressione()){
            case CONST_INTEGER:
                expr1_str=CONST_INTEGER_TO_STR+expr1_str+")";
                break;
            case CONST_FLOAT:
                expr1_str=CONST_REAL_TO_STR+expr1_str+")";
                break;
            case CONST_CHAR:
                expr1_str=CONST_CHAR_TO_STR+expr1_str+")";
                break;
            case CONST_BOOLEAN:
                expr1_str=CONST_BOOL_TO_STR+expr1_str+")";
                break;
            default:
        }
        switch (expr2.getTipoEspressione()){
            case CONST_INTEGER:
                expr2_str=CONST_INTEGER_TO_STR+expr2_str+")";
                break;
            case CONST_FLOAT:
                expr2_str=CONST_REAL_TO_STR+expr2_str+")";
                break;
            case CONST_CHAR:
                expr2_str=CONST_CHAR_TO_STR+expr2_str+")";
                break;
            case CONST_BOOLEAN:
                expr2_str=CONST_BOOL_TO_STR+expr2_str+")";
                break;
            default:
        }
        return "str_concat("+expr1_str+","+expr2_str+")";
    }

    private String generaOpPow(Expr expr1,Expr expr2,String tipoOperazione) throws Exception {
        String expr1_str= (String) expr1.accept(this);
        String expr2_str= (String) expr2.accept(this);
        return tipoOperazione+"("+expr1_str+","+expr2_str+")";
    }

}