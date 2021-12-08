package com.epam.training.ticketservice.core.movie.impl;

import com.epam.training.ticketservice.core.movie.model.MovieDto;
import com.epam.training.ticketservice.core.movie.persistence.repository.MovieRepository;
import com.epam.training.ticketservice.core.room.impl.RoomServiceImpl;
import com.epam.training.ticketservice.core.room.model.RoomDto;
import com.epam.training.ticketservice.core.room.persistence.repository.RoomRepository;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class MovieServiceImplTest {

    private final MovieRepository roomRepository = mock(MovieRepository.class);
    private final MovieServiceImpl roomService = new MovieServiceImpl(roomRepository);
    private final MovieDto movieDto = new MovieDto("Movie","comedy",120);

    @Test
    void testGetRoomByNameShouldReturnNull()
    {
        //Given- When - Then
        assertThrows(NoSuchElementException.class,() ->roomService.getMovieByName(movieDto.getName()).get());
    }
}