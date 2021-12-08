package com.epam.training.ticketservice.core.screening.model;

import com.epam.training.ticketservice.core.movie.persistence.entity.Movie;
import com.epam.training.ticketservice.core.room.persistence.entity.Room;
import com.epam.training.ticketservice.core.screening.persistence.entity.Screening;
import com.epam.training.ticketservice.core.user.model.UserDto;
import com.epam.training.ticketservice.core.user.persistence.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScreeningDtoTest
{
    private final Movie movie = Movie.of("Movie","comedy",120);
    private final Room room = Room.of("Terem01",10,20);
    private final ScreeningDto screeningDto = ScreeningDto.builder().movie(movie).room(room).date("2001-10-02 17:00").build();
    private final ScreeningDto screeningDto2 = ScreeningDto.builder().movie(movie).room(room).date("2001-10-02 17:00").build();


    @Test
    void testEqualsShouldBeEqual()
    {
        //Given - When
        boolean actual = screeningDto.equals(screeningDto2);

        //Then
        Assertions.assertEquals(true,actual);
    }

    @Test
    void testHashCodeShouldBeEqual()
    {
        //Given - When - Then
        Assertions.assertEquals(screeningDto.hashCode(),screeningDto2.hashCode());

    }

    @Test
    void testHashCodeShouldNotBeEqual()
    {
        //Given
        ScreeningDto screeningDtoModified = ScreeningDto.builder().movie(movie).room(room).date("2001-10-10 19:00").build();

        //Given - When - Then
        Assertions.assertNotEquals(screeningDtoModified.hashCode(),screeningDto.hashCode());

    }

    @Test
    void testToStringShouldBeCorrect()
    {
        //Given
        String expected = "Movie (comedy, 120 minutes), screened in room Terem01, at 2001-10-02 17:00";

        //When
        String actual = screeningDto.toString();

        //Then
        Assertions.assertEquals(expected,actual);

    }
}