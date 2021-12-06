package com.epam.training.ticketservice.ui.command;

import com.epam.training.ticketservice.core.cinema.Cinema;
import com.epam.training.ticketservice.core.movie.persistence.entity.Movie;
import com.epam.training.ticketservice.core.user.UserService;
import com.epam.training.ticketservice.core.user.model.UserDto;
import com.epam.training.ticketservice.core.user.persistence.entity.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@NoArgsConstructor
@AllArgsConstructor
@ShellComponent
public class MovieCommand
{
    private Movie movie;
    @Autowired
    private UserService userService;

    @ShellMethod(key = "create movie", value = "Add movie")
    public String addMovie(String movieName, String genre, int movieLength)
    {
        if(isAvailable().isAvailable()) {
            String resultString = movieName + " is added!";
            Movie toBeAdded = Movie.of(movieName, genre, movieLength);
            if (Cinema.getInstance().getMovieList().contains(toBeAdded)) {
                resultString = movieName + "is already in the MovieList!";
            } else {
                Cinema.getInstance().addMovie(toBeAdded);
            }
            return resultString;
        }
        else
        {
            return isAvailable().getReason();
        }
    }

    @ShellMethod(key = "update movie", value = "Update an existing movie")
    public String updateMovie(String movieName, String genre, int movieLength)
    {
        if(isAvailable().isAvailable()) {
            String resultString = movieName + " is modified!";
            Movie toReplaceWith = Movie.of(movieName, genre, movieLength);
                Cinema.getInstance().updateMovieByName(movieName, toReplaceWith);
                return toReplaceWith.toString();
        }
        return isAvailable().getReason();
    }


    @ShellMethod(key = "delete movie", value = "Delete an existing movie")
    public String deleteMovie(String movieName)
    {
        if(isAvailable().isAvailable()) {
            String resultString = movieName + " is deleted!";
            Cinema.getInstance().deleteMovieByName(movieName);
        }
        return isAvailable().getReason();
    }
    @ShellMethod(key = "list movies", value = "Lists the existing movie(s)")
    public String listMovie()
    {
            if(Cinema.getInstance().getMovieList().size() == 0)
            {
                return "There are no movies at the moment";
            }
            StringBuilder result = new StringBuilder();
        for (Movie line:Cinema.getInstance().getMovieList()) {
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
