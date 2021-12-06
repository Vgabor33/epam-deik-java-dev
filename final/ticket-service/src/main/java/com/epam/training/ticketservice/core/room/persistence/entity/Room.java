package com.epam.training.ticketservice.core.room.persistence.entity;

import com.epam.training.ticketservice.core.movie.persistence.entity.Movie;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Room
{
    @Id
    @Column(unique = true)
    private String name;
    private int seatRows;
    private int seatCols;

    public static Room of(String name, int seatRows, int seatCols)
    {
        return new Room(name,seatRows,seatCols);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return name.equals(room.name) && Integer.compare(seatCols,room.seatCols) == 0 && Integer.compare(seatRows, room.seatRows) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash( name, seatCols, seatRows);
    }

    @Override
    public String toString() {
        return "Room "+ name +" with " + getSeats() +" seats, "+seatRows + " rows and " + seatCols + " columns";
    }

    public int getSeats()
    {
        return seatCols*seatRows;
    }

    public void updateRoom(Room room)
    {
        this.name = room.getName();
        this.seatRows = room.getSeatRows();
        this.seatCols = room.getSeatCols();
    }
}
