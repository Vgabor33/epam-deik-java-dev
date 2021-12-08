package com.epam.training.ticketservice.core.room.persistence.entity;

import com.epam.training.ticketservice.core.movie.persistence.entity.Movie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {

    private final Room room = Room.of("Terem01",10,20);

    @Test
    void testUpdateMovieShouldUpdateMovie()
    {
        //Given
        Room expected = Room.of("Terem01",10,30);

        //When
        room.updateRoom(expected);
        Room actual = room;

        //Then
        Assertions.assertEquals(expected,actual);

    }

    @Test
    void testEqualsShouldBeEqual()
    {
        //Given
        Room subject1 = Room.of("R1",10,10);
        Room subject2 = Room.of("R1",10,10);

        //When
        boolean actual = subject1.equals(subject2);

        //Then
        Assertions.assertEquals(true,actual);
    }

    @Test
    void testHashCodeShouldBeEqual()
    {
        //Given
        Room subject1 = Room.of("R1",10,10);
        Room subject2 = Room.of("R1",10,10);

        //When - Then
        Assertions.assertEquals(subject1.hashCode(),subject2.hashCode());

    }

    @Test
    void testToStringShouldBeCorrect()
    {
        //Given
        String expected = "Room R1 with 200 seats, 20 rows and 10 columns";
        Room subject1 = Room.of("R1",20,10);

        //When
        String actual = subject1.toString();

        //Then
        Assertions.assertEquals(expected,actual);

    }

    @Test
    void testOfShouldBeTheSame()
    {
        //Given
        Room expected = new Room("Terem01",10,20);

        //When
        Room actual = Room.of("Terem01",10,20);

        //Then
        Assertions.assertEquals(expected,actual);
    }

    @Test
    void testOfShouldNotBeTheSame()
    {
        //Given
        Room expected = new Room("Terem01",10,20);

        //When
        Room actual = Room.of("Terem02",10,20);

        //Then
        Assertions.assertNotEquals(expected,actual);
    }
}