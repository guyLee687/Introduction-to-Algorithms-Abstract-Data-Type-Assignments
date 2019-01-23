
public OverflowHandling
{
	private Integer digit;

	class IntegerLinkedList
	{
		private Node<Integer> head;
		private Node<Integer> tail;
		private Node<Integer> current = tail;
		private int size = 0; 
		private int defaultDigits = 8;
		private int realSign, sign = 1;			      //(+ is a 1, - is a -1)	


		public IntegerLinkedList()
		{
			head = tail = null;
		}

		public IntegerLinkedList(Character[] digits)
		{
			if(digits[0] == '-')			//Might need to revise if digits[0]
				sign == -1;					//Doesn't contain sign

				Node<Integer> temp = new Node<Integer>(0);
				tail = temp;
				for(int i = 1; i < digits.length; i + defaultDigits)
				{ 
					int cell;
					for (int k = 0; k < 8; k++)
					{
						cell += digits[k] * (Math.pow(10,k));
					}
					head = new Node<Integer>(cell);
					temp.previous = head;
					head.next = temp;
					size++;
					removeLast();
				}
			}

			public IntegerLinkedList(Character[] digits, int defaultDigits)
			{
				if(digits[0] == '-')
					sign == 0;

				Node<Integer> temp = new Node<Integer>(0);
				tail = temp;
				for(int i = 1; i < digits.length; i + defaultDigits)
				{ 
					int cell;
					for (int k = 0; k < 8; k++)
					{
						cell += digits[k] * (Math.pow(10,k));
					}
					head = new Node<Integer>(cell);
					temp.previous = head;
					head.next = temp;
					size ++;
				}
				removeLast();
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

			public int getSize()
			{
				return size;
			}

			public void setCurrent(int index)
			{
				if(index == 0)
					current = head;
				else if(index = (size - 1))
					current = tail
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

			public int getSize()
			{
				return size;
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
			public IntegerLinkedList add(IntegerLinkedList addend)
			{
				if (sign == addend.getSign())
				{ 
					IntegerLinkedList sum = new IntegerLinkedList();
					IntegerLinkedList biggerAddend
					int amount_Of_Calculations;
					if(size > addend.getSize())
					{
						amount_Of_Calculations = addend.getSize();
						biggerAddend = this;
					}
					else
					{
						amount_Of_Calculations = size;
						biggerAddend = addend;
					}

					setCurrent(size - 1);
					addend.setCurrent(size - 1);

					int temp = 0;
					int limit = Math.pow(10,defaultDigits);
					for (int i = 0; i < amount_Of_Calculations; i++)
					{
						int total = getCurrent() + addend.getCurrent() + temp;
						if(total > limit)
							temp = total % limit;
						sum.addFirst(total);
						prevCurrent();
						addend.prevCurrent();
					}

					while(!(biggerAddend.getSize() - amount_Of_Calculations == 0))
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

			public IntegerLinkedList subtract(IntegerLinkedList subtrahend)
			{
				if (sign == subtrahend.getSign())
				{
					IntegerLinkedList diff = new IntegerLinkedList();
					IntegerLinkedList minuend = this;
					
					setCurrent(size - 1);
					subtrahend.setCurrent(size - 1);

					if(size < subtrahend.getSize())
					{
						minuend.changeSign();
						minuend = subtrahend;
						subtrahend = this;
					}

					int temp = 0;
					int carryOver = Math.pow(10, defaultDigits + 1);
					for (int i = 0; i < subtrahend.size(); i++)
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
					while(!(minuend.getSize() - amount_Of_Calculations == 0))
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

			public IntegerLinkedList multiply(IntegerLinkedList factor)
			{
				IntegerLinkedList product = new IntegerLinkedList();
				int position = Math.pow(10, defaultDigits + 1);

				for(int i = 0; i < size; i++)
				{
					for(int k = 0; k < factor.getSize(); k++)
					{
						IntegerLinkedList placeHolder = new IntegerLinkedList();
						
						for(int = l; l <= (i+k); l++)
							placeHolder.addLast(0);
						
						int dig = tempRow * factor.getCurrent();
						
						if(dig > position)
						{
							placeHolder.addFirst(dig % position);
							dig %= position;
						}
						
						placeHolder.addFirst(dig);
						product = product.add(placeHolder)
					}
				}

				if(getSign() != factor.getSign())
					product.changeSign();
			}

			public IntegerLinkedList divide(IntegerLinkedList divisor)
			{
				IntegerLinkedList quotient = new IntegerLinkedList(new IntegerLinkedList(),1);
				if (divisor.getSize() > size)
				{
					quotient.addFirst(0);
					return quotient
				}
				else if (divisor.getSize() == size)
				{
					quotient.addFirst(head.value/divisor.getFirst())
					return quotient;
				}

				IntegerLinkedList dynamicDividend = new IntegerLinkedList(this, 1);
				IntegerLinkedList dynamicDivisor = new IntegerLinkedList(divisor, 1);
				
				dynamicDividend.setCurrent(0);
				IntegerLinkedList dynamic = new IntegerLinkedList(new IntegerLinkedList(), 1);
				
				for (int i = 0; i <= dynamicDivisor; i++)
				{
					dynamic.addLast(dynamicDividend.getCurrent());
					dynamicDividend.nextCurrent();
				}

				while(dynamicDividend.currentHasNext())
				{
					int count = 0;
					if(dynamic.CompareTo(divisor) > 1)
					{
						dynamic = dynamic.subtract(dynamicDivisor);
						count++;
					}
					else
					{
						dynamic.addLast(dynamicDividend.getCurrent());
						quotient.addLast(count);
						count = 0;
					}
				}

				if(sign != divisor.getSign())
					quotient.changeSign();
				return quotient;
			}

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
	}