package com.epam.training.ticketservice.core.screening.impl;

import com.epam.training.ticketservice.core.movie.impl.MovieServiceImpl;
import com.epam.training.ticketservice.core.movie.model.MovieDto;
import com.epam.training.ticketservice.core.movie.persistence.entity.Movie;
import com.epam.training.ticketservice.core.movie.persistence.repository.MovieRepository;
import com.epam.training.ticketservice.core.room.persistence.entity.Room;
import com.epam.training.ticketservice.core.screening.model.ScreeningDto;
import com.epam.training.ticketservice.core.screening.persistence.entity.Screening;
import com.epam.training.ticketservice.core.screening.persistence.repository.ScreeningRepository;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ScreeningImplTest {

    private final ScreeningRepository screeningRepository = mock(ScreeningRepository.class);
    private final ScreeningImpl roomService = new ScreeningImpl(screeningRepository);
    private final Movie movie = Movie.of("Movie","comedy",120);
    private final Room room = Room.of("Terem01",10,20);
    private final ScreeningDto screeningDto = new ScreeningDto(movie,room,"2000-01-01 12:00");

    @Test
    void testGetRoomByIdReturnNull()
    {
        //Given- When - Then
        assertThrows(NoSuchElementException.class,() ->roomService.getScreeningById(1).get());
    }
}