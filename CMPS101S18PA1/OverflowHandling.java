
public OverflowHandling
{
	private Integer digit;

	class IntegerLinkedList
	{
		private Node<Integer> head;
		private Node<Integer> tail;
		private static size = 0; 
		private int defaultDigits = 8;
		private int sign = 1;			(+ is a 1, - is a 0)	


		public IntegerLinkedList()
		{
			head = tail = new Node<Integer>(null);
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