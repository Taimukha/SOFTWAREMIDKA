package com.example.projectsoftware;
import com.example.projectsoftware.dto.MovieDto;
import com.example.projectsoftware.mapper.MovieMapper;
import com.example.projectsoftware.model.Director;
import com.example.projectsoftware.model.Genre;
import com.example.projectsoftware.model.Movie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class MovieMapperTest {

    @Autowired
    private MovieMapper movieMapper;

    @Test
    void convertEntityToDtoTest(){
        Director director = new Director(1L, "name");
        Genre genre = new Genre(1L, "horror");
        Movie entityMovie = new Movie(1L, "Qwerty", 2020, 4.5, director, List.of(genre));

        MovieDto dtoMovie = movieMapper.toDto(entityMovie);
        Assertions.assertNotNull(dtoMovie);

        Assertions.assertNotNull(dtoMovie.getId());
        Assertions.assertNotNull(dtoMovie.getTitle());
        Assertions.assertNotNull(dtoMovie.getYear());
        Assertions.assertNotNull(dtoMovie.getRating());
        Assertions.assertNotNull(dtoMovie.getDirector());
        Assertions.assertNotNull(dtoMovie.getGenres());

        Assertions.assertEquals(entityMovie.getId(), dtoMovie.getId());
        Assertions.assertEquals(entityMovie.getTitle(), dtoMovie.getTitle());
        Assertions.assertEquals(entityMovie.getYear(), dtoMovie.getYear());
        Assertions.assertEquals(entityMovie.getRating(), dtoMovie.getRating());
        Assertions.assertEquals(entityMovie.getDirector().getId(), dtoMovie.getDirector().getId());
        Assertions.assertEquals(entityMovie.getDirector().getName(), dtoMovie.getDirector().getName());

        Assertions.assertEquals(entityMovie.getGenres().size(), dtoMovie.getGenres().size());
        Assertions.assertEquals(entityMovie.getGenres().get(0).getId(), dtoMovie.getGenres().get(0).getId());
        Assertions.assertEquals(entityMovie.getGenres().get(0).getName(), dtoMovie.getGenres().get(0).getName());

    }

    @Test
    void ConvertDtoToEntityTest(){
        MovieDto dtoMovie = new MovieDto();
        dtoMovie.setId(1L);
        dtoMovie.setTitle("Qwerty");
        dtoMovie.setYear(2010);
        dtoMovie.setRating(4.5);

        Movie entityMovie = movieMapper.toEntity(dtoMovie);
        Assertions.assertNotNull(entityMovie);

        Assertions.assertNotNull(dtoMovie.getId());
        Assertions.assertNotNull(dtoMovie.getTitle());
        Assertions.assertNotNull(dtoMovie.getYear());
        Assertions.assertNotNull(dtoMovie.getRating());

        Assertions.assertEquals(entityMovie.getId(), dtoMovie.getId());
        Assertions.assertEquals(entityMovie.getTitle(), dtoMovie.getTitle());
        Assertions.assertEquals(entityMovie.getYear(), dtoMovie.getYear());
        Assertions.assertEquals(entityMovie.getRating(), dtoMovie.getRating());
    }

    @Test
    void convertEntityListToDtoList(){
        List<Movie> entityList = new ArrayList<>();
        entityList.add(new Movie(1L,"qwer", 2011, 4.5, new Director(1L, "name"),  List.of(new Genre(1L,"comedy"))));
        entityList.add(new Movie(2L,"rewq", 2015, 4.0, new Director(2L, "Miko"),  List.of(new Genre(2L,"horror"))));
        entityList.add(new Movie(3L,"qwerty", 2020, 3.8, new Director(3L, "NoName"),  List.of(new Genre(3L,"fantasy"))));

        List<MovieDto> dtoList = movieMapper.toDtoList(entityList);

        Assertions.assertNotNull(dtoList);
        Assertions.assertNotEquals(0, dtoList.size());
        Assertions.assertEquals(entityList.size(), dtoList.size());

        for (int i = 0; i<entityList.size();i++){
            Movie entityMovie = entityList.get(i);
            MovieDto dtoMovie = movieMapper.toDto(entityMovie);

            Assertions.assertNotNull(dtoMovie);

            Assertions.assertNotNull(dtoMovie.getId());
            Assertions.assertNotNull(dtoMovie.getTitle());
            Assertions.assertNotNull(dtoMovie.getYear());
            Assertions.assertNotNull(dtoMovie.getRating());




            Assertions.assertEquals(entityMovie.getId(), dtoMovie.getId());
            Assertions.assertEquals(entityMovie.getTitle(), dtoMovie.getTitle());
            Assertions.assertEquals(entityMovie.getYear(), dtoMovie.getYear());
            Assertions.assertEquals(entityMovie.getRating(), dtoMovie.getRating());

        }
    }
}
