
//Programmer: Jeffrey Wang
//Date: 02/06/19
//Class: CMPS101-db

/*sa
 * - a constructor which uses a String, made up of alphabetic characters either 
 *   upper or lower case as an input argumen
 * - a constructor which uses a char array, made up of alphabetic characters
 *   either upper or lower case as an input argument. 
 * - a method for printing. 
 * - a method for comparing two Anagram variables that returns true or false.
 * - a method that returns the word part of an Anagram variable. 
 * - do not allow user to get the code part of an Anagram variable.
 */


public class Anagram
{
	private String satellite;		//A word;
	private long hashValue;			//The hash Value to the word
	 
	/**
	  A constructor which a word into an Anagram type and assigns a
	  hash value to it.
	  @param String value: The word that needs to turn into an anagram..
	 */
	public Anagram(String value)
	{
		satellite = value.toLowerCase();
	        hashValue =  createHashValue(satellite);     
	}

	/**
	  A constructor which a word into an Anagram type and assigns a
	  hash value to it.
	  @param Character[] value: The characters in a word that needs to 
	  							turn into an anagram..
	 */
	public Anagram(Character[] value)
	{
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < value.length; i++)
			str.append(value[i]);
		satellite = str.toString().toLowerCase();
		hashValue = createHashValue(satellite);
	}

	/**The print method for Anagram which prints the word*/
	public void print()
	{
		System.out.println(satellite);
	}

	/** 
		This method compares this word and a following word 
		to see if they are indeed Anagrams.
		@param Anagram word: The word in which this Anagram 
							is being compared to.
		@return boolean: whether or not the words are Anagrams
	*/
	public boolean compare(Anagram word)
	{
		// Only continue to compare if the words have the same hasValues(collision)
		if(hashValue == word.getHashValue())
		{
			//Assign an Integer array that represents a histogram of letters in words9
			int[] letter1 = new int[26];
			int[] letter2 = new int[26];

			for(int i = 0; i < satellite.length(); i++){
				int alphabet = satellite.charAt(i) - 97;
				if(alphabet >= 0 && alphabet <=26)
					letter1[alphabet]++;
			}

			for(int i = 0; i < word.getAnagram().length(); i++){
				int alphabet = word.getAnagram().charAt(i) - 97;
				if(alphabet >= 0 && alphabet <=26)
					letter2[alphabet]++;
			}

			for(int i = 0; i < 26; i++)
			{
				if(letter1[i] != letter2[i])
					return false;
			}
			return true;
		}
		else ;
			return false; 
	}	

	public String getAnagram()
	{
		return satellite;
	}

	public long getHashValue()
	{
		return hashValue;
	}
	
	/**
 	 *This function creates a hash value through polynomial
	 *accumulation in order to generate a hash value. It uses
	 *a base 5 in order to generate a unique value. 
     *@param satellite: The string;
	 *@return hashValue: A unique hash value
	 */  	
	private long createHashValue(String satellite)
	{
		long hashValue = 0;
		for (int i = 0; i < satellite.length(); i++)
			hashValue += Math.pow(5,((satellite.charAt(i) - 98)));
		return hashValue;	
	}
}

