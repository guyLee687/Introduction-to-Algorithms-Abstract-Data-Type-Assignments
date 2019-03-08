
//Programmer: Jeffrey Wang
//Date: 03/07/19
//COMPS-101-db

/*
 * Test.java
 * This is the test file which demonstrates that our Vector ADT 
 * And the extension of the Vector ADT (ThreeDVector) works. 
 * The following vectors will be formated to a precision of 2.
*/

public class test
{
	public static void main(String[] args) 
	{
		System.out.println("We will first represent a 2D vector: ");
		System.out.println();

		System.out.println("This a default 2D vector: ");
		System.out.println(new Vector());
		System.out.println();

		Vector v1 = new Vector(2.0f, 1.0f);
		System.out.println("Vector1: " + v1 + " | " + "X: " + v1.getX() + " Y: " + v1.getY());

		Vector v2 = Vector.polarVector((float)(Math.PI / 4), (float)(Math.sqrt(2)) * 3);
		System.out.printf("Vector2: " +  v2 + " | " + "X: %.2f Y: %.2f \n", v2.getX(), v2.getY());
		System.out.printf("DotProduct of V1 and V2: %.2f \n", v1.dotProduct(v2));
		System.out.printf("Distance of V1 and V2: %.2f \n", v1.getDistance(v2));
		System.out.println();

		Vector v3 = v1.add(v2);
		System.out.printf("Vector3 (v1 + v2): " +  v3 + " | " + "X: %.2f Y: %.2f \n",
							 v3.getX(), v3.getY());

		Vector v4 = v1.subtract(v2);
		System.out.printf("Vector4 (v1 - v2): " +  v4 + " | " + "X: %.2f Y: %.2f \n",
							 v4.getX(), v4.getY());

		Vector v5 = v1.scalarMultiply(4);
		System.out.printf("Vector5 (4*v1): " +  v5 + " | " + "X: %.2f Y: %.2f \n",
							 v5.getX(), v5.getY());

		Vector v6 = v2.normalize();
		System.out.printf("Vector6 (Normalize(v2): " +  v6 + " | " + "X: %.2f Y: %.2f \n",
							 v6.getX(), v6.getY());	
		System.out.println();

		System.out.printf("Angle of Vector 6:  %.2f \n" , v6.getAngle());	
		System.out.printf("Magnitude of Vector 6:  %.2f \n" , v6.getMagnitude());
		System.out.println();

		System.out.println("We shall now find the vectors from (v1, v2, v3, v4, v5, v6) ");
		System.out.println("Which has the smallest distance from (4,5): ");
		
		Vector v7 = new Vector(4, 5);
		Vector[] group1 = new Vector[]{v1, v2, v3, v4, v5, v6};
		System.out.println(v7.getShortest(group1));
		System.out.println();

		//--------------------------------------------------------------------------------------------

		System.out.println("We will first represent 3D vectors: ");
		System.out.println();

		System.out.println("This a default 3D vector: ");
		System.out.println(new Vector());
		System.out.println();

		ThreeDVector v8 = new ThreeDVector(2.0f, 1.0f, 2.0f);
		System.out.println("ThreeDVector8: " + v8 + " | " + "X: " + v8.getX() + " Y: " + v8.getY() + " Z: " + v8.getZ());

		ThreeDVector v9 = ThreeDVector.ThreeDPolarVector((float)(Math.PI / 4), (float)(Math.sqrt(2)) * 3, (float)(Math.PI / 4));
		System.out.printf("ThreeDVector9: " +  v9 + " | " + "X: %.2f Y: %.2f Z: %.2f\n", v9.getX(), v9.getY(), v9.getZ());
		System.out.printf("DotProduct of V1 and V2: %.2f \n", v8.dotProduct(v9));
		System.out.printf("Distance of V1 and V2: %.2f \n", v8.getDistance(v9));
		System.out.println();

		ThreeDVector v10 = v8.add(v9);
		System.out.printf("ThreeDVector10 (v8 + v9): " +  v10 + " | " + "X: %.2f Y: %.2f Z: %.2f\n",
						   v10.getX(), v10.getY(), v10.getZ());

		ThreeDVector v11 = v8.subtract(v9);
		System.out.printf("ThreeDVector11 (v8 - v9): " +  v11 + " | " + "X: %.2f Y: %.2f Z: %.2f\n",
						  v11.getX(), v11.getY(), v11.getZ());

		ThreeDVector v12 = v8.scalarMultiply(4);
		System.out.printf("ThreeDVector12 (4*v8): " +  v12 + " | " + "X: %.2f Y: %.2f Z: %.2f\n", 
						  v12.getX(), v12.getY(), v12.getZ());

		ThreeDVector v13 = v9.normalize();
		System.out.printf("ThreeDVector13 (Normalize(v9): " +  v13 + " | " + "X: %.2f Y: %.2f Z: %.2f\n", 
							v13.getX(), v13.getY(), v13.getZ());

		ThreeDVector v14 = v8.getOrthogonal(v9);
		System.out.printf("ThreeDVector14 Orthogonal to v8 & v9: " +  v14 + " | " + "X: %.2f Y: %.2f Z: %.2f\n", 
							v14.getX(), v14.getY(), v14.getZ());
		System.out.println();

		System.out.printf("Angle of ThreeDVector 13:  %.2f \n" , v13.getAngle());	
		System.out.printf("Magnitude of ThreeDVector 13:  %.2f \n" , v13.getMagnitude());
		System.out.println();

		System.out.println("We shall now find the vectors from (v8, v9, v10, v11, v12, v13, v14) ");
		System.out.println("Which has the smallest distance from (4,5,6): ");
		
		ThreeDVector v15 = new ThreeDVector(4, 5, 6);
		ThreeDVector[] group2 = new ThreeDVector[]{v8, v9, v10, v11, v12, v13, v14};
		System.out.println(v15.getShortest(group2));
		System.out.println();
	}
}