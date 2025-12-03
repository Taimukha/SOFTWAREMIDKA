package com.example.projectsoftware.mapper;

import com.example.projectsoftware.dto.GenreDto;
import com.example.projectsoftware.model.Genre;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    GenreDto toDto(Genre genre);

    Genre toEntity(GenreDto genreDto);

    List<GenreDto> toDtoList(List<Genre> genreEntityList);
}


