/** A Date object with a month, day, and year.
 * 
 * 01-13-2019
 */
class Date {

    //state variables
  int month, day, year;

  /** Creates a date with a month, day, and year.
   * @param month
   * @param day 
   * @param year 
   */
  public Date(int month, int day, int year) {
    this.month = month;
    this.day = day;
    this.year = year;
  }

  /** Gets the month 
   * @return month
   */
  public int getMonth() {
    return this.month;
  }

  /** Gets the day 
   * @return day 
   */
  public int getDay() {
    return this.day;
  }

  /** Gets the year 
   * @return year
   */
  public int getYear() {
    return this.year;
  }

  /** Returns the full date as a string with the format MM/DD/YYYY
   * @return date
   */
  @Override
  public String toString() {
    String date = this.month + "%" + this.day + "%" + this.year; 
    return date;
  }

  /** Returns the full date as a string for the user output
   * @return date
   */
  public String userOutput() {
    String date = this.month + "/" + this.day + "/" + this.year;
    return date;
  }

  /** Checks if a date is after a date to which it is being compared.
   * @param date
   * @return boolean
   */
  public boolean isAfter(Date date) {
    if (getDay() > date.getDay() && getMonth() > date.getMonth() && getYear() > date.getYear()) {
      return true;
    }
    else{
      return false;
    }
  }

  /** Checks if a date is before a date to which it is being compared
   * @param date
   * @return boolean
   */
  public boolean isBefore(Date date) {
    if (getDay() < date.getDay() && getMonth() < date.getMonth() && getYear() < date.getYear()) {
      return true;
    }
    else {
      return false;
    }
  }

  /** Checks if two dates are equal
   * @param date
   * @return boolean
   */
  public boolean equals(Date date) {
    boolean equals = false;
    if (getMonth() == date.getMonth() && getDay() == date.getDay() && getYear() == date.getYear()) {
      equals = true;
    }
    return equals;
  }

  // end of Date class
}
