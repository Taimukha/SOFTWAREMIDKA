package com.example.projectsoftware.service;

import com.example.projectsoftware.dto.GenreDto;
import java.util.List;

public interface GenreService {
    List<GenreDto> getAll();
    GenreDto getById(Long id);
    GenreDto addGenre(GenreDto genreDto);
    GenreDto updateGenre(Long id, GenreDto dto);
    boolean deleteGenre(Long id);
}