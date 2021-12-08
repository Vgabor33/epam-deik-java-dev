package com.epam.training.ticketservice.core.cinema;

import com.epam.training.ticketservice.core.movie.persistence.entity.Movie;
import com.epam.training.ticketservice.core.room.persistence.entity.Room;
import com.epam.training.ticketservice.core.screening.persistence.entity.Screening;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CinemaTest {

    private final Movie movie = Movie.of("Movie","comedy",120);
    private final Room room = Room.of("TEREM01",10,30);
    private final Screening screening = Screening.of(movie,room,"2020-01-01 15:00");

    @Test
    void testFindMovieByNameShouldFindMovie()
    {
        //Given
        Cinema.getInstance().addMovie(movie);
        Movie expected = Movie.of("Movie","comedy",120);

        //When
        Movie actual = Cinema.getInstance().findMovieByName("Movie");

        //Then
        Assertions.assertEquals(expected,actual);

    }

    @Test
    void testFindMovieByNameShouldNotFindMovie()
    {
        //Given
        Movie nonexistent = Movie.of("Movie","comedy",120);

        //When
        Movie actual = Cinema.getInstance().findMovieByName("Movie");

        //Then
        Assertions.assertNotEquals(nonexistent,actual);

    }


    @Test
    void testUpdateMovieByNameShouldUpdateMovie()
    {
        //Given
        Cinema.getInstance().addMovie(Movie.of("Movie2","comedy",120));
        Movie expected = Movie.of("Movie2","comedy",200);

        //When
        Cinema.getInstance().updateMovieByName(expected.getName(),Movie.of(expected.getName(),expected.getGenre(),200));
        Movie actual = Cinema.getInstance().findMovieByName(expected.getName());

        //Then
        Assertions.assertEquals(expected,actual);

    }

    @Test
    void testUpdateMovieByNameShouldNotUpdateMovieWhenMovieDidNotExist()
    {
        //Given - When - Then
        assertThrows(NullPointerException.class,() -> Cinema.getInstance().updateMovieByName(movie.getName(),Movie.of(movie.getName(),movie.getGenre(),250)));

    }

    @Test
    void testFindRoomByNameShouldFindRoom()
    {
        //Given
        Cinema.getInstance().addRoom(Room.of("TEREM02",10,30));
        Room expected =  Room.of("TEREM02",10,30);

        //When
        Room actual = Cinema.getInstance().findRoomByName(expected.getName());

        //Then
        Assertions.assertEquals(expected,actual);
    }

    @Test
    void testFindRoomByNameShouldNotFindMovie()
    {
        //Given
        Room nonexistent = Room.of("R10",10,20);

        //When
        Room actual = Cinema.getInstance().findRoomByName(nonexistent.getName());

        //Then
        Assertions.assertNotEquals(nonexistent,actual);

    }

    @Test
    void testUpdateRoomByNameShouldUpdateRoom()
    {
        //Given
        Cinema.getInstance().addRoom(room);
        Room expected = Room.of("TEREM01",10,20);

        //When
        Cinema.getInstance().updateRoomByName(room.getName(),Room.of("TEREM01",10,20));
        Room actual = Cinema.getInstance().findRoomByName(room.getName());

        //Then
        Assertions.assertEquals(expected,actual);
    }

    @Test
    void testUpdateRoomByNameShouldNotUpdateMovieWhenMovieDidNotExist()
    {
        //Given - When - Then
        assertThrows(NullPointerException.class,() -> Cinema.getInstance().updateRoomByName(room.getName(),Room.of(room.getName(),100,20)));

    }

    @Test
    void testOverlappingScreeningFilterShouldReturnOverlap()
    {
        //Given
        Cinema.getInstance().addScreening(screening);
        String expected = "There is an overlapping screening";

        //When
        String actual = Cinema.getInstance().OverlappingScreeningFilter(screening.getRoom(),screening.getDate());

        //Then
        Assertions.assertEquals(expected,actual);
    }

    @Test
    void testOverlappingScreeningFilterShouldReturnBreakOverlap()
    {
        //Given
        Cinema.getInstance().addScreening(screening);
        String expected = "This would start in the break period after another screening in this room";

        //When
        String actual = Cinema.getInstance().OverlappingScreeningFilter(screening.getRoom(),"2020-01-01 17:00");

        //Then
        Assertions.assertEquals(expected,actual);
    }

    @Test
    void testOverlappingScreeningFilterShouldReturnNull()
    {
        //Given
        Cinema.getInstance().addScreening(screening);

        //When
        String actual = Cinema.getInstance().OverlappingScreeningFilter(
                Room.of("TEREM02",10,30)
                ,"2020-01-01 17:00");

        //Then
        assertNull(actual);
    }

    @Test
    void testFindScreeningShouldFindScreening()
    {
        //Given
        Cinema.getInstance().addScreening(screening);
        Screening expected =  screening;

        //When
        Screening actual = Cinema.getInstance().findScreening(screening.getMovie(),screening.getRoom(),screening.getDate());

        //Then
        Assertions.assertEquals(expected,actual);
    }

    @Test
    void testDeleteMovieByNameShouldDeleteMovie()
    {
        //Given
        Cinema.getInstance().addMovie(movie);
        Movie expected = null;

        //When
        Cinema.getInstance().deleteMovieByName(movie.getName());
        Movie actual = Cinema.getInstance().findMovieByName(movie.getName());

        //Then
        Assertions.assertEquals(expected,actual);
    }

    @Test
    void testDeleteRoomByNameShouldDeleteRoom()
    {
        //Given
        Cinema.getInstance().addRoom(Room.of("TEREM03",10,40));
        Room expected = null;

        //When
        Cinema.getInstance().deleteRoomByName("TEREM03");
        Room actual = Cinema.getInstance().findRoomByName("TEREM03");

        //Then
        Assertions.assertEquals(expected,actual);
    }

    @Test
    void testDeleteScreeningShouldDeleteScreening()
    {
        //Given
        Cinema.getInstance().addScreening(screening);
        Screening expected = null;

        //When
        Cinema.getInstance().deleteScreening(screening.getMovie(),screening.getRoom(),screening.getDate());
        Screening actual = Cinema.getInstance().findScreening(screening.getMovie(),screening.getRoom(),screening.getDate());

        //Then
        Assertions.assertEquals(expected,actual);
    }
}