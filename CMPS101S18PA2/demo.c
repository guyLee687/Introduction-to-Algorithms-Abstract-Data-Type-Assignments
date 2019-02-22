
//Programmer: Jeffrey Wang 
//CruzID: 1659820
//Data: 02.13.19
//Class: COMPS-101B (D.Bailey)

/**********************************************************************************
Programming Assignment 2: Demo -- Insures that the ADT's are working 
correctly is working correctly

CREATED: 2/11/19 12:23 P.M
Edit: 2/11/19 10:19 P.M. -- Finsished Implementation of LInked List
Edit 2/12/19 2:14 P.M -- Finsished Implementation of adder and subtractor.
Edit: 2/15/19 1:17 A.M -- Finished Documentation for all files 
LinkedList Tutorial by  Jonathan Engelsma 
"Learning to Program in C " - tutorial
***********************************************************************************/

/* 
All Calculations are double checked with programming assignment 1*/

#include <stdio.h>
#include "APInt.h"
int main(void)
{
	APInt* number_1 = initi_APInt();
	APInt* number_2 = initi_APInt_String("-2718281828459045235360287471352");
	APInt* number_3 = initi_APInt_String("1414213562373095048801688724209698078569671875");
	APInt* number_4 = initi_APInt_Int(-123456);
	

	printf("Representation of No-Arg Constructor for APInt (number_1): \n ");
	print(number_1);
	printf("\n");
	
	printf("Representation of String Conversion (with sign) to APInt (number_2): \n ");
	print(number_2);
	printf("\n");
	
	printf("Representation of String Conversion (without sign) to APInt (number_3):  \n ");
	print(number_3);
	printf("\n");

	printf("Representation of Integer Conversion (number_4): \n ");
    print(number_4);
	printf("\n");

	printf("num4 + num1(0):\n"); 
	APInt* sum0 = add(number_4, number_1);
	print(sum0);
	printf("\n");
	CleanUp(sum0);
	
	printf("num2 + num3:\n"); 
	APInt* sum1 = add(number_2, number_3);
	print(sum1);
	printf("\n");
	CleanUp(sum1);

	printf("num2 + num4:\n"); 
	APInt* sum2 = add(number_2, number_3);
	print(sum2);
	printf("\n");
	CleanUp(sum2);


	printf("num2 - num3:\n"); 
	APInt* difference1 = subtract(number_2, number_3);
	print(difference1);
	printf("\n");
	CleanUp(difference1);

	printf("num4 - num2:\n"); 
	APInt* difference2 = subtract(number_4, number_2);
	print(difference2);
	printf("\n");
	CleanUp(difference2);


	printf("num2 * num3:\n"); 
	APInt* product = multiply(number_2, number_3);
	print(product);
	printf("\n");
	CleanUp(product);

	CleanUp(number_1);
	CleanUp(number_2);
	CleanUp(number_3);
	CleanUp(number_4);
}
