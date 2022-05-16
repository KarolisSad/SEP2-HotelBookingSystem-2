package viewModel;

import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import javafx.util.converter.IntegerStringConverter;
import model.Guest;
import model.Model;

public class GuestDetailsForReceptionistViewModel {

    private Model model;
    private SimpleStringProperty email;
    private SimpleStringProperty bookingID;
    private SimpleStringProperty fName; //ref first name of the guest
    private SimpleStringProperty lName; //ref last name of the guest
    private SimpleStringProperty phoneNr;
    private SimpleStringProperty errorLabel;

    public GuestDetailsForReceptionistViewModel(Model model){
        this.model = model;

        this.email = new SimpleStringProperty();
        this.bookingID = new SimpleStringProperty();
        this.fName = new SimpleStringProperty();
        this.lName = new SimpleStringProperty();
        this.phoneNr = new SimpleStringProperty();
        this.errorLabel = new SimpleStringProperty();
    }

    public SimpleStringProperty getEmailProperty(){
        return email;
    }

    public String getEmail(){
        return email.get();
    }

    public SimpleStringProperty getBookingIDProperty(){
        return bookingID;
    }

    public int getBookingID(){
        IntegerStringConverter integerStringConverter = new IntegerStringConverter();
        return integerStringConverter.fromString(bookingID.get());
    }

    public SimpleStringProperty getfNameProperty(){
        return fName;
    }

    public String getfName(){
        return fName.get();
    }

    public SimpleStringProperty getlNameProperty(){
        return lName;
    }

    public String getlName(){
        return lName.get();
    }

    public SimpleStringProperty getPhoneNrProperty(){
        return phoneNr;
    }

    public int getPhoneNr(){
        return convertToInteger(phoneNr);
    }

    public void updateGuest(){
        model.editGuest("editGuest", getBookingID(),  getfName(), getlName(), getEmail(), Integer.parseInt(phoneNr.get()));
        System.out.println("In the viewModel update guest argumentd:\n" + getBookingID() + " " + getEmail() + " " +  getfName() + " " +  getlName() + " " +  getPhoneNr());
    }

    private int convertToInteger(StringProperty property){
        IntegerStringConverter converter = new IntegerStringConverter();
        int integer = converter.fromString(property.get());
        return integer;
    }

    public StringProperty getErrorLabelProperty() {
        return errorLabel;
    }

    public void setErrorLabel(String errorLabel) {
        this.errorLabel.set(errorLabel);
    }

    public void reset(){
        bookingID.setValue("");
        email.setValue("");
        fName.setValue("");
        lName.setValue("");
        phoneNr.setValue("");
    }

    public void setGuest(IntegerProperty bookingIdProperty, ObjectProperty<Guest> guestProperty) {
        bookingID.setValue("" + bookingIdProperty.get());
        email.setValue("" + guestProperty.get().getEmail());
        fName.setValue("" + guestProperty.get().getfName());
        lName.setValue("" + guestProperty.get().getlName());
        phoneNr.setValue("" + guestProperty.get().getPhoneNr());
    }
}

