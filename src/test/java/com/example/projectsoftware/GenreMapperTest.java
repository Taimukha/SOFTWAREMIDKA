package com.example.projectsoftware;

import com.example.projectsoftware.dto.GenreDto;
import com.example.projectsoftware.mapper.GenreMapper;
import com.example.projectsoftware.model.Genre;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
@SpringBootTest
public class GenreMapperTest {

    @Autowired
    private GenreMapper genreMapper;

    @Test
    void convertEntityToDtoTest(){
        Genre entityGenre = new Genre(1L, "horror");

        GenreDto genreDto = genreMapper.toDto(entityGenre);
        Assertions.assertNotNull(genreDto);

        Assertions.assertNotNull(genreDto.getId());
        Assertions.assertNotNull(genreDto.getName());

        Assertions.assertEquals(entityGenre.getId(), genreDto.getId());
        Assertions.assertEquals(entityGenre.getName(), genreDto.getName());
    }

    @Test
    void ConvertDtoToEntityTest(){
        GenreDto dtoGenre = new GenreDto();
        dtoGenre.setId(2L);
        dtoGenre.setName("comedy");

        Genre entityGenre = genreMapper.toEntity(dtoGenre);
        Assertions.assertNotNull(entityGenre);

        Assertions.assertNotNull(dtoGenre.getId());
        Assertions.assertNotNull(dtoGenre.getName());

        Assertions.assertEquals(entityGenre.getId(), dtoGenre.getId());
        Assertions.assertEquals(entityGenre.getName(), dtoGenre.getName());
    }

    @Test
    void convertEntityListToDtoList(){
        List<Genre> entityList = new ArrayList<>();
        entityList.add(new Genre(1L,"comedy"));
        entityList.add(new Genre(2L,"horror"));
        entityList.add(new Genre(3L,"fantasy"));

        List<GenreDto> dtoList = genreMapper.toDtoList(entityList);

        Assertions.assertNotNull(dtoList);
        Assertions.assertNotEquals(0, dtoList.size());
        Assertions.assertEquals(entityList.size(), dtoList.size());

        for (int i = 0; i<entityList.size();i++){
            Genre entityGenre = entityList.get(i);
            GenreDto dtoGenre = genreMapper.toDto(entityGenre);

            Assertions.assertNotNull(dtoGenre);

            Assertions.assertNotNull(dtoGenre.getId());
            Assertions.assertNotNull(dtoGenre.getName());

            Assertions.assertEquals(entityGenre.getId(), dtoGenre.getId());
            Assertions.assertEquals(entityGenre.getName(), dtoGenre.getName());

        }
    }
}