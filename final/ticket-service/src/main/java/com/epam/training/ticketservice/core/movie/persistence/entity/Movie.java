package com.epam.training.ticketservice.core.movie.persistence.entity;

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
public class Movie
{
    @Id
    @Column(unique = true)
    private String name;
    private String genre;
    private int movieLength;


    public static Movie of(String name, String genre, int movieLength)
    {
        return new Movie(name, genre, movieLength);
    }

    public void updateMovie(Movie movie)
    {
        this.name = movie.getName();
        this.genre = movie.getGenre();
        this.movieLength = movie.getMovieLength();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return name.equals(movie.name) && genre.equals(movie.genre) && Integer.compare(movieLength, movie.movieLength) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash( name, genre, movieLength);
    }

    @Override
    public String toString() {
        return name+" (" + genre +", "+movieLength + " minutes)";
    }
}