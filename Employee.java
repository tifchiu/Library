/** An Employee object -- which will have access to the library inventory and its
 * functionality -- with a name, age, employee number, and position.
 * 
 * 2019-01-10
 */
class Employee {

    //state variables
  String name;
  int age;
  int employeeNum;
  String position;

  /** Creates an Employee with a name, age, employee number, and position
   * @param name
   * @param age
   * @param employeeNum 
   * @param position 
   */
  public Employee (String name, int age, int employeeNum, String position) {
    this.name = name;
    this.age = age;
    this.employeeNum = employeeNum;
    this.position = position;
  }

  /** Returns a string to the employee info (for file I/O)
   * @return employee
   */
  @Override
  public String toString() {
    String employee = getName() + "%" + getAge() + "%" + getEmployeeNum() + "%" + getPosition();
    return employee;
  }

  /** Returns the employee's name 
   * @return name
   */
  public String getName() {
    return this.name;
  }

  /** Returns the employee's age
   * @return age
   */
  public int getAge() {
    return this.age;
  }

  /** Gets the employee's employee number 
   * @return employeeNum 
   */
  public int getEmployeeNum() {
    return this.employeeNum;
  }

  /** Gets the position held by the employee 
   * @return position
   */
  public String getPosition() {
    return this.position;
  }

  /** Sets the name of the employee 
   * @param name 
   */
  public void setName (String name) {
    this.name = name;
  }

  /** Sets the position of the employee 
   * @param position
   */
  public void setPosition(String position) {
    this.position = position;
  }

  //end of Employee class 
}
