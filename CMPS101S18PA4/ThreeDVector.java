
//Programmer: Jeffrey Wang
//Date: 03/07/19
//COMPS-101-db
//
//3D Vector extendsion
//A class that will extend Vector ADT to 3 dimenssions
//
//It still must maintaint the original 2D vector 
//
//Notes:
//Angles are always in radians
//
// Additional Methods
// - getZ: 			Gets the Z value of the vector: 
// - getPhi: 		Gets the additional angle formed by spherical coordinates
// - getDistance:   Gets the distance between this vector and other vector
// - getShortest:   Gets the vector in an array of vectors that has the shortest
//                  distance to this Vector
// - getOrthogonal: Gets the orthongonal vector to the plane formed by this vector
//					And another vector.
// - print:         Prints the vector formatted to two show a precision of 2.


class ThreeDVector extends Vector{
	//Fields

	private float z; 

	//Constructors 

	//Default Constructor that should create a new 3-D Vector with no magnitude 
	public ThreeDVector(){
		super();
		z = 0;
	}

	// This constructor takes an x, y, and z coordinate for the Vector.
	public ThreeDVector(float x, float y, float z){
		super(x , y);
		this.z = z; 
	}

	 // This "constructor" takes an angle and a magnitude for the Vector.
 	 // It is not a traditional constructor because only one function can have
  	 //   the signature Vector(float, float).
  	public static ThreeDVector ThreeDPolarVector(float angle, float magnitude, float phi) {
     	 float x = (magnitude * (float)Math.sin(angle) * (float)Math.cos(phi));
     	 float y = (magnitude * (float)Math.sin(angle) * (float)Math.sin(phi));
     	 float z = (magnitude * (float)Math.cos(phi));
     	 return new ThreeDVector(x, y, z);
     }

     /** getZ
      *  Returns the z coordinate of the Vector.
      */
     public float getZ(){
     	return z;
     }

     /** getAngle
  	  *  Returns the angle of the Vector.
   	  */
      public float getAngle(){
      		float angle = (float)Math.acos(z / getMagnitude());
      		return angle;
      }
      
     /** getMagnitude
      *  Returns the magnitude of the Vector.
      */
	  public float getMagnitude() {
	  		float x_sqr = (float)Math.pow(super.getX(), 2);
	  		float y_sqr = (float)Math.pow(super.getY(), 2);
	  		float z_sqr = (float)Math.pow(z, 2);
          	return (float)Math.sqrt(x_sqr + y_sqr + z_sqr);
  	  }

  	  /** GetPhi
	   *  Returns the phi value of the Vector
	   */
  	  public float getPhi(){
  	  	    return super.getAngle();
  	  }

  	  /** add
	   *  Returns the sum of this Vector with the given Vector
	   */
  	  public ThreeDVector add(ThreeDVector other){
  	  		float new_x = super.getX() + other.getX();
  	  		float new_y = super.getY() + other.getY();
  	  		float new_z = z + other.getZ();
  	  		return new ThreeDVector(new_x, new_y, new_z);
  	  }

  	  /** subtract
	   *  Returns the difference of this Vector with the given Vector
	   */
  	  public ThreeDVector subtract(ThreeDVector other){
  	  		float new_x = super.getX() - other.getX();
  	  		float new_y = super.getY() - other.getY();
  	  		float new_z = z - other.getZ();
  	  		return new ThreeDVector(new_x, new_y, new_z);
  	  }

  	  /** dotProduct
  	   *  Returns the dot product of this Vecotr and the given Vecotor
  	   */
  	  public float dotProduct(ThreeDVector other){
  	  		return (super.getX() + other.getX()) + (super.getY() + other.getY()) + 
  	  				(z + other.getZ());
  	  }

  	  /** scalarMultiply
  	   *  REturns the vector scaled by the given scalar
  	   */
  	  public ThreeDVector scalarMultiply(float scalar){
  	  		return new ThreeDVector((scalar * super.getX()), (scalar * super.getY()),
  	  									  (scalar * z));
  	  }

  	  /** normalize
  	   *  Returns the normalized version of the Vector, a Vector with same
  	   *  angle with magnitude 1.
  	   */
  	  public ThreeDVector normalize(){
  	  		float mag = this.getMagnitude();
  	  		return new ThreeDVector((super.getX() / mag), (super.getY() / mag), (z / mag));
  	  }

  	  /** getDistance
  	   *  get the Distance between this Vector, and another vector.
	   *  Return the distance
	   */ 
  	  public float getDistance(ThreeDVector other){
  	  		ThreeDVector diff = this.subtract(other);
  	  		float distance = (float)(diff.getMagnitude());
  	  		return distance;
  	  }


  	  /** getShortestDistance
  	   *  gets an array of ThreeDVectors and returns the ThreeDVectors with the 
  	   *  shortest distance to this Vector
	   */
  	  public ThreeDVector getShortest(ThreeDVector[] array){
  	      //Shortest distance from vector is assign to the first array element. 
          ThreeDVector shortest = array[0]; 
          //The shortest distance
          float distance = this.getDistance(shortest);
          //Temporary distance
          float temp;
          for (ThreeDVector i: array){
              temp = this.getDistance(i);
              if(temp < distance){
                  distance = temp;
                  shortest = i;
              }
          }
          return shortest;
  	  }

  	  /** getOrthogonal 
  	   *  This method generates an orthonogonal vector of this 
  	   *  vector and other vector
  	   */ 
  	  public ThreeDVector getOrthogonal(ThreeDVector other){
  	  		float new_x = (this.getY() * other.getZ()) - (other.getY() * z);
  	  		float new_y = (z * other.getZ()) - (other.getZ() * this.getX());
  	  		float new_z = (this.getX() * other.getY()) - (other.getX() * this.getY());
  	  		return new ThreeDVector(new_x, new_y, new_z);
  	  }

  	  /** toString(
   		*  This method overwrites standard toString method in  Object class
   		*  The print method for Vector class 
   		*/
  	  public String toString(){
      	  String str = String.format("(%.2f, %.2f, %.2f)", super.getX(), super.getY(), z);
      	  return str;
 	  }
  	  
  	  // Manipulation functions
      // None.  Vectors are immutable.
}