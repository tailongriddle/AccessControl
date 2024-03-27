import java.util.ArrayList;

//////////////// FILE HEADER //////////////////////////
//
// Title: Access control tester
// Course: CS 300 Spring 2022
//
// Author: Tai-Long Riddle
// Email: mlriddle@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: (name of your pair programming partner)
// Partner Email: (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understand the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: Instructors on Piazza
// Online Sources: NONE
//
///////////////////////////////////////////////////////////////////////////////

public class AccessControlTester {

  public static boolean runAllTests() {
    if (testUserConstructorAndMethods() && testAccessControlIsValidLoginNotValidUser()
        && testAddUserWithNoAdminPowers() && testAddRemoveUserWithAdminPowers()) {
      return true;
    }
    return false;
  }

  /**
   * Tests all necessary methods in User class
   * 
   * @return true if all tests passed
   */
  public static boolean testUserConstructorAndMethods() {
    User test = new User("namename", "pass", false);

    if (test.getUsername() == null) {
      return false;
    } else if (!test.getUsername().equals("namename")) {
      return false;
    }

    if (test.getIsAdmin()) {
      return false;
    }

    if (!test.isValidLogin("pass")) {
      return false;
    } else if (test.isValidLogin("notpass")) {
      return false;
    }

    test.setPassword("newpass");
    if (!test.isValidLogin("newpass")) {
      return false;
    }

    test.setIsAdmin(true);
    if (!test.getIsAdmin()) {
      return false;
    }

    return true;
  }

  /**
   * Checks the correctness of AccessControl.isValidLogin() method when called with incorrect
   * username or not matching (username, password) pair
   * 
   * @return false if user is not valid
   */
  public static boolean testAccessControlIsValidLoginNotValidUser() {
    AccessControl accessControl = new AccessControl();
    User testUser = new User("userTest", "pass", true);

    accessControl.addUser(testUser.getUsername(), true);

    if (AccessControl.isValidLogin("fake name", "pass") == true) {
      return false;
    } else if (AccessControl.isValidLogin("name", "fakePass") == true) {
      return false;
    }
    return true;
  }

  /**
   * Creates a new AccessControl object and does not log in an admin.
   * 
   * @return false if addUser does not return false or a user was added after the method returns
   */
  public static boolean testAddUserWithNoAdminPowers() {
    AccessControl accessControl = new AccessControl();
    User userNotAdmin = new User("userNotAdmin1", "pass", false);
    User toAddUser = new User("toAddUser", "pass", false);
    accessControl.addUser("userNotAdmin1");
    
    accessControl.setCurrentUser(userNotAdmin.getUsername());

    if (accessControl.addUser(toAddUser.getUsername())) {
      return false;
    }

    return true;
  }

  /**
   * Checks the correctness of addUsr and removeUser methods
   * 
   * @return false if methods do not work when current user is set as an admin
   */
  public static boolean testAddRemoveUserWithAdminPowers() {
    AccessControl accessControl = new AccessControl();
    User theAdmin = new User("theAdmin", "pass", true);
    User moveUser = new User("moveUser", "pass", false);

    accessControl.addUser("theAdmin", true);

    accessControl.setCurrentUser(theAdmin.getUsername());

    if (!accessControl.addUser(moveUser.getUsername())) {
      return false;
    }

    if (!accessControl.removeUser(moveUser.getUsername())) {
      return false;
    }
    return true;

  }



  /**
   * Runs all tests
   * 
   * @param args
   */
  public static void main(String[] args) {
    
    
    
    
    // All tests
    System.out.println("All Tests: " + runAllTests());
    
    // Individual tests for troubleshooting
    /*
    System.out.println("Individual Tests: ");
    System.out.println(testUserConstructorAndMethods());
    System.out.println(testAccessControlIsValidLoginNotValidUser());
    System.out.println(testAddUserWithNoAdminPowers());
    System.out.println(testAddRemoveUserWithAdminPowers());
    */
  }

}
