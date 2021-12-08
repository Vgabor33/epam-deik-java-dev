package com.epam.training.ticketservice.core.room.model;

import com.epam.training.ticketservice.core.room.persistence.entity.Room;
import com.epam.training.ticketservice.core.screening.model.ScreeningDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomDtoTest {

    private final RoomDto roomDto = new RoomDto("Terem01",10,20);
    private final RoomDto roomDto2 = new RoomDto("Terem01",10,20);


    @Test
    void testEqualsShouldBeEqual()
    {
        //Given - When
        boolean actual = roomDto.equals(roomDto2);

        //Then
        Assertions.assertEquals(true,actual);
    }

    @Test
    void testHashCodeShouldBeEqual()
    {
        //Given - When - Then
        Assertions.assertEquals(roomDto.hashCode(),roomDto2.hashCode());

    }

    @Test
    void testHashCodeShouldNotBeEqual()
    {
        //Given
        RoomDto roomDtoModified = new RoomDto("Terem09",10,20);

        //Given - When - Then
        Assertions.assertNotEquals(roomDtoModified.hashCode(),roomDto.hashCode());

    }

    @Test
    void testToStringShouldBeCorrect()
    {
        //Given
        String expected = "Room Terem01 with 200 seats, 10 rows and 20 columns";

        //When
        String actual = roomDto.toString();

        //Then
        Assertions.assertEquals(expected,actual);

    }
}