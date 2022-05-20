-- DELETE ALL --
DROP SCHEMA hoteltest CASCADE;
--------------------------


CREATE SCHEMA hoteltest;
SET SCHEMA 'hoteltest';

CREATE TABLE IF NOT EXISTS room
(
    roomID   varchar(20) PRIMARY KEY NOT NULL,
    roomType varchar(30)             NOT NULL CHECK (roomType IN ('Family', 'Single', 'Double', 'Suite')),
    nrBeds   integer                 NOT NULL CHECK ( nrBeds BETWEEN 1 AND 20)
);

CREATE TABLE IF NOT EXISTS guest
(
    username varchar(100) NOT NULL PRIMARY KEY,
    fName    VARCHAR(60)  NOT NULL,
    lName    VARCHAR(60)  NOT NULL,
    email    VARCHAR(60) CHECK ( email LIKE ('%@%')),
    phoneNr  integer      NOT NULL
);
CREATE TABLE IF NOT EXISTS roomBooking
(
    bookingID SERIAL PRIMARY KEY,
    startDate date,
    endDate   date,
    guest     varchar(100),
    roomID    varchar(20),
    state     varchar(12),
    FOREIGN KEY (roomID) REFERENCES room (roomID),
    FOREIGN KEY (guest) REFERENCES guest (username)
);

CREATE TABLE IF NOT EXISTS login
(
    username     varchar(100) PRIMARY KEY,
    userPassword varchar(100) NOT NULL,
    FOREIGN KEY (username) REFERENCES guest (username)
);

ALTER TABLE roomBooking
    ADD CONSTRAINT
        start_date_is_before_current_date CHECK ( startDate >= CURRENT_DATE);

ALTER TABLE roomBooking
    ADD CONSTRAINT
        end_date_is_before_start_date CHECK ( startDate < endDate);


-------------------------------<

-----------Trigger------------->
-- Checking if this room is not booked during selected period
CREATE OR REPLACE FUNCTION double_booking()
    RETURNS trigger AS
$BODY$
DECLARE
    vBookingCount NUMERIC;

BEGIN


    SELECT COUNT(*)
    INTO vBookingCount
    FROM roomBooking
    WHERE roomID = new.roomID
        AND old.state NOT LIKE 'Cancelled'
        AND (new.startDate BETWEEN startDate AND endDate
            OR new.endDate BETWEEN startDate AND endDate)
       OR (new.startDate < startDate AND new.endDate > endDate)
       OR (new.startDate > startDate AND new.endDate < endDate);


    IF (vBookingCount > 0) THEN
        RAISE EXCEPTION 'Room % is already booked during these dates',
            new.roomID;
    END IF;
    RETURN new;

END
$BODY$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS BookingDate
    ON roomBooking;

-- attaching trigger to roomBooking
CREATE TRIGGER BookingDate
    BEFORE INSERT
    ON roomBooking
    FOR EACH ROW
EXECUTE PROCEDURE double_booking();



-------------------------------<

-----------Trigger------------->

-------------------------------<

--------------------------------
------ NEW FUNCTION ------------

CREATE OR REPLACE FUNCTION update_booking()
    RETURNS trigger AS
$BODY$
DECLARE
    vBookingCount NUMERIC;

BEGIN


    SELECT COUNT(*)
    INTO vBookingCount
    FROM roomBooking
    WHERE roomID = new.roomID
        AND bookingID != new.bookingID
        AND (new.startDate BETWEEN startDate AND endDate
            OR new.endDate BETWEEN startDate AND endDate)
       OR (new.startDate < startDate AND new.endDate > endDate)
       OR (new.startDate > startDate AND new.endDate < endDate);


    IF (vBookingCount > 0) THEN
        RAISE EXCEPTION 'Room % is already booked during these dates',
            new.roomID;
    END IF;
    RETURN new;

END
$BODY$ LANGUAGE plpgsql;


CREATE TRIGGER BookingDateUpdate
    BEFORE UPDATE
    ON roomBooking
    FOR EACH ROW
    WHEN ( old.state = new.state )
EXECUTE PROCEDURE update_booking();
