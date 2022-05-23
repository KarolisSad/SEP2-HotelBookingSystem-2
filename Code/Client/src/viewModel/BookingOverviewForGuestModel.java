package viewModel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mediator.RoomBookingTransfer;
import model.Model;

/**
 * A class providing functionality for BookingOverViewForGuestController.
 *
 * @author Group 5
 * @version 23/05/2022
 */
public class BookingOverviewForGuestModel {

    private Model model;
    private ObservableList<SimpleBookingViewModel> bookings;

    private SimpleStringProperty errorLabel;

    private ObjectProperty<SimpleBookingViewModel> selectedBookingProperty;

    /**
     * Constructor initializing instance variables.
     *
     * @param model model interface
     */
    public BookingOverviewForGuestModel(Model model)
    {
        this.model = model;
        this.bookings = FXCollections.observableArrayList();
        this.errorLabel = new SimpleStringProperty("");
        this.selectedBookingProperty = new SimpleObjectProperty<>();
    }

    /**
     * Method returning list of Bookings made by Guest.
     * @return ObservableList<SimpleBookingViewModel> bookings
     */
    public ObservableList<SimpleBookingViewModel> getBookings() {
        return bookings;
    }

    /**
     * Method returning errorLabel property.
     * @return SimpleStringProperty errorLabel
     */
    public SimpleStringProperty getErrorLabel()
    {
        return errorLabel;
    }

    /**
     * Method returning booking property that was selected by the Guest.
     * @return SimpleBookingViewModel selected booking property
     */
    public SimpleBookingViewModel getSelectedBookingProperty()
    {
        return selectedBookingProperty.get();
    }

    /**
     * Method setting the selected booking.
     * @param selectedBooking a selected booking
     */
    public void setSelected(SimpleBookingViewModel selectedBooking)
    {
        selectedBookingProperty.set(selectedBooking);
    }

    /**
     * Method calling updateBookings().
     */
    public void reset() {
        updateBookings();
    }

    /**
     * Method clearing the table with all the bookings
     * and filling it again calling getBookingsWhenLoggedIn()
     * from the model.
     */
    public void updateBookings() {
        bookings.clear();
        RoomBookingTransfer roomBookings = model.getBookingsWhenLoggedIn();
        if (roomBookings.getMessage() == null)
        {
            for (int i = 0; i < roomBookings.getRoomBookings().size(); i++)
            {
                bookings.add(new SimpleBookingViewModel(roomBookings.getRoomBookings().get(i),"User"));
            }
        }
    }
}
