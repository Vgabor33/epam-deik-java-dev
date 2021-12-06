package com.epam.training.ticketservice.core.room.impl;

import com.epam.training.ticketservice.core.room.RoomService;
import com.epam.training.ticketservice.core.room.model.RoomDto;
import com.epam.training.ticketservice.core.room.persistence.entity.Room;
import com.epam.training.ticketservice.core.room.persistence.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class RoomServiceImpl implements RoomService
{
    private final RoomRepository roomRepository;

    @Override
    public List<RoomDto> getRoomList() {
        return roomRepository.findAll().stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    @Override
    public Optional<RoomDto> getRoomByName(String roomName) {
        return convertEntityToDto(roomRepository.findByName(roomName));
    }

    @Override
    public void createRoom(RoomDto roomDto) {
        Objects.requireNonNull(roomDto, "Room cannot be null");
        Objects.requireNonNull(roomDto.getName(), "Room name cannot be null");
        Objects.requireNonNull(roomDto.getSeatCols(), "Number of seat columns cannot be null");
        Objects.requireNonNull(roomDto.getSeatRows(), "Number of seat rows cannot be null");
        Room room = new Room(roomDto.getName(),
                roomDto.getSeatRows(),
                roomDto.getSeatCols());
        roomRepository.save(room);
    }

    private RoomDto convertEntityToDto(Room room) {
        return RoomDto.builder().name(room.getName()).seatRows(room.getSeatRows()).seatCols(room.getSeatCols()).build();
    }

    private Optional<RoomDto> convertEntityToDto(Optional<Room> room) {
        return room.isEmpty() ? Optional.empty() : Optional.of(convertEntityToDto(room.get()));
    }
}
