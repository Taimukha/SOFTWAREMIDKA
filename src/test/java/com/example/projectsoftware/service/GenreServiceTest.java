package com.example.projectsoftware.service;

import com.example.projectsoftware.dto.GenreDto;
import com.example.projectsoftware.service.GenreService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

@SpringBootTest
public class GenreServiceTest {

    @Autowired
    private GenreService genreService;

    @Test
    void getAllTest() {
        List<GenreDto> genres = genreService.getAll();

        Assertions.assertNotNull(genres);
        Assertions.assertNotEquals(0, genres.size());

        for (GenreDto g : genres) {
            Assertions.assertNotNull(g);
            Assertions.assertNotNull(g.getId());
            Assertions.assertNotNull(g.getName());
        }
    }

    @Test
    void getByIdTest() {
        List<GenreDto> all = genreService.getAll();
        Random random = new Random();

        GenreDto randomGenre = all.get(random.nextInt(all.size()));

        GenreDto genre = genreService.getById(randomGenre.getId());

        Assertions.assertNotNull(genre);
        Assertions.assertEquals(randomGenre.getId(), genre.getId());

        // negative test
        Assertions.assertNull(genreService.getById(-1L));
    }

    @Test
    void addGenreTest() {
        GenreDto dto = new GenreDto();
        dto.setName("NewGenreTest");

        GenreDto created = genreService.addGenre(dto);

        Assertions.assertNotNull(created);
        Assertions.assertNotNull(created.getId());
        Assertions.assertEquals(dto.getName(), created.getName());

        GenreDto found = genreService.getById(created.getId());

        Assertions.assertNotNull(found);
        Assertions.assertEquals(created.getId(), found.getId());
    }

    @Test
    void updateGenreTest() {
        List<GenreDto> all = genreService.getAll();
        Random random = new Random();

        GenreDto randomGenre = all.get(random.nextInt(all.size()));

        GenreDto dto = new GenreDto();
        dto.setName("UpdatedName");

        GenreDto updated = genreService.updateGenre(randomGenre.getId(), dto);

        Assertions.assertNotNull(updated);
        Assertions.assertEquals(dto.getName(), updated.getName());

        GenreDto after = genreService.getById(randomGenre.getId());
        Assertions.assertEquals("UpdatedName", after.getName());
    }

    @Test
    void deleteGenreTest() {
        GenreDto dto = new GenreDto();
        dto.setName("ToDelete");

        GenreDto created = genreService.addGenre(dto);

        boolean deleted = genreService.deleteGenre(created.getId());
        Assertions.assertTrue(deleted);

        Assertions.assertNull(genreService.getById(created.getId()));
    }
}