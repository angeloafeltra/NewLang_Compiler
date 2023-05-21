package compiler.visitors;

public final class ScriptConstant {

    private ScriptConstant() {}

    public static final String SUPPORT_FUN_INTEGER_TO_STR= "\n//Funzioni di supporto \n" +
                                            "char* integer_to_str(int i){\n" +
                                            "int length= snprintf(NULL,0,\"%d\",i);\n" +
                                            "char* result=malloc(length+1);\n" +
                                            "snprintf(result,length+1,\"%d\",i);\n" +
                                            "return result;\n" +
                                            "}\n";

    public static final String SUPPORT_FUN_REAL_TO_STR= "char* real_to_str(float i){\n" +
                                                        "int length= snprintf(NULL,0,\"%f\",i);\n" +
                                                        "char* result=malloc(length+1);\n" +
                                                        "snprintf(result,length+1,\"%f\",i);\n" +
                                                        "return result;\n" +
                                                        "}\n";

    public static final String SUPPORT_FUN_CHAR_TO_STR= "char* char_to_str(char i){\n" +
                                                        "int length= snprintf(NULL,0,\"%c\",i);\n" +
                                                        "char* result=malloc(length+1);\n" +
                                                        "snprintf(result,length+1,\"%c\",i);\n" +
                                                        "return result;\n" +
                                                        "}\n";

    public static final String SUPPORT_FUN_BOOL_TO_STR= "char* bool_to_str(bool i){\n" +
                                                        "int length= snprintf(NULL,0,\"%d\",i);\n" +
                                                        "char* result=malloc(length+1);\n" +
                                                        "snprintf(result,length+1,\"%d\",i);\n" +
                                                        "return result;\n" +
                                                        "}\n";

    public static final String SUPPORT_FUN_STR_CONCAT=  "char* str_concat(char* str1, char* str2){\n" +
                                                        "char* result=malloc(sizeof(char)*MAXCHAR);\n" +
                                                        "result=strcat(result,str1);\n" +
                                                        "result=strcat(result,str2);\n" +
                                                        "return result;}\n\n";

    public static final String SUPPORT_FUN_READ_STR=    "char* read_str(){\n" +
                                                        "char* str=malloc(sizeof(char)*MAXCHAR);\n" +
                                                        "scanf(\"%s\",str);\n" +
                                                        "return str;}\n\n";

    public static final String SUPPORT_FUN_STR_TO_BOOL= "int str_to_bool(char* expr){\n" +
                                                        "int i=0;\n" +
                                                        "if ( (strcmp(expr, \"true\")==0) || (strcmp(expr, \"1\"))==0 )\n" +
                                                        "i=1;\n" +
                                                        "if ( (strcmp(expr, \"false\")==0) || (strcmp(expr, \"0\"))==0 )\n" +
                                                        "i=0;\n" +
                                                        "return i;}\n";

    public static final String LIBRERIE=    "#include <stdio.h>\n" +
                                            "#include <stdlib.h>\n" +
                                            "#include <string.h>\n" +
                                            "#include <math.h>\n" +
                                            "#include <unistd.h>\n" +
                                            "#include <stdbool.h>\n" +
                                            "#define MAXCHAR 512\n";

    public static final String PROTOTIPI_FUN_SUP=   "char* integer_to_str(int i);\n" +
                                                    "char* real_to_str(float i);\n" +
                                                    "char* char_to_str(char i);\n" +
                                                    "char* bool_to_str(bool i);\n" +
                                                    "char* str_concat(char* str1, char* str2);\n" +
                                                    "char* read_str();\n" +
                                                    "int str_to_bool(char* expr);\n";

}


