package com.epam.training.ticketservice.core.room.impl;

import com.epam.training.ticketservice.core.room.model.RoomDto;
import com.epam.training.ticketservice.core.room.persistence.repository.RoomRepository;
import com.epam.training.ticketservice.core.user.impl.UserServiceImpl;
import com.epam.training.ticketservice.core.user.persistence.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class RoomServiceImplTest {

    private final RoomRepository roomRepository = mock(RoomRepository.class);
    private final RoomServiceImpl roomService = new RoomServiceImpl(roomRepository);
    private final RoomDto roomDto = new RoomDto("R1",10,20);

    @Test
    void testGetRoomByNameShouldReturnNull()
    {
        //Given- When - Then
        assertThrows(NoSuchElementException.class,() ->roomService.getRoomByName(roomDto.getName()).get());
    }
}