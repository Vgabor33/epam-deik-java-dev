package com.epam.training.ticketservice.core.screening.persistence.entity;

import com.epam.training.ticketservice.core.movie.persistence.entity.Movie;
import com.epam.training.ticketservice.core.room.persistence.entity.Room;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScreeningTest
{
    private final Movie movie = Movie.of("Movie","comedy",120);
    private final Room room = Room.of("Terem01",10,20);
    private final Screening screening = Screening.of(movie,room,"2001-10-02 17:00");
    private final Screening screening2 = Screening.of(movie,room,"2001-10-02 17:00");

    @Test
    void testUpdateMovieShouldUpdateMovie()
    {
        //Given
        Screening expected = Screening.of(movie,room,"2000-01-01 10:00");

        //When
        screening.updateScreening(Screening.of(movie,room,"2000-01-01 10:00"));
        Screening actual = screening;

        //Then
        Assertions.assertEquals(expected,actual);

    }

    @Test
    void testEqualsShouldBeEqual()
    {
        //Given - When
        boolean actual = screening.equals(screening2);

        //Then
        Assertions.assertEquals(true,actual);
    }

    @Test
    void testHashCodeShouldBeEqual()
    {
        //Given - When - Then
        Assertions.assertEquals(screening.hashCode(),screening2.hashCode());

    }

    @Test
    void testToStringShouldBeCorrect()
    {
        //Given
        String expected = "Movie (comedy, 120 minutes), screened in room Terem01, at 2001-10-02 17:00";

        //When
        String actual = screening.toString();

        //Then
        Assertions.assertEquals(expected,actual);

    }

    @Test
    void testOfShouldBeTheSame()
    {
        //Given
        Screening expected = new Screening(movie,room,"2001-10-02 17:00");

        //When
        Screening actual = Screening.of(movie,room,"2001-10-02 17:00");

        //Then
        Assertions.assertEquals(expected,actual);
    }

    @Test
    void testOfShouldNotBeTheSame()
    {
        //Given
        Screening expected = new Screening(movie,room,"2001-10-02 17:00");

        //When
        Screening actual = Screening.of(movie,room,"2001-10-02 18:00");

        //Then
        Assertions.assertNotEquals(expected,actual);
    }
}