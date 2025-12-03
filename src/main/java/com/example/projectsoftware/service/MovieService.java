package com.example.projectsoftware.service;

import com.example.projectsoftware.dto.MovieDto;

import java.util.List;

public interface MovieService {

    List<MovieDto> getAll();
    MovieDto addMovie(MovieDto movieDto);
    MovieDto updateMovie(Long id, MovieDto movieDto);
    void deleteMovie(Long id);
}