package model;

import mediator.GuestTransfer;
import mediator.RoomBookingTransfer;
import mediator.RoomTransfer;
import utility.observer.javaobserver.UnnamedPropertyChangeSubject;

import java.time.LocalDate;
import java.util.ArrayList;

public interface Model extends UnnamedPropertyChangeSubject
{
  RoomTransfer availableRooms(LocalDate startDate, LocalDate endDate);
  RoomTransfer book(String roomId, LocalDate startDate, LocalDate endDate, Guest guest);
  RoomTransfer addRoom(String roomId, RoomType type, int nrBeds);
  RoomTransfer removeRoom(String roomId);
  RoomTransfer editRoomInfo(String roomId, RoomType type, int nrBeds);
  RoomTransfer getAllRooms();
  RoomBookingTransfer editBooking(int bookingId, LocalDate startDate, LocalDate endDate, String roomid, String status);
  RoomBookingTransfer removeBooking(int bookingId, int guestID);

  GuestTransfer editGuest(String type, int bookingID, String fName, String lName, String email, int phoneNr);

  // TODO CHR
  RoomBookingTransfer getAllBookings();
  RoomBookingTransfer getBookedBookings();
  RoomBookingTransfer getInProgressBookings();
  RoomBookingTransfer getCancelledBookings();
  RoomBookingTransfer processBooking(int bookingNumber);
  RoomBookingTransfer cancelBooking(int bookingNumber);

  GuestTransfer getAllGuests();

}
