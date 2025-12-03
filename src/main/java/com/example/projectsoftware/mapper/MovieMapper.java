package com.example.projectsoftware.mapper;

import org.mapstruct.Mapper;
import com.example.projectsoftware.dto.MovieDto;
import com.example.projectsoftware.model.Movie;

import java.util.List;

@Mapper(componentModel = "spring", uses = {GenreMapper.class, DirectorMapper.class})
public interface MovieMapper {

    MovieDto toDto(Movie movie);
    Movie toEntity(MovieDto movieDto);
    List<MovieDto> toDtoList(List<Movie> movieEntityList);
}
