
//////////////// FILE HEADER //////////////////////////
//
// Title: Access control
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
import java.util.ArrayList;
import java.util.NoSuchElementException;



public class AccessControl {
  private static ArrayList<User> users;
  private User currentUser;
  private static final String DEFAULT_PASSWORD = "changeme";


  public AccessControl() {
    if (this.currentUser != null) {
      this.currentUser = null;
    }

    if (users == null) {
      users = new ArrayList<>();
      users.add(new User("admin", "root", true));
    }


  } // A no-argument constructor

  /**
   * Report whether a given username/password pair is a valid login
   * 
   * @param username
   * @param password
   * @return false if not valid combo, true if valid
   */
  public static boolean isValidLogin(String username, String password) {
    for (User combo : users) {
      if (username.equals(combo.getUsername())) {
        return combo.isValidLogin(password);
      }
    }

    return false;
  }

  /**
   * Change the password of the current user
   * 
   * @param newPassword
   */
  public void changePassword(String newPassword) {
    currentUser.setPassword(newPassword);
  }

  /**
   * Log out the current user
   */
  public void logout() {
    this.setCurrentUser(null);
  }

  /**
   * A mutator that you can use to write tests without simulating user input. It sets the current
   * user to the user from the users list whose username matches the string provided as input to the
   * method (exact match case sensitive).
   * 
   * @param username
   */
  public void setCurrentUser(String username) {
    for (User account : users) {
      if (account.getUsername().equals(username)) {
        currentUser = account;
      }
    }
  }

  /**
   * Creates a new user with default password and isAdmin == false and adds it to the users
   * ArrayList. User takes a chosen username as input.
   * 
   * @param username
   * @return True if current user has Admin power and the new user was successfully added and false
   *         if current user is null or does not have Admin power
   */
  public boolean addUser(String username) {
    if (username == null) {
      throw new IllegalArgumentException("Illegal Argument: Username cannot be null!");
    } else if (username.length() < 5) {
      throw new IllegalArgumentException(
          "Illegal Argument: Username cannot be less" + " than 5 letters!");
    }
    for (User name : users) {
      if (name.getUsername().equals(username)) {
        throw new IllegalArgumentException("Illegal Argument: Username must be unique!");
      }
    }

    User newUser = new User(username, DEFAULT_PASSWORD, false);

    users.add(newUser);



    if (currentUser == null || currentUser.getIsAdmin() == false) {
      return false;

    }

    for (int i = 0; i < users.size(); i++) {
      if (users.get(i).getUsername().equals(username)) {
        return true;
      }

    }
    return false;
  }

  /**
   * Creates a new user with default password and adds it to the users ArrayList. User takes isAdmin
   * and username as input.
   * 
   * @param username
   * @param isAdmin
   * @return True if current user has Admin power and the new user was successfully added and false
   *         if current user is null or does not have Admin power
   */
  public boolean addUser(String username, boolean isAdmin) {
    if (username == null) {
      throw new IllegalArgumentException("Illegal Argument: Username cannot be null!");
    } else if (username.length() < 5) {
      throw new IllegalArgumentException(
          "Illegal Argument: Username cannot be less" + " than 5 letters!");
    }
    for (User name : users) {
      if (name.getUsername().equals(username)) {
        throw new IllegalArgumentException("Illegal Argument: Username must be unique!");
      }
    }
    User newUser = new User(username, DEFAULT_PASSWORD, isAdmin);

    users.add(newUser);

    if (currentUser == null || currentUser.getIsAdmin() == false) {
      return false;
    }

    for (int i = 0; i < users.size(); i++) {
      if (users.get(i).getUsername().equals(username)) {
        return true;
      }

    }
    return false;

  }

  /**
   * Removes a user given a unique username
   * 
   * @param username
   * @return true if the current user has Admin powers and the user whose username is passed as
   *         input was successfully removed.
   */

  public boolean removeUser(String username) {
    boolean ifFound = false;

    for (int i = 0; i < users.size(); i++) {
      if (users.get(i).getUsername().equals(username)) {
        users.remove(users.get(i));
        ifFound = true;
      }

    }

    if (ifFound == false) {
      throw new NoSuchElementException("No Such Element: Username not found!");
    }

    for (int i = 0; i < users.size(); i++) {
      if (users.get(i).getUsername().equals(username)) {
        return false;
      }
    }

    if (currentUser == null || !currentUser.getIsAdmin()) {
      return false;

    }
    return true;
  }


  /**
   * Gives a user admin power
   * 
   * @param username
   * @return true if this operation terminates successfully and false if the current user is null or
   *         does not have admin powers
   */
  public boolean giveAdmin(String username) {
    for (User changeAdmin : users) {
      if (changeAdmin.getUsername().equals(username)) {
        changeAdmin.setIsAdmin(true);
        username = "found";
      }

    }

    if (!username.equals("found")) {
      throw new NoSuchElementException("No Such Element: Username not found!");
    }

    for (User removeName : users) {
      if (removeName.getUsername().equals(username)) {
        return false;
      }
    }

    if (currentUser == null || currentUser.getIsAdmin() == false) {
      return false;

    }

    return true;
  }

  /**
   * Removes the admin power of a user given their username
   * 
   * @param username
   * @return true if this operation terminates successfully and false if the current user is null or
   *         does not have admin powers
   */
  public boolean takeAdmin(String username) {
    for (User changeAdmin : users) {
      if (changeAdmin.getUsername().equals(username)) {
        changeAdmin.setIsAdmin(false);
        username = "found";
      }

    }

    if (!username.equals("found")) {
      throw new NoSuchElementException("No Such Element: Username not found!");
    }

    for (User removeName : users) {
      if (removeName.getUsername().equals(username)) {
        return false;
      }
    }

    if (currentUser == null || currentUser.getIsAdmin() == false) {
      return false;

    }
    return true;
  }

  /**
   * 
   * Resets the password of a user given their username
   * 
   * @param username
   * @return true if this operation terminates successfully and return false if the current user is
   *         null or does not have admin powers
   */
  public boolean resetPassword(String username) {
    for (User changeAdmin : users) {
      if (changeAdmin.getUsername().equals(username)) {
        changeAdmin.setPassword(DEFAULT_PASSWORD);
        username = "found";
      }

    }

    if (!username.equals("found")) {
      throw new NoSuchElementException("No Such Element: Username not found!");
    }

    for (User removeName : users) {
      if (removeName.getUsername().equals(username)) {
        return false;
      }
    }

    if (currentUser == null || currentUser.getIsAdmin() == false) {
      return false;

    }
    return true;
  }


  /**
   * @param args
   */
  public static void main(String[] args) {

  }

}
