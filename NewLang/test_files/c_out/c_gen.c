#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <unistd.h>
#include <stdbool.h>
#define MAXCHAR 512

//Prototipi
char* integer_to_str(int i);
char* real_to_str(float i);
char* char_to_str(char i);
char* bool_to_str(bool i);
char* str_concat(char* str1, char* str2);
char* read_str();
int str_to_bool(char* expr);
void main2();

//Variabili Globali

int main (int argc, char *argv[]){
main2();
return 0;
}
void main2(){
int a;
float b;
char c;
char* d;
int f;
float i;
char* n;
bool o;
int e=15;
int g=5;
float h=3.15;
char l='l';
char* m="ciao";
bool p=true;
bool q=false;
int obb1=3;
float obb2=3.15;
char obb3='c';
char* obb4="casa";
bool obb5=true;
printf("%s\n",integer_to_str(e));
printf("%s\n",integer_to_str(g));
printf("%s\n",real_to_str(h));
printf("%s\n",char_to_str(l));
printf("%s\n",m);
printf("%s\n",bool_to_str(p));
printf("%s\n",bool_to_str(q));
printf("%s\n",integer_to_str(obb1));
printf("%s\n",real_to_str(obb2));
printf("%s\n",char_to_str(obb3));
printf("%s\n",obb4);
printf("%s\n",bool_to_str(obb5));
}

//Funzioni di supporto 
char* integer_to_str(int i){
int length= snprintf(NULL,0,"%d",i);
char* result=malloc(length+1);
snprintf(result,length+1,"%d",i);
return result;
}
char* real_to_str(float i){
int length= snprintf(NULL,0,"%f",i);
char* result=malloc(length+1);
snprintf(result,length+1,"%f",i);
return result;
}
char* char_to_str(char i){
int length= snprintf(NULL,0,"%c",i);
char* result=malloc(length+1);
snprintf(result,length+1,"%c",i);
return result;
}
char* bool_to_str(bool i){
int length= snprintf(NULL,0,"%d",i);
char* result=malloc(length+1);
snprintf(result,length+1,"%d",i);
return result;
}
char* str_concat(char* str1, char* str2){
char* result=malloc(sizeof(char)*MAXCHAR);
result=strcat(result,str1);
result=strcat(result,str2);
return result;}

char* read_str(){
char* str=malloc(sizeof(char)*MAXCHAR);
scanf("%s",str);
return str;}

int str_to_bool(char* expr){
int i=0;
if ( (strcmp(expr, "true")==0) || (strcmp(expr, "1"))==0 )
i=1;
if ( (strcmp(expr, "false")==0) || (strcmp(expr, "0"))==0 )
i=0;
return i;}
