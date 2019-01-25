
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
		if(number.charAt(0) == '+' || number.charAt(0) == '-')
		{
			if(number.charAt(0)== '-')
			{		
				sign = -1;					
			}
		    number = number.substring(1);
		}

		char[] digits = number.toCharArray();
		Node<Integer> temp;
		head.next = tail;
		tail.previous = head;

		for(Character d: digits)
		{
		    temp = tail;
			tail = new Node<>(Character.getNumericValue(d));
			temp.next = tail;
			tail.previous = temp;
		}
		removeFirst();
		removeFirst();
		addLast(null);
	}

	public APInt(int integer)
	{
		if(integer < 0)
		{
			changeSign();
			integer *= -1;
		}
        
		Node<Integer> temp;
		head.next = tail;
		tail.previous = head;
		do
		{
		    temp = head;
			head = new Node<>(integer % 10);
			temp.previous = head;
			head.next = temp;
			integer /= 10;
		}
		while(integer != 0);
		removeLast();
	}
	
	public APInt(double realNum)
	{
		int altInt = (int) realNum;
		if (altInt < 0)
		{
			changeSign();
			realNum *= -1;
		}

		Node<Integer> temp;
		head.next = tail;
		tail.previous = head;
		do
		{
		    temp = head;
			head = new Node<>(altInt % 10);
			head.next = temp;
			temp.previous = head;
			altInt /= 10;
		}
		while(altInt != 0);
		removeLast();
	}

	public APInt(APInt original)
	{
		if(original.getSign() == -1)				
			changeSign();

		original.setCurrent(0);
		Node<Integer> temp;
		head.next = tail;
		tail.previous = head;
		do
		{
            temp = tail;
			tail = new Node<>(original.getCurrent());
			temp.next = tail;
			tail.previous = temp;
			original.nextCurrent();
		}
		while(original.getCurrent() != null);
	  	removeFirst();
		removeFirst();
		addLast(null);
	}
	
	public Integer getFirst()
	{
		return head.value;
	}
	
	public Integer getLast()
	{
		return tail.value;
	}
	
	public void addFirst(Integer digit) 
	{
		Node<Integer> temp = head;
		head = new Node<Integer>(digit);
		head.next = temp;
		temp.previous = head;
	}
	
	public void addLast(Integer digit)
	{

		Node<Integer> temp = tail;
		tail = new Node<Integer>(digit);
		tail.previous = temp;
		temp.next = tail;
	}
	
	public void removeFirst()
	{	
		head = head.next;
		head.previous = null;
	}
	
	public void removeLast()
	{
		tail = tail.previous;
		tail.next = null;
	}
	
	public Integer getPrev()
	{
		return (tail.previous).value;
	}
	
	public void setCurrent(int index)
	{
		if(index == 0)
			current = head;
		else if(index == -1)
			current = tail;
		else
		{
			Node<Integer> temp = head;
			for(int i = 1; i < index; i++)
				temp = temp.next;
			current = temp;
		}
	}
	
	public Integer getCurrent()
	{
		if(current.value != null)
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
		sign *= -1;
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
				biggerAddend = addend;
				smallerAddend = this;
			}
			else
			{
				biggerAddend = this;
				smallerAddend = addend;
			}

			biggerAddend.setCurrent(-1);
			smallerAddend.setCurrent(-1);
			biggerAddend.prevCurrent();
			smallerAddend.prevCurrent();
			

			int temp = 0;
			int limit = (int) Math.pow(10,defaultDigits);
				
			smallerAddend.addFirst(null);
			biggerAddend.addFirst(null);
			while(smallerAddend.getCurrent() != null)
			{
				int total = biggerAddend.getCurrent() + smallerAddend.getCurrent() + temp;
				temp = 0;
				if(total >= limit)
				{
					temp = total / limit;
					total %= limit;
				}
				sum.addFirst(total);
				System.out.println(total);
				biggerAddend.prevCurrent();
				smallerAddend.prevCurrent();
			}
			
			smallerAddend.removeFirst();

			while(biggerAddend.getCurrent() != null)
			{
				sum.addFirst(biggerAddend.getCurrent() + temp);
				temp = 0;
				biggerAddend.prevCurrent();
			}
			if(temp != 0)
			    sum.addFirst(temp);
			biggerAddend.removeFirst();
			
			sum.setSign(sign);
			return sum;
		}
		else 
		{
			addend.changeSign();
			return subtract(addend);
		}

	}
	
	public APInt subtract(APInt subtractor)
	{
		if (sign == subtractor.getSign())
		{
			APInt diff = new APInt();
			APInt minuend, subtrahend;

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
			
			minuend.setCurrent(-1);
			subtrahend.setCurrent(-1);
		    minuend.prevCurrent();
		    subtrahend.prevCurrent();
            
			int temp = 0;
			int carryOver = (int) Math.pow(10, defaultDigits);
		
			subtrahend.addFirst(null);
			minuend.addFirst(null);
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
            subtrahend.removeFirst();
                
			while(minuend.getCurrent() != null)
			{
				diff.addFirst(minuend.getCurrent() - temp);
				temp = 0;
				minuend.prevCurrent();
			}
			minuend.removeFirst();
			diff.setSign(minuend.getSign());
			return diff;

		}
		else
		{
			subtractor.changeSign();
			return add(subtractor);
		}
	}
	
	public APInt multiply(APInt factor)
	{
		APInt product = new APInt(0);
		int position = (int) Math.pow(10, defaultDigits);

		setCurrent(-1);
		factor.setCurrent(-1);
		prevCurrent();
		factor.prevCurrent();
		
		int carryOver;
		APInt placeHolder = new APInt();
		
		addFirst(null);
		factor.addFirst(null);
		while(getCurrent() != null)
		{
		    APInt tempPlaceHolder = new APInt();
		    factor.setCurrent(-1);
		    factor.prevCurrent();
		    carryOver = 0;
			while(factor.getCurrent() != null)
			{	
				int dig = (getCurrent() * factor.getCurrent()) + carryOver;
                carryOver = 0;
				
				if(dig > position)
				{
					tempPlaceHolder.addFirst(dig % position);
					carryOver = dig/position;
				}
                else
			    	tempPlaceHolder.addFirst(dig);
				factor.prevCurrent();;
			}
			    if(carryOver != 0)
			        tempPlaceHolder.addFirst(carryOver);
		        tempPlaceHolder.removeLast();
				
				placeHolder.setCurrent(0);
				tempPlaceHolder.removeLast();
				while(placeHolder.getCurrent() != null)
				{
					tempPlaceHolder.addLast(0);
					placeHolder.nextCurrent();
				}
				tempPlaceHolder.addLast(null);
				
                System.out.println("before " + tempPlaceHolder);
				product = product.add(tempPlaceHolder);
				product.removeLast();
				  placeHolder.addFirst(0);
				  System.out.println("After " + product);
			
			placeHolder.addLast(0);
			prevCurrent(); 
		}
		factor.removeFirst();
		removeFirst();
        
		if(getSign() != factor.getSign())
			product.changeSign();

		return product;
	}
	
	public APInt divide(APInt divisor)
	{
		APInt quotient = new APInt();
		if (divisor.compareTo(this) > 0)
		{
			quotient.addFirst(0);
			return quotient;
		}

		APInt dynamicDividend = this;
		APInt dynamicDivisor = divisor;

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

		APInt dynamicDividend = this;
		APInt dynamicDivisor = divisor;

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
	
	public Boolean currentHasNext()
	{
		if(current.next == null) 
			return false;
		return true;
	}
	
	public Boolean currentHasPrev()
	{
		if(current.previous == null)
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
		return number.toString();
	}
	
	private static class Node<Integer> 
	{
		Node<Integer> next;
		Node<Integer> previous;
		Integer value;

		public Node(Integer value)
		{
			this.value = value; 
		}
	}
}