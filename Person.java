/** A Person object with a first and last name.
 * @author Tiffany Chiu
 * 01-13-2019
 */
class Person {

    // State variables 
  String lastName;
  String firstName;

  /** The main constructor creates a person with a first and last name.
   * @param firstName 
   * @param lastName
   */
  public Person (String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  /** The overloaded constructer creates a person with just a last name 
   * @param lastName
   */
  public Person(String lastName) {
    this.lastName = lastName;
  }

  /** Sets the person's first name 
   * @param firstName
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /** Gets the person's last name 
   * @return lastName 
   */
  public String getLastName() {
    return this.lastName;
  }

  /** Gets the person's first name 
   * @return firstName
   */
  public String getFirstName() {
    return this.firstName;
  }

  /** Combines the first and last names into a single string 
   * @return the person's full name, a concatenation of the first and last names 
   */
  @Override
  public String toString() {
    return this.firstName + " " + this.lastName;
  }
  


    // end of Person class
  }
