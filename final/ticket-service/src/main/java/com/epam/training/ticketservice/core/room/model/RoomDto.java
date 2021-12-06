package com.epam.training.ticketservice.core.room.model;

import com.epam.training.ticketservice.core.movie.model.MovieDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Builder
@Getter
@AllArgsConstructor
public class RoomDto
{
    private final String name;
    private final int seatRows;
    private final int seatCols;


    @Override
    public int hashCode() {
        return Objects.hash(name, seatCols, seatRows);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        RoomDto roomDto = (RoomDto) o;
        return Objects.equals(name, roomDto.name) && Integer.compare(seatCols,roomDto.seatCols) == 0 && Integer.compare(seatRows, roomDto.seatRows) == 0;
    }

    @Override
    public String toString() {return "Room "+ name +" with " + getSeats() +" seats, "+seatRows + " rows and " + seatCols + " columns";}

    private int getSeats() {
        return seatCols*seatRows;
    }
}
