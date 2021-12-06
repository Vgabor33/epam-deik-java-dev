package com.epam.training.ticketservice.core.movie.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Builder
@Getter
@AllArgsConstructor
public class MovieDto {
    private final String name;
    private final String genre;
    private final int movieLength;


    @Override
    public int hashCode() {
        return Objects.hash(name, genre, movieLength);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        MovieDto movieDto = (MovieDto) o;
        return Objects.equals(name, movieDto.name) && Objects.equals(genre, movieDto.genre) && Objects.equals(movieLength, movieDto.movieLength);
    }

    @Override
    public String toString() {
        return name + " (" + genre + ", " + movieLength + " minutes)";
    }

}
