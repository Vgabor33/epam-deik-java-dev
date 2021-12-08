package com.epam.training.ticketservice.core.user.model;

import com.epam.training.ticketservice.core.user.persistence.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDtoTest {

    private final UserDto userDto = new UserDto("user", User.Role.ADMIN);
    private final UserDto userDto2 = new UserDto("user", User.Role.ADMIN);

    @Test
    void testEqualsShouldBeEqual()
    {

        //Given - When
        boolean actual = userDto.equals(userDto2);

        //Then
        Assertions.assertEquals(true,actual);
    }

    @Test
    void testHashCodeShouldBeEqual()
    {
        //Given - When - Then
        Assertions.assertEquals(userDto.hashCode(),userDto2.hashCode());

    }

    @Test
    void testHashCodeShouldNotBeEqual()
    {
        //Given
        UserDto userDtoModified = new UserDto("user01", User.Role.ADMIN);

        //Given - When - Then
        Assertions.assertNotEquals(userDtoModified.hashCode(),userDto2.hashCode());

    }

    @Test
    void testToStringShouldBeCorrect()
    {
        //Given
        String expected = "UserDto{username='user', role=ADMIN}";

        //When
        String actual = userDto.toString();

        //Then
        Assertions.assertEquals(expected,actual);
    }
}