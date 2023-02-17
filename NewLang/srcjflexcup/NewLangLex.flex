package main.java;
import java_cup.runtime.*;
%%

%public
%class Lexer
%unicode //Inisieme dei caratteri su cui lo scanner lavorerà, leggendo da un file
         //di testo e necessario che venga abilitato
%cup //Si usa per interfarciarsi con un parser generato da CUP
%cupsym Token
%line
%column


%{

    StringBuffer string = new StringBuffer();
    private Symbol symbol(int type) {
        return new Symbol(type, yyline, yycolumn);
    }
    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline, yycolumn, value);
    }

    private Symbol installID(String lessema){
            Symbol token;

            token=symbol(sym.ID,lessema);
            return token;
        }
%}



identificatore=[$_A-Za-z][$_A-Za-z0-9]*
intLiteral =  [0-9]+ (e-?[0-9]+)? | 0x[0-9a-f] | 0b[01]+
realLiteral = [0-9]+ \. [0-9]+ (e-?[0-9]+)?

LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
whitespace     = {LineTerminator} | [ \t\f]



%state STRING
%state CHAR
%state INLINE_COMMENT
%state BLOCK_COMMENT

%%

//Constanti
<YYINITIAL> {intLiteral} {return symbol(sym.INTEGER_CONST, yytext());}
<YYINITIAL> {realLiteral} {return symbol(sym.REAL_CONST, yytext());}
<YYINITIAL> \"  { string.setLength(0); yybegin(STRING); }
<YYINITIAL> \' {string.setLength(0); yybegin(CHAR); }

//Parole Chiavi
<YYINITIAL> "start:" {return symbol(sym.MAIN);}
<YYINITIAL> "var" {return symbol(sym.VAR);}
<YYINITIAL> "integer" {return symbol(sym.INT);}
<YYINITIAL> "float" {return symbol(sym.FLOAT);}
<YYINITIAL> "string" {return symbol(sym.STRING);}
<YYINITIAL> "boolean" {return symbol(sym.BOOL);}
<YYINITIAL> "char" {return symbol(sym.CHAR);}
<YYINITIAL> "void" {return symbol(sym.VOID);}
<YYINITIAL> "def" {return symbol(sym.DEF);}
<YYINITIAL> "out" {return symbol(sym.OUT);}
<YYINITIAL> "for" {return symbol(sym.FOR);}
<YYINITIAL> "if" {return symbol(sym.IF);}
<YYINITIAL> "else" {return symbol(sym.ELSE);}
<YYINITIAL> "then" {return symbol(sym.THEN);}
<YYINITIAL> "while" {return symbol(sym.WHILE);}
<YYINITIAL> "to" {return symbol(sym.TO);}
<YYINITIAL> "loop" {return symbol(sym.LOOP);}
<YYINITIAL> "return" {return symbol(sym.RETURN);}
<YYINITIAL> "true" {return symbol(sym.TRUE);}
<YYINITIAL> "false" {return symbol(sym.FALSE);}
<YYINITIAL> "and" {return symbol(sym.AND);}
<YYINITIAL> "or" {return symbol(sym.OR);}
<YYINITIAL> "not" {return symbol(sym.NOT);}


//Parole
<YYINITIAL> {identificatore} {return installID(yytext());}



<YYINITIAL> "|*" {string.setLength(0); yybegin(BLOCK_COMMENT); }
<YYINITIAL> "||" {string.setLength(0); yybegin(INLINE_COMMENT); }


//Operatori Relazionali
<YYINITIAL> "=" {return symbol(sym.EQ);}
<YYINITIAL> "<>" {return symbol(sym.NE);}
<YYINITIAL> "!=" {return symbol(sym.NE);}
<YYINITIAL> "<" {return symbol(sym.LT);}
<YYINITIAL> "<=" {return symbol(sym.LE);}
<YYINITIAL> ">" {return symbol(sym.GT);}
<YYINITIAL> ">=" {return symbol(sym.GE);}


//Operatori Aritmetici
<YYINITIAL> "+" {return symbol(sym.PLUS);}
<YYINITIAL> "-" {return symbol(sym.MINUS);}
<YYINITIAL> "*" {return symbol(sym.TIMES);}
<YYINITIAL> "/" {return symbol(sym.DIV);}
<YYINITIAL> "^" {return symbol(sym.POW);}
<YYINITIAL> "&" {return symbol(sym.STR_CONCAT);}


//Separatori
<YYINITIAL> ";" {return symbol(sym.SEMI);}
<YYINITIAL> "," {return symbol(sym.COMMA);}
<YYINITIAL> "|" {return symbol(sym.PIPE);}
<YYINITIAL> "(" {return symbol(sym.LPAR);}
<YYINITIAL> ")" {return symbol(sym.RPAR);}
<YYINITIAL> "{" {return symbol(sym.LBRACK);}
<YYINITIAL> "}" {return symbol(sym.RBRACK);}


//Input, Output, Assign ecc...
<YYINITIAL> "<--" {return symbol(sym.READ);}
<YYINITIAL> "-->!" {return symbol(sym.WRITELN);}
<YYINITIAL> "-->" {return symbol(sym.WRITE);}
<YYINITIAL> ":" {return symbol(sym.COLON);}
<YYINITIAL> "<<" {return symbol(sym.ASSIGN);}



<YYINITIAL> {whitespace} {/*no action and no return*/}

/* error fallback */
<YYINITIAL> [^] { throw new Error("Illegal character <"+yytext()+">"); }
<<EOF>> { return null; }


<STRING> {
      \"                             { yybegin(YYINITIAL);
                                       return symbol(sym.STRING_CONST,
                                       string.toString()); }
      [^\n\r\"\\]+                   { string.append( yytext() ); }
      \\t                            { string.append('\t'); }
      \\n                            { string.append('\n'); }

      \\r                            { string.append('\r'); }
      \\\"                           { string.append('\"'); }
      \\                             { string.append('\\'); }
}

<CHAR>{
    \'   {yybegin(YYINITIAL);
          return symbol(sym.CHAR_CONST, string.toString());}

    [^\'] { string.append( yytext() ); }
    \\t  { string.append('\t'); }
    \\n  { string.append('\n'); }
    \\r  { string.append('\r'); }
    \\\"  { string.append('\"'); }
    \\  { string.append('\\'); }

    [^\']+ {throw new Error("Char scritto come una stringa"); }


}

<BLOCK_COMMENT> {
      \|  { yybegin(YYINITIAL);}
      [^\|]+  { }
      <<EOF>>  {throw new Error("Commento non chiuso"); }
}


<INLINE_COMMENT> {
      {LineTerminator}  { yybegin(YYINITIAL);}
      [^\n]+  { }
}


