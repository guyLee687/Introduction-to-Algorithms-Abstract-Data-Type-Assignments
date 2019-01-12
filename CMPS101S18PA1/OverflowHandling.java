
public OverflowHandling
{
	private Integer digit;

	class IntegerLinkedList
	{
		private Node<Integer> head;
		private Node<Integer> tail;
		private Node<Integer> current = tail;
		private static size = 0; 
		private int defaultDigits = 8;
		private int sign = 1;			      //(+ is a 1, - is a 0)	


		public IntegerLinkedList()
		{
			head = tail = null;
		}

		public IntegerLinkedList(Character[] digits)
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
		}

		public void prevCurrent()
		{
			current = current.previous;
		}

		public void nextCurrent()
		{
			current = current.next;
		}

		// Revise with sign notations
		public IntegerLinkedList add(IntegerLinkedList addend)
		{
			IntegerLinkedList sum = new IntegerLinkedList()
			int amount_Of_Calculations;
			if(size > addend.getSize())
				amount_Of_Calculations = addend.getSize();
			else
				amount_Of_Calculations = size;

			setCurrent(size - 1);
			addend.setCurrent(size - 1);

			int temp = 0;
			int limit = Math.pow(10,defaultDigits);
			for (int i = 0; i < amount_Of_Calculations; i++)
			{
				int total = getCurrent() + addend.getCurrent() + temp;
				if(total > limit)
					temp %= limit;
				sum.addFirst(total);
				prevCurrent();
				addend.prevCurrent();
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
}