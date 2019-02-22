
//Programmer: Jeffrey Wang
//Date: 02/06/19
//Class: CMPS101-db

/*
 * - a constructor which uses a String, made up of alphabetic characters either 
 *   upper or lower case as an input argumen
 * - a constructor which uses a char array, made up of alphabetic characters
 *   either upper or lower case as an input argument. 
 * - a method for printing. 
 * - a method for comparing two Anagram variables that returns true or false.
 * - a method that returns the word part of an Anagram variable. 
 * - do not allow user to get the code part of an Anagram variable.
 */


public Anagram
{(
	private String satellite;
	private long hashValue;
	    
	public Anagram(String value)
	{
		satellite = value.toLowerCase();
	        hashValue =  createHashValue(satellite);     
	}

	public Anagram(Char[] value)
	{
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < value.length; i++)
			str.append(value[i]);
		satellite = str.toString().toLowerCase();
	}

	public void print()
	{
		System.out.println(satellite);
	}

	public compare(Anagram word)
	{
		if(hashValue == word.getHashValue())
		{
			int[] letter1 = new Int[26];
			int[] letter2 = new Int[26];

			for(int i = 0; i < satellite.length(); i++)
				letter1[satellite.charAt(i)]++;

			for(int i = 0; i < word.getAnagram().length(); i++)
				letter2[satellite.getAnagram().charAt(i)]++;

			for(int i = 0; i < 26; i++)
			{
				if(letter1[i] != letter2[i])
					return false;
			}
			return true;
		}
		else 
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
			hashValue += Math.pow(5,(satellite.charAt(i) - 98));
		return hashValue;	
	}
}

