/** An AudioBook object -- extending from the abstract class Item -- with a title, creator,
 * language, release date, loan period, serial number, duration, and narrator.
 * 
 * 2019-01-10
 */
class AudioBook extends Item {

    // state variables
  int duration;

  /** Creates an AudioBook with a title, creator, language, release date, loan period, serial number, length, and narrator. 
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
  public AudioBook (String title, Person creator, String language, Date releaseDate, int loanPeriod, int serialNum, int length, String genre, boolean available) {
    super(title, creator, language, releaseDate, loanPeriod, serialNum, length, genre, available);
    this.duration = length;
  }
  
  /** Creates an AudioBook with a title, creator, and serial number. 
   * @param title 
   * @param creator  
   * @param serialNum
   */
  public AudioBook (String title, Person creator, int serialNum) {
    super(title, creator, serialNum);
  }

  /** Sets the duration of the audiobook.
   * @param length 
   */
  @Override
  public void setLength(int length) {
    this.duration = length;
  }

  /** Gets the type of the object
   * @return Audio Book
   */
  @Override
  public String getType() {
    return "Audio Book";
  }

  // end of AudioBook class
}
