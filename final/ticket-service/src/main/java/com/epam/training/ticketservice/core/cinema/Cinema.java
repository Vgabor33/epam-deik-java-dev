package com.epam.training.ticketservice.core.cinema;

import com.epam.training.ticketservice.core.movie.persistence.entity.Movie;
import com.epam.training.ticketservice.core.room.persistence.entity.Room;
import com.epam.training.ticketservice.core.screening.persistence.entity.Screening;

import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Cinema
{
    private static Cinema cinema = null;
    private List<Room> roomList;
    private List<Movie> movieList;
    private List<Screening> screeningList;

    private Cinema()
    {
        this.roomList = new ArrayList<Room>();
        this.movieList = new ArrayList<Movie>();
        this.screeningList = new ArrayList<Screening>();
    }

    public static Cinema getInstance()
    {
        initializer();
        return cinema;
    }

    public List<Room> getRoomList()
    {
        initializer();
        return roomList;
    }

    public List<Movie> getMovieList()
    {
        initializer();
        return movieList;
    }

    public List<Screening> getScreeningList()
    {
        initializer();
        return screeningList;
    }

    private static void initializer()
    {
        if(cinema == null)
        {
            cinema = new Cinema();
        }
    }

    public void addRoom(Room room)
    {
        this.roomList.add(room);
    }

    public void addMovie(Movie movie)
    {
        this.movieList.add(movie);
    }

    public void addScreening(Screening screening) { this.screeningList.add(screening);}

    public Movie findMovieByName(String name)
    {
        if(Cinema.getInstance().getMovieList().stream().anyMatch(m -> m.getName().equals(name)))
        {
            return Cinema.getInstance().getMovieList().stream().filter(m -> m.getName().equals(name)).findFirst().get();
        }
        return null;
    }

    public void updateMovieByName(String name, Movie movie)
    {
        Cinema.getInstance().findMovieByName(name).updateMovie(movie);
    }

    public void deleteMovieByName(String name)
    {
        Cinema.getInstance().getMovieList().remove(findMovieByName(name));
    }

    public Room findRoomByName(String name)
    {
        if(Cinema.getInstance().getRoomList().stream().anyMatch(m -> m.getName().equals(name)))
        {
            return Cinema.getInstance().getRoomList().stream().filter(m -> m.getName().equals(name)).findFirst().get();
        }
        return null;
    }

    public void updateRoomByName(String roomName, Room toReplaceWith) {
        Cinema.getInstance().findRoomByName(roomName).updateRoom(toReplaceWith);
    }

    public void deleteRoomByName(String name)
    {
        Cinema.getInstance().getRoomList().remove(findRoomByName(name));
    }

    public String OverlappingScreeningFilter(Room room, String date)
    {
            for (Screening screening: this.screeningList)
            {
                try
                {
                Date planedScreening = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(screening.getDate());
                Date toBeAddedScreening = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(date);
                long diff = planedScreening.getTime() - toBeAddedScreening.getTime();
                long minutes = TimeUnit.MILLISECONDS.toMinutes(diff);
                long days = TimeUnit.MILLISECONDS.toDays(diff);
                if (screening.getRoom().equals(room) &&
                        planedScreening.getDay() == toBeAddedScreening.getDay() &&
                        minutes >
                        -(screening.getMovie().getMovieLength())) {
                    return "There is an overlapping screening";
                } else if (screening.getRoom().equals(room) &&
                        planedScreening.getDay() == toBeAddedScreening.getDay() &&
                        minutes >
                                -(screening.getMovie().getMovieLength()+10)) {
                    return "This would start in the break period after another screening in this room";
                }
            }
            catch (Exception ignored) {

        }
        }
        return null;
    }

    public Screening findScreening (Movie movie, Room room, String date)
    {
        if(Cinema.getInstance().getScreeningList().stream().anyMatch(m -> m.getMovie().equals(movie) && m.getRoom().equals(room) && m.getDate().equals(date)))
        {
            return Cinema.getInstance().getScreeningList().stream().filter(m -> m.getMovie().equals(movie) && m.getRoom().equals(room) && m.getDate().equals(date)).findFirst().get();
        }
        return null;
    }

    public void deleteScreening(Movie movie, Room room, String date)
    {
        Cinema.getInstance().getScreeningList().remove(findScreening(movie,room,date));
    }
}
