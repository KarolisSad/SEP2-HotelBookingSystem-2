package viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import mediator.RoomTransfer;
import model.Guest;
import model.Model;

/**
 * A class providing functionality for GuestInformationViewController.
 *
 * @version 04/05/2022
 */

public class GuestInformationViewModel
{

  private Model model;
  private StringProperty firstNameField;
  private StringProperty lastNameField;
  private StringProperty emailField;
  private StringProperty phoneNumberField;
  private StringProperty errorLabel;
  private TemporaryInformation temp;

  /**
   * Constructor initializing instance variables.
   *
   * @param model    model interface
   * @param tempInfo shared object with ReservationViewModel to store selected room
   */
  public GuestInformationViewModel(Model model, TemporaryInformation tempInfo)
  {
    this.model = model;
    this.firstNameField = new SimpleStringProperty("");
    this.lastNameField = new SimpleStringProperty("");
    this.emailField = new SimpleStringProperty("");
    this.phoneNumberField = new SimpleStringProperty("");
    this.errorLabel = new SimpleStringProperty();
    this.temp = tempInfo;
  }

  /**
   * Delegates model to create new booking from
   * TemporaryInformation objects values and
   * fields from instance variables
   */
  public void bookRoomWithGuest()
  {

    RoomTransfer roomTransfer = model.book(temp.getRoomID(),
        temp.getStartDate(), temp.getEndDate(),
        new Guest(firstNameField.getValue(), lastNameField.getValue(),
            emailField.getValue(),
            Integer.parseInt(phoneNumberField.getValue())));
    if (roomTransfer.getMessage() == null)
    {
      errorLabel.setValue("Room was booked!");
    }
    else
    {
      errorLabel.setValue(roomTransfer.getMessage());
    }

  }

  public StringProperty getFirstNameField()
  {
    return firstNameField;
  }

  public StringProperty getLastNameField()
  {
    return lastNameField;
  }

  public StringProperty getEmailField()
  {
    return emailField;
  }

  public StringProperty getPhoneNumberField()
  {
    return phoneNumberField;
  }

  public StringProperty getErrorLabel()
  {
    return errorLabel;
  }
}

