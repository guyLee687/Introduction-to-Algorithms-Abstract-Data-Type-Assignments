import java.io.*;
import java.util.*;

//Programmer: Jeffrey Wang
//Date: 02/26/19
//COMPS-101

/**
 * The following class generates a Hash Table from a list of Anagram words
 * from a textfile. The Table with be sized 10 times the available list and will * use a linkedList to store sets of Anagram.
 */

public class HashTable{
	private LinkedList[] anagramSets;	//A Hash Table that utilizes a LinkedList to 
						//Handle collisions.
	private int n; 				//Size of list

	/**
 	* HashTable Constructor: Intializes a HashTable with a file.txt. It must
 	* be initialized with a file. 
 	* @param File wordList: A file that contains the words of anagrams.
 	*
 	*/
	public HashTable(File wordList)
	{
		//Implement File reader to tranverse through list of words
		FileReader file = new FileReader(wordList);
		Scanner scan = new Scanner(file);
		
		//Set the size of the HashTable
		n = 10 * (Integer.parseInt(scan.nextLine()));
		anagramSet = new LinkedList[n];
		
		//Inputs the keys into the hash table
		while(scan.hasNextLine())
		{
			//Generates the key associated with the hash table
			//By taking the mod of the size of array
			Anagram temp = new Angram(scan.nextLine());
			long key =  temp.getHashValue % n;
			
			//Flag for when the Anagram has be inserted into Hash Table
			Boolean flag = true;
			
			//Labels the first and next LinkedList within a space in anagram
			LinkedList head = anagramSets[key];
                        LinkedList next = head;

			//Transerve through loop till Anagram type is inserted
			while(flag)
			{	
				//If there is no nextLinkedList
				if(next == null)
				{
					//Input a newLinkedList with set of Angrams to array
					if(head == null)
					{
						angramSets[key] =  new LinkedList(temp);
						flag = false;
					}
					//Sets the new LinkedList to point as the next one
					//if there was a LinkedList before it.	
					else
					{
						head.next() = new LinkedList(temp);
						flag = false;	
					}
				} 
				//Checks whether or not the Anagram belongs in a set of 
				//Anagrams
				else if(temp.compare(next.getHead()))
				{
					head.addToSet(temp);
					flag = false;
				}
				//Point to the next set of LinkedLists
				else
				{
					head = next;
					next = next.next;
				}
			}	
		}
		/**
 		 * This method searchs through a Hash Table and returns an
 		 * array of Strings that are anagrams of a word.
 		 * @param Anagram focus: The word that we are trying to 
 		 * 			 search in the hash Table.
 		 * @return String[]: Returns an array of string that is an anagram 
 		 * 		    of focus.
 		 */
		public String[] search(Anagram focus)
		{	
			LinkedList sameKey = anagramSets[focus.getHashValue()];
			while(sameKey != null)
			{
				if(focus.compare(sameKey.getHead()));
					return sameKey.getAnagrams();
				sameKey = sameKey.getNext();
			}
			return null;
		}
	}
	/**
 	 * The following private class will define a set of 'Anagram(object)' that 
 	 * are actual Anagrams.
 	 */ 
	private class LinkedList
	{
		private Anagram head;
		private LinkedList next;
		private ArrayList<Anagram> set;
		
		public LinkedList(Anagram first)
		{
			head = first;
			set = new ArrayList();
			set.add(first);
		}
		public Anagram getHead()
		{
			return head;
		}
		
		public void setNext(LinkedList next)
		{
			this.next = next;
		}
		
		public LinkedList getNext()
		{
			return next;
		}
		
		public void add(Anagram newEle)
		{
			set.add(newEle);
		}	
		
		public String[] getAnagrams()
		{
			ArrayList<String> wordAnagrams = new ArrayList<>();
			for(Anagram i:set)
				wordAnagrams.add(i.getString());
			return(wordAnagrams.toArray());
		}	 	
	}
}
 
