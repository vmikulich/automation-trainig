package by.bsu.expedia.model;

import java.util.Objects;
import java.util.Optional;

public class CarReservation {
    private String pickUpPlace;
    private String dropOffPlace;
    private ReservationDates reservationDates;

    public CarReservation(String placeFrom,
                          String placeTo,
                          ReservationDates reservationDates) {
        this.pickUpPlace = placeFrom;
        this.dropOffPlace = placeTo;
        this.reservationDates = reservationDates;
    }

    public CarReservation() {
    }

    public Optional<String> getPlaceFrom() {
        return Optional.ofNullable(pickUpPlace);
    }

    public CarReservation setPlaceFrom(String placeFrom) {
        this.pickUpPlace = placeFrom;
        return this;
    }

    public Optional<String> getPlaceTo() {
        return Optional.ofNullable(dropOffPlace);
    }

    public CarReservation setPlaceTo(String placeTo) {
        this.dropOffPlace = placeTo;
        return this;
    }

    public Optional<ReservationDates> getReservationDates() {
        return Optional.ofNullable(reservationDates);
    }

    public CarReservation setReservationDates(ReservationDates reservationDates) {
        this.reservationDates = reservationDates;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarReservation that = (CarReservation) o;
        return Objects.equals(pickUpPlace, that.pickUpPlace) &&
                Objects.equals(dropOffPlace, that.dropOffPlace) &&
                Objects.equals(reservationDates, that.reservationDates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pickUpPlace, dropOffPlace, reservationDates);
    }

    @Override
    public String toString() {
        return "HotelReservation{" +
                "placeFrom='" + pickUpPlace + '\'' +
                "placeTo='" + dropOffPlace + '\'' +
                ", reservationDates=" + reservationDates +
                '}';
    }
}
