package main.java.main;

import java_cup.runtime.Symbol;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class TesterLexer {
    public static void main(String[] args) throws FileNotFoundException {

        String separator = File.separator;
        File input = new File(
                System.getProperty("user.dir") +separator+"test_files"+ separator+ args[0]);
        main.java.Lexer lexicalAnalyzer = new main.java.Lexer(new FileReader(input));

        final String[] tokenName= {"MAIN", "SEMI", "COMMA", "PIPE", "VAR", "INT", "FLOAT", "STRING", "BOOL", "CHAR", "VOID", "DEF", "OUT", "FOR", "IF", "ELSE", "WHILE",
                "TO", "LOOP", "READ", "WRITE", "WRITELN", "LPAR", "RPAR", "LBRACK", "RBRACK", "COLON", "ASSIGN", "RETURN", "ID", "INTEGER_CONST", "REAL_CONST",
                "STRING_CONST", "CHAR_CONST", "TRUE", "FALSE", "PLUS", "MINUS", "TIMES", "DIV", "POW", "STR_CONCAT", "EQ", "NE", "LT", "LE", "GT",
                "GE", "AND", "OR", "NOT", "THEN"};




        Symbol token;
        try {
            while ((token = lexicalAnalyzer.next_token()) != null) {
                if (token.value!=null)
                    System.out.println("<"+tokenName[token.sym-2] + "," + token.value+">");
                else
                    System.out.println("<"+tokenName[token.sym-2]+">");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}