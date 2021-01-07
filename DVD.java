/** A DVD object -- extending from the abstract class Item -- with a title, creator,
 * language, release date, loan period, serial number, number of pages
 * 
 * 2019-01-10
 */
class DVD extends Item {

  /** Creates a DVD with a title, creator, language, release date, loan period, serial number, length, and availability. 
   * @param title 
   * @param creator 
   * @param language 
   * @param releaseDate 
   * @param loanPeriod 
   * @param serialNum
   * @param length
   * @param genre
   * @param availability 
   */
  public DVD (String title, Person creator, String language, Date releaseDate, int loanPeriod, int serialNum, int length, String genre, boolean available) {
    super(title, creator, language, releaseDate, loanPeriod, serialNum, length, genre, available);
  }

  /** Creates a DVD with a title, creator, and serial number. 
   * @param title 
   * @param creator  
   * @param serialNum
   */
  public DVD (String title, Person creator, int serialNum) {
    super(title, creator, serialNum);
  }

  /** Gets the type of the object
   * @return DVD
   */
  @Override
  public String getType() {
    return "DVD";
  }
  
  // end of DVD class
}
