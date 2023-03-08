package main.java.visitors;

import main.java.nodi.*;
import main.java.nodi.expr.*;
import main.java.nodi.statement.*;
import main.java.symbolTable.RowTable;
import main.java.symbolTable.SymbolTable;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class TranslatorVisitor2 implements Visitor{

    public static String FILE_NAME = "c_gen.c";
    private static File FILE;
    private static FileWriter fileWriter;
    private static int currentTab = 0;
    private boolean main=false;
    private SymbolTable currentScope;

    @Override

    public Object visit(ProgramOp programOp) throws Exception {

        currentScope=programOp.getSymbolTable();
        // Inizializzo il file di scrittura
        if (!(new File("test_files" + File.separator + "c_out" + File.separator)).exists()) {
            Files.createDirectory(Paths.get("test_files" + File.separator + "c_out" + File.separator));
        }
        FILE = new File("test_files" + File.separator + "c_out" + File.separator + FILE_NAME);
        FILE.createNewFile();
        fileWriter = new FileWriter(FILE);


        // Inserisco le librerie
        addLibrerie();
        fileWriter.write("\n");

        //Inserisco i prototipi delle funzioni
        fileWriter.write("//Prototipi\n");
        addPrototipiFunzioniDiSupporto();
        if(programOp.getFunOpList()!=null){
            for(FunOp fun:programOp.getFunOpList()){
                String prototipo=generaPrototipo(fun);
                fileWriter.write(prototipo);
            }
            //Se la funzione start si chiama main
            //cambio il nome in main2, poiche avrei
            //un errore altrimenti
            if (programOp.getMain().getIdentificatore().getLessema().equals("main"))
                programOp.getMain().getIdentificatore().setLessema("main2");
            String prototipo=generaPrototipo(programOp.getMain()); //Prototipo Main
            fileWriter.write(prototipo);
        }
        fileWriter.write("\n");


        //Inserisco le variabili
        fileWriter.write("//Variabili Globali\n");
        if(programOp.getVarDeclList()!=null){

            for(VarDeclOp var:programOp.getVarDeclList()){
                String tipo=convertType(var.getType());
                for(Expr expr:var.getExprList()){
                    if(expr instanceof Identifier) {
                        fileWriter.write(tipo+" " + ((Identifier) expr).getLessema() + ";\n");
                    }
                }
            }

            for(VarDeclOp var:programOp.getVarDeclList()){
                var.accept(this);
            }
        }
        fileWriter.write("\n");


        //Inserisco il main
        fileWriter.write("int main (int argc, char *argv[]){\n");

        //Richiamo la funzione di start nel main
        FunOp main=programOp.getMain();
        fileWriter.write(main.getIdentificatore().getLessema()+"(");
        //Se la funzione di start possiede dei parametri, li recupero da argv
        if(main.getParams()!=null){
            int i=0;
            String parametri="";
            for(ParDeclOp param: main.getParams()){
                String tipo=param.getType();
                for(Identifier id:param.getIdList()){
                    i++;
                    if(!tipo.equals("string")){
                        if(tipo.equals("integer")){
                            parametri=parametri+"atoi(argv["+i+"]),";
                        }
                        if(tipo.equals("float")){
                            parametri=parametri+"atof(argv["+i+"]),";
                        }
                        if(tipo.equals("boolean")){
                            parametri=parametri+"str_to_bool(argv["+i+"]),";
                        }
                        if(tipo.equals("char")){
                            parametri=parametri+"*argv["+i+"],";
                        }
                    }
                    else
                        parametri=parametri+"argv["+i+"],";
                }
            }
            if(!parametri.equals(""))
                parametri=parametri.substring(0,parametri.length()-1);
            fileWriter.write(parametri);
        }

        fileWriter.write(");\n");

        /**
        currentScope=programOp.getMain().getSymbolTable();
        programOp.getMain().getBody().accept(this);
        currentScope=programOp.getSymbolTable();
         */
        fileWriter.write("return 0;\n");
        fileWriter.write("}\n");


        //Inserisco le funzioni
        if(programOp.getFunOpList()!=null){
            for(FunOp fun: programOp.getFunOpList()){
                currentScope=fun.getSymbolTable();
                fun.accept(this);
                fileWriter.write("\n");
                currentScope=programOp.getSymbolTable();
            }
            currentScope=programOp.getMain().getSymbolTable();
            programOp.getMain().accept(this);
            currentScope=programOp.getSymbolTable();

        }


        addFunzioniDiSupporto();
        fileWriter.close();
        return null;
    }

    @Override
    /**
     * Genero il codice c per le variabili inizializzate o quelle di tipo var
     * il codice c delle variabili non inizializzate viene generato direttamente
     * dalla root per correggere l'utilizzo di una variabile prima della sua dichiarazione
     * permesso dal linguaggio NewLang
     */
    public Object visit(VarDeclOp varDecl) throws Exception {
        String tipo=convertType(varDecl.getType());

        for(Expr expr: varDecl.getExprList()){
            /*
            if(expr instanceof Identifier) {
                fileWriter.write(tipo+" " + ((Identifier) expr).getLessema() + ";\n");
            }*/
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

        if(funOp.getParams()!=null && funOp.getParams().size()>0) {
            ArrayList<String> parms = new ArrayList<String>(); //Lista di parametri
            for(ParDeclOp param:funOp.getParams()){
                parms.addAll((Collection<? extends String>) param.accept(this));
            }
            String parametri="";
            for(String par:parms){
                parametri=parametri+par+", ";
            }
            parametri=parametri.substring(0,parametri.length()-2);
            fileWriter.write(parametri);
        }

        fileWriter.write("){\n");

        //Inserisco il body
        funOp.getBody().accept(this);

        fileWriter.write("}\n");
        return null;
    }

    @Override
    public Object visit(ParDeclOp parDeclOp) throws Exception {
        ArrayList<String> params=new ArrayList<String>();
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
        if(bodyOp.getListVar()!=null){
            //Inserisco prima solo le dichiarazioni degli identificatori(ese: integer id;)
            for(VarDeclOp var:bodyOp.getListVar()){
                String tipo=convertType(var.getType());
                for(Expr expr:var.getExprList()){
                    if(expr instanceof Identifier) {
                        fileWriter.write(tipo+" " + ((Identifier) expr).getLessema() + ";\n");
                    }
                }
            }

            for(VarDeclOp var:bodyOp.getListVar())
                var.accept(this);
        }

        if(bodyOp.getListStatement()!=null){
            Collections.reverse(bodyOp.getListStatement()); //Provare ordine senza dopo aver completato altro
            for(Statement stmt: bodyOp.getListStatement()){
                if(main==true && stmt instanceof ReturnOp){

                }else {
                    stmt.accept(this);
                }
            }
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
            id="main2";
        String espressione="";
        fileWriter.write(id+"(");

        if(callFunOpStat.getListExpr()!=null) {
            for (Expr expr : callFunOpStat.getListExpr()){
                String tipoChiamata="";
                if(expr.getMode().equals("out"))
                    tipoChiamata="&";
                espressione=espressione+tipoChiamata+(String) expr.accept(this);
                espressione=espressione+",";
            }
            fileWriter.write(espressione.substring(0,espressione.length()-1));
        }else{
            fileWriter.write(espressione);
        }
        fileWriter.write(");\n");
        return null;

    }

    @Override
    public Object visit(ForOp forOp) throws Exception {
        currentScope=forOp.getSymbolTable();
        String init=(String) forOp.getId().accept(this); //Esempio: i<<1
        String var=((IdInitOp)forOp.getId()).getId().getLessema(); //Esempio i
        String ind1=((ConstOp)forOp.getId().getExpr()).getLessema();
        String ind2= (String) forOp.getCons().accept(this);
        if(Integer.valueOf(ind1)<=Integer.valueOf(ind2))
            fileWriter.write("for(int "+init+";"+var+"<="+ind2+";"+var+"++){\n");
        else
            fileWriter.write("for(int "+init+";"+var+">="+ind2+";"+var+"--){\n");
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
            if(id.getTipoEspressione().equals("integer")) {
                fileWriter.write("scanf(\"%d\",&" + id.getLessema() + ");\n");
            }else {
                if (id.getTipoEspressione().equals("float")) {
                    fileWriter.write("scanf(\"%f\",&" + id.getLessema() + ");\n");
                }else {
                    if (id.getTipoEspressione().equals("char")) {
                        fileWriter.write("scanf(\"%c\",&" + id.getLessema() + ");\n");
                    }else {
                        if (id.getTipoEspressione().equals("string")) {
                            fileWriter.write(id.getLessema() + "=read_str();\n");
                        }else {
                            if (id.getTipoEspressione().equals("boolean")) {
                                fileWriter.write("scanf(\"%b\",&" + id.getLessema() + ");\n");
                            }
                        }
                    }
                }
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
            ((AssignOp) statement).accept(this);
        }
        if(statement instanceof CallFunOpStat){
            ((CallFunOpStat) statement).accept(this);
        }
        if(statement instanceof ForOp){
            ((ForOp) statement).accept(this);
        }
        if(statement instanceof IfStatOp){
            ((IfStatOp) statement).accept(this);
        }
        if(statement instanceof ReadOp){
            ((ReadOp) statement).accept(this);
        }
        if(statement instanceof WhileOp){
            ((WhileOp) statement).accept(this);
        }
        if(statement instanceof WriteOp){
            ((WriteOp) statement).accept(this);
        }

        if (statement instanceof ReturnOp){
            ((ReturnOp) statement).accept(this);
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
            if(expr.getTipoEspressione().equals("integer"))
                espressione="integer_to_str("+espressione+")";
            if(expr.getTipoEspressione().equals("float"))
                espressione="real_to_str("+espressione+")";
            if(expr.getTipoEspressione().equals("char"))
                espressione="char_to_str("+espressione+")";
            if(expr.getTipoEspressione().equals("boolean"))
                espressione="bool_to_str("+espressione+")";
            fileWriter.write(espressione+");\n");
        }

        return null;
    }

    @Override
    public Object visit(AritAndRelOp aritAndRelOp) throws Exception {

        String espressione="";
        String tipoOperazione=convertTypeOp(aritAndRelOp.getTypeOp());
        //Ottengo la prima espressione
        String expr1= (String) aritAndRelOp.getExpr1().accept(this);
        //Ottengo la secondaEspressione
        String expr2= (String) aritAndRelOp.getExpr2().accept(this);

        //Ottengo il tipo di operazione
        String typeOp=aritAndRelOp.getTypeOp();

        //Operazioni Aritmetiche
        if(typeOp.equals("AddOp") | typeOp.equals("DiffOp") | typeOp.equals("MulOp") | typeOp.equals("DivOp")){
            espressione = expr1 + tipoOperazione + expr2;
            return espressione;
        }


        //Operazioni Relazionali
        if(typeOp.equals("GTOp") | typeOp.equals("GEOp") | typeOp.equals("LTOp") | typeOp.equals("LEOp") |
            typeOp.equals("EQOp") | typeOp.equals("NEOp")) {

            if (aritAndRelOp.getExpr1().getTipoEspressione().equals("string") | aritAndRelOp.getExpr1().getTipoEspressione().equals("char")){
                if(aritAndRelOp.getExpr1().getTipoEspressione().equals("char"))
                    expr1="char_to_str("+expr1+")";
                if(aritAndRelOp.getExpr2().getTipoEspressione().equals("char"))
                    expr2="char_to_str("+expr2+")";
                espressione="strcmp("+expr1+","+expr2+")"+tipoOperazione+"0";
            }else{
                espressione = expr1 + tipoOperazione + expr2;
            }


            return espressione;

        }

        if(typeOp.equals("AndOp") | typeOp.equals("OrOp")){

            espressione = expr1 + tipoOperazione + expr2;
            return espressione;

        }

        if(typeOp.equals("StrCatOp")){
            //Converto le espressioni in stringhe se sono di altro tipo
            if(aritAndRelOp.getExpr1().getTipoEspressione().equals("integer"))
                expr1="integer_to_str("+expr1+")";
            if(aritAndRelOp.getExpr2().getTipoEspressione().equals("integer"))
                expr2="integer_to_str("+expr2+")";
            if(aritAndRelOp.getExpr1().getTipoEspressione().equals("float"))
                expr1="real_to_str("+expr1+")";
            if(aritAndRelOp.getExpr2().getTipoEspressione().equals("float"))
                expr2="real_to_str("+expr2+")";
            if(aritAndRelOp.getExpr1().getTipoEspressione().equals("char"))
                expr1="char_to_str("+expr1+")";
            if(aritAndRelOp.getExpr2().getTipoEspressione().equals("char"))
                expr2="char_to_str("+expr2+")";
            if(aritAndRelOp.getExpr1().getTipoEspressione().equals("boolean"))
                expr1="bool_to_str("+expr1+")";
            if(aritAndRelOp.getExpr2().getTipoEspressione().equals("boolean"))
                expr2="bool_to_str("+expr2+")";

            espressione="str_concat("+expr1+","+expr2+")";
            return espressione;
        }

        if(typeOp.equals("PowOp")){
            espressione=tipoOperazione+"("+expr1+","+expr2+")";
            return espressione;
        }


        return espressione;
    }

    @Override
    public Object visit(CallFunOpExpr callFunOpExpr) throws Exception {
        String id=callFunOpExpr.getIdentifier().getLessema();
        if(id.equals("main"))
            id="main2";
        String espressione=id+"(";
        if(callFunOpExpr.getListExpr()!=null) {
            for (Expr expr : callFunOpExpr.getListExpr()){
                String tipoChiamata="";
                if(expr.getMode().equals("out"))
                    tipoChiamata="&";
                espressione=espressione+tipoChiamata+(String) expr.accept(this);
                espressione=espressione+",";
            }
            espressione=espressione.substring(0,espressione.length()-1);
        }

        espressione=espressione+")";
        return espressione;
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
            type="float";
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
        if (typeOp.equals("UminusOp") | typeOp.equals("NotOp")){
            espressione=operazione+"("+exp1+")";
        }

        return espressione;
    }

    public String convertType(String type){
        if(type.equals("integer")) type="int";
        if(type.equals("boolean")) type="bool";
        if(type.equals("string")) type="char*";
        return type;
    }

    public String generaPrototipo(FunOp fun) throws Exception {
        String tipoFunzione=convertType(fun.getType()); //Tipo della funzione
        String nomeFunzione=fun.getIdentificatore().getLessema(); //Nome della funzione
        String parametri=""; //Parametri Funzione
        if(fun.getParams()!=null && fun.getParams().size()>0) {
            ArrayList<String> parms = new ArrayList<String>(); //Lista di parametri
            for(ParDeclOp param:fun.getParams()){
                parms.addAll((Collection<? extends String>) param.accept(this));
            }
            for(String par:parms){
                parametri=parametri+par+", ";
            }
            parametri=parametri.substring(0,parametri.length()-2);
        }

        return tipoFunzione+" "+nomeFunzione+ "("+parametri+");\n";
    }

    public String convertTypeOp(String typeOp){

        String tipoOprazione="";
        if(typeOp.equals("AddOp"))
            tipoOprazione="+";
        if(typeOp.equals("DiffOp"))
            tipoOprazione="-";
        if(typeOp.equals("MulOp"))
            tipoOprazione="*";
        if(typeOp.equals("DivOp"))
            tipoOprazione="/";
        if(typeOp.equals("PowOp"))
            tipoOprazione="pow";
        if(typeOp.equals("GTOp"))
            tipoOprazione=">";
        if(typeOp.equals("GEOp"))
            tipoOprazione=">=";
        if(typeOp.equals("LTOp"))
            tipoOprazione="<";
        if(typeOp.equals("LEOp"))
            tipoOprazione="<=";
        if(typeOp.equals("NEOp"))
            tipoOprazione="!=";
        if(typeOp.equals("EQOp"))
            tipoOprazione="==";
        if(typeOp.equals("StrCatOp"))
            tipoOprazione="strcat";
        if(typeOp.equals("AndOp"))
            tipoOprazione="&&";
        if(typeOp.equals("OrOp"))
            tipoOprazione="||";
        if(typeOp.equals("UminusOp"))
            tipoOprazione="-1*";
        if(typeOp.equals("NotOp"))
            tipoOprazione="!";

        return tipoOprazione;
    }

    public void addLibrerie() throws IOException {
        fileWriter.write("#include <stdio.h>\n");
        fileWriter.write("#include <stdlib.h>\n");
        fileWriter.write("#include <string.h>\n");
        fileWriter.write("#include <math.h>\n");
        fileWriter.write("#include <unistd.h>\n");
        fileWriter.write("#include <stdbool.h>\n");
        fileWriter.write("#define MAXCHAR 512\n");
    }

    public void addPrototipiFunzioniDiSupporto() throws IOException {
        fileWriter.write("char* integer_to_str(int i);\n");
        fileWriter.write("char* real_to_str(float i);\n");
        fileWriter.write("char* char_to_str(char i);\n");
        fileWriter.write("char* bool_to_str(bool i);\n");
        fileWriter.write("char* str_concat(char* str1, char* str2);\n");
        fileWriter.write("char* read_str();\n");
        fileWriter.write("int str_to_bool(char* expr);\n");


    }

    public void addFunzioniDiSupporto() throws IOException {
        fileWriter.write("\n//Funzioni di supporto \n");
        fileWriter.write("char* integer_to_str(int i){\n");
        fileWriter.write("int length= snprintf(NULL,0,\"%d\",i);\n");
        fileWriter.write("char* result=malloc(length+1);\n");
        fileWriter.write("snprintf(result,length+1,\"%d\",i);\n");
        fileWriter.write("return result;\n");
        fileWriter.write("}\n");

        fileWriter.write("char* real_to_str(float i){\n");
        fileWriter.write("int length= snprintf(NULL,0,\"%f\",i);\n");
        fileWriter.write("char* result=malloc(length+1);\n");
        fileWriter.write("snprintf(result,length+1,\"%f\",i);\n");
        fileWriter.write("return result;\n");
        fileWriter.write("}\n");

        fileWriter.write("char* char_to_str(char i){\n");
        fileWriter.write("int length= snprintf(NULL,0,\"%c\",i);\n");
        fileWriter.write("char* result=malloc(length+1);\n");
        fileWriter.write("snprintf(result,length+1,\"%c\",i);\n");
        fileWriter.write("return result;\n");
        fileWriter.write("}\n");

        fileWriter.write("char* bool_to_str(bool i){\n");
        fileWriter.write("int length= snprintf(NULL,0,\"%d\",i);\n");
        fileWriter.write("char* result=malloc(length+1);\n");
        fileWriter.write("snprintf(result,length+1,\"%d\",i);\n");
        fileWriter.write("return result;\n");
        fileWriter.write("}\n");

        fileWriter.write("char* str_concat(char* str1, char* str2){\n");
        fileWriter.write("char* result=malloc(sizeof(char)*MAXCHAR);\n");
        fileWriter.write("result=strcat(result,str1);\n");
        fileWriter.write("result=strcat(result,str2);\n");
        fileWriter.write("return result;}\n");

        fileWriter.write("\n");
        fileWriter.write("char* read_str(){\n");
        fileWriter.write("char* str=malloc(sizeof(char)*MAXCHAR);\n");
        fileWriter.write("scanf(\"%s\",str);\n");
        fileWriter.write("return str;}\n");

        fileWriter.write("\n");
        fileWriter.write("int str_to_bool(char* expr){\n");
        fileWriter.write("int i=0;\n");
        fileWriter.write("if ( (strcmp(expr, \"true\")==0) || (strcmp(expr, \"1\"))==0 )\n");
        fileWriter.write("i=1;\n");
        fileWriter.write("if ( (strcmp(expr, \"false\")==0) || (strcmp(expr, \"0\"))==0 )\n");
        fileWriter.write("i=0;\n");
        fileWriter.write("return i;}\n");

    }

}
