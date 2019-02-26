import java.io.*;
import java.util.*;

//Programmer: Jeffrey Wang
//Date: 02/26/19
//COMPS-101-db

/**
 * The following class generates a Hash Table from a list of Anagram words
 * from a textfile. The Table with be sized 10 times the available list and will * use a linkedList to store sets of Anagram.
 */

public class HashTable
{
	private LinkedList[] anagramSets;	//A Hash Table that utilizes a LinkedList to 
						//Handle collisions.
	private int n; 				//Size of list

	/**
 	* HashTable Constructor: Intializes a HashTable with a file.txt. It must
 	* be initialized with a file. 
 	* @param File wordList: A file that contains the words of anagrams.
 	*
 	*/
	public HashTable(File wordList) throws IOException
	{
		//Implement File reader to tranverse through list of words
		FileReader file = new FileReader(wordList);
		Scanner scan = new Scanner(file);
		System.out.println("Reading wordList.txt...");
		
		//Set the size of the HashTable
		n = 2017 * (Integer.parseInt(scan.nextLine()));
		anagramSets = new LinkedList[n];
		
		//Counting number of collisions
		int count = 0;

		//Inputs the keys into the hash table
		while(scan.hasNextLine())
		{
			//Generates the key associated with the hash table
			//By taking the mod of the size of array
			Anagram temp = new Anagram(scan.nextLine());
			int key = (int)(temp.getHashValue() % n);
			
			//Flag for when the Anagram has be inserted into Hash Table
			Boolean flag = true;
			
			//Labels the first and next LinkedList within a space in anagram
			LinkedList head = anagramSets[key];
                        LinkedList next = head;

			//Transerve through loop till Anagram type is inserted
			while(flag)
			{	
				if(next != null){
				}
				//If there is no nextLinkedList
				if(next == null)
				{
					//Input a newLinkedList with set of Angrams to array
					if(head == null)
					{
						anagramSets[key] =  new LinkedList(temp);
						flag = false;
					}
					//Sets the new LinkedList to point as the next one
					//if there was a LinkedList before it.	
					else
					{
						head.setNext(new LinkedList(temp));
						flag = false;
						count++;	
					}
				} 
				//Checks whether or not the Anagram belongs in a set of 
				//Anagrams
				else if(temp.compare(next.getHead()))
				{
					next.add(temp);
					flag = false;
				}
				//Point to the next set of LinkedLists
				else
				{
					head = next;
					next = next.getNext();
				}
			}
		}
		System.out.println("Handling " + count + " collisions...");

		//Close filereader
		file.close();
		System.out.println("Closing wordList.txt...");

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
		LinkedList sameKey = anagramSets[(int)(focus.getHashValue() % n)];
		while(sameKey != null)
		{
			//Returns LinkedList holding Anagrams
			if(focus.compare(sameKey.getHead()))
				return sameKey.getAnagrams();
			sameKey = sameKey.getNext();
		}
		return new String[] {""};
	}

	/**
 	 * The following private class will define a set of 'Anagram(object)' that 
 	 * are actual Anagrams.
 	 */ 
	private class LinkedList
	{
		private Anagram head = null;		//Points to the first Anagram of the set
		private LinkedList next = null;		//Points to the next set of Anagrams with the same hash value.
		private ArrayList<Anagram> set;		//Creates an ArrayList that stores values of set.
		
		/**
		* Required Constructor which takes in the first Anagram word in a set of Anagrams.
		* @param Anagram first: The first word in a set of Anagrams.
		*/			
		public LinkedList(Anagram first)
		{
			head = first;					
			set = new ArrayList<>();
			set.add(first);
		}

		/**Accessor for Head*/
		public Anagram getHead()
		{
			return head;
		}
		
		/**Mutator for next set of Anagrams*/
		public void setNext(LinkedList next)
		{
			this.next = next;
		}
		
		/**Accessor for next set of Anagrams*/
		public LinkedList getNext()
		{
			return next;
		}
		
		/**adds an Anagram to the set*/
		public void add(Anagram newEle)
		{
			set.add(newEle);
		}	
		
		/**Returns and array of strings that in the Anagram set*/
		public String[] getAnagrams()
		{
			String[] a = new String[0];
			ArrayList<String> wordAnagrams = new ArrayList<>();
			for(Anagram word : set){
				wordAnagrams.add(word.getAnagram());
			}
			return(wordAnagrams.toArray(a));
		}	 	
	}
}
 
