package com.epam.training.ticketservice.core.movie.impl;

import com.epam.training.ticketservice.core.movie.MovieService;
import com.epam.training.ticketservice.core.movie.model.MovieDto;
import com.epam.training.ticketservice.core.movie.persistence.entity.Movie;
import com.epam.training.ticketservice.core.movie.persistence.repository.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Currency;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Override
    public List<MovieDto> getMovieList() {
        return movieRepository.findAll().stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    @Override
    public Optional<MovieDto> getMovieByName(String productName) {
        return convertEntityToDto(movieRepository.findByName(productName));
    }

    @Override
    public void createMovie(MovieDto movieDto) {
        Objects.requireNonNull(movieDto, "Movie cannot be null");
        Objects.requireNonNull(movieDto.getName(), "Movie name cannot be null");
        Objects.requireNonNull(movieDto.getGenre(), "Movie genre cannot be null");
        Objects.requireNonNull(movieDto.getMovieLength(), "Movie length cannot be null");
        Movie product = new Movie(movieDto.getName(),
                movieDto.getGenre(),
                movieDto.getMovieLength());
        movieRepository.save(product);
    }

    private MovieDto convertEntityToDto(Movie movie) {
        return MovieDto.builder().name(movie.getName()).genre(movie.getName()).movieLength(movie.getMovieLength()).build();
    }

    private Optional<MovieDto> convertEntityToDto(Optional<Movie> product) {
        return product.isEmpty() ? Optional.empty() : Optional.of(convertEntityToDto(product.get()));
    }
}
