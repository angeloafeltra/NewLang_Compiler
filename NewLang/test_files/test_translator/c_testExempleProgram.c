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
float somma(float a, float b);
float differenza(float a, float b);
float prodotto(float a, float b);
float divisione(float a, float b);
float potenza(float a, float b);
int menu();
void main2(int par1, float par2, bool par3, char* par4, char par5);

//Variabili Globali

int main (int argc, char *argv[]){
main2(atoi(argv[1]),atof(argv[2]),str_to_bool(argv[3]),argv[4],*argv[5]);
return 0;
}
float somma(float a, float b){
return a+b;
}

float differenza(float a, float b){
return a-b;
}

float prodotto(float a, float b){
return a*b;
}

float divisione(float a, float b){
return a/b;
}

float potenza(float a, float b){
return pow(a,b);
}

int menu(){
int op;
printf("%s\n","--------Menu--------");
printf("%s\n","2) Addizione");
printf("%s\n","3) Sottrazione");
printf("%s\n","4) Moltiplicazione");
printf("%s\n","5) Divisione");
printf("%s\n","6) Potenza");
printf("Inserisci operazione:");
scanf("%d",&op);
while(op<2||op>6){
printf("Operazione non valida [2-6], inserisci operazione:");
scanf("%d",&op);
}
return op;
}

void main2(int par1, float par2, bool par3, char* par4, char par5){
int op;
float risultato;
float a;
float b;
int comando=-1*(1);
printf("%s\n",str_concat("Primo parametro:",integer_to_str(par1)));
printf("%s\n",str_concat("Secondo parametro:",real_to_str(par2)));
printf("%s\n",str_concat("Terzo parametro:",bool_to_str(par3)));
printf("%s\n",str_concat("Quarto parametro:",par4));
printf("%s\n",str_concat("Quinto parametro:",char_to_str(par5)));
while(comando!=0){
printf("%s\n","1) Visualizza Menù");
printf("%s\n","0) Termina");
printf("Inserisci comando:");
scanf("%d",&comando);
if(comando==1){
op=menu();
printf("Inserisci il primo intero:");
scanf("%f",&a);
printf("Inserisci il secondo intero:");
scanf("%f",&b);
if(op==2){
risultato=somma(a,b);
}else{
if(op==3){
risultato=differenza(a,b);
}else{
if(op==4){
risultato=prodotto(a,b);
}else{
if(op==5){
int op;
risultato=divisione(a,b);
}else{
risultato=potenza(a,b);
}
}
}
}
printf("%s\n",str_concat("Il risultato dell'operazione scelta è :",real_to_str(risultato)));
}else{
}
}
printf("%s","Ciao");
return;
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
