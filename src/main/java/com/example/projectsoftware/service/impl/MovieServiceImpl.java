package com.example.projectsoftware.service.impl;

import com.example.projectsoftware.dto.MovieDto;
import com.example.projectsoftware.mapper.MovieMapper;
import com.example.projectsoftware.model.Movie;
import com.example.projectsoftware.repository.MovieRepository;
import com.example.projectsoftware.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    @Override
    @Transactional(readOnly = true)
    public List<MovieDto> getAll() {
        List<Movie> movieEntityList = movieRepository.findAll();
        return movieMapper.toDtoList(movieEntityList);
    }

    @Override
    @Transactional
    public MovieDto addMovie(MovieDto movieDto){
        Movie movieEntity = movieMapper.toEntity(movieDto);
        Movie saved = movieRepository.save(movieEntity);
        return movieMapper.toDto(saved);
    }

    @Override
    @Transactional
    public MovieDto updateMovie(Long id, MovieDto movieDto){
        Movie updateMovie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found with id: " + id));

        updateMovie.setTitle(movieDto.getTitle());
        updateMovie.setYear(movieDto.getYear());
        updateMovie.setRating(movieDto.getRating());

        if (movieDto.getDirector() != null) {
            updateMovie.setDirector(movieMapper.toEntity(movieDto).getDirector());
        }
        if (movieDto.getGenres() != null) {
            updateMovie.setGenres(movieMapper.toEntity(movieDto).getGenres());
        }

        Movie saved = movieRepository.save(updateMovie);
        return movieMapper.toDto(saved);
    }

    @Override
    @Transactional
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }
}