
public class APRat
{
	private int sign = 1; // 1 (+) & 0 (-)
	private APInt numerator;
	private APInt denominator;

	public APRat()
	{
		numerator = new APInt(0);
		denominator = new APInt(1);
	}

	public APRat(APInt numerator, APInt double denominator)
	{
		this.numerator = numerator;
		this.denominator = denominator;
	}

	public APRat(int numerator, int denominator)
	{
		this.numerator = new APInt(numerator);
		this.denominator = new APInt(denominator);
	}

	public APRat(double numerator, double denominator, int pos)
	{
		this.numerator = new APInt(numerator * Math.pow(10,pos));
		this.numerator = new APInt(numerator * Math.pow(10,pos));
	}

	public APInt getNumerator()
	{
		return new APInt(numerator);
	}

	public APInt getDenominator()
	{
		return new APInt(denominator);
	}

	public Aprat add(APRat fracAdd)
	{
		APInt num2 = fracAdd.getNumerator();
		APInt dem2 = fracAdd.getDenominator();
		APInt newNum = (numerator.multiply(dem2)).add(num2.multiply(denominator));
		APInt newDem = (denominator.multiply(dem2));
		return new APRat(newNum, newDem);
	}

	public Aprat subtract(APRat fracSubtr)
	{
		APInt num2 = fracAdd.getNumerator();
		APInt dem2 = fracAdd.getDenominator();
		APInt newNum = (numerator.multiply(dem2)).add(num2.multiply(denominator));
		APInt newDem = (denominator.multiply(dem2));
		return new APRat(newNum, newDem);

	}

	public Aprat multiply(APRat fracFac)
	{
		APInt num = numerator.multiply(fracFac.getNumerator());
		APInt dem = denominator.multiply(fracFac.getDenominator());
		return new APRat(num, dem);
	}

	public Aprat divide(Aprat fracDiv)
	{
		APInt num = numerator.multiply(fracFac.getDenominator());
		APInt dem = denominator.multiply(fracFac.getNumerator());
		return new APRat(num, dem);
	}

	public void normalize()
	{
		APInt remainder;
		APInt dividend;
		APInt empty = new APInt(0);

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
		
		do
		{
			remainder = dividend.getRemainder(remainder);
		}
		while(remainder.compareTo(empty) == 0)
		
		numerator = numerator.divide(remainder);
		denominator = denominator.divide(remainder);
	}

	public String toString()
	{
		normalize();
		return "Numerator: " numerator.toString() + 
				"\nDenominator: " + denominator.toString();
	}

}