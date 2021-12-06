package com.epam.training.ticketservice.ui.command;

import com.epam.training.ticketservice.core.cinema.Cinema;
import com.epam.training.ticketservice.core.screening.persistence.entity.Screening;
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
public class ScreeningCommand
{
    private Screening screening;
    @Autowired
    private UserService userService;

    @ShellMethod(key = "create screening", value = "Add screening")
    public String addScreening(String movieName, String roomName, String date)
    {
        if(isAvailable().isAvailable()) {
            String resultString =  "New screening is added!";
            Screening toBeAdded = Screening.of(Cinema.getInstance().findMovieByName(movieName),Cinema.getInstance().findRoomByName(roomName),date);
            if (Cinema.getInstance().OverlappingScreeningFilter(Cinema.getInstance().findRoomByName(roomName),date) != null) {
                resultString = Cinema.getInstance().OverlappingScreeningFilter(Cinema.getInstance().findRoomByName(roomName),date);
            } else {
                Cinema.getInstance().addScreening(Screening.of(Cinema.getInstance().findMovieByName(movieName),Cinema.getInstance().findRoomByName(roomName),date));
            }
            return resultString;
        }
        else
        {
            return isAvailable().getReason();
        }
    }

    @ShellMethod(key = "list screenings", value = "Lists screening(s)")
    public String listScreening()
    {
        if(Cinema.getInstance().getScreeningList().size() == 0)
        {
            return "There are no screenings";
        }
        StringBuilder result = new StringBuilder();
        for (Screening line:Cinema.getInstance().getScreeningList()) {
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

    @ShellMethod(key = "delete screening", value = "Delete screening")
    public String deleteScreening(String movieName, String roomName, String date)
    {
        if(isAvailable().isAvailable()) {
            String resultString = "Screening deleted!";
            Cinema.getInstance().deleteScreening(Cinema.getInstance().findMovieByName(movieName), Cinema.getInstance().findRoomByName(roomName), date);
            return resultString;
        }
        return isAvailable().getReason();
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
