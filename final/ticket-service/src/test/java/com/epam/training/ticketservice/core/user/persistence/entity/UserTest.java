package com.epam.training.ticketservice.core.user.persistence.entity;

import com.epam.training.ticketservice.core.room.persistence.entity.Room;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private final User user = new User("user","userpass", User.Role.ADMIN);
    private final User user2 = new User("user","userpass", User.Role.ADMIN);

    @Test
    void testEqualsShouldBeEqual()
    {

        //Given - When
        boolean actual = user.equals(user2);

        //Then
        Assertions.assertEquals(true,actual);
    }

    @Test
    void testHashCodeShouldBeEqual()
    {
        //Given - When - Then
        Assertions.assertEquals(user.hashCode(),user2.hashCode());

    }

    @Test
    void testToStringShouldBeCorrect()
    {
        //Given
        String expected = "User{id=null, username='user', password='userpass', role=ADMIN}";

        //When
        String actual = user.toString();

        //Then
        Assertions.assertEquals(expected,actual);
    }
}