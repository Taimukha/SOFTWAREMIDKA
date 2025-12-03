package com.example.projectsoftware.mapper;

import com.example.projectsoftware.dto.DirectorDto;
import com.example.projectsoftware.model.Director;
import org.mapstruct.Mapper;


import java.util.List;

@Mapper(componentModel = "spring")
public interface DirectorMapper {

    DirectorDto toDto(Director director);

    Director toEntity(DirectorDto directorDTO);

    List<DirectorDto> toDtoList(List<Director> directors);

    List<Director> toEntityList(List<DirectorDto> dtos);
}

