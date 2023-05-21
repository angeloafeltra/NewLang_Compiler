package compiler.visitors;

public final class ScriptConstant {

    private ScriptConstant() {}

    private static final String DUP_MALLOC="char* result=malloc(length+1);\n";
    private static final String DUP_RETURN="return result;\n";

    public static final String SUPPORT_FUN_INTEGER_TO_STR= "\n//Funzioni di supporto \n" +
            "char* integer_to_str(int i){\n" +
            "int length= snprintf(NULL,0,\"%d\",i);\n" +
            DUP_MALLOC+
            "snprintf(result,length+1,\"%d\",i);\n" +
            DUP_RETURN +
            "}\n";
    public static final String SUPPORT_FUN_REAL_TO_STR= "char* real_to_str(float i){\n" +
                                                        "int length= snprintf(NULL,0,\"%f\",i);\n" +
                                                        DUP_MALLOC +
                                                        "snprintf(result,length+1,\"%f\",i);\n" +
                                                        DUP_RETURN +
                                                        "}\n";
    public static final String SUPPORT_FUN_CHAR_TO_STR= "char* char_to_str(char i){\n" +
                                                        "int length= snprintf(NULL,0,\"%c\",i);\n" +
                                                        DUP_MALLOC +
                                                        "snprintf(result,length+1,\"%c\",i);\n" +
                                                        DUP_RETURN +
                                                        "}\n";
    public static final String SUPPORT_FUN_BOOL_TO_STR= "char* bool_to_str(bool i){\n" +
                                                        "int length= snprintf(NULL,0,\"%d\",i);\n" +
                                                        DUP_MALLOC +
                                                        "snprintf(result,length+1,\"%d\",i);\n" +
                                                        DUP_RETURN +
                                                        "}\n";

    public static final String SUPPORT_FUN_STR_CONCAT=  """
                                                        char* str_concat(char* str1, char* str2){
                                                        char* result=malloc(sizeof(char)*MAXCHAR);
                                                        result=strcat(result,str1);
                                                        result=strcat(result,str2);
                                                        return result;}\n
                                                        """;

    public static final String SUPPORT_FUN_READ_STR=    """
                                                        char* read_str(){
                                                        char* str=malloc(sizeof(char)*MAXCHAR);
                                                        scanf(\"%s\",str);
                                                        return str;}\n
                                                        """;

    public static final String SUPPORT_FUN_STR_TO_BOOL= """
                                                        int str_to_bool(char* expr){
                                                        int i=0;
                                                        if ( (strcmp(expr, \"true\")==0) || (strcmp(expr, \"1\"))==0 )
                                                        i=1;
                                                        if ( (strcmp(expr, \"false\")==0) || (strcmp(expr, \"0\"))==0 )
                                                        i=0;
                                                        return i;}
                                                        """;

    public static final String LIBRERIE=    """
                                            #include <stdio.h>
                                            #include <stdlib.h>
                                            #include <string.h>
                                            #include <math.h>
                                            #include <unistd.h>
                                            #include <stdbool.h>
                                            #define MAXCHAR 512
                                            """;

    public static final String PROTOTIPI_FUN_SUP=   """
                                                    char* integer_to_str(int i);
                                                    char* real_to_str(float i);
                                                    char* char_to_str(char i);
                                                    char* bool_to_str(bool i);
                                                    char* str_concat(char* str1, char* str2);
                                                    char* read_str();
                                                    int str_to_bool(char* expr);
                                                    """;


}


