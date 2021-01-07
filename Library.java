/** A Library object with a name, phone number, address, an array of Members and Employees, and an inventory.
 *
 * 01-10-2019
 */
import java.util.*;
import java.io.*;
import java.io.FileNotFoundException;
import java.util.Random;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

class Library {

    //declare scanners
  Scanner sc = new Scanner (System.in);   //use for ints
  Scanner ss = new Scanner (System.in);   // use for strings 
  Scanner sd = new Scanner (System.in);     // use for doubles
    
    // state variables 
  private String name;
  private String phoneNum;
  private String address;
  private Member[] members;
  Inventory inventory;
  private Employee[] employees;

  /** Creates a Library with a name, phone number, address, inventory, and arrays of Members and Employees which both default to empty.
   * @param name 
   * @param phoneNum
   * @param address 
   * @param inventory 
   */
  public Library (String name, String phoneNum, String address, Inventory inventory) {
    this.name = name;
		this.phoneNum = phoneNum;
		this.address = address;
		this.inventory = inventory;
    this.members = new Member[0];
    this.employees = new Employee[0];
  }

  /** Adds a member to the array of members.
   * @param member  
   */
  public void addMember(Member member) {
      // creates a copy of the members array that is one index greater
    Member[] membersCopy = Arrays.copyOf(this.members, this.members.length+1);
      // adds the new member to the copy of the array
    membersCopy[this.members.length] = member;
      // replaces the state variable array with the copy containing the new member
    this.members = membersCopy; 
  }

  /** Returns a list of members and their info in a single string (for file I/O use,
   * not user output).
   * @return members 
   */
  public String getMembers() {
    String members = "";
    for (int i = 0; i < this.members.length; i++) {
      if (this.members[i].itemsOut.length > 0) {
      members += this.members[i].toString() + this.members[i].getItemsOut() + "\n";
      }
      else {
        members += this.members[i].toString() + "\n";
      }
    }
    return members;
  }

  /** Returns a list of employees and their info in a single string (for file I/O use, 
   * not user output).
   * @return employees
   */
  public String getEmployees() {
    String employees = "";
    for (int i = 0; i < this.employees.length; i++) {
      employees += this.employees[i].toString() + "\n";
    }
    return employees;
  }

  /** Gets a member from their member number.
   * @param memberNum
   * @return member
   */
  public Member getMember(int memberNum) {
    int index = -1;
      // loops through the member array 
    for (int i = 0; i < this.members.length; i++) {
        // if the member number in the method paramater matches the member number at index i, i is saved in the variable index and the loop's exit condition is met
      if (memberNum == this.members[i].getMemberNum()) {
        index = i;
        i = this.members.length;
      }
    }
    return this.members[index];
  }

  /** Adds an employee to the array of employees.
   * @param employee  
   */
  public void addEmployee(Employee employee) {
    Employee[] employeesCopy = Arrays.copyOf(this.employees, this.employees.length+1);
    employeesCopy[this.employees.length] = employee;
    this.employees = employeesCopy;
  }

  /** Signs an item out of the library, into the possession of a given member.
   * Uses the signOut() method from the Member class
   * @param itemSerial 
   * @param member   
   */
  public void signOut(int itemSerial, Member member) {
      int index = this.inventory.findItem(itemSerial);
      
        member.signOut(this.inventory.getItems()[index]);
            // sets the items availability to false
        this.inventory.getItems()[index].setAvailability(false);
      
    
  }
	
  /**Signs a member's item back into the library inventory 
   * Uses the signIn() method from the Member class
   * @param itemSerial
   * @param member
   */
  public void signIn(int itemSerial, Member member) {
    for (int i = 0; i < this.inventory.getItems().length; i++) {
      if (itemSerial == this.inventory.getItems()[i].getSerialNum()) {
          // removes the item from the member's items out
        member.signIn(this.inventory.getItems()[i]);
          // sets the item's availability to true
        this.inventory.getItems()[i].setAvailability(true);
      }
    }
  }

//POTENTIALLY TAKE ALL THE GETTERS OUT BC THE LIBRARY IS NEVER INTERACTED WITH
  /** Gets the library's phone number 
   * @return phoneNum
   */
  public String getPhoneNum() {
    return phoneNum;
  }

  /** Gets the library's address
   * @return address
   */
  public String getAddress() {
    return address;
  }
//TAKE OUT?

  /** Get's the library's inventory
   * @return inventory, an Inventory type 
   */
  public Inventory getInventory() {
    return this.inventory;
  }

  /** Determines whether a member number exists in the library system 
   * @param memberNum 
   * @return exists
   */
  public boolean findMember(int memberNum) {
    boolean exists = false;
      //loops through the members array to find a matching member number, in which case the variable "exists" is set to true, and the loop is ended
    for (int i = 0; i < this.members.length; i++) {
      if (memberNum == this.members[i].getMemberNum()) {
        exists = true;
        i = this.members.length;
      }
    }
    return exists;
  }

  /** Determines whether an employee number exists in the library system 
   * @param employeeNum
   * @return exists
   */
  public boolean findEmployee(int employeeNum) {
    boolean exists = false;
      // loops through the employees array to find a matching employee number, in which case the variable "exists" is set to ture, and the loop is ended.
    for (int i = 0; i < this.employees.length; i++) {
      if (employeeNum == this.employees[i].getEmployeeNum()) {
        exists = true;
        i = this.employees.length;
      }
    } 
    return exists;
  }

  /** This method is used for the user interface. It checks that the input is an integer 
   * and that the user's input is within a range of acceptable choices 
   * @param start, the lowest number they can select 
   * @param end, the highest number they can select
   */
  public int checkInput(int start, int end) {
    // ensures that the input is an integer
  while (!sc.hasNextInt()) {
      sc.next();
      System.out.println("Invalid entry. Try again: ");
    }
    int choice = sc.nextInt();

      // ensures that the input is within the range start to end
    while (choice > end || choice < start) {
      System.out.println("Invalid entry. Try again: ");
      while (!sc.hasNextInt()) {
        sc.next();
        System.out.println("Invalid entry. Try again: ");
      }
      choice = sc.nextInt();
    }
  return choice;
}

/** Prints the user's search results using string formatting 
 * @param array, an array of Items
 */
public void searchResults(Item[] array) {
  System.out.println("\nSEARCH RESULTS:\n");
  System.out.printf("%-26s%-20s%-20s%-10s%-15s%-15s%-15s%-10s\n", "TITLE", "CREATOR", "RELEASE DATE", "LANGUAGE", "LOAN PERIOD", "SERIAL NUMBER", "TYPE", "AVAILABILITY");
  for (int i = 0; i < array.length; i++) {
    System.out.printf(array[i].userOutput());
  }
}

/** Checks user input is an int, and that it is a valid item number. If the user cannot
 * enter a valid serial number, they can enter 0 to exit the loop.
 * @return serialNum
 */
public int checkSerial() {
    // loop continues if the user enters non-integer values
  while (!sc.hasNextInt()) {
    sc.next();
    System.out.println("Invalid entry. Try again: ");
  }
  int serialNum = sc.nextInt();

    // loop continues if the user's input does not exist in the database 
  while (!this.inventory.itemExists(serialNum)) {
    System.out.println("Item does not exist. Re-enter the serial number, or enter 0 to return to the previous menu: ");
      // nested loop continues if the user inputs a non-integer
    while (!sc.hasNextInt()) {
      sc.next();
      System.out.println("Invalid entry. Try again: ");
    }
    serialNum = sc.nextInt();
    if (serialNum == 0) {
      break;
    }
  }
  return serialNum;
}


// USER INTERFACE

/** This is the first menu when the program is run. The user selects either the admin 
 * or member menu, or exits the program. It calls the following private functions: 
 * startFile(), the file reader 
 * memberMenu(int), calls the member menu method
 * adminMenu(int), calls the admin menu
 * exit(), the file writer 
 */
public void start() throws IOException{

    //reader methods
  startFile();
  startMemberFile();
  startEmployeeFile();

  int exit = 0;     //exit condition
 
  System.out.println("********************Welcome to the Public Library!********************\n              Please Select From The Following Options:               \n**********************************************************************\n");
	
  // do while exit != -1
  do {  
    System.out.println("1: Member\n2: Employee\n3: Exit Program");
    System.out.print ("Please enter your selection: ");
    
    int menuSelect = checkInput(1,3);   // checks for valid user input
 
    while (menuSelect != -1) {
      switch (menuSelect) {
        case 1: // member
          System.out.print("Please enter your 9-digit member number located on the back of your library card, or enter 0 to return to the previous menu: ");
          
          while (!sc.hasNextInt()) {    // checks for an integer
            sc.next();
            System.out.println("Invalid entry. Try again: ");
          }

          int memberNum = sc.nextInt();
          
          while (!findMember(memberNum)) {    // if the member number does not exist
            System.out.println ("\nInvalid Member Number. If you are having trouble logging in, contact the front desk :^)\nPlease enter your member number, or enter 0 to return to the previous menu: ");
            
            while (!sc.hasNextInt()) {
              sc.next();
              System.out.println("Invalid. Try again: ");
            }
            memberNum = sc.nextInt();
            if (memberNum == 0) {
                break;
            }
          }
          
          if (memberNum == 0) {   // exits to the previous menu
            menuSelect = -1;
          }
          else {    
            memberMenu(memberNum);    // calls the member menu
            menuSelect = -1;
            exit = -1;
          }
        break;

        case 2:   // employee
          System.out.print("Please enter your 5-digit employee number, or enter 0 to return to the previous menu: ");

          while (!sc.hasNextInt()) {    // checks for an int
            sc.next();
            System.out.println("Invalid entry. Try again: ");
          }
          int employeeNum = sc.nextInt();

          while (!findEmployee(employeeNum)) {    // if the employee num doesnt exist
            System.out.println ("\nInvalid employee number. Try again, or enter 0 to return to the previous menu: ");
            while (!sc.hasNextInt()) {
              sc.next();
              System.out.println("Invalid. Try again: ");
            }
            employeeNum = sc.nextInt();
            if (employeeNum == 0) {
              break;
            }
          }

          if (employeeNum == 0) {   // exits to the previous menu
            menuSelect = -1;
          }
          else {
            adminMenu(employeeNum);   // calls the admin menu
            menuSelect = -1;
            exit = -1;
          }
        break;

        case 3: // exits the program
          System.out.println("Exit"); 
          exit();   
          exit = -1;
          menuSelect = -1;
        break;
        }
    }
    
  } while (exit != -1);
}

/** The member menu allows the member to return or sign out an item, edit their account 
 * info, search the database, or exit the program. It calls the following private functions:
 * memberMenuEditInfo(int)
 * search(int)
 * exit(), the file writer
 * @param memberNum
 */
private void memberMenu(int memberNum) throws IOException {
  int exit = 0; // exit condition 
  
  do {  //do while exit does not equal -1
    System.out.println("1: Return an Item\n2: Sign Out an Item\n3: Edit Your Personal Information\n4: Search for items\n5: Exit");
    System.out.print("Select a menu option: ");
    
    int choice = checkInput(1, 5);    // checks for valid input

    while(choice != -1) {   //different menu options
      switch(choice) {
            
        case 1: // Return an item
          System.out.print("Please enter the item's serial number: ");
          
          int serialNum = checkSerial();  // checks the serial number 

          if (serialNum == 0) { // exit condition
            choice = -1;
          }
          else if (this.inventory.itemExists(serialNum) && this.inventory.getItems()[this.inventory.findItem(serialNum)].getAvailability() == false) {   // if the item exists and is signed out
            signIn(serialNum, getMember(memberNum));
            System.out.println ("Return successful.");
            choice = -1;
          }
          else {
            System.out.println("Return unsuccessful. Please try again.");
          }
        break;

        case 2: // Sign out an item
          System.out.println("Please enter the item's serial number: ");
          
          serialNum = checkSerial();  // checks the serial number

          if (serialNum == 0) { // exit condition
            choice = -1;
          }
          else if (this.inventory.itemExists(serialNum) && this.inventory.getItems()[this.inventory.findItem(serialNum)].getAvailability()){   // if the item exists and is available, it is signed out
            signOut(serialNum, getMember(memberNum));
            System.out.println ("You've successfully signed out " + this.inventory.items[this.inventory.findItem(serialNum)].getTitle() + "(" + this.inventory.items[this.inventory.findItem(serialNum)].getType() + ")");
            choice = -1;
          }
          else {    
            System.out.println("This item is unavailable.");
            choice = -1;
          }
        break;

        case 3:     // user edits personal information 
          memberMenuEditInfo(memberNum);  // calls edit menu 
          exit();
          exit = -1;
          choice = -1;
        break;
               
        case 4: // search for items
          search(memberNum);    // calls search menu
          exit();
          exit = -1;
          choice = -1;
        break;

        case 5: // exit
          System.out.println("Exit");
          exit();   // calls file writer
          exit = -1;
          choice = -1;
        break;
      }
    }
    } while (exit != -1); 
}

/** This is a sub menu accessed through the member menu. It allows the member to edit their
 * name and phone number, and view their fines.
 * @param memberNum 
 */
private void memberMenuEditInfo(int memberNum) throws IOException {
  int exit = 0; // exit condition

  do { //do while exit does not equal -1
    System.out.println("1: Edit name\n2: Edit phone number\n3: View your outstanding fees\n4: Return to the previous menu\n5: Exit");
    System.out.print("Select a menu option: ");

    int choice = checkInput(1,5);   // checks input

      // menu options
    while(choice != -1) {
      switch(choice) {
              
        case 1: // changes the member's name 
          System.out.println("Enter your name: ");
          String name = ss.nextLine();
          getMember(memberNum).setName(name);
          choice = -1;
        break;

        case 2: // changes the member's phone number
          System.out.println("Enter your phone number: ");
          String phoneNum = ss.nextLine();
          getMember(memberNum).setPhoneNum(phoneNum);
          choice = -1;
        break;

        case 3: // displays the member's fines
          System.out.println("You currently owe: $" + getMember(memberNum).getFines());
          choice = -1;
        break; 

        case 4: // calls memberMenu()
          memberMenu(memberNum);
          choice = -1;
        break;

        case 5: // exit
          System.out.println("Exit");
          exit();
          exit = -1;
          choice = -1;
        break;
      }
    }
  } while (exit != -1);
}

/** The admin menu can only be accessed using a valid employee number (the one I created
 * in the main method is 12345). It allows the employee to add members and other employees, 
 * charge a fine to a member, and edit, sort, search, add, and remove items in the inventory
 * It calls the following private methods:
 * search(int)
 * adminMenuSort(int)
 * adminMenuAddItem(int)
 * editItemInfo(int)
 * exit()
 * adminMenu
 * @param employeeNum 
 */
private void adminMenu(int employeeNum) throws IOException {
  Random r = new Random();  // new random 
  int exit = 0; // exit condition 
  
  do {      // do while exit does not equal -1
    System.out.println("1: Add a member\n2: Add an employee\n3: Charge a member a fine\n4: Add an item\n5: Edit an item's information\n6: Remove an item\n7: Search\n8: Sort\n9: Exit");
    System.out.print("Select a menu option: ");

    int choice = checkInput(1,9); // checks input

    while (choice != -1) {  // menu options
      switch (choice) {
        case 1:   // adds a member 
          System.out.println ("\nEnter the new member's personal information: \nName: ");
          String name = ss.nextLine();
          System.out.print("Phone Number: ");
          String phoneNum = ss.nextLine();

            // generates a random member number
          int memberNum = 100000000 + r.nextInt(900000000);
          while (findMember(memberNum)) { // if the random 9 digit number somehow manages to be an existing number, a new one is generated
            memberNum = 100000000 + r.nextInt(900000000);
          }
            // new member is created 
          Member newMember = new Member (name, phoneNum, memberNum);
          addMember(newMember); // member is added to the library 
          System.out.println("New member number: " + memberNum);  
          choice = -1;
        break;

        case 2:     // adds an employee 
          System.out.println("\nEnter the new employee's information: \nPosition being filled: ");
          String position = ss.nextLine();

          System.out.println("Name: ");
          name = ss.nextLine();

          System.out.println("Age as of January 1 of the current year: ");
          int age = checkInput(8,200);  // checks user input
            // generates a random employee number with 5 digits
          int newEmployeeNum = 10000 + r.nextInt(90000);
          while (findEmployee(newEmployeeNum)) {  // ensures the number is unique
            newEmployeeNum = 10000 + r.nextInt(90000);
          }
            // new employee is created
          Employee newEmployee = new Employee(name,age, newEmployeeNum, position);
          addEmployee(newEmployee); // employee is added to the library
          System.out.println("New employee number: " + newEmployeeNum);
          choice = -1;
        break;

        case 3:   // charges a member a fee
          System.out.println("\nEnter the member's member number: ");
          
            // checks menu number 
          while (!sc.hasNextInt()) {
            sc.next();
            System.out.println("Invalid. Try again: ");
          }
          memberNum = sc.nextInt();
          while (!findMember(memberNum)) {
            System.out.println("Member number is not registered. Enter the member number again, or enter 0 to return to the previous menu: ");
            while (!sc.hasNextInt()) {
              sc.next();
              System.out.println("Invalid. Try again: ");
            }
            memberNum = sc.nextInt();
            if (memberNum == 0) {
              choice = -1;
              break;
            }
          }

          System.out.println("Enter the amount owed: ");
          while (!sd.hasNextDouble()) {   // checks input
            sc.next();
            System.out.println("Invalid entry. Try again: ");
          }
          double amount = sd.nextDouble();
          getMember(memberNum).setFines(amount);
          System.out.println ("Updated: " + getMember(memberNum).getName() + " now owes $" + getMember(memberNum).getFines());
          choice = -1;
        break;

        case 4:   // calls the add item menu
          adminMenuAddItem(employeeNum);
          exit = -1;
          choice = -1;
        break;

        case 5:   // edit item info
          editItemInfo(employeeNum);
          exit = -1;
          choice = -1;
        break;

        case 6: //remove an item
          System.out.println("Enter the item's serial number: ");
          
          int serialNum = checkSerial();

          if (serialNum == 0) {
            choice = -1;
          }
            // removes the item from the inventory 
          this.inventory.removeItem(this.inventory.getItems()[this.inventory.findItem(serialNum)]);
          System.out.println("Item #" + serialNum + " was removed from the database.");
          choice = -1;
        break;

        case 7:   //search menu is called 
          search(employeeNum);
          exit = -1;
          choice = -1;
        break;

        case 8:   //sort menu is called
          adminMenuSort(employeeNum);
          exit = -1;
          choice = -1;
        break;

        case 9:   //exit
          System.out.println("Exit");
          exit();
          exit = -1;
          choice = -1;  
        break;
      }
    }
  } while (exit != -1);   
}

/** This is a sub menu accessed through the admin menu. It allows the employee to edit or 
 * add to an item its languge, loan period, release date, genre, and length.
 * It calls the following private methods 
 * adminMenu(int), to return to the main admin menu 
 * exit(), the file writer 
 * @param employeeNum, so that the employee can return to the admin menu
 */
private void editItemInfo(int employeeNum) throws IOException {
  int exit = 0;   // exit condition 
  
  do {    // do while exit is not equal to -1
    System.out.println("1: Edit language\n2: Item loan period\n3: Item release date\n4: Item genre\n5: Item length\n6: Return to previous menu\n7: Exit");
    System.out.print("Select a menu option: ");

    int choice = checkInput(1,7); // checks input 
    
    while (choice != -1) {
      switch (choice) {

        case 1:   //sets language
          System.out.println("Enter item's serial number: ");
          int serial = checkSerial();
          System.out.println("Enter the item's language: ");
          String lang = ss.nextLine();
            // sets the item's language 
          this.inventory.getItems()[this.inventory.findItem(serial)].setLanguge(lang);
          choice = -1;
        break;

        case 2:   // sets loan period
          System.out.println("Enter the item's serial number: ");
          serial = checkSerial();   // checks valid serial 
          System.out.println("Enter the item's loan period (days): ");
          int loan = checkInput(1,75);    // checks input 
            // sets the item's loan period
          this.inventory.getItems()[this.inventory.findItem(serial)].setLoanPeriod(loan);
          choice = -1;
        break;

        case 3:   // set release date
          System.out.println("Enter the item's serial number: ");
          serial = checkSerial();   

          System.out.println("Release date: ");
          System.out.println("Month: ");
          int month = checkInput(1, 12);
          System.out.println("Day: ");
          int day = checkInput(1,31);
          System.out.println("Year: ");
          int year = checkInput(1,2019);

            // creates a new date 
          Date date = new Date(month,day,year);
            // sets the items release date 
          this.inventory.getItems()[this.inventory.findItem(serial)].setReleaseDate(date);
          choice = -1;
        break;

        case 4:   // sets genre
          System.out.println("Enter the item's serial number: ");
          serial = checkSerial();
          System.out.println("Enter the item's genre: ");
          String genre = ss.nextLine();
              // sets the item's genre 
          this.inventory.getItems()[this.inventory.findItem(serial)].setGenre(genre);
          choice = -1;
        break;

        case 5:   //sets length
          System.out.println("Enter the item's serial number: ");
          serial = checkSerial();
          System.out.println("Enter the item's length: ");
          int length = checkInput(1, 10000);
            // sets the items length 
          this.inventory.getItems()[this.inventory.findItem(serial)].setLength(length);
          choice = -1;
        break;

        case 6:   //return to previous menu
          adminMenu(employeeNum);   // calls adminu menu 
          exit = -1;
          choice = -1;
        break;

        case 7:   //exit
          System.out.println("Exit");
          exit();
          exit = -1;
          choice = -1;
        break;
      }
    }
    
  } while (exit != -1);
}

/** This sub menu allows the admin to sort the inventory which will be output to the file.
 * It calls the following private methods:
 * adminMenu(int)
 * exit()
 * @param employeeNum
 */
private void adminMenuSort(int employeeNum) throws IOException {
  int exit = 0;   // exit condition  

  do {    // do while exit does not equal -1 
    System.out.println("1: Sort by titles\n2: Sort by release date\n3: Sort by creator's last name\n4: Return to the previous menu\n5: Exit");
    System.out.print("Select a menu option: ");
    
    int choice = checkInput(1,5);   // checks input 

    while (choice != -1) {
      switch(choice) {

        case 1:
          this.inventory.sortByTitle();
          System.out.println ("Inventory sorted by title");
          choice = -1;
        break;

        case 2:
          this.inventory.sortByDate();
          System.out.println ("Inventory sorted in ascending order by release date");
          choice = -1;
        break;

        case 3:
          this.inventory.sortByCreator();
          System.out.println ("Inventory sorted alphabetically by creator name");
          choice = -1;
        break;

        case 4:
          adminMenu(employeeNum);
          exit = -1;
          choice = -1;
        break;

        case 5:
          System.out.println("Exit");
          exit();
          exit = -1;
          choice = -1;
        break;
      }
    }
    
  } while (exit != -1);
}

/** This is a sub menu accessed through the admin menu. It allows the employee to add items 
 * to the database. It calls the following private methods: 
 * adminMenu(int)
 * exit()
 * @param employeeNum 
 */
private void adminMenuAddItem(int employeeNum) throws IOException {
  int exit = 0;   // exit condition   

  do {  // do while exit does not equal -1
    System.out.println("1: Add book\n2: Add DVD\n3: Add audiobook\n4: Return to the previous menu\n5: Exit");
    System.out.print("Select a menu option: ");
    
    int choice = checkInput(1,5);   //checks input 

    while (choice != -1) {    // menu options 
      switch (choice) {

        case 1:   //add book
          System.out.println("Book title: ");
          String title = ss.nextLine();

          System.out.println("Author's first name: ");
          String first = ss.nextLine();

          System.out.println ("Author's last name: ");
          String last = ss.nextLine();
          Person author = new Person(first,last);   // creates a new person 

          System.out.println("Serial number: ");
          int serialNum = checkInput(100000000,900000000);  //checks serial num 
          Book book = new Book (title, author, serialNum);    // creates a new book
          System.out.println("Book #" + serialNum + " added. To input additional item information, go through the 'Edit item information' menu");
          choice = -1;
        break;

        case 2:   //add dvd
          System.out.println ("DVD title: ");
          title = ss.nextLine();

          System.out.println ("Director's first name: ");
          first = ss.nextLine();

          System.out.println("Director's last name: ");
          last = ss.nextLine();
          Person director = new Person(first,last);   // creates a new person 

          System.out.println("Serial number: ");
          serialNum = checkInput(100000000,900000000);    // checks serial num 
          DVD dvd = new DVD(title,director,serialNum);    // creates a new DVD
          System.out.println("DVD #" + serialNum + " added. To input additional item information, go through the 'Edit item information' menu");
          choice = -1;
        break;

        case 3:   //add audiobook
          System.out.println("Audio Book title: ");
          title = ss.nextLine();

          System.out.println("Author's first name: ");
          first = ss.nextLine();

          System.out.println("Author's last name: ");
          last = ss.nextLine();
          author = new Person(first,last);    // creates a new person  

          System.out.println("Serial number: ");     
          serialNum = checkInput(100000000,900000000);  //checks serial num  
          AudioBook audio = new AudioBook(title,author,serialNum);  // creates a new audiobook
          System.out.println("Audio Book #" + serialNum + " added. To input additional item information, go through the 'Edit item information' menu");
          choice = -1;
        break;

        case 4:   // goes back to admin menu
          adminMenu(employeeNum);
          exit = -1;
          choice = -1;
        break;

        case 5:   //exits program
          System.out.println("Exit");
          exit();
          exit = -1;
          choice = -1;
        break;      
      }
    }

  } while (exit != -1);
}

/** This is a sub menu accessed by either the admin or user. It allows them to search for 
 * items using different paramenters (title, name, release date, etc). 
 * It calls the following private methods: 
 * memberMenu(int), returns to the member menu if the int is a valid member number 
 * adminMenu(int), returns to the admin menu if the int is not a member number 
 * exit(), the file writer 
 * @param num, either an employee or member number 
 */
private void search(int num) throws IOException {
  int exit = 0;   // exit condition 
  
  do {    //do while exit does not equal -1 
    System.out.println ("1: Search a title\n2: Search a creator name\n3: Search a language\n4: Search a range of release dates\n5: Search a genre\n6: Return to the previous menu\n7: Exit program");
    System.out.print ("Select a menu option: ");

    int choice = checkInput(1,7);   // checks input 
              
    while(choice != -1) {     //menu options 
      switch(choice) {
                      
        case 1: //search title
          System.out.print("Enter a title: ");
          String title = ss.nextLine();
          searchResults(this.inventory.searchTitle(title));
          choice = -1;
        break;

        case 2: //search creator
          System.out.println("Enter a creator: ");
          String creator = ss.nextLine();
          searchResults(this.inventory.searchCreator(creator));
          choice = -1;
        break;

        case 3:   //search language
          System.out.println("Enter a language: ");
          String language = ss.nextLine();
          searchResults(this.inventory.searchLanguage(language));
          choice = -1;
        break;

        case 4:   //search release dates
          System.out.println("Start of range: ");
          System.out.print("\tMonth: ");
          int month1 = checkInput(1,12);
          System.out.print("\tDay: ");
          int day1 = checkInput(1,31);
          System.out.print("\tYear: ");
          int year1 = checkInput(1,2019);
          Date start = new Date(month1, day1, year1);
          System.out.println("End of range: ");
          System.out.print("\tMonth: ");
          int month2 = checkInput(1,12);
          System.out.print("\tDay: ");
          int day2 = checkInput(1,31);
          System.out.print("\tYear: ");
          int year2 = checkInput(1,2019);
          Date end = new Date(month2, day2, year2);
          searchResults(this.inventory.searchDate(start, end));
          choice = -1;
        break;

        case 5:   // search genre
          System.out.println("Enter a genre: ");
          String genre = ss.nextLine();
          searchResults(this.inventory.searchGenre(genre));
          choice = -1;
        break;

        case 6:
          if (findMember(num)) {
            memberMenu(num);
            exit = -1;
            choice = -1;
          }
          else {
            adminMenu(num);
            exit = -1;
            choice = -1;
          }
        break;

        case 7:
          System.out.println("Exit");
          exit();
          exit = -1;
          choice = -1;
        break;
      }
    }
  } while (exit != -1);
}

/** This method is called every time an exit condition in any of the menus is met. It 
 * outputs all the data from the inventory, members, and employees into its respective file.
 */
private void exit() throws IOException {
    //print writers for each file output 
  PrintWriter outputStream1 = null;   // library 
  PrintWriter outputStream2 = null;   // employee 
  PrintWriter outputStream3 = null;   // member 
  try {
    outputStream1 = new PrintWriter(new FileWriter("librarydatabase.txt"));
    outputStream2 = new PrintWriter(new FileWriter("employeelist.txt"));
    outputStream3 = new PrintWriter(new FileWriter("memberlist.txt"));
    
    outputStream1.print(this.inventory.getInventory());
    outputStream2.print(getEmployees());
    outputStream3.print(getMembers());
  }
  finally { // close files 
    if (outputStream1 != null ) {
      outputStream1.close();
      outputStream2.close();
      outputStream3.close();
    }
  }
}

/** This method is called when the program starts. It reads the file and recreates the inventory data
 */
private void startFile() throws IOException {
  // Open the file.
  Scanner inFile = null;
  
  try {

    inFile = new Scanner(new BufferedReader(new FileReader("librarydatabase.txt")));
      // Read lines from the file till end of file
    while (inFile.hasNextLine())
    {
    
      String textLine = inFile.nextLine();  //reads next line 
                      
      String[] details = textLine.split("%"); // splits each % from the txt file
      String title = details[0]; // first split is title
      String author = details[1]; // second is author
      int month = Integer.parseInt(details[2]);	 //converts month to int
      int day = Integer.parseInt(details[3]);	 // converts day to int
      int year = Integer.parseInt(details[4]);	  // converts year to int
      String language = details[5]; // sixth is language
      int loanPeriod = Integer.parseInt(details[6]); // seventh is load period (as a string)
      int serialNum = Integer.parseInt(details[7]); //converts serial num to int
      String itemType = details[8]; // ninth is item type (book, audio, etc)
      boolean availability = Boolean.parseBoolean(details[9]); // converts avail. to boolean
 
      String[] firstLastName = author.split(" "); // since Person requires first and last name, i needed to split whatever name into two (first and last) with the space character
        String first = firstLastName[0]; 
        String last = firstLastName[1]; 

        Person person = new Person(first, last); // new person 
        Date date = new Date(month,day,year);   // new date 

      if (itemType.equals("Book")) {

        Book book = new Book (title, person, language, date, loanPeriod, serialNum, 0, " ", availability); // makes a book 
        this.inventory.addItem(book);
      }
      else if (itemType.equals("Audio Book"))
      {
        AudioBook audio = new AudioBook (title, person, language, date, loanPeriod, serialNum, 0, " ", availability);   // makes an audiobook
        this.inventory.addItem(audio);
      }
      else if (itemType.equals("DVD"))
      {
        DVD disc = new DVD (title, person, language, date, loanPeriod, serialNum, 0, " ", availability);  // makes a dvd 
        this.inventory.addItem(disc);
      }
    }
  }
  finally {
    if (inFile != null){
        // Close the file.
        inFile.close();
    }
  }
}

/** This method reads the data from the employee file I/O and creates the necessary objects
 */
private void startEmployeeFile() throws IOException {
  // Open the file.
  Scanner inFile = null;
  
  try {

    inFile = new Scanner(new BufferedReader(new FileReader("employeelist.txt")));
      //  (String name, int age, int employeeNum, String position) {
    // Read lines from the file till end of file
    while (inFile.hasNextLine())
    {
    String textLine = inFile.nextLine(); 
    
   // (String name, int age, int employeeNum, String position) {
    String[] details = textLine.split("%"); // splits each % from the txt file
    String name = details[0]; // first split is name
    String ageString = details[1]; // second is age (as a string)
    String employeeNumString = details[2]; // third is employee number (string)
    String position = details[3]; // fourth is position 

    int age = Integer.parseInt(ageString);
    int employeeNum = Integer.parseInt(employeeNumString);
    
    Employee employee = new Employee(name, age, employeeNum, position);
    addEmployee(employee);
    }
  }
  finally {
    if (inFile != null) {
      inFile.close();   //closes file
    }
  }
}

/** This method reads the data from the member file I/O and creates the necessary objects
 */
private void startMemberFile() throws IOException {
  Scanner inFile = null;

  try {
    inFile = new Scanner(new BufferedReader(new FileReader("memberlist.txt")));
      //parameters (String name, String phone number, int member number)
      
      while (inFile.hasNextLine()) {
        String textLine = inFile.nextLine();
        String[] details = textLine.split("~");   //splits member parameters from serial numbers of items out 
        String params = details[0];
        
          //parameters 
        String[] parameters = params.split("%");
        String name = parameters[0];    // first string is the name 
        String phoneNum = parameters[1];    // second is the phone number
        int memberNum = Integer.parseInt(parameters[2]);   // parses the member number 
        double fines = Double.parseDouble(parameters[3]);   // fines associated with the member

        Member member = new Member(name, phoneNum, memberNum);
        addMember(member);
        member.setFines(fines);   // creates new member 

        String itemsOut = details[1];     // reads the serial numbers of what the member has signed out 
        String[] items = itemsOut.split("%");
        for (int i = 0; i < items.length; i++) {
          int serialNum = Integer.parseInt(items[i]);
          signOut(serialNum, member);   // signs it out through their account 
        }
      } 
  }
  catch (Exception e) {
    System.out.println ("");
  }
  finally {
    if (inFile != null) {
      inFile.close();
    }
  }
}


//end of Library class
}
