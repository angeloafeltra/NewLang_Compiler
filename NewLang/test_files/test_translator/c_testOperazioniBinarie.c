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
char* stringa;
bool result;
a=3+2;
printf("%s\n",integer_to_str(a));
a=3-2;
printf("%s\n",integer_to_str(a));
a=3*2;
printf("%s\n",integer_to_str(a));
a=3/2;
printf("%s\n",integer_to_str(a));
a=pow(3,2);
printf("%s\n",integer_to_str(a));
b=3+2.5;
printf("%s\n",real_to_str(b));
b=3-2.5;
printf("%s\n",real_to_str(b));
b=3*2.5;
printf("%s\n",real_to_str(b));
b=3/2.5;
printf("%s\n",real_to_str(b));
b=pow(3,2.5);
printf("%s\n",real_to_str(b));
b=2.5+3;
printf("%s\n",real_to_str(b));
b=2.5-3;
printf("%s\n",real_to_str(b));
b=2.5*3;
printf("%s\n",real_to_str(b));
b=2.5/3;
printf("%s\n",real_to_str(b));
b=pow(2.5,3);
printf("%s\n",real_to_str(b));
b=2.5+3.1;
printf("%s\n",real_to_str(b));
b=2.5-3.1;
printf("%s\n",real_to_str(b));
b=2.5*3.1;
printf("%s\n",real_to_str(b));
b=2.5/3.1;
printf("%s\n",real_to_str(b));
b=pow(2.5,3.1);
printf("%s\n",real_to_str(b));
stringa=str_concat("Ciao","Mondo");
printf("%s\n",stringa);
stringa=str_concat("Ciao",integer_to_str(1));
printf("%s\n",stringa);
stringa=str_concat("Ciao",real_to_str(1.0));
printf("%s\n",stringa);
stringa=str_concat("Ciao",char_to_str('!'));
printf("%s\n",stringa);
stringa=str_concat(integer_to_str(1),"Ciao");
printf("%s\n",stringa);
stringa=str_concat("1.0","Ciao");
printf("%s\n",stringa);
stringa=str_concat(char_to_str('!'),"Ciao");
printf("%s\n",stringa);
stringa=str_concat(str_concat(str_concat("Ciao","Mondo"),"Come"),"Va?");
printf("%s\n",stringa);
result=true&&false;
printf("%s\n",bool_to_str(result));
result=true||false;
printf("%s\n",bool_to_str(result));
result=1>2;
printf("%s\n",bool_to_str(result));
result=1>=2;
printf("%s\n",bool_to_str(result));
result=1<2;
printf("%s\n",bool_to_str(result));
result=1<=2;
printf("%s\n",bool_to_str(result));
result=1==2;
printf("%s\n",bool_to_str(result));
result=1!=2;
printf("%s\n",bool_to_str(result));
result=1>2.1;
printf("%s\n",bool_to_str(result));
result=1>=2.1;
printf("%s\n",bool_to_str(result));
result=1<2.1;
printf("%s\n",bool_to_str(result));
result=1<=2.1;
printf("%s\n",bool_to_str(result));
result=1==2.1;
printf("%s\n",bool_to_str(result));
result=1!=2.1;
printf("%s\n",bool_to_str(result));
result=1.1>2;
printf("%s\n",bool_to_str(result));
result=1.1>=2;
printf("%s\n",bool_to_str(result));
result=1.1<2;
printf("%s\n",bool_to_str(result));
result=1.1<=2;
printf("%s\n",bool_to_str(result));
result=1.1==2;
printf("%s\n",bool_to_str(result));
result=1.1!=2;
printf("%s\n",bool_to_str(result));
result=1.1>2.1;
printf("%s\n",bool_to_str(result));
result=1.1>=2.1;
printf("%s\n",bool_to_str(result));
result=1.1<2.1;
printf("%s\n",bool_to_str(result));
result=1.1<=2.1;
printf("%s\n",bool_to_str(result));
result=1.1==2.1;
printf("%s\n",bool_to_str(result));
result=1.1!=2.1;
printf("%s\n",bool_to_str(result));
result=strcmp("ciao","mondo")>0;
printf("%s\n",bool_to_str(result));
result=strcmp("ciao","mondo")>=0;
printf("%s\n",bool_to_str(result));
result=strcmp("ciao","mondo")<0;
printf("%s\n",bool_to_str(result));
result=strcmp("ciao","mondo")<=0;
printf("%s\n",bool_to_str(result));
result=strcmp("ciao","mondo")==0;
printf("%s\n",bool_to_str(result));
result=strcmp("ciao","mondo")!=0;
printf("%s\n",bool_to_str(result));
result=strcmp(char_to_str('1'),char_to_str('2'))>0;
printf("%s\n",bool_to_str(result));
result=strcmp(char_to_str('1'),char_to_str('2'))>=0;
printf("%s\n",bool_to_str(result));
result=strcmp(char_to_str('1'),char_to_str('2'))<0;
printf("%s\n",bool_to_str(result));
result=strcmp(char_to_str('1'),char_to_str('2'))<=0;
printf("%s\n",bool_to_str(result));
result=strcmp(char_to_str('1'),char_to_str('2'))==0;
printf("%s\n",bool_to_str(result));
result=strcmp(char_to_str('1'),char_to_str('2'))!=0;
printf("%s\n",bool_to_str(result));
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
