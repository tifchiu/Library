/** An Inventory object with an array of Item objects. This class will have the basic 
 * functionalities of a library, allowing the Employee to interact with the Items.
 * Implements the Search interface.
 * 
 * 2019-01-11
 */
import java.io.*;
import java.util.*;
class Inventory implements Search {
 
    // state variables
  Item [] items;
  int numOfItems;
  
  /** Creates an Inventory with an array of Items with a default length of 0. The counter 
   * numOfItems also defaults to 0. 
   */
  public Inventory(){
    this.items = new Item[0];
  }

  /** Searches the inventory for a title; overrides from the Search interface.
   * @param title
   * @return searchList, which is an array of the items found
   */
  @Override
  public Item[] searchTitle(String title) {
    int counter = 0;
    Item[] searchList = new Item[0];
      // loops through the inventory to find a matching title of an available item
    for (int i = 0; i < this.items.length; i++) {
      if (title.equals(this.items[i].getTitle()) && items[i].getAvailability() == true) {
        counter++;    // each time the loop finds an item which matches, counter increases
        Item[] copy = Arrays.copyOf(searchList, counter); // a copy of search list is made increased by one index 
        copy[counter-1] = items[i];   // the search result is added to the array 
        searchList = copy;    // search list is updated 
      }
    }
    return searchList;
  }

  /** Searches the inventory for works from a creator; overrides from the Search interface.
   * @param lastName, which is the last name of the creator
   * @return searchList, which is an array of the items found
   */
  @Override
  public Item[] searchCreator(String lastName){
    int counter = 0;
    Item[] searchList = new Item[0];

    for (int i = 0; i < this.items.length; i++) {
      if (lastName.equals(this.items[i].getCreator().getLastName()) && items[i].getAvailability() == true) {
        counter++;
        Item[] copy = Arrays.copyOf(searchList, counter);
        copy[counter-1] = items[i];
        searchList = copy;
      }
    }
    return searchList;
  }

  /** Searches the inventory items of a certain language; overrides from the Search interface.
   * @param language
   * @return searchList, which is an array of the items found
   */
  @Override
  public Item[] searchLanguage(String language){
    int counter = 0;
    Item[] searchList = new Item[0];

    for (int i = 0; i < this.items.length; i++) {
      if (language.equals(this.items[i].getLanguage()) && items[i].getAvailability() == true) {
        counter++;
        Item[] copy = Arrays.copyOf(searchList, counter);
        copy[counter-1] = items[i];
        searchList = copy;
      }
    }
    return searchList;
  }

  /** Searches the inventory for items of a specified date range; overrides from the Search interface.
   * @param start (inclusive)
   * @param end (inclusive)
   * @return searchList, which is an array of the items found
   */
  @Override
  public Item[] searchDate(Date start, Date end){
    int counter = 0;
    Item[] searchList = new Item[0];

    for (int i = 0; i < this.items.length; i++) {
      //if the item's release date is equal to either the start or end of the date range or inbetween and is available 
      if ((this.items[i].getReleaseDate().equals(start) || items[i].getReleaseDate().equals(end) || (this.items[i].getReleaseDate().isAfter(start) && items[i].getReleaseDate().isBefore(end))) && this.items[i].getAvailability() == true) {
        counter++;
        Item[] copy = Arrays.copyOf(searchList, counter);
        copy[counter-1] = this.items[i];
        searchList = copy;
      }
    }
    return searchList;
  }
  
  /** Searches the inventory for items of a certain genre; overrides from the Search interface.
   * @param genre
   * @return searchList, which is an array of the items found
   */
  @Override
  public Item[] searchGenre(String genre) {
    int counter = 0;
    Item[] searchList = new Item[0];

    for (int i = 0; i < this.items.length; i++) {
      // items genre equals the genre being searched for, and it is available
      if (genre.equals(this.items[i].getGenre()) && items[i].getAvailability() == true) {
        counter++;
        Item[] copy = Arrays.copyOf(searchList, counter);
        copy[counter-1] = items[i];
        searchList = copy;
      }
    }
    return searchList;
  }

  /** Determines whether an item exists in the inventory.
   * @param serial, the item's serial number 
   * @return exists
   */
  public boolean itemExists(int serial) {
    boolean exists = false;   // declare variable 

      //loops through the inventory to find a matching serial number 
    for (int i = 0; i < this.items.length; i++) {
      if (serial == this.items[i].getSerialNum()) {
        exists = true;
        i = this.items.length;
      }
    }
    return exists;
  }

  /** Finds the index at which a specified item is in the items array.
   * @param item
   * @return index
   */
  @Override
  public int findItem(Item item) {
    int index = -1;

    for (int i = 0; i < this.items.length; i++) {
      if (items[i].getSerialNum() == item.getSerialNum()) {
        index = i;
        i = this.items.length;    
      }
    }
    return index;
  }

  /** Finds the index at which a specified serial number occurs in the items array.
   * @param serial
   * @return index
   */
  public int findItem(int serial) {
    int index = -1;

    for (int i = 0; i < this.items.length; i++) {
      if (items[i].getSerialNum() == serial) {
        index = i;
        i = this.items.length;    
      }
    }
    return index;
  }
  
  /** Adds an item to the inventory.
   * @param item
   */
  public void addItem(Item item) {
      // Creates a copy of the array of items in the inventory that is one index longer
    Item[] itemsCopy = Arrays.copyOf(this.items, this.items.length+1);
      // adds the item in the inventory 
    itemsCopy[this.items.length] = item;
    this.items = itemsCopy;
    this.numOfItems ++;
  }

  /** Removes an item from the inventory 
   * @param item 
   */
  public void removeItem(Item item) {
      // finds the index where the item being removed is 
    int index = findItem(item);
      // replaces the item being removed with the item at the end of the array
    this.items[index] = this.items[this.items.length - 1];
      // creates a copy of the array reduced by one index
    Item[] itemsCopy = Arrays.copyOf(this.items, this.items.length - 1);
    
    this.items = itemsCopy;
    this.numOfItems --;
  }

  /** Gets the number of items in the inventory 
   * @return numOfItems 
   */
  public int getNumOfItems(){
    return this.numOfItems;
  }

  /** Returns the contents of the inventory as a string, with each item on a new line.
   * Uses the string formatting of the toString() method in the Items class.
   * @return inventory
   */
  public String getInventory() {
    String inventory = "";
    for (int i = 0; i < this.items.length; i++) {
      inventory += (this.items[i].toString() + "\n");
    }
    return inventory;
  }

  /** Sorts the inventory in terms of the release dates of the items
   * This uses two helper functions: 
   * mergeDateHelper(Item[], int)
   * merge(Item[], Item[], Item[], int, int)
   */
  public void sortByDate() {
      mergeDateHelper(this.items, this.items.length);
  }

    /** This recursive function halves the array and subsequent arrays. When the resulting array is 1 index in length, the function no longer recurs and the next helper function is called. 
     * @param array, which is the array being halved 
     * @param size, which is the length of the array 
     */
    public void mergeDateHelper(Item[] array, int size) {
    
        //base case 
      if (size < 2) {
        return;
      }

      int middle = size/2;
      Item[] left = new Item[middle];
      Item[] right = new Item[middle+1];

      for (int i = 0; i < middle; i++) {
        left[i] = array[i];
      }  
      for (int j = middle; j < size; j++) {
          
        right[j - middle] = array[j];
      }

      mergeDateHelper(left, middle);
      mergeDateHelper(right, size - middle);

      mergeDate(left, right, array, middle, size - middle);
    }

    /** This helper function goes about sorting the left and right arrays -- in terms of release date -- back into the original array.
     * @param left, the left half of the original array 
     * @param right, the right half of the original array 
     * @param og, the original array 
     * @param left_size, the length of the left array 
     * @param right_size, the length of the right array  
     */
    public void mergeDate(Item[] left, Item[] right, Item[] og, int left_size, int right_size) {
      int left_index = 0; 
      int right_index = 0;
      int og_index = 0;  

      while (left_index < left_size && right_index < right_size) {
          // if the two release dates being compared don't have the same release year
        if (left[left_index].getReleaseDate().getYear() != right[right_index].getReleaseDate().getYear()){
            // if the release year on the left index is earlier than the release year on the right index, the left one is ordered first 
          if (left[left_index].getReleaseDate().getYear() < right[right_index].getReleaseDate().getYear())  {
            og[og_index++] = left[left_index++]; 
          }
          else {
            og[og_index++] = right[right_index++];
          }
        }
          // if the release years are the same, the sort checks the release month
        else {
            // if the release dates being compared don't have the same release month
          if (left[left_index].getReleaseDate().getMonth() != right[right_index].getReleaseDate().getMonth()) {
            if (left[left_index].getReleaseDate().getMonth() < right[right_index].getReleaseDate().getMonth())  {
            og[og_index++] = left[left_index++]; 
            }
            else {
              og[og_index++] = right[right_index++];
            }
          }
            // if the release months are the same, the sort checks the day
          else {
            if (left[left_index].getReleaseDate().getDay() < right[right_index].getReleaseDate().getDay())  {
            og[og_index++] = left[left_index++]; 
            }
            else {
              og[og_index++] = right[right_index++];
            }
          }
        }
      }
          // populates the original array with whatever is left in either arrays (left/right)
      while (left_index < left_size) {
        og[og_index++] = left[left_index++];
      }
      while (right_index < right_size) {
        og[og_index++] = right[right_index++];
      }
    }

  /** Sorts the inventory in terms of the titles of the items.
   * This uses three helper functions:
   * mergeTitleHelper(Item[], int)
   * findLetter(String, String)
   * mergeTitle(Item[], Item[], Item[], int, int) 
   */
  public void sortByTitle() {
    mergeTitleHelper(this.items, this.items.length);
  }

    /** This recursive function halves the array and subsequent arrays. When the resulting array is 1 index in length, 
     * the function no longer recurs and the next helper function is called. 
     * @param array, which is the array being halved 
     * @param size, which is the length of the array 
     */
    public void mergeTitleHelper(Item[] array, int size) {
    
        //base case 
      if (size < 2) {
        return;
      }

      int middle = size/2;
      Item[] left = new Item[middle];
      Item[] right = new Item[middle+1];

      for (int i = 0; i < middle; i++) {
        left[i] = array[i];
      }  
      for (int j = middle; j < size; j++) {
          
        right[j - middle] = array[j];
      }

      mergeTitleHelper(left, middle);
      mergeTitleHelper(right, size - middle);

      mergeTitle(left, right, array, middle, size - middle);
    }

    /** This helper function compares two strings and finds the first index at which two unique letters occur.
     * @param left
     * @param right
     * @return index 
     */
    public int findLetter(String left, String right) {
      int index = 0;
      for (int i = 0; i < left.length() && i < right.length(); i++) {
        if ((left.substring(i,i+1).compareToIgnoreCase(right.substring(i,i+1))) != 0) {
          index = i;
          i = left.length();
        }
      }
      return index;
    } 

    /** This function goes about sorting the left and right arrays -- in alphabetical order in terms of title -- back into the original array.
     * @param left, the left half of the original array 
     * @param right, the right half of the original array 
     * @param og, the original array 
     * @param left_size, the length of the left array 
     * @param right_size, the length of the right array  
     */
    public void mergeTitle(Item[] left, Item[] right, Item[] og, int left_size, int right_size) {
        //declare variables
      int left_index = 0; 
      int right_index = 0;
      int og_index = 0;  

        // runs while both left and right arrays are populated 
      while (left_index < left_size && right_index < right_size) {
        int index = findLetter(left[left_index].getTitle(), right[right_index].getTitle());

          // compares the first unique letter between the two titles and determines which precedes which
        if ((left[left_index].getTitle().substring(index,index+1).compareToIgnoreCase(right[right_index].getTitle().substring(index,index+1))) < 0)  {
            //the comparison returned a negative number, meaning the left array title preceeded the right array title 
          og[og_index++] = left[left_index++]; 
        }
        else {
          og[og_index++] = right[right_index++];
        }
      }

        // when either the left or right array is empty, the rest of the remaining array is put into the original array 
      while (left_index < left_size) {
        og[og_index++] = left[left_index++];
      }
      while (right_index < right_size) {
        og[og_index++] = right[right_index++];
      }
  }

  /** Sorts the inventory in terms of the name of the creator of the items.
   * This uses three helper functions:
   * mergeTitleHelper(Item[], int)
   * findLetter(String, String)
   * mergeTitle(Item[], Item[], Item[], int, int) 
   */
  public void sortByCreator() {
    mergeCreatorHelper(this.items, this.items.length);
  }

    /** This recursive function halves the array and subsequent arrays. When the resulting array is 1 index in length, 
     * the function no longer recurs and the next helper function is called. 
     * @param array, which is the array being halved 
     * @param size, which is the length of the array 
     */
    public void mergeCreatorHelper(Item[] array, int size) {
    
        //base case 
      if (size < 2) {
        return;
      }

      int middle = size/2;
      Item[] left = new Item[middle];
      Item[] right = new Item[middle+1];

      for (int i = 0; i < middle; i++) {
        left[i] = array[i];
      }  
      for (int j = middle; j < size; j++) {
          
        right[j - middle] = array[j];
      }

      mergeCreatorHelper(left, middle);
      mergeCreatorHelper(right, size - middle);

      mergeCreator(left, right, array, middle, size - middle);
    }

    /** This function goes about sorting the left and right arrays -- in alphabetical order in terms of creator's name -- back into the original array.
     * @param left, the left half of the original array 
     * @param right, the right half of the original array 
     * @param og, the original array 
     * @param left_size, the length of the left array 
     * @param right_size, the length of the right array  
     */
    public void mergeCreator(Item[] left, Item[] right, Item[] og, int left_size, int right_size) {
      int left_index = 0; 
      int right_index = 0;
      int og_index = 0;  

      while (left_index < left_size && right_index < right_size) {
        int index = findLetter(left[left_index].getCreator().getLastName(), right[right_index].getCreator().getLastName());

        if ((left[left_index].getCreator().getLastName().substring(index,index+1).compareToIgnoreCase(right[right_index].getCreator().getLastName().substring(index,index+1))) < 0)  {
          og[og_index++] = left[left_index++]; 
        }
        else {
          og[og_index++] = right[right_index++];
        }
      }

      while (left_index < left_size) {
        og[og_index++] = left[left_index++];
      }
      while (right_index < right_size) {
        og[og_index++] = right[right_index++];
      }
  }

  /** Returns an array of items 
   * @return items
   */
  public Item[] getItems() {
    return this.items;
  }

  //end of Inventory class
}
