package com.epam.training.ticketservice.core.screening.persistence.entity;

import com.epam.training.ticketservice.core.movie.persistence.entity.Movie;
import com.epam.training.ticketservice.core.room.persistence.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Screening
{
    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    private Movie movie;
    @ManyToOne
    private Room room;
    private String date;

    public Screening(Movie movie, Room room, String date)
    {
        this.movie = movie;
        this.room = room;
        this.date = date;
    }

    public static Screening of(Movie movie, Room room, String date)
    {
        return new Screening(movie,room,date);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Screening screening = (Screening) o;
        return movie.equals(screening.movie) && Integer.compare(id,screening.id) == 0 && room.equals(screening.room) && date.equals(screening.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash( id,movie,room,date);
    }

    @Override
    public String toString() {
        return movie.getName() + " (" + movie.getGenre() + ", " + movie.getMovieLength() + " minutes), screened in room " + room.getName() + ", at " + this.date;
    }

    public void updateScreening(Screening screening)
    {
        this.movie = screening.getMovie();
        this.room = screening.getRoom();
        this.date = screening.getDate();
    }
}
