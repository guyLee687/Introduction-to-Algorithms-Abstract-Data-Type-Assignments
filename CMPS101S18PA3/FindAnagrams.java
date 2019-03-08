import java.util.*;
import java.io.*;

//Programmer: Jeffrey Wang
//Date: 02/26/19
//COMPS-101-db

/**
 *	This test program demonstrates storing a list of words in a 
 *	Hash Table with an emphasis on these points: 
 *	- compiles without errors and is a serious attempt at a solution
 *  - only reads word file from disk once.
 *	- for comparisons uses an algorithm faster than brute force, 
 *	letterby letter comparison for each word in the word list.T(wordsize) =O(1).
 *	- correctly identifies anagrams for the input string
 *	- does NOT print out the input string as a possible anagram 
 *	if ithappens to be a word (the input string does not need to be a word)
 *	- allows for multiple input strings
*/

public class FindAnagrams 
{
	//Analyze running time of program.
	private static long startTime;
	private static Long endTime;

	public static void main(String[] args) throws IOException
	{
		//Opens file in order to 
		File file = new File("wordList.txt");

		//Analyze running time of creating hashTable
		startTime = System.nanoTime();
		HashTable list = new HashTable(file);
		endTime = System.nanoTime();
		System.out.println("HashTable Generate Time in milliseconds: " + (endTime - startTime)/1000000 + "\n");

		Scanner keyboard = new Scanner(System.in);

		StringTokenizer inputs;			//Tokenizer to take in multiple inputs
		String sentinel = " ";			//Sentinel value in order to exit anagram loop

		//Tihs loop interates through multiple inputs from user and displays anagram
		while(!sentinel.equals("-1"))
		{
			System.out.println("Type in a word to find the anagrams or type in -1 to quit");
			inputs = new StringTokenizer(keyboard.nextLine());

			System.out.println("\n");

			//Iterates through multiple inputs within a single line
			while(inputs.hasMoreElements())
			{	
				sentinel = inputs.nextToken();
				Anagram word = new Anagram(sentinel);
				System.out.print("Word: ");
				word.print();

				System.out.println("Anagrams: ");

				//Analyze the running time of hash search
				startTime = System.nanoTime(); 
				String[] anagra = list.search(word);
				endTime = System.nanoTime();

				//Prints set of anagrams in Linked List except the word itself
				for (int i = 0; i < anagra.length; i++)
				{
					if(!word.getAnagram().equals(anagra[i]))
						System.out.println(anagra[i]);
				}

				System.out.println("Search Time in milliseconds: " + (endTime - startTime)/1000000);
				System.out.println("\n\n");
			}	

		}

			// Testing character constructor of Anagram
			System.out.println("Testing Character Constructor: ");
			Character[] characters = new Character[]{'d', 'o', 'g'};
			Anagram dog = new Anagram(characters);
			String[] doglist = list.search(dog);

			System.out.println("Angrams of " + dog.getAnagram() + ":");
			for (int i = 0; i < doglist.length; i++)
			{
				if(!dog.getAnagram().equals(doglist[i]))
					System.out.println(doglist[i]);
			}
				
	}
}
