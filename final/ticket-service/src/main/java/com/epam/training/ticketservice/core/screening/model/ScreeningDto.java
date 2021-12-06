package com.epam.training.ticketservice.core.screening.model;

import com.epam.training.ticketservice.core.movie.persistence.entity.Movie;
import com.epam.training.ticketservice.core.room.persistence.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Builder
@Getter
@AllArgsConstructor
public class ScreeningDto
{
    private final Movie movie;
    private final Room room;
    private final String date;


    @Override
    public int hashCode() {
        return Objects.hash(movie,room,date);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ScreeningDto screeningDto = (ScreeningDto) o;
        return Objects.equals(movie, screeningDto.movie) && Objects.equals(room, screeningDto.room) && Objects.equals(date,screeningDto.date);
    }

    @Override
    public String toString() {return movie.getName() + " (" + movie.getGenre() + ", " + movie.getMovieLength() + " minutes), screened in room " + room.getName() + ", at " + date; }
}
