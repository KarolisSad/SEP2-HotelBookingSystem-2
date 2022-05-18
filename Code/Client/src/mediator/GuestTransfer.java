package mediator;

import model.Guest;

import java.util.ArrayList;

/**
 * Purpose of this object is to store different values when sending to a server
 *
 * @version 2022-05-18
 * @author Group5
 */
public class GuestTransfer {
  private String fName;
  private String lName;
  private String email;
  private int phoneNr;
  private int bookingID;
  private String type;
  private String errorMessage;
  private ArrayList<Guest> guests;

  /**
   * 6 argument constructor used for transferring guest information and a booking identification.
   * @param type The type of transfer
   * @param bookingID The booking identification
   * @param fName First name of the guest
   * @param lName Last name of the guest
   * @param email Guest's email
   * @param phoneNr Guest's phone number
   */
  public GuestTransfer(String type, int bookingID, String fName, String lName, String email, int phoneNr){
    this.type = type;
    this.bookingID = bookingID;
    this.email = email;
    this.phoneNr = phoneNr;
    this.fName = fName;
    this.lName = lName;
  }

  /**
   * 1 argument constructor used for transferring type information.
   * @param type The type of transfer
   */
  public GuestTransfer(String type)
  {
    this.type = type;
  }

  /**
   * A getter method that returns ArrayList of Guest.
   * @return ArrayList of Guest called guests.
   */
  public ArrayList<Guest> getGuests() {
    return guests;
  }

  /**
   * A getter method that returns a type of the GuestTransfer.
   * @return String called type.
   */
  public String getType() {
    return type;
  }

  /**
   * A getter method that returns a String of all the information from the GuestTransfer.
   * @return String object.
   */
  @Override
  public String toString() {
    return "GuestTransfer{" +
            "fName='" + fName + '\'' +
            ", lName='" + lName + '\'' +
            ", email='" + email + '\'' +
            ", phoneNr=" + phoneNr +
            ", bookingID=" + bookingID +
            ", type='" + type + '\'' +
            ", guests=" + guests +
            '}';
  }
}
