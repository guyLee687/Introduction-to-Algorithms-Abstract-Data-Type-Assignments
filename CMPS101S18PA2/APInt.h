
/*
CREATED: 2/11/19
LinkedList Tutorial by  Jonathan Engelsma 
"Learning to Program in C " - tutorial
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

typedef struct Node {
	
	int value;
	struct Node *next;
	struct Node *prev;

} Node;

typedef struct Ele
{
	int sign;
	struct Node *head;
	struct Node *tail;
	struct Node *current;

}Ele;

/*Adds value to digit*/
void Add_Digit_First(Ele *list, int value)
{ 
	Node new_digit = {value, NULL, NULL};
	if(list -> head == NULL)
	{
		list -> head = &new_digit;
		list -> tail = &new_digit;
	}
	else
	{
		Node *temp = list -> head;
		list -> head = &new_digit;
		list -> head -> next = temp;
		temp -> prev = list -> head;
	}
}

void Add_Digit_Last(Ele *list, int value)
{ 
	Node new_digit = {value, NULL, NULL};
	if(list -> tail == NULL)
	{
		list -> head = &new_digit;
		list -> tail = &new_digit;
	}
	else
	{
		Node *temp = list -> tail;
		list -> tail = &new_digit;
		list -> tail -> prev = temp;
		temp -> next = list -> tail;
	}
}

/*Intializes a zero element LinkedList*/
Ele initi_APInt(void)
{
	Ele *number;
	number -> head = NULL;
	number -> tail = NULL;
	return *number;
}

Ele initi_APInt_String(char number[])
{
	Ele list = {1, NULL, NULL, NULL};
	char* str_ref;
	if((number[0] == '+') || number[0] == '-')
	{
		if(number[0] == '-')
		{
			list.sign = -1;
		}
		str_ref = &number[1];
	}
	else 
		str_ref = number;

	for (int i = 0; i < strlen(number); i++)
	{
		Add_Digit_First(&list, number[i]);
	}
	return list;
}

Ele initi_APInt_Int(int number)
{
	Ele list = {1, NULL, NULL, NULL};
	if(number < 0)
	{
		list.sign = -1;
		number *= -1;
	}

	int remainder;
	while (number != 0)
	{
		Add_Digit_Last(&list, (number % 10));
		number /= 10;
	}
	return list;
}

void CleanUp(Ele *list){
	Node *freeMe = list -> head;
	Node *holdMe = NULL;

	while(freeMe != NULL)
	{
		holdMe = freeMe -> next;
		free(freeMe);
		freeMe = holdMe;
	}
}

void print(Ele *list)
{
	Ele *dec;
	dec -> head = list -> head;

	if(list -> sign == 1)
		printf("%s\n", "+" );
	else
		printf("%s\n", "-");
	
	while(dec -> head != NULL)
	{
		printf("%d", dec -> head -> value);
		dec -> head = dec -> head -> next;
	}
	printf("\n");
}

bool compareTo(Ele *list1, Ele *list2)
{
	Node *current_1 = list1 -> head;
	Node *current_2 = list2 -> head;

	while(current_1 != NULL || current_2 != NULL)
	{
		if(current_1 == NULL && current_2 != NULL)
			return false;
		else if(current_1 != NULL && current_2 == NULL)
			return true;
		
		current_1 = current_1 -> next;
		current_2 = current_2 -> next;
	}

	current_1 = list1 -> head;
	current_2 = list2 -> head;
	while(current_1 != NULL)
	{
		if(current_1 -> value > current_2 -> value)
			return true;

		current_1 = current_1 -> next;
		current_2 = current_2 -> next;
	}
	return false;
}

Ele add(Ele *adder, Ele *addend)
{

}

Ele subtract(Ele *minuend, Ele *subtrahend)
{

}

Ele multiply(Ele *factor_1, Ele *factor_2)
{

}
