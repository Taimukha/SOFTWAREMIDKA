package com.example.projectsoftware.service;

import com.example.projectsoftware.dto.DirectorDto;
import com.example.projectsoftware.model.Director;
import com.example.projectsoftware.repository.DirectorRepository;
import com.example.projectsoftware.service.DirectorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

@SpringBootTest
public class DirectorServiceTest {

    @Autowired
    private DirectorService directorService;

    @Test
    void getAllTest(){
        List<DirectorDto> directors = directorService.getAll();

        Assertions.assertNotNull(directors);
        Assertions.assertNotEquals(0, directors.size());

        for (DirectorDto d : directors) {
            Assertions.assertNotNull(d);
            Assertions.assertNotNull(d.getId());
            Assertions.assertNotNull(d.getName());
        }
    }

    @Test
    void getByIdTest(){
        List<DirectorDto> all = directorService.getAll();
        Random rand = new Random();

        Long id = all.get(rand.nextInt(all.size())).getId();
        DirectorDto dto = directorService.getById(id);

        Assertions.assertNotNull(dto);
        Assertions.assertNotNull(dto.getId());
        Assertions.assertNotNull(dto.getName());

        DirectorDto notFound = directorService.getById(-1L);
        Assertions.assertNull(notFound);
    }

    @Test
    void addDirectorTest(){
        DirectorDto newDto = new DirectorDto();
        newDto.setName("Test Director");

        DirectorDto created = directorService.addDirector(newDto);

        Assertions.assertNotNull(created);
        Assertions.assertNotNull(created.getId());
        Assertions.assertEquals("Test Director", created.getName());
    }

    @Test
    void updateDirectorTest(){
        List<DirectorDto> all = directorService.getAll();
        Random rand = new Random();

        Long id = all.get(rand.nextInt(all.size())).getId();

        DirectorDto dto = new DirectorDto();
        dto.setName("Updated Name");

        DirectorDto updated = directorService.updateDirector(id, dto);

        Assertions.assertNotNull(updated);
        Assertions.assertEquals("Updated Name", updated.getName());

        DirectorDto check = directorService.getById(id);
        Assertions.assertEquals("Updated Name", check.getName());
    }

    @Test
    void deleteDirectorTest(){
        List<DirectorDto> all = directorService.getAll();
        Random rand = new Random();

        Long id = all.get(rand.nextInt(all.size())).getId();

        Assertions.assertTrue(directorService.deleteDirector(id));

        DirectorDto dto = directorService.getById(id);
        Assertions.assertNull(dto);
    }
}