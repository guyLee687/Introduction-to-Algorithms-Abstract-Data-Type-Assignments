

//Programmer: Jeffrey Wang 
//CruzID: 1659820
//Data: 11.29.19
//Class: COMPS-101B (D.Bailey)

/**********************************************************************************
Programming Assignment 1: APInt Class (Abstract Data Type)

***********************************************************************************/

public class demo
{
	public static void main(String[] args) {

		APInt num0 = new APInt();
		APInt num1 = new APInt(3141592.65897);
		APInt num2 = new APInt("-2718281828459045235360287471352");
		APInt num2_5 = new APInt("1414213562373095048801688724209698078569671875");
		APInt num3 = new APInt(-123456);

		
		System.out.println("Representation of No-Arg Constructor for APInt: ");
		System.out.println("num0: " + num0);
		System.out.println();

		System.out.println("Representation of Floating Point Number conversion to APInt: ");
		System.out.println("num1:" + num1);
		System.out.println();

		System.out.println("Representation of String Conversion (with sign) to APInt: ");
		System.out.println("num2:" + num2);
		System.out.println();

		System.out.println("Representation of String Conversion (without sign) to APInt: ");
		System.out.println("num2_5:" + num2_5);
		System.out.println();

		System.out.println("Representation of Integer Conversion: ");
		System.out.println("num3:" + num3);
		System.out.println();

		System.out.println("num1 + num2_5: "); 
		System.out.println("Sum: " + num1.add(num2_5));
		System.out.println();

		System.out.println("num1 + num2: "); 
		System.out.println("Sum: " + num1.add(num2));
		System.out.println();

		System.out.println("num2 + num2_5: "); 
		System.out.println("Sum: " + num2.add(num2_5));
		System.out.println();

		System.out.println("num2 + num3: "); 
		System.out.println("Sum: " + num2.add(num3));
		System.out.println();

		System.out.println("num1 - num2_5: "); 
		System.out.println("Difference: " + num1.subtract(num2_5));
		System.out.println();
				
		System.out.println("num1 - num2: "); 
		System.out.println("Difference: " + num1.subtract(num2));
		System.out.println();
		
		System.out.println("num2 - num2_5: "); 
		System.out.println("Difference: " + num2.subtract(num2_5));
		System.out.println();
		
		System.out.println("num2 - num3: "); 
		System.out.println("Difference: " + num2.subtract(num3));
		System.out.println();

		System.out.println("num1 * num2_5: "); 
		System.out.println("Product: " + num1.multiply(num2_5));
		System.out.println();
				
		System.out.println("num1 * num2: "); 
		System.out.println("Product: " + num1.multiply(num2));
		System.out.println();
		
		System.out.println("num2 * num2_5: "); 
		System.out.println("Product: " + num2.multiply(num2_5));
		System.out.println();
		
		System.out.println("num2 * num3: "); 
		System.out.println("Product: " + num2.multiply(num3));
		System.out.println();

		System.out.println("num1 / num2_5: "); 
		System.out.println("Quotient: " + num1.divide(num2_5));
		System.out.println();
				
		System.out.println("num1 / num2: "); 
		System.out.println("Quotient: " + num1.divide(num2));
		System.out.println();
		
		System.out.println("num2 / num2_5: "); 
		System.out.println("Quotient: " + num2.divide(num2_5));
		System.out.println();
		
		System.out.println("num2 / num3: "); 
		System.out.println("Quotient: " + num2.divide(num3));
		System.out.println();		

		APRat frac0 = new APRat();
		APRat frac1 = new APRat(100, 50);
		APRat frac2 = new APRat(-3.141592, -1.0, 2);
		APRat frac3 = new APRat(num2, num2_5);

		
		System.out.println("Representation of No-Arg Constructor for APRat: ");
		System.out.println("frac0:\n  " + frac0);
		System.out.println();

		System.out.println("Representation of Integer conversion to APRat (Normalize 100/50): ");
		System.out.println("frac1:\n " + frac1);
		System.out.println();

		System.out.println("Representation of Floating Point Conversion (precision 2) to APRat: ");
		System.out.println("frac2:\n " + frac2);
		System.out.println();

		System.out.println("Representation of APInt Conversion to APRat: ");
		System.out.println("frac3:\n " + frac3);
		System.out.println();

		APInt newNum = new APInt("16598201897434");
		APInt newDem = new APInt("1800284892835639221607851");
		APRat frac4 = new APRat(newNum, newDem);

		System.out.println("Representation of frac4:");
		System.out.println("frac4:\n " + frac4);
		System.out.println();

		System.out.println("frac3 + frac4");
		System.out.println("Sum:\n" + frac3.add(frac4));
		System.out.println();

		System.out.println("frac3 - frac4");
		System.out.println("Difference:\n" + frac3.subtract(frac4));
		System.out.println();

		System.out.println("frac3 * frac4");
		System.out.println("Product:\n" + frac3.multiply(frac4));
		System.out.println();

		System.out.println("frac3 / frac4");
		System.out.println("Quotient:\n" + frac3.divide(frac4));
		System.out.println();


		//ExtraCredit Problem:
		APInt factorial = new APInt(1);
		for(int i = 2; i <= 1000; i++)
		{
			factorial = factorial.multiply(new APInt(i));
		}
		System.out.println(factorial);


	}
}