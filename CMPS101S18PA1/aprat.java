

//Programmer: Jeffrey Wang 
//CruzID: 1659820
//Data: 11.29.19
//Class: COMPS-101B (D.Bailey)

/**********************************************************************************
Programming Assignment 1: APInt Class (Abstract Data Type)

***********************************************************************************/
public class APRat
{
	private APInt numerator;	//An APInt numerator 
	private APInt denominator;	//An APInt denominator

	/** 
	* No-Arg Constructor for APRat
	* Assigns the numerator with an APInt of value 0
	* Assigns the denominator with an APInt of value 1.
	*/
	public APRat()
	{
		numerator = new APInt(0);
		denominator = new APInt(1);
	}

	/**
	* APRat Constructor: Intializes an APRAt with APInt inputs
	* @param numerator - An APInt representative of the numerator
	* @param denominator - An APInt representative of the denominator
	*/
	public APRat(APInt numerator, APInt denominator)
	{
		this.numerator = numerator;
		this.denominator = denominator;
	}

	/**
	* APRat Constructor: Intializes an APRAt with int inputs
	* @param numerator - An int representative of the numerator
	* @param denominator - An int representative of the denominator
	*/
	public APRat(int numerator, int denominator)
	{
		this.numerator = new APInt(numerator);
		this.denominator = new APInt(denominator);
	}

	/**
	* APRat Constructor: Intializes an APRAt with double inputs with a 
	* specific precision
	* @param numerator - A double representative of the numerator
	* @param denominator - An double representative of the denominator
	* @param pos - The level of precision of APRat
	*/
	public APRat(double numerator, double denominator, int pos)
	{
		this.numerator = new APInt((numerator * Math.pow(10,pos)));
		this.denominator = new APInt(denominator * Math.pow(10,pos));

	}

	/**
	* getNumerator: Returns the APInt numerator
	* @return numerator - The numerator
	*/
	public APInt getNumerator()
	{
		return new APInt(numerator);
	}

	/**
	* getNumerator: Returns the APInt denominator
	* @return denominator - The denominator
	*/
	public APInt getDenominator()
	{
		return new APInt(denominator);
	}

	/**
	* add: Performs and fractional addition
	* This is done by multiplying each numerators by its opposit denominators.
	* And adding the numerators
	* @param fracAdd - The fractional addend
	* @return An APRat that represents the sum
	*/
	public APRat add(APRat fracAdd)
	{
		APInt num2 = fracAdd.getNumerator();
		APInt dem2 = fracAdd.getDenominator();
		APInt newNum = (numerator.multiply(dem2)).add(num2.multiply(denominator));
		APInt newDem = (denominator.multiply(dem2));
		return new APRat(newNum, newDem);
	}

	/**
	* subtract: Performs and fractional subtraction
	* This is done by multiplying each numerators by its opposit denominators.
	* And subtracting the numerators
	* @param fracSubtr - The fractional subtrahend
	* @return An APRat that represents the difference
	*/
	public APRat subtract(APRat fracSubtr)
	{
		APInt num2 = fracSubtr.getNumerator();
		APInt dem2 = fracSubtr.getDenominator();
		APInt newNum = (numerator.multiply(dem2)).add(num2.multiply(denominator));
		APInt newDem = (denominator.multiply(dem2));
		return new APRat(newNum, newDem);

	}

	/**
	* multiply: Performs and fractional multiplication
	* This is done through multiplying both the numerators and denominators
	* @param fracFac - The fractional factor
	* @return An APRat that represents the product
	*/
	public APRat multiply(APRat fracFac)
	{
		APInt num = numerator.multiply(fracFac.getNumerator());
		APInt dem = denominator.multiply(fracFac.getDenominator());
		return new APRat(num, dem);
	}

	/**
	* divide: Performs and fractional division
	* This is done by multiplying the reciprocal
	* @param fracDiv - The fractional divisor
	* @return An APRat that represents the quotient
	*/
	public APRat divide(APRat fracDiv)
	{
		APInt num = numerator.multiply(fracDiv.getDenominator());
		APInt dem = denominator.multiply(fracDiv.getNumerator());
		return new APRat(num, dem);
	}

	/**
	* normalize: Reduce the fraction to its simpliest form by finding the 
	* Greatest Common Multiple(GCM) and dividing it from the numerator and denominator.
	* This is done through using Euclid's method.
	*/
	public void normalize()
	{
		//The following three APInt's represents the three values in Euclid's Algorithm
		APInt remainder;
		APInt modulus_a;
		APInt modulus_b;
		APInt dividend;
		APInt empty = new APInt(1);

		// Only normalize if the numerator isn't zero
		if(numerator.getFirst() != 0)
		{
			//Assign the largest part of the fraction to the dividend
			if(numerator.compareTo(denominator) > 0)
			{
				dividend = numerator;
				remainder = new APInt(denominator);
			}
			else
			{
				dividend = denominator;
				remainder = new APInt(numerator);
			}

			//Euclid's method
			modulus_a = new APInt(remainder);
			modulus_b = dividend.getRemainder(remainder);
			//Continue till remainder is zero
			while(remainder.getFirst() != 0 )
			{
				remainder = modulus_a.getRemainder(modulus_b);
				modulus_a = new APInt(modulus_b);
				modulus_b = new APInt(remainder);
			}

			//modulus_a represents the Greatest common multiple
			numerator = numerator.divide(modulus_a);
			denominator = denominator.divide(modulus_a);
		}
	}

	/** toString: Print method for APInt Class
	* @Override toString
	* @return: A string representative of an Integer
	*/
	public String toString()
	{
		normalize();
		return "Numerator: " + numerator.toString() + 
		"\nDenominator: " + denominator.toString();
	}

}