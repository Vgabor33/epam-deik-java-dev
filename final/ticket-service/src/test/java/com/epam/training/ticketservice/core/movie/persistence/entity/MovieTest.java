package com.epam.training.ticketservice.core.movie.persistence.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieTest {

    private final Movie movie = Movie.of("Movie","comedy",120);

    @Test
    void testUpdateMovieShouldUpdateMovie()
    {
        //Given
        Movie expected = Movie.of("Movie","comedy",200);

        //When
        movie.updateMovie(expected);
        Movie actual = movie;

        //Then
        Assertions.assertEquals(expected,actual);

    }

    @Test
    void testEqualsShouldBeEqual()
    {
        //Given
        Movie subject1 = Movie.of("Subject","horror",100);
        Movie subject2 = Movie.of("Subject","horror",100);

        //When
        boolean actual = subject1.equals(subject2);

        //Then
        Assertions.assertEquals(true,actual);
    }

    @Test
    void testHashCodeShouldBeEqual()
    {
        //Given
        Movie subject1 = Movie.of("Subject","horror",100);
        Movie subject2 = Movie.of("Subject","horror",100);

        //When - Then
        Assertions.assertEquals(subject1.hashCode(),subject2.hashCode());

    }

    @Test
    void testToStringShouldBeCorrect()
    {
        //Given
        String expected = "Movie (comedy, 120 minutes)";
        Movie subject1 = Movie.of("Movie","comedy",120);

        //When
        String actual = subject1.toString();

        //Then
        Assertions.assertEquals(expected,actual);

    }

    @Test
    void testOfShouldBeTheSame()
    {
        //Given
        Movie expected = new Movie("Movie","comedy",120);

        //When
        Movie actual = Movie.of("Movie","comedy",120);

        //Then
        Assertions.assertEquals(expected,actual);
    }

    @Test
    void testOfShouldNotBeTheSame()
    {
        //Given
        Movie expected = new Movie("Movie","comedy",120);

        //When
        Movie actual = Movie.of("Movie2","comedy",120);

        //Then
        Assertions.assertNotEquals(expected,actual);
    }
}