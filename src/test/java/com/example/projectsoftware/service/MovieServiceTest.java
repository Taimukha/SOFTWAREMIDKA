package com.example.projectsoftware.service;

import com.example.projectsoftware.dto.MovieDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

@SpringBootTest
public class MovieServiceTest {

    @Autowired
    private MovieService movieService;

    @Test
    void getAllTest() {
        List<MovieDto> movies = movieService.getAll();
        Assertions.assertNotNull(movies);
        Assertions.assertNotEquals(0, movies.size());

        for (MovieDto movie : movies) {
            Assertions.assertNotNull(movie);
            Assertions.assertNotNull(movie.getId());
            Assertions.assertNotNull(movie.getTitle());
            Assertions.assertNotNull(movie.getYear());
            Assertions.assertNotNull(movie.getRating());
        }
    }

    @Test
    void addMovieTest() {
        MovieDto movieDto = new MovieDto();
        movieDto.setTitle("Test Movie");
        movieDto.setYear(2025);
        movieDto.setRating(9.0);

        MovieDto createdMovie = movieService.addMovie(movieDto);

        Assertions.assertNotNull(createdMovie);
        Assertions.assertNotNull(createdMovie.getId());
        Assertions.assertEquals(movieDto.getTitle(), createdMovie.getTitle());
        Assertions.assertEquals(movieDto.getYear(), createdMovie.getYear());
        Assertions.assertEquals(movieDto.getRating(), createdMovie.getRating());
    }

    @Test
    void updateMovieTest() {
        List<MovieDto> movies = movieService.getAll();
        Random random = new Random();
        MovieDto movieToUpdate = movies.get(random.nextInt(movies.size()));

        MovieDto updateDto = new MovieDto();
        updateDto.setTitle("Updated Movie");
        updateDto.setYear(2030);
        updateDto.setRating(10.0);

        MovieDto updatedMovie = movieService.updateMovie(movieToUpdate.getId(), updateDto);

        Assertions.assertNotNull(updatedMovie);
        Assertions.assertEquals(movieToUpdate.getId(), updatedMovie.getId());
        Assertions.assertEquals("Updated Movie", updatedMovie.getTitle());
        Assertions.assertEquals(2030, updatedMovie.getYear());
        Assertions.assertEquals(10.0, updatedMovie.getRating());
    }

    @Test
    void deleteMovieTest() {
        List<MovieDto> movies = movieService.getAll();
        Random random = new Random();
        MovieDto movieToDelete = movies.get(random.nextInt(movies.size()));

        movieService.deleteMovie(movieToDelete.getId());

        MovieDto deletedMovie = movieService.getAll().stream()
                .filter(m -> m.getId().equals(movieToDelete.getId()))
                .findFirst()
                .orElse(null);

        Assertions.assertNull(deletedMovie);
    }
}