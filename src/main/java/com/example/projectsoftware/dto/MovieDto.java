package com.example.projectsoftware.dto;

import com.example.projectsoftware.model.Director;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDto {
    private Long id;
    private String title;
    private int year;
    private Double rating;
    private DirectorDto director;
    private List<GenreDto> genres;
}
