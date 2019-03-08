//Programmer: Jeffrey Wang
//Date: 03/07/19
//COMPS-101-db
//
// Vector.java
// A class that implements the Vector ADT.
//
// For this assignment, you must complete this code skeleton.
// You may not change the function prototypes.
// You are expected to fill in the functions to make them work
// as expected, and you can add as much as you need or want.
// We recommend implementing the Vector ADT using x and y coordinates.
//
// Notes:
// Angles are always in radians, not degrees.
//
// Additional Methods: 
// - getDistance: Gets the distance between this vector and other vector
// - getShortest: Gets the vector in an array of vectors that has the shortest
//                 distance to this Vector
// - print:       Prints the vector formatted to two show a precision of 2.

class Vector {

  // Fields
  private float x; 
  private float y;

  // Constructors

  // The default constructor should create a new Vector with no magnitude.
  public Vector() {
      x = 0;
      y = 0;
  }

  // This constructor takes an x and a y coordinate for the Vector.
  public Vector(float x, float y) {
      this.x = x; 
      this.y = y;
  }

  // This "constructor" takes an angle and a magnitude for the Vector.
  // It is not a traditional constructor because only one function can have
  //   the signature Vector(float, float).
  public static Vector polarVector(float angle, float magnitude) {
      float x = magnitude * (float)(Math.cos(angle));
      float y = magnitude * (float)(Math.sin(angle));
      return new Vector(x, y);
  }

  // Access functions

  /** getX
   *  Returns the x coordinate of the Vector.
   */
  public float getX() {
      return x;
  }

  /** getY
   *  Returns the y coordinate of the Vector.
   */
  public float getY() {
      return y;
  }

  /** getAngle
   *  Returns the angle of the Vector.
   */
  public float getAngle() {
       float angle = (float)Math.atan(y/x);
       return angle;
  }

  /** getMagnitude
   *  Returns the magnitude of the Vector.
   */
  public float getMagnitude() {
        float magnitude = (float)Math.sqrt(Math.pow(x,2) + Math.pow(y,2));
        return magnitude;
  }

  /** add
   *  Returns the sum of this Vector with the given Vector.
   */
  public Vector add(Vector other) {
        Vector sum =  new Vector((this.x + other.getX()), (this.y + other.getY()));
        return sum;
  }

  /** subtract
   *  Returns the difference between this Vector and the given Vector.
   */
  public Vector subtract(Vector other) {
         Vector difference = new Vector((this.x - other.getX()), (this.y - other.getY()));
         return difference; 
  }

  /** dotProduct
   *  Returns the dot product of this Vector and the given Vector.
   */
  public float dotProduct(Vector other) {
         float product = (this.x * other.getX()) + (this.y * other.getY());
         return product; 
  }

  /** scalarMultiply
   *  Returns this Vector scaled by the given scalar.
   */
  public Vector scalarMultiply(float scalar) {
          Vector scaler = new Vector((this.x * scalar), (this.y * scalar));
          return scaler;
  }

  /** normalize
   *  Returns the normalized version of this Vector, a Vector with the same
   *    angle with magnitude 1.
   */
  public Vector normalize() {
          float mag = this.getMagnitude();
          return new Vector((this.x / mag), (this.y / mag));
  }

  /** getDistance
    * get the Distance between this Vector, and a another vector
    * Returns the distance
    */
  public float getDistance(Vector other) {
          Vector diff = this.subtract(other);
          float distance = (float)(diff.getMagnitude());
          return distance; 
  }

  /** getShortestDistance
    * gets an array of Vectors and returns the Vector with the 
    * shortest distance to this Vector
    */
  public Vector getShortest(Vector[] array){
          //Shortest distance from vector is assign to the first array element. 
          Vector shortest = array[0]; 
          //The shortest distance
          float distance = this.getDistance(shortest);
          //Temporary distance
          float temp;
          for (Vector i: array){
              temp = this.getDistance(i);
              if(temp < distance){
                  distance = temp;
                  shortest = i;
              }
          }
          return shortest;
  }

  /** toString(
   *  This method overwrites standard toString method in  Object class
   *  The print method for Vector class 
   */
  public String toString(){
      String str = String.format("(%.2f, %.2f)", x, y);
      return str;
  }

  // Manipulation functions
  // None.  Vectors are immutable.
}
