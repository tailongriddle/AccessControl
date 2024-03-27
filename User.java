//////////////// FILE HEADER //////////////////////////
//
// Title: User class
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



public class User {


  private final String USERNAME;
  private String password;
  private boolean isAdmin;
  

  /**
   * Creates a new user with the given username, password, and admin status
   * 
   * @param username
   * @param password
   * @param isAdmin
   */
  public User(String username, String password, boolean isAdmin) {
    
    this.password = password;
    this.isAdmin = isAdmin;
    this.USERNAME = username;
    
  }

  /**
   * Determines whether or not the password parameter matches the assigneed password
   * 
   * @param password
   * @return whether password is correct
   */
  public boolean isValidLogin(String password) {
    return this.password.equals(password);
   
  }

  /**
   * Gets the name of the user
   * 
   * @return Return the name of the user
   */
  public String getUsername() {
    return USERNAME;
  }

  /**
   * Checks whether or not use is admin
   * @return true if admin, false if not
   */
  public boolean getIsAdmin() {
    if (isAdmin == true) {
      return true;
    }
    return false;
  }

  /**
   * Sets a new password
   * 
   * @param password
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Sets the new admin status
   * 
   * @param isAdmin
   */
  public void setIsAdmin(boolean isAdmin) {
    this.isAdmin = isAdmin;
  }
  
 

}
