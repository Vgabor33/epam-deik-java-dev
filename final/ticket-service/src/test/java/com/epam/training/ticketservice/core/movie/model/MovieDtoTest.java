package com.epam.training.ticketservice.core.movie.model;

import com.epam.training.ticketservice.core.movie.persistence.entity.Movie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieDtoTest {
    private final MovieDto movieDto = new MovieDto("Movie","comedy",120);
    private final MovieDto movieDto2 = new MovieDto("Movie","comedy",120);

    @Test
    void testEqualsShouldBeEqual()
    {
        //Given - When
        boolean actual = movieDto.equals(movieDto2);

        //Then
        Assertions.assertEquals(true,actual);
    }

    @Test
    void testHashCodeShouldBeEqual()
    {
        //Given - When - Then
        Assertions.assertEquals(movieDto.hashCode(),movieDto2.hashCode());

    }

    @Test
    void testToStringShouldBeCorrect()
    {
        //Given
        String expected = "Movie (comedy, 120 minutes)";

        //When
        String actual = movieDto.toString();

        //Then
        Assertions.assertEquals(expected,actual);
    }
}