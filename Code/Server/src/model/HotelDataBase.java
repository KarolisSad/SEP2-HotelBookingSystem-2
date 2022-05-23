package model;

import mediator.GuestTransfer;
import mediator.RoomBookingTransfer;
import mediator.RoomTransfer;
import network.MyDataBase;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class HotelDataBase implements HotelPersistence
{

  public HotelDataBase()
  {
  }

  @Override public void addRoom(String ID, RoomType type, int numberOfBeds)
      throws SQLException
  {
    MyDataBase dataBase = MyDataBase.getInstance();
    dataBase.addOneRoom(ID, type, numberOfBeds);
  }

  @Override public void remove(String ID) throws SQLException
  {
    MyDataBase dataBase = MyDataBase.getInstance();
    dataBase.removeOneRoom(ID);
  }

  @Override public ArrayList<Room> getAllRooms() throws SQLException
  {
    MyDataBase dataBase = MyDataBase.getInstance();
    return dataBase.getAllRooms();
  }

  @Override public ArrayList<Room> availableRooms(LocalDate startDate,
      LocalDate endDate) throws SQLException
  {
    MyDataBase dataBase = MyDataBase.getInstance();
    return dataBase.availableRooms(startDate, endDate);
  }

  @Override public void editRoomInfo(String roomID, RoomType type, int nrBeds)
      throws SQLException
  {
    MyDataBase dataBase = MyDataBase.getInstance();
    dataBase.editRoomInfo(roomID, type, nrBeds);
  }

  @Override public void editGuest(int bookingID, String fName, String lName,
      String email, int phoneNr) throws SQLException
  {
    MyDataBase dataBase = MyDataBase.getInstance();
    dataBase.editGuest(bookingID, fName, lName, email, phoneNr);
  }

  /**
   * Method used for getting all bookings of a specific type from the database.
   *
   * @param type the type of rooms to get
   * @return An ArrayList of bookings of the type corresponding to the String passed as argument.
   */
  @Override public ArrayList<RoomBooking> getAllBookings(String type)
      throws SQLException
  {
    MyDataBase dataBase = MyDataBase.getInstance();
    return dataBase.getAllRoomBookings(type);
  }

  /**
   * Method used for updating the state of a room in the database.
   *
   * @param booking the booking to be updated
   */
  @Override public void processBooking(RoomBooking booking) throws SQLException
  {
    MyDataBase dataBase = MyDataBase.getInstance();
    dataBase.processBooking(booking);
  }

  @Override public void cancelBooking(RoomBooking roomBooking)
      throws SQLException
  {
    MyDataBase dataBase = MyDataBase.getInstance();
    dataBase.cancelBooking(roomBooking);
  }

  @Override public void editBooking(int bookingId, LocalDate startDate,
      LocalDate endDate, String roomId) throws SQLException
  {
    MyDataBase dataBase = MyDataBase.getInstance();
    dataBase.editBooking(bookingId, startDate, endDate, roomId);
  }

  @Override public Room getRoom(String roomId) throws SQLException
  {
    MyDataBase dataBase = MyDataBase.getInstance();
    return dataBase.getRoom(roomId);

  }

  @Override public ArrayList<Guest> getAllGuests() throws SQLException
  {
    MyDataBase dataBase = MyDataBase.getInstance();
    return dataBase.getAllGuests();
  }

  /*
  @Override public RoomBookingTransfer getBookingWithGuest(int bookingNr,
      int phoneNr) throws SQLException
  {
    MyDataBase dataBase = MyDataBase.getInstance();
    return dataBase.getRoomWithGuest(bookingNr, phoneNr);
  }

   */

  /**
   * Method used for adding a booking to the database.
   *
   * @param roomBooking The booking to add.
   */
  @Override public void book(RoomBooking roomBooking) throws SQLException
  {
    MyDataBase dataBase = MyDataBase.getInstance();
    dataBase.book(roomBooking);
  }

  @Override
  public void register(Guest guest) throws SQLException {
    MyDataBase dataBase = MyDataBase.getInstance();
    dataBase.register(guest);
  }

  @Override
  public void login(String username, String password) throws SQLException {
    MyDataBase dataBase = MyDataBase.getInstance();
    dataBase.login(username,password);
  }

  /*

  @Override public ArrayList<RoomBooking> getAllBookings(String type)
          throws SQLException
  {
    MyDataBase dataBase = MyDataBase.getInstance();
    return dataBase.getAllRoomBookings(type);}

   */

  @Override
  public ArrayList<RoomBooking> getBookingsWhenLoggedIn(String username) throws SQLException {
    MyDataBase dataBase = MyDataBase.getInstance();
    return dataBase.getBookingsWhenLoggedIn(username);
  }

  @Override
  public void bookARoomWhenLoggedIn(RoomBooking roomBooking) throws SQLException {
    MyDataBase dataBase = MyDataBase.getInstance();
    dataBase.bookARoomWhenLoggedIn(roomBooking);
  }

  @Override public void clearDatabase() throws SQLException
  {
    MyDataBase dataBase = MyDataBase.getInstance();
    dataBase.clearDatabase();
  }

  @Override
  public void editGuestWithUsername(String username, String getfName, String getlName, String email, int phoneNr) throws SQLException {
    MyDataBase dataBase = MyDataBase.getInstance();
    dataBase.editGuestWithUsername( username,  getfName,  getlName,  email,  phoneNr);
  }

  @Override
  public GuestTransfer getGuestByUsername(String username) throws SQLException {
    MyDataBase dataBase = MyDataBase.getInstance();
    return dataBase.getGuestByUsername(username);
  }

}
