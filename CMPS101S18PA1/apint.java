

public class APInt
{
	private Node<Integer> head = new Node<>(null);
	private Node<Integer> tail = new Node<>(null);
	private Node<Integer> current = tail;
	private int defaultDigits = 1;
	private int realSign, sign = 1;			      //(+ is a 1, - is a -1)	


	public APInt()
	{
		head.next = tail;
		tail.previous = head;
	}

	public APInt(String number)		//Needs Revision (Sign is optional)
	{
		if(number.charAt(0) == '+' || number.charAr(0) == '-')
		{
			if(number[0] == '-')
			{		
				//Might need to revise if digits[0]
				sign == -1;					//Doesn't contain sign
				number = number.substring(1);
			}
		}

		Char[] digits = number.getCharArray(number);
		Node<Integer> newDig, temp;
		head.next = tail;
		tail.previous = head;

		for(Char d: digits)
		{
			newDig = new Node<>(Character.getNumericValue(digits));
			temp = tail.previous;
			temp.next = newDig;
			newDig.previous = temp;
			newDig.next = tail;
			tail.previous = newDig;
		}
	}

	public APInt(int integer)
	{
		if(integer < 0)
			changeSign();

		Node<Integer> newDig, temp;
		head.next = tail;
		tail.previous = head;
		do
		{
			newDig = new Node<>(integer % 10);
			temp = tail.previous;
			temp.next = newDig;
			newDig.previous = temp;
			newDig.next = tail;
			tail.previous = newDig;
			integer /= 10;
		}
		while(integer != 0)
	}
	
	public APInt(double realNum)
	{
		int altInt = (int) realNum;
		if (altInt < 0)
			changeSign();
	
			Node<Integer> newDig, temp;
			head.next = tail;
			tail.previous = head;
			do
			{
				newDig = new Node<>(altInt % 10);
				temp = tail.previous;
				temp.next = newDig;
				newDig.previous = temp;
				newDig.next = tail;
				tail.previous = newDig;
					integer /= 10;
				}
				while(altInt != 0)
			}
		
		public APInt(APInt original)
		{
			APInt copy = new APInt();
			if(original.getSign() == -1)
				changeSign();
	
		original.setCurrent(0);
		<Integer> newDig, temp;
		head.next = tail;
		tail.previous = head;
		do
		{
			newDig = new Node<>(original.getCurrent());
			temp = tail.previous;
			temp.next = newDig;
			newDig.previous = temp;
			newDig.next = tail;
			tail.previous = newDig;
			original.nextCurrent();
		}
		while(altInt != 0)
	}
	
	public Integer getFirst()
	{
		if (size == 0)
			return null;
		else 
			return head.value;
	}
	
	public Integer getLast()
	{
		if(size == 0)
			return null;
		else
			return tail.value;
	}
	
	public void addFirst(Integer digit) 
	{
		if(size == 0)
			head = tail = new Node<Integer>(digit);
		else if (size == 1)
		{
			head = new Node<Integer>(digit);
			head.next = tail;
			tail.previous = head;
		}
		else
		{
			Node<Integer> temp = head;
			head = new Node<Integer>(digit);
			head.next = temp;
			temp.previous = head;
			size++;
		}
	}
	
	public void addLast(Integer digit)
	{
		if(size == 0)
			head = tail = new Node<Integer>(digit);
		else if (size == 1)
		{
			tail = new Node<Integer>(digit);
			tail.previous = head;
			head.next = previous;
		}
		else
		{
			Node<Integer> temp = tail;
			tail = new Node<Integer>(digit);
			tail.previous = temp;
			temp.next = tail;
			size++;
		}
	}
	
	public void removeFirst()
	{
		if(size > 1)
		{
			head = head.next;
			head.previous = null;
		}
		else if(size == 1)
			head = tail = null;
	}
	
	public void removeLast()
	{
		if (!(size > 1))
		{
			tail = tail.previous;
			tail.next = null;
		}
		else if(size == 1)
			head = tail = null;
	}
	
	public Integer getPrev()
	{
		if(size == 0)
			return null;
		else 
			return tail.previous
	}
	
	public void setCurrent(int index)
	{
		if(index == 0)
			current = head;
		else if(index = -1)
			current = tail;
		else
		{
			Node<Integer> temp = head;
			for(int i = 1; i < index; i++)
				temp = temp.next;
			current = temp;
		}
	}
	
	public int getCurrent()
	{
		if(!current = null)
			return current.value;
		return null;
	}
	
	public void prevCurrent()
	{
		current = current.previous;
	}
	
	public void nextCurrent()
	{
		current = current.next;
	}
	
	public void setSign(int i)
	{
		sign = i;
	}
	
	public void changeSign()
	{
		size *= -1;
	}
	
	public int getSign()
	{
		return sign;
	}
	
			// Revise with sign notations
	public APInt add(APInt addend)
	{
		if (sign == addend.getSign())
		{ 
			APInt sum = new APInt();
			APInt biggerAddend;
			APInt smallerAddend;
	
			if(compareTo(addend) < 0)
			{
				biggerAddend = this;
				smallerAddend = addend;
			}
			else
			{
				biggerAddend = addend;
				smallerAddend = this;
			}
	
			setCurrent(-1);
			addend.setCurrent(-1);
	
			int temp = 0;
			int limit = Math.pow(10,defaultDigits);
			while(smallerAddend.getCurrent() != null)
			{
				int total = getCurrent() + addend.getCurrent() + temp;
				if(total > limit)
					temp = total / limit;
				sum.addFirst(total);
				prevCurrent();
				addend.prevCurrent();
			}
	
			while(biggerAddend.getCurrent() != null)
			{
				biggerAddend.prevCurrent();
				sum.addFirst(biggerAddend.getCurrent());
				amount_Of_Calculations++;
			}
			addend.setSign(sign);
			return sum;
		}
		else 
		{
			addend.changeSign();
			return subtract(addend)
		}
	
	}
	
	public APInt subtract(APInt subtrahend)
	{
		if (sign == subtrahend.getSign())
		{
			APInt diff = new APInt();
			APInt minuend = this;
	
			setCurrent(-1);
			subtrahend.setCurrent(-1);
	
			if(compareTo(subtrahend) < 0)
			{
				minuend.changeSign();
				minuend = subtrahend;
				subtrahend = this;
			}
	
			int temp = 0;
			int carryOver = Math.pow(10, defaultDigits + 1);
			while(subtrahend.getCurrent() != null)
			{
				int difference = (minuend.getCurrent() - temp) - subtrahend.getCurrent();
				temp = 0;
				if (difference < 0)
				{
					temp++;
					difference += carryOver;
				}
				diff.addFirst(difference);
				minuend.prevCurrent();
				subtrahend.prevCurrent();
			}
	
			int amount_Of_Calculations = subtrahend.size();
			while(minuend.getCurrent() != null)
			{
				minuend.prevCurrent();
				diff.addFirst(minuend.getCurrent());
				amount_Of_Calculations++;
			}
			diff.setSign(minuend.getSign());
			return diff;
	
		}
		else
		{
			subtrahend.changeSign();
			return add(subtrahend);
		}
	}
	
	public APInt multiply(APInt factor)
	{
		APInt product = new APInt();
		APInt placeHolder = new APInt();
		int position = Math.pow(10, defaultDigits + 1);
	
		setCurrent(-1);
		while(getCurrent() != null)
		{
			while(factor.getCurrent() != null)
			{					
				APInt tempPlaceHolder = new APInt();
	
				int dig = getCurrent() * factor.getCurrent();
	
				if(dig > position)
				{
					tempPlaceHolder.addFirst(dig % position);
					dig /= position;
				}
	
				tempPlaceHolder.addFirst(dig);
				tempPlaceHolder.addLast(placeHolder);
				product = product.add(placeHolder);
				factor.nextCurrent();
				placeHolder.addLast(0);
			}
			placeHolder.addLast(0);
			nextCurrent();
		}
	
		if(getSign() != factor.getSign())
			product.changeSign();
	}
	
	public APInt divide(APInt divisor)
	{
		APInt quotient = new APInt();
		if (divisior.compareTo(this) > 0)
		{
			quotient.addFirst(0);
			return quotient
		}
	
		APInt dynamicDividend = new this;
		APInt dynamicDivisor = new divisor;
	
		dynamicDividend.setCurrent(0);
		dynamicDivisor.setCurrent(0);
		APInt dynamic = new APInt();
	
		while(dynamicDivisor.getCurrent() != null)
		{
			dynamic.addLast(dynamicDividend.getCurrent());
			dynamicDividend.nextCurrent();
		}
	
		dynamic.setCurrent(1);
		while(dynamicDividend.getCurrent() != null)
		{
			int count = 0;
			while(dynamic.compareTo(divisor) > 0)
			{
				dynamic = dynamic.subtract(dynamicDivisor);
				count++;
			}
			dynamic.addLast(dynamicDividend.getCurrent());
			quotient.addLast(count);
			dynamicDividend.nextCurrent();
			count = 0;
		}
	
		if(sign != divisor.getSign())
			quotient.changeSign();
		return quotient;
	}
	
	public APInt getRemainder(APInt divisor)
	{
		APInt remainder = new APInt();
	
		APInt dynamicDividend = new this;
		APInt dynamicDivisor = new divisor;
	
		dynamicDividend.setCurrent(0);
		dynamicDivisor.setCurrent(0);
		APInt dynamic = new APInt();
	
		while(dynamicDivisor.currentHasNext())
		{
			dynamic.addLast(dynamicDividend.getCurrent());
			dynamicDividend.nextCurrent();
		}
	
		dynamic.setCurrent(1);
		while(dynamicDividend.getCurrent() != null)
		{
			while(dynamic.compareTo(divisor) > 0)
			{
				dynamic = dynamic.subtract(dynamicDivisor);
			}
			dynamic.addLast(dynamicDividend.getCurrent());
			dynamicDividend.nextCurrent();
		}
		dynamic.removeLast();
		return dynamic;
	}
	
	public int compareTo(APInt logic)
	{
		setCurrent(0);
		logic.setCurrent(0);
		while(this.getCurrent() != null || logic.getCurrent() != null)
		{
			if(this.getCurrent() == null && logic.getCurrent() != null)
				return -1;
			else if(this.getCurrent() != null && logic.getCurrent() == null)
				return 1;
			nextCurrent();
			logic.nextCurrent();
		}
	
		if(head.value > logic.getFirst())
			return 1;
		return -1;
	}
	
	public modulus
	
	public Boolean currentHasNext()
	{
		if(current.next == null) 
			return false;
		return true;
	}
	
	public Boolean currentHasPrev()
	{
		if(current.previous null)
			return false;
		return true;
	}
	
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
	}
	
	private static class Node<Integer> 
	{
		Node<Integer> next;
		Integer<Integer> previous;
		Integer value;
	
		public Node<Integer>(value)
		{
			this.value = value; 
		}
	}
}