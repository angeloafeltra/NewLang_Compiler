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
float sommac(int a, int d, float b, char* *size);
void stampa(char* messaggio);
void main2();

//Variabili Globali
int c=1;

int main (int argc, char *argv[]){
main2();
return 0;
}
float sommac(int a, int d, float b, char* *size){
float result;
result=a+b+c+d;
if(result>100){
char* valore="grande";
*size=valore;
}else{
char* valore="piccola";
*size=valore;
}
return result;
}

void stampa(char* messaggio){
for(int i=1;i<=4;i++){
printf("%s\n","");
}
printf("%s\n",messaggio);
}

void main2(){
char* taglia;
char* ans1;
int a=1;
float b=2.2;
int x=3;
char* ans="no";
float risultato=sommac(a,x,b,&taglia);
stampa(str_concat(str_concat(str_concat(str_concat(str_concat(str_concat(str_concat("la somma di ",integer_to_str(a))," e "),real_to_str(b))," incrementata di "),integer_to_str(c))," è "),taglia));
stampa(str_concat("ed è pari a ",real_to_str(risultato)));
printf("%s\n","vuoi continuare? (si/no) - inserisci due volte la risposta");
ans=read_str();
ans1=read_str();
while(strcmp(ans,"si")==0){
printf("inserisci un intero:");
scanf("%d",&a);
printf("inserisci un reale:");
scanf("%f",&b);
risultato=sommac(a,x,b,&taglia);
stampa(str_concat(str_concat(str_concat(str_concat(str_concat(str_concat(str_concat("la somma di ",integer_to_str(a))," e "),real_to_str(b))," incrementata di "),integer_to_str(c))," è "),taglia));
stampa(str_concat(" ed è pari a ",real_to_str(risultato)));
printf("vuoi continuare? (si/no):	");
ans=read_str();
}
printf("%s\n","");
printf("%s","ciao");
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
