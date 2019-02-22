

//Programmer: Jeffrey Wang 
//CruzID: 1659820
//Data: 02.13.19
//Class: COMPS-101B (D.Bailey)

/**********************************************************************************
Programming Assignment 2: APInt Class (Abstract Data Type)
An arbitrary precision Integer which has no fixed limit to the size of 
the number. It implements a LinkedList where the nodes designates the positional
value of the digits. It contains the following methods

•a default constructor
•a constructor which uses a string, made up of optional{+,-}
followed bya string of characters from{0,1,2,3,4,5,6,7,8,9}as an input argument.
•a constructor for conversion of ints.
•a method for printing.
•methods for addition, subtraction, multiplication 

CREATED: 2/11/19 12:23 P.M
Edit: 2/11/19 10:19 P.M. -- Finsished Implementation of LInked List
Edit: 2/12/19 2:14 P.M -- Finsished Implementation of adder and subtractor.
Edit: 2/15/19 1:17 A.M -- Finished Documentation for all files 
LinkedList Tutorial by  Jonathan Engelsma 
"Learning to Program in C " - tutorial
***********************************************************************************/


#include "APInt.h"
#include <string.h>

/*Defines inner structure Node type for LinkedList*/
typedef struct Nodea {
	
	int value;					//Value of Node
	struct Nodea *next;			//Next Node
	struct Nodea *prev;			//Previous Node

} Node;

/*Define's structure of LinkedList*/
typedef struct Elea
{
	int sign;					//Sign of APInt
	struct Nodea *head;			//Head of Node
	struct Nodea *tail;			//Tail of Node
	struct Nodea *current;		//Current Node for traversal

}Ele;

/*Adds value to the APInt as the most signficant digit*/
void Add_Digit_First(APInt * list, int data)
{ 
	//Intializes and allocates memory to a Node
	Node *new_digit;
	new_digit = malloc(sizeof(Node));
	new_digit -> value = data;
	new_digit -> next = NULL;
	new_digit -> prev = NULL;

	//If the LinkedList has no digits assign it as a first and have head, tail,
	//And current point to it.
	if((*list) -> head == NULL)
	{
		(*list) -> head = new_digit;
		(*list) -> tail = new_digit;
		(*list) -> current = new_digit;
	}
	//Else add new value to the head
	else
	{
		new_digit -> next = (*list) -> head;
		(*list) -> head -> prev = new_digit;
		(*list) -> head = new_digit;
	}
}

/*Adds value to the APInt as the least significant digit*/
void Add_Digit_Last(APInt * list, int data)
{ 
	//Intializes and allocates memory to a Node
	Node *new_digit;
	new_digit = malloc(sizeof(Node));
	new_digit -> value = data;
	new_digit -> next = NULL;
	new_digit -> prev = NULL;

	//If the LinkedList has no digits assign it as a first and have head, tail,
	//And current point to it.
	if((*list) -> tail == NULL)
	{
		(*list) -> head = new_digit;
		(*list) -> tail = new_digit;
		(*list) -> current = new_digit;
	}
	//Else add new value to the tail
	else
	{
		new_digit -> prev = (*list) -> tail;
		(*list) -> tail -> next = new_digit;
		(*list) -> tail = new_digit;
	}
}

/*This function allocates a new Ele type which is the APInt object*/
void allocate_APInt (APInt* list)
{
	APInt new_ele;
	new_ele = malloc(sizeof(Ele));
	new_ele -> sign = 1;
	new_ele -> head = NULL;
	new_ele -> tail = NULL;
	new_ele -> current = NULL;
	(*list) = new_ele;
}

/*Intializes a zero element LinkedList*/
APInt* initi_APInt(void)
{
	//Allocate memory to the pointer of pointer to APInt to store the pointer
	APInt *list =  malloc(sizeof(APInt)); 
	allocate_APInt(list);

	//Adds zero to the list
	Add_Digit_First(list, 0);
	return list;
}

/*Intializes a LinkedList with a string of digits.*/
/*The sign is optional*/
APInt* initi_APInt_String(char number[])
{
	/*Allocate memory to list*/
	APInt *list =  malloc(sizeof(APInt)); 
	allocate_APInt(list);

	//Create a pointer to string that holds to parameter
	char* str_ref;

	//Check whether or not the string has a sign.
	if((number[0] == '+') || number[0] == '-')
	{
		if(number[0] == '-')
		{
			(*list)->sign = -1;
		}
		//Return the string of digits after the sign
		str_ref = &number[1];
	}
	else 
		str_ref = number;

	//Iterate the strings and store it in list
	for (int i = 0; i < strlen(str_ref); i++)
	{
		int num =  str_ref[i] - '0';
		Add_Digit_Last(list, num);
	}
	return list;
}


/*Initializes an APInt type with an integer input*/
APInt* initi_APInt_Int(int number)
{
	/*Allocates memory to the list*/
	APInt *list =  malloc(sizeof(APInt)); 
	allocate_APInt(list);

	//Sets the sign of the int to a positive value
	(*list)->sign = 1;
	
	//If the integer is less than zero assign a negative sign
	if(number < 0)
		number *= -1;

	//Iterate through the digits in the integer
	int remainder;
	while (number != 0)
	{
		Add_Digit_First(list, (number % 10));
		number /= 10;
	}
	return list;
}

/*Frees up the allocated memory*/
void CleanUp(APInt *list)
{
	//Assigns the Nodes in List that need to be free
	Node *freeMe = (*list) -> head;
	Node *holdMe = NULL;

	//Iterate through the Nodes and frees them
	while(freeMe != NULL)
	{
		holdMe = freeMe -> next;
		free(freeMe);
		freeMe = holdMe;
	}
	// Free the allocated memory to the Ele, and APInt(pointer to Ele)
	free(*list);
	free(list);
}

/* A print function for the APInt*/
void print(APInt* list)
{
	//Assign the current Node of APInt to head for the start of a tranversal
	(*list) -> current = (*list) -> head;
	
	//Prints the sign
	if((*list) -> sign == 1)
		printf("%s", "+" );
	else
		printf("%s", "-");

	//Iterate through the nodes and print the value
	while((*list) -> current != NULL)
	{
		printf("%d", (*list) -> current -> value);
		(*list) -> current  = (*list) -> current -> next;
	}
	printf("\n");
	
}

/*Compares the value of the APInt numbers*/
bool compareTo(APInt* list1, APInt* list2)
{
	//Assign pointers to the list heads for transveral
	Node *current_1 = (*list1) -> head;
	Node *current_2 = (*list2) -> head;

	//Checks whether one APInt has more digits than the other to check value
	//Returns true if list1 has more digits than list2 (vice versa.)
	while(current_1 != NULL || current_2 != NULL)
	{
		if(current_1 == NULL && current_2 != NULL)
			return false;
		else if(current_1 != NULL && current_2 == NULL)
			return true;
		
		current_1 = current_1 -> next;
		current_2 = current_2 -> next;
	}

	//Reassigns transerval Nodes back to head if digits are the same
	current_1 = (*list1) -> head;
	current_2 = (*list2) -> head;

	//Checks each digit from MSB to LSB to check value comparision
	while(current_1 != NULL)
	{
		if(current_1 -> value > current_2 -> value)
			return true;

		current_1 = current_1 -> next;
		current_2 = current_2 -> next;
	}
	return false;
}

/*Predefines the subtract function for add*/
APInt* subtract(APInt* subtractor_1, APInt* subtractor_2);

/*The rest of the following Arithmetic functions are simply translations from
Assigment 1*/

/*Add Function*/
APInt* add(APInt* adder, APInt* addend)
{
	if ((*adder) -> sign == (*addend) -> sign)
	{
		APInt *sum;
		sum = malloc(sizeof(APInt));
		allocate_APInt(sum);
		(*sum) -> sign = (*adder) ->sign;

		Ele *biggerAddend;
		Ele *smallerAddend;

		if(compareTo(adder, addend))
		{
			biggerAddend = (*adder);
			smallerAddend = (*addend);
		}
		else
		{
			biggerAddend = (*addend);
			smallerAddend = (*adder);
		}

		Node *current_1 = biggerAddend -> tail;
		Node *current_2 = smallerAddend -> tail;

		int carry_over = 0;
		while(current_2 != NULL)
		{
			int total = current_1 -> value + current_2 -> value + carry_over;
			carry_over = 0;
			if (total >= 10)
			{
				carry_over = total/10;
				total %= 10;
			}
			Add_Digit_First(sum, total);
			current_1 = current_1 -> prev;
			current_2 = current_2 -> prev;
		}

		while(current_1 != NULL)
		{
			Add_Digit_First(sum, (current_1 -> value + carry_over));
			carry_over = 0;
			current_1 = current_1 -> prev;
		}

		if(carry_over != 0)
			Add_Digit_First(sum, carry_over);

		return sum;
	}	
	else
	{
		APInt *new_addend = malloc(sizeof(APInt));
		allocate_APInt(new_addend);
		 (*new_addend) -> sign = -1*((*addend)-> sign);
		 (*new_addend) -> head = (*addend) -> head;
		 (*new_addend) -> tail = (*addend) -> tail;
		 (*new_addend) -> current = NULL;
		return subtract(adder, new_addend);
	}
}

/*Subtract Function*/
APInt* subtract(APInt* subtractor_1, APInt* subtractor_2)
{
	if((*subtractor_1) -> sign == (*subtractor_2) -> sign)
	{
		Ele minuend = {(-1 * (*subtractor_2) -> sign), 
						(*subtractor_2) -> head, 
						(*subtractor_2) ->tail, NULL};
		Ele	subtrahend = {(*subtractor_1) -> sign, 
						(*subtractor_1) -> head, 
						(*subtractor_1) ->tail, NULL};

		if(compareTo(subtractor_1, subtractor_2))
		{
			minuend.sign = (*subtractor_1) -> sign; 
			minuend.head = (*subtractor_1) -> head; 
			minuend.tail = (*subtractor_1) -> tail;
			subtrahend.sign = (*subtractor_2) -> sign; 
			subtrahend.head = (*subtractor_2) -> head; 
			subtrahend.tail = (*subtractor_2) ->tail;
		}
	
		APInt * diff = malloc(sizeof(APInt));
		allocate_APInt(diff);

		Node *current_1 = minuend.tail;
		Node *current_2 = subtrahend.tail;
	
		int temp = 0;
		while(current_2 != NULL)
		{
			int difference = (current_1 -> value - temp) - current_2 -> value;
			temp = 0;
	
			if(difference < 0)
			{
				temp++;
				difference += 10;
			}

			Add_Digit_First(diff, difference);
			current_1 = current_1 -> prev;
			current_2 = current_2 -> prev;
		}
		
		while(current_1 != NULL)
		{
			Add_Digit_First(diff, current_1 -> value - temp);
			temp = 0;
			current_1 = current_1 -> prev;
		}
	
		while(((*diff)->head->value == 0) && (*diff)->head->next != NULL)
		{
			(*diff)->head = (*diff)->head->next;
			(*diff)->head->prev = NULL;
		}
	
		return diff;
	}
	else
	{
		APInt *new_subtractor = malloc(sizeof(APInt));
		allocate_APInt(new_subtractor);
		(*new_subtractor) -> sign =  -1*((*subtractor_2) -> sign);
		(*new_subtractor) -> head =  (*subtractor_2) -> head;
		(*new_subtractor) -> tail = (*subtractor_2) -> tail;
		(*new_subtractor) -> current = NULL;	
		return add(subtractor_1, new_subtractor);
	}
}

/*Multiply Function*/
APInt* multiply(APInt* factor_1, APInt* factor_2)
{
	APInt *product = malloc(sizeof(APInt));
	allocate_APInt(product);

	Node *current_1 = (*factor_1)->tail;
	Node *current_2;

	int carry_over;

	APInt *placeHolder = malloc(sizeof(APInt));
	allocate_APInt(placeHolder);

	while(current_1 != NULL)
	{
		APInt *tempPlaceHolder = malloc(sizeof(APInt));
		allocate_APInt(tempPlaceHolder);

		current_2 = (*factor_2)->tail;
		carry_over = 0;
		while(current_2 != NULL)
		{
			int dig = (current_1 -> value * current_2 -> value + carry_over);
			carry_over = 0;

			if(dig >= 10)
			{
				Add_Digit_First(tempPlaceHolder, (dig % 10));
				carry_over = dig/10;
			}
			else 
				Add_Digit_First(tempPlaceHolder, (dig));

			current_2 = current_2 -> prev;
		}
		if(carry_over != 0)
		{
			Add_Digit_First(tempPlaceHolder, carry_over);
		}

		Node *place_current = (*placeHolder)->head;

		while(place_current != NULL)
		{
			Add_Digit_Last(tempPlaceHolder, 0);
			place_current = place_current -> next;
		}

		
		APInt * update_product = add(product, tempPlaceHolder);
		CleanUp(product);
		product = update_product;
		
		Add_Digit_First(placeHolder, 0);
		CleanUp(tempPlaceHolder);
		current_1 = current_1 -> prev;
	}
	CleanUp(placeHolder);

	if((*factor_1) -> sign != (*factor_2) -> sign)
	{
		(*product) -> sign = -1;
	}
	else 
	{
		(*product) -> sign = 1;
	}

	return product;
}

