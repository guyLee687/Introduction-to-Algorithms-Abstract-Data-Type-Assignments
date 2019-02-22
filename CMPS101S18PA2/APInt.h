
//Programmer: Jeffrey Wang 
//CruzID: 1659820
//Data: 02.13.19
//Class: COMPS-101B (D.Bailey)

/**********************************************************************************
Programming Assignment 2: APInt Header -- Creates an interface for the APInt

CREATED: 2/11/19 12:23 P.M
Edit: 2/11/19 10:19 P.M. -- Finsished Implementation of LInked List
Edit 2/12/19 2:14 P.M -- Finsished Implementation of adder and subtractor.
Edit: 2/15/19 1:17 A.M -- Finished Documentation for all files 
LinkedList Tutorial by  Jonathan Engelsma 
"Learning to Program in C " - tutorial
***********************************************************************************/

#ifndef APINT_Li
#define APINT_Li

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

/*Hides Ele with APInt alias*/
typedef struct Elea * APInt;


/*Adds digit to the top of the list*/
void Add_Digit_First(APInt *list, int value);
/*Adds digits to the bottom of the list*/
void Add_Digit_Last(APInt *list, int value);


/*Intializes a zero number */
APInt* initi_APInt(void);

/*Intializes a APInt with string numerics */
APInt* initi_APInt_String(char number[]);

/*Intializes a APInt with integer type */
APInt* initi_APInt_Int(int number);

/*Free's up heap space after structure is no longer needed*/
void CleanUp(APInt *list);

/*Prints values in structure*/
void print(APInt* list);

/*Adds two APInt numbers and returns the address*/
APInt* add(APInt* adder, APInt* addend);

/*Subtracts two APInt numbers and returns the address*/
APInt* subtract(APInt* subtractor_1, APInt* subtractor_2);

/*Mulitplies two APInt numbers and returns the address*/
APInt* multiply(APInt* factor_1, APInt* factor_2);
#endif
