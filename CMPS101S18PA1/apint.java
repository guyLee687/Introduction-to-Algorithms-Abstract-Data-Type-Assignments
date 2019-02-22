
//Programmer: Jeffrey Wang 
//CruzID: 1659820
//Data: 11.29.19
//Class: COMPS-101B (D.Bailey)

/**********************************************************************************
Programming Assignment 1: APInt Class (Abstract Data Type)
An arbitrary precision Integer which has no fixed limit to the size of 
the number. It implements a LinkedList where the nodes designates the positional
value of the digits. It contains the following methods

•a default constructor•a constructor which uses a string, made up of optional{+,-}
followed bya string of characters from{0,1,2,3,4,5,6,7,8,9}as an input argument.
•a constructor for conversion of ints.•a constructor for conversion of reals that truncates the fractional part.
•a method for printing.
•methods for addition, subtraction, multiplication and division.
***********************************************************************************/

public class APInt
{
	private Node<Integer> head = new Node<>(null);	//Represents the head node and intializes at null
	private Node<Integer> tail = new Node<>(null);	//Represents the tail node and intializes at null
	private Node<Integer> current = tail;			//Default pointer for current it the tail node
	private int defaultDigits = 1;					//Set default size of each null to one digit
	private int realSign, sign = 1;			      //(+ is a 1, - is a -1)	

	/**
	* No-Arg Constructor: Creates an empty APInt type where 
	* there is no positional digits, the head and tail of the Linked List
	* points to null.
	*/
	public APInt()
	{
		head.next = tail;
		tail.previous = head;
	}

	/** 
	* APInt Constructor: Creates an APInt type out of a string where the first
	* character of the string can either denote the sign of an Integer or the 
	* first positional digit. 
	* @param number - A string of an optional sign (+/-) and numeric digits (0-9) 
	*/
	public APInt(String number)		//Needs Revision (Sign is optional)
	{
		//Determines whether the first character contains a sign
		if(number.charAt(0) == '+' || number.charAt(0) == '-')
		{
			//Checks and changes sign if the first character is a negative (-)
			if(number.charAt(0)== '-')
			{		
				sign = -1;					
			}
			//Take the substring of digits after sign character
			number = number.substring(1);
		}

		//Intialize a character array preparing to stored
		char[] digits = number.toCharArray(); 
		
		//Intialize a Node which stores values from digits array
		Node<Integer> temp;
		
		//Set pointers for heads and tails
		head.next = tail;
		tail.previous = head;

		//Starting from the first numeric character, store each array element into LinkedList
		//And set pointers
		for(Character d: digits)
		{
			temp = tail;
			tail = new Node<>(Character.getNumericValue(d));
			temp.next = tail;
			tail.previous = temp;
		}

		//Remove first two nodes which point to null
		//And add a null to the tail for tranversal condition
		removeFirst();
		removeFirst();
		addLast(null);
	}

	/** 
	* APInt Constructor: Creates an APInt type by taking in an int type and extracting each 
	* positional digit of the int. 
	* @param integer - An int type
	*/
	public APInt(int integer)
	{
		//Change the sign of both the default APInt sign and the integer by mulitiplying one
		if(integer < 0)
		{
			changeSign();
			integer *= -1;
		}

		//Intialize a Node which stores values each positional value of integer
		Node<Integer> temp;
		
		//Set pointers for heads and tails
		head.next = tail;
		tail.previous = head;

		//Continually tranversal through the positional digits of integer by taking the modulus of
		//Integer by 10 until temp reachs the last digit
		do
		{
			temp = head;
			head = new Node<>(integer % 10);
			temp.previous = head;
			head.next = temp;
			integer /= 10;
		}
		while(integer != 0);
		
		//Remove additional null
		removeLast();
	}
	
	/** 
	* APInt Constructor: Creates an APInt type by taking in an double type and extracting each 
	* positional digit of the int. Note that the precision digits are removed
	* @param realNum - An double type
	*/
	public APInt(double realNum)
	{
		int altInt = (int) realNum; 	//Remove precision digits

		//Change the sign of both the default APInt sign and the double by mulitiplying one
		if (altInt < 0)
		{
			changeSign();
			altInt *= -1;
		}

		//Intialize a Node which stores values each positional value of realNum
		Node<Integer> temp;
		
		//Set pointers for heads and tails
		head.next = tail;
		tail.previous = head;

		//Continually tranversal through the positional digits of realNum by taking the modulus of
		//RealNum by 10 until temp reachs the last digit
		do
		{
			temp = head;
			head = new Node<>(altInt % 10);
			head.next = temp;
			temp.previous = head;
			altInt /= 10;
		}
		while(altInt != 0);

		//Remove additional null
		removeLast();
	}

	/** 
	* APInt Constructor: Creates a deep copy of APInt through copying the value and each node of the original 
	* APInt type.
	* @param original - An APInt type that needs to be copied.
	*/
	public APInt(APInt original)
	{
		//Changes the default sign of APInt if original's sign is negative
		if(original.getSign() == -1)				
			changeSign();

		//Set's original current node to head for transveral.

		original.setCurrent(0);
		
		//Intialize a Node which stores values each positional value of original
		Node<Integer> temp;
		
		//Set pointers for heads and tails
		head.next = tail;
		tail.previous = head;
		
		//Traverse through original and update tail of this APInt
		do
		{
			temp = tail;
			tail = new Node<>(original.getCurrent());
			temp.next = tail;
			tail.previous = temp;
			original.nextCurrent();		//Updates original's current node
		}
		while(original.getCurrent() != null);

		//Remove first two nodes which point to null
		//And add a null to the tail for tranversal condition
		removeFirst();
		removeFirst();
		addLast(null);

	}
	/**
	* getFirst: returns the value of the head node
	* @return head.value -- An int of the first positional value
	*/
	public Integer getFirst()
	{
		return head.value;
	}
	
	/**
	* getLast: returns the value of the tail node
	* @return tail.value -- An int of the last positional value
	*/
	public Integer getLast()
	{
		return tail.value;
	}
	
	/**
	* addFirst: Adds a value to the new head node, and updates pointer to 
	* have the odd head node be the next node.
	* @param digit -- An integer value
	*/
	public void addFirst(Integer digit) 
	{
		Node<Integer> temp = head;		  //Temporary node to hold the address for the old head.
		head = new Node<Integer>(digit);
		head.next = temp;
		temp.previous = head;
	}
	
	/**
	* addFirst: Adds a value to the new tail node, and updates pointer to 
	* have the odd tail node be the previous node.
	* @param digit -- An integer value
	*/
	public void addLast(Integer digit)
	{

		Node<Integer> temp = tail;		 //Temporary node to hold the address for the old teil.
		tail = new Node<Integer>(digit);
		tail.previous = temp;
		temp.next = tail;
	}
	
	/**
	* removeFirst: Removes the head node and 
	* reassigns the next node to be the new head
	*/
	public void removeFirst()
	{	
		head = head.next;
		head.previous = null;
	}
	
	/**
	* removeLast: Removes the tail node and 
	* reassigns the previous node to be the new tail
	*/
	public void removeLast()
	{
		tail = tail.previous;
		tail.next = null;
	}
	
	/**
	* setCurrent: Sets the current node to a specific index on the LinkedList
	* @param index - An int that designates which node should be set to current
	*/
	public void setCurrent(int index)
	{
		//set current to head if index = 0
		if(index == 0)
			current = head;

		//set current to tail if index = -1 
		else if(index == -1)
			current = tail;
		
		//Find designated node and set it to currrent
		else
		{
			Node<Integer> temp = head;
			for(int i = 1; i <= index; i++)
				temp = temp.next;
			current = temp;
		}
	}
	
	/** 
	* getCurrent: returns the value of the current node 
	* @return current.value: The int value of current node
	* @return null: Returns null if current value is null
	*/
	public Integer getCurrent()
	{
		if(current.value != null)
			return current.value;
		return null;
	}
	
	/**prevCurrent: Assigns current to the previous node*/
	public void prevCurrent()
	{
		current = current.previous;
	}
	
	/**nextCurrent: Assigns current to the next node*/
	public void nextCurrent()
	{
		current = current.next;
	}
	
	/**
	* Set's sign to a specific int
	* @param i - An integer
	*/
	private void setSign(int i)
	{
		sign = i;
	}
	
	/**changeSign: changes sign by mulitiplying 1*/
	public void changeSign()
	{
		sign *= -1;
	}
	
	/**
	* getSign: Returns sign
	* @return sign - An integer designating the sign of APInt
	*/
	public int getSign()
	{
		return sign;
	}
	
	/** 
	* add: Adds the positional values of APInt types. It also 
	* takes into account carryover and special cases such as the sign
	* @param addend - An APInt type which is the addend of the add method
	* @return sum - An APInt type that is representative of the sum
	*/
	public APInt add(APInt addend)
	{
		//Performs an additional if addend's sign matches to this APInt
		if (sign == addend.getSign())
		{ 
			//Intialize sum as an APInt type
			APInt sum = new APInt();
			
			//Intialize a pointer to larger or smaller APInt
			APInt biggerAddend;
			APInt smallerAddend;

			//Assigns addend to the biggerAddend if its bigger than this APInt (v.v.)
			if(compareTo(addend) < 0)
			{
				biggerAddend = addend;
				smallerAddend = this;
			}
			else
			{
				biggerAddend = this;
				smallerAddend = addend;
			}

			//Set the bigger and smaller addend to previous node of the tail.
			//Since the tail's value is null
			biggerAddend.setCurrent(-1);
			smallerAddend.setCurrent(-1);
			biggerAddend.prevCurrent();
			smallerAddend.prevCurrent();
			

			int temp = 0;				//Set addition carry over to 0
			int limit = (int) Math.pow(10,defaultDigits);	//Set the size of each node to be one digit

			//Add a null value to the head of both addends to signal 
			//When the addition of each positional digit is no longer needed.
			smallerAddend.addFirst(null);
			biggerAddend.addFirst(null);

			//Adds each positional digit from smallerAddend to bigger addend
			while(smallerAddend.getCurrent() != null)
			{
				//Takes the sum of each positional column 
				int total = biggerAddend.getCurrent() + smallerAddend.getCurrent() + temp;
				
				temp = 0;	//Reassigns carryover to zero

				//If total contains more than 1 digit than take the leading digits and assign it to 
				//Temp as carryover. 
				if(total >= limit)
				{
					temp = total / limit;
					total %= limit;
				}

				//Add total to the sum and update both addends
				sum.addFirst(total);
				biggerAddend.prevCurrent();
				smallerAddend.prevCurrent();
			}
			
			//Remove null value from head
			smallerAddend.removeFirst();

			//Input the remaining digits including the carryover
			while(biggerAddend.getCurrent() != null)
			{
				sum.addFirst(biggerAddend.getCurrent() + temp);
				temp = 0;
				biggerAddend.prevCurrent();
			}

			//Input last carryover if it is not 0
			if(temp != 0)
				sum.addFirst(temp);

			//Remove null value from head
			biggerAddend.removeFirst();
			
			//Set the sign of the sum as the sign of this addend
			sum.setSign(sign);
			return sum;
		}
		else 
		{
			//Apply a subtract method by changing the sign of a copy of 
			//Addend, since the signs are different.
			APInt new_addend = new APInt(addend);
			new_addend.changeSign();
			return subtract(new_addend);
		}

	}
	
	/** 
	* subtract: Subtract the positional values of APInt types. It also 
	* takes into account carryover and special cases such as the sign
	* @param subtractor - An APInt type which is the subtrahend of the subtrahend method
	* @return difference - An APInt type that is representative of the difference.
	*/
	public APInt subtract(APInt subtractor)
	{
		if (sign == subtractor.getSign())
		{
			//Intialize difference as an APInt type
			APInt diff = new APInt();
			//Create a pointer to the minuend end and subtrahend.
			APInt minuend, subtrahend;
			
			//Assign subtractor to minuend is bigger and reverse the sign of minuend
			//Which will later be used to set diff's sign.
			if(compareTo(subtractor) < 0)
			{
				minuend = new APInt(subtractor);
				subtrahend = new APInt(this);
				minuend.changeSign();
			}
			else
			{
				minuend = new APInt(this);
				subtrahend = new APInt(subtractor);
			}
			
			//Set the minuend and subtrahend to previous node of the tail.
			//Since the tail's value is null
			minuend.setCurrent(-1);
			subtrahend.setCurrent(-1);
			minuend.prevCurrent();
			subtrahend.prevCurrent();

			int temp = 0;		//Set the subtraction carry over to 0
			int limit = (int) Math.pow(10, defaultDigits); //Set the size of each node to be one digit

			//Add a null value to the head of both the minuhead and subtrahend to signal 
			//When the subtraction of each positional digit is no longer needed.
			subtrahend.addFirst(null);
			minuend.addFirst(null);

			//Subtracts each positional digit from minuend to subtrahend
			while(subtrahend.getCurrent() != null)
			{
				//Takes the difference of each positional column 
				int difference = (minuend.getCurrent() - temp) - subtrahend.getCurrent();
				
				temp = 0;	//Reasigns the carryover to zero
				
				//Increments carryover if the difference is less zero
				if (difference < 0)
				{
					temp++;
					//Finds the positive difference by adding the 10
					difference += limit;	
				}

				//Adds the positional differences and updates both the minuend and 
				//subtrahend
				diff.addFirst(difference);
				minuend.prevCurrent();
				subtrahend.prevCurrent();
			}

			//Remove null value from head
			subtrahend.removeFirst();

			//Input the remaining digits including the carryover
			while(minuend.getCurrent() != null)
			{
				diff.addFirst(minuend.getCurrent() - temp);
				temp = 0;
				minuend.prevCurrent();
			}

			//Remove null value from head
			minuend.removeFirst();
			
			diff.setCurrent(1);
			//Remove's First digit if it is 0 followed by non-zero digits
			while(diff.getFirst() == 0 && diff.getCurrent() != null)
			{
				diff.removeFirst();
				diff.nextCurrent();
			}

			//Set the sign of the difference as minuend's sign
			diff.setSign(minuend.getSign());
			return diff;

		}
		else
		{
			//Apply the add method by changing the sign of a copy of 
			//subtractor, since signs are different.
			APInt new_subtractor = new APInt(subtractor);
			new_subtractor.changeSign();
			return add(new_subtractor);
		}
	}

	/** 
	* multiply: Multiplies the positional values of APInt types. It also 
	* takes into account carryover and special cases such as the sign. 
	* @param factor - An APInt type which is the factor of the multiply method
	* @return product - An APInt type that is representative of the product
	*/
	public APInt multiply(APInt factor)
	{
		//Intialize the product as an APInt type of value zero
		APInt product = new APInt(0);
		//Set the size of each node to be one digit
		int position = (int) Math.pow(10, defaultDigits);

		//Set's this APInt's and factor's current node to the previous 
		//Node of tail since the tail's value is null 
		setCurrent(-1);
		factor.setCurrent(-1);
		prevCurrent();
		factor.prevCurrent();
		
		int carryOver; 		//Intialize an int type carryover.
		//Represents a APInt of zero to correct place each tempPlaceHolder
		APInt placeHolder = new APInt();
		
		//Add a null value to the head of both t this APInt and factor to signal 
		//When the multiply method of each positional digit is no longer needed.
		addFirst(null);
		factor.addFirst(null);

		//Adds up all the tempPlaceholder APInts that represent standard 
		//multi-digit multiplication.
		while(getCurrent() != null)
		{
			//Intialize an APInt representative of a poistional multiplying another APInt
			APInt tempPlaceHolder = new APInt();
			
			//Reset factor's current node to the previous node of its tail
			factor.setCurrent(-1);
			factor.prevCurrent();

			//Reset the carryover to 0
			carryOver = 0;

			//Creates a tempPlaceHolder where a positional digit from this APInt
			//Is multiplied with a positional digit from factor
			while(factor.getCurrent() != null)
			{	
				//Takes the product of two positional digits and adds the carryover
				int dig = (getCurrent() * factor.getCurrent()) + carryOver;
				carryOver = 0;   //Reset the carryover to 0
				
				//Adds digs to tempPlaceHolder and updates carryover if 
				//digs is greater than 10
				if(dig > position)
				{
					tempPlaceHolder.addFirst(dig % position);
					carryOver = dig/position;
				}
				else
					tempPlaceHolder.addFirst(dig);
				//Updates factor
				factor.prevCurrent();;
			}
			//Adds carryover to tempHolder if it is nonzero
			if(carryOver != 0)
				tempPlaceHolder.addFirst(carryOver);
			tempPlaceHolder.removeLast();

			//Sets placeholder to the head
			placeHolder.setCurrent(0);

			//Removes null from the tail of tempPlaceHolder for input
			tempPlaceHolder.removeLast();

			//Adds neccessary zeros before addition of tempPlaceHoldders
			while(placeHolder.getCurrent() != null)
			{
				tempPlaceHolder.addLast(0);
				placeHolder.nextCurrent();
			}
			
			//Reassigns tail to null
			tempPlaceHolder.addLast(null);

			//Adds tempPlaceHolder to product
			product = product.add(tempPlaceHolder);
			product.removeLast();
			
			//Increase the amount of 0s in placeholder by 1
			placeHolder.addFirst(0);
			
			placeHolder.addLast(0);
			//Updates this APInt's current
			prevCurrent(); 
		}
		//Remove null value from head from both APInts
		factor.removeFirst();
		removeFirst();

		//Change the sign of product to negative if signs don't match.
		if(getSign() != factor.getSign())
			product.changeSign();

		return product;
	}
	
	/** 
	* divide: Divides the positional values of APInt types. It also 
	* takes into account carryover and special cases such as the sign. 
	* @param divisor - An APInt type which is the divisor of the divide method
	* @return quotient - An APInt type that is representative of the quotient
	*/
	public APInt divide(APInt divisor)
	{
		//Intialize the quotient 
		APInt quotient = new APInt();
		APInt identity = new APInt(1);

		//If divisor is 0 return a null quotient (undefined)
		if(divisor.getFirst() == 0)
			return quotient;
		//If divisor is 1 return copy of dividend
		else if (divisor.compareTo(identity) == 0)
			return new APInt(this);
		//Returns quotient as zero if divisor is greater than this int
		else if (divisor.compareTo(this) > 0)
		{
			quotient.addFirst(0);
			return quotient;
		}
		//Returns a quotient as 1 if divisor is equal to this APInt
		else if(divisor.compareTo(this) == 0)
			return new APInt(1);

		//Intialize a copy of this APInt and divisor
		APInt dynamicDividend = new APInt(this);
		APInt dynamicDivisor = new APInt(divisor);

		//Set dividend's and divisor's current node as the head.
		dynamicDividend.setCurrent(0);
		dynamicDivisor.setCurrent(0);
		//Intialize a partition of the dividend the same size of divisor 
		APInt dynamic = new APInt();

		//Add the positional digits from dividend to dynamic
		while(dynamicDivisor.getCurrent() != null)
		{
			dynamic.addLast(dynamicDividend.getCurrent());
			dynamicDividend.nextCurrent();
			dynamicDivisor.nextCurrent();

		}

		//Add an additional null value as tail.
		dynamicDividend.addLast(null);
		//Add an additional digit from dividend to dynamic if its still
		//less than divisor
		if(dynamic.compareTo(dynamicDivisor) < 0)
		{
			dynamic.addLast(dynamicDividend.getCurrent());
			dynamicDividend.nextCurrent();
		}

		//Remove null values from head and add null value to tail
		dynamic.removeFirst();
		dynamic.removeFirst();
		dynamic.addLast(null);

		//change sign of dynamic if it is different from divisor
		if(dynamicDivisor.getSign() != dynamic.getSign())
			dynamic.changeSign();

		//Set the current node of dynamic to head.
		dynamic.setCurrent(0);


		//Continously subtract divisor from dynamic and count the number of 
		//Times it can be subtracted from dynamic till it becomes greater. 
		//The result is represented as the quotient
		while(dynamicDividend.getCurrent() != null)
		{
			//Remove's First digit if it is 0 followed by non-zero digits
			dynamic.setCurrent(1);
			while(dynamic.getFirst() == 0 && dynamic.getCurrent() != null)
			{
				dynamic.removeFirst();
				dynamic.nextCurrent();
			}

			int count = 0; //Set quotient of dynamic and divisor to zero
			//Update count till dynamic is less than divisor
			while(dynamic.compareTo(divisor) >= 0)
			{
				dynamic = dynamic.subtract(dynamicDivisor);
				count++;
			}

			//Remove null tail of dynamic if count is not zero.
			if(count != 0)
				dynamic.removeLast();

			//Update the previous node of tail to the next digit
			//Of next dynamic digit
			dynamic.removeLast();
			dynamic.addLast(dynamicDividend.getCurrent());
			dynamic.addLast(null);

			//Add count to quotient	
			quotient.addLast(count);
			
			//Update
			dynamicDividend.nextCurrent();
			count = 0;
		}

		//Add and additional zero if dynamic contains a leading zero,
		//And the compareTo method states that it is still bigger.
		if(dynamic.compareTo(divisor) > 0 && dynamic.getFirst() == 0)
			quotient.addLast(0);
		
		//Update the last count if dynamic is still greater than or equal to 
		//divisor
		else if(dynamic.compareTo(divisor) >= 0)
		{
			int count = 0;
			while(dynamic.compareTo(divisor) >= 0)
			{
				dynamic = dynamic.subtract(dynamicDivisor);
				count++;
			}
			dynamic.removeLast();
			dynamic.removeLast();
			dynamic.addLast(dynamicDividend.getCurrent());
			dynamic.addLast(null);
			if(count != 0)
				quotient.addLast(count);
		}

		//Remove null values from head and add null value to the tail
		quotient.removeFirst();
		quotient.removeFirst();
		quotient.addLast(null);

		//Change the sign of quotient if the dividend and divisor signs are 
		//Different
		if(sign != divisor.getSign())
			quotient.changeSign();
		return quotient;
	}
	
	/** 
	* getRemainder: This is modulus method in which the remainder is returned. This is 
	* similar to the divide method except the remainder (dynamic is returned).
	* @param divisor - An APInt type which is the divisor of the getRemainder method
	* @return dynamic - An APInt type that is representative of the remainder
	*/
	public APInt getRemainder(APInt divisor)
	{
		APInt remainder = new APInt();

		//Return's Remainder 0 if modulus is 1
		if(divisor.compareTo(new APInt(1)) == 0)
			return new APInt(0);

		//Returns remainder as 0 if divisor is 0
		if(divisor.compareTo(new APInt(0)) == 0)
		{
			return new APInt(0);
		}
		
		//Returns the remainder as 1 if the divisor is equal to this APInt
		if (divisor.compareTo(this) == 0)
		{
			remainder.addFirst(0);
			return remainder;
		}

		//Intialize a copy of this APInt and divisor
		APInt dynamicDividend = new APInt(this);
		APInt dynamicDivisor = new APInt(divisor);

		//Set dividend's and divisor's current node as the head.
		dynamicDividend.setCurrent(0);
		dynamicDivisor.setCurrent(0);
		//Intialize a partition of the dividend the same size of divisor 
		APInt dynamic = new APInt();

		//Add the positional digits from dividend to dynamic
		while(dynamicDivisor.getCurrent() != null)
		{
			dynamic.addLast(dynamicDividend.getCurrent());
			dynamicDividend.nextCurrent();
			dynamicDivisor.nextCurrent();
		}

		dynamicDividend.addLast(null);
		if(dynamic.compareTo(dynamicDivisor) < 0)
		{
			dynamic.addLast(dynamicDividend.getCurrent());
			dynamicDividend.nextCurrent();
		}
		//Add an additional null value as tail.
		dynamic.removeFirst();
		dynamic.removeFirst();
		dynamic.addLast(null);

		//Add an additional digit from dividend to dynamic if its still
		//less than divisor
		if(dynamicDivisor.getSign() != dynamic.getSign())
			dynamic.changeSign();
		
		//Set the current node of dynamic to head.
		dynamic.setCurrent(0);
		int count = 0; //Set quotient of dynamic and divisor to zero

		//Continously subtract divisor from dynamic and count the number of 
		//Times it can be subtracted from dynamic till it becomes greater. 
		//The result is represented as the quotient
		while(dynamicDividend.getCurrent() != null)
		{
			//Remove's First digit if it is 0 followed by non-zero digits
			dynamic.setCurrent(1);
			while(dynamic.getFirst() == 0 && dynamic.getCurrent() != null)
			{
				dynamic.removeFirst();
				dynamic.nextCurrent();
			}

			//Update count till dynamic is less than divisor
			while(dynamic.compareTo(divisor) > 0)
			{
				dynamic = dynamic.subtract(dynamicDivisor);
				count++;
			}
		
			//Remove null tail of dynamic if count is not zero.
			if(count != 0)
				dynamic.removeLast();

			//Update the previous node of tail to the next digit
			//Of next dynamic digit
			dynamic.removeLast();
			dynamic.addLast(dynamicDividend.getCurrent());
			dynamic.addLast(null);
			
			//Update
			dynamicDividend.nextCurrent();
			count = 0;
		}
		
		//Update the last count if dynamic is still greater than or equal to 
		//divisor
		if(dynamic.compareTo(divisor) >= 0)
		{
			while(dynamic.compareTo(divisor) >= 0)
			{
				dynamic = dynamic.subtract(dynamicDivisor);
			}
			dynamic.removeLast();
			dynamic.removeLast();
			dynamic.addLast(dynamicDividend.getCurrent());
			dynamic.addLast(null);
		}

		//Returns dynamic which represents the remainder
		return dynamic;
	}
	
	/** 
	* compareTo: Displays 1 if this APInt is greater, 
	* 0 if it is equal to 
	* -1 if it is less than
	* @param logic - The APInt that is being compared to
	@ @return An Integer representing the state of the comparsion
	*/
	public int compareTo(APInt logic)
	{
		//Sets the current node of both comparisions to head
		setCurrent(0);
		logic.setCurrent(0);

		//Compares the positional digits, if one has more returns the states.
		while(this.getCurrent() != null || logic.getCurrent() != null)
		{
			if(this.getCurrent() == null && logic.getCurrent() != null)
				return -1;
			else if(this.getCurrent() != null && logic.getCurrent() == null)
				return 1;
			nextCurrent();
			logic.nextCurrent();
		}

		//Sets the current node of both comparisions to head
		setCurrent(0);
		logic.setCurrent(0);

		//Compares the first node if they both have the same positional digits
		while(getCurrent() != null)
		{
			if(getCurrent() > logic.getCurrent())
				return 1;
			else if(getCurrent() < logic.getCurrent())
				return -1;
			nextCurrent();
			logic.nextCurrent();
		}
		return 0;
	}
	
	/** toString: Print method for APInt Class
	* @Override toString
	* @return: A string representative of an Integer
	*/
	public String toString()
	{
		StringBuilder number = new StringBuilder();
		setCurrent(0);
		if(sign == 1)
			number.append('+');
		else
			number.append('-');
		while(getCurrent() != null)
		{
			number.append(Integer.toString(getCurrent()));
			nextCurrent();
		}
		return number.toString();
	}
	
	/**Node<Integer> Represents and Integer Node for LinkedList*/
	private static class Node<Integer> 
	{
		Node<Integer> next;			//Points to the next Node
		Node<Integer> previous;		//Points to the previous Node
		Integer value;				//Represents the value

		/**Constructor which assigns Integer value to value*/
		public Node(Integer value)
		{
			this.value = value; 
		}
	}
}
