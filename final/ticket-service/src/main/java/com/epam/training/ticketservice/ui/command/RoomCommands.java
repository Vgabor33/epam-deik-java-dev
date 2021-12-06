package com.epam.training.ticketservice.ui.command;

import com.epam.training.ticketservice.core.cinema.Cinema;
import com.epam.training.ticketservice.core.room.persistence.entity.Room;
import com.epam.training.ticketservice.core.user.UserService;
import com.epam.training.ticketservice.core.user.model.UserDto;
import com.epam.training.ticketservice.core.user.persistence.entity.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@ShellComponent
public class RoomCommands
{
    private Room room;
    @Autowired
    private UserService userService;

    @ShellMethod(key = "create room", value = "Add room")
    public String addRoom(String roomName, int seatRows, int seatCols)
    {
        if(isAvailable().isAvailable()) {
            String resultString = roomName + " is added!";
            Room toBeAdded = Room.of(roomName, seatRows, seatCols);
            if (Cinema.getInstance().getRoomList().contains(toBeAdded)) {
                resultString = roomName + "is already in the RoomList!";
            } else {
                Cinema.getInstance().addRoom(toBeAdded);
            }
            return resultString;
        }
        else
        {
            return isAvailable().getReason();
        }
    }

    @ShellMethod(key = "update room", value = "Updates an existing room")
    public String updateRoom(String roomName, int seatRows, int seatCols)
    {
        if(isAvailable().isAvailable()) {
            String resultString = roomName + " is modified!";
            Room toReplaceWith = Room.of(roomName, seatRows, seatCols);
            Cinema.getInstance().updateRoomByName(roomName, toReplaceWith);
            return toReplaceWith.toString();
        }
        return isAvailable().getReason();
    }


    @ShellMethod(key = "delete room", value = "Deletes an existing room")
    public String deleteRoom(String roomName)
    {
        if(isAvailable().isAvailable()) {
            String resultString = roomName + " is deleted!";
            Cinema.getInstance().deleteRoomByName(roomName);
        }
        return isAvailable().getReason();
    }

    @ShellMethod(key = "list rooms", value = "Lists the existing room(s)")
    public String listRooms()
    {
        if(Cinema.getInstance().getRoomList().size() == 0)
        {
            return "There are no rooms at the moment";
        }
        StringBuilder result = new StringBuilder();
        for (Room line:Cinema.getInstance().getRoomList()) {
            if(result.toString().equals(""))
            {
                result.append(line.toString());
            }
            else {
                result.append("\n").append(line.toString());
            }
        }
        return result.toString();
    }

    private Availability isAvailable() {
        try {
            Optional<UserDto> user = userService.getLoggedInUser();
            if (user.isPresent() && user.get().getRole() == User.Role.ADMIN) {
                return Availability.available();
            }
            return Availability.unavailable("You are not an admin!");
        }
        catch (Exception e)
        {
            return Availability.unavailable("You are not signed in");
        }
    }
}
