package com.example.projectsoftware.service.impl;

import com.example.projectsoftware.dto.GenreDto;
import com.example.projectsoftware.mapper.GenreMapper;
import com.example.projectsoftware.model.Genre;
import com.example.projectsoftware.repository.GenreRepository;
import com.example.projectsoftware.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    @Override
    public List<GenreDto> getAll() {
        return genreMapper.toDtoList(genreRepository.findAll());
    }

    @Override
    public GenreDto getById(Long id) {
        return genreRepository.findById(id)
                .map(genreMapper::toDto)
                .orElse(null);
    }

    @Override
    public GenreDto addGenre(GenreDto genreDto) {
        Genre genreEntity = genreMapper.toEntity(genreDto);
        Genre saved = genreRepository.save(genreEntity);
        return genreMapper.toDto(saved);
    }

    @Override
    public GenreDto updateGenre(Long id, GenreDto genreDto) {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Genre not found with id: " + id));

        genre.setName(genreDto.getName());
        Genre updated = genreRepository.save(genre);

        return genreMapper.toDto(updated);
    }

    @Override
    public boolean deleteGenre(Long id) {
        if (!genreRepository.existsById(id)) return false;
        genreRepository.deleteById(id);
        return true;
    }
}