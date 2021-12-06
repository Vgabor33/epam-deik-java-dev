package com.epam.training.ticketservice.core.screening;

import com.epam.training.ticketservice.core.room.model.RoomDto;
import com.epam.training.ticketservice.core.screening.model.ScreeningDto;

import java.util.List;
import java.util.Optional;

public interface ScreeningService
{
    List<ScreeningDto> getScreeningList();

    Optional<ScreeningDto> getScreeningById(int id);

    void createScreening(ScreeningDto screening);
}
