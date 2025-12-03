package com.example.projectsoftware;

import com.example.projectsoftware.dto.DirectorDto;
import com.example.projectsoftware.mapper.DirectorMapper;
import com.example.projectsoftware.model.Director;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
@SpringBootTest
public class DirectorMapperTest {
    @Autowired
    private DirectorMapper directorMapper;

    @Test
    void convertEntityToDtoTest(){
        Director entityDirector = new Director(1L, "Qwerty");

        DirectorDto directorDto = directorMapper.toDto(entityDirector);
        Assertions.assertNotNull(directorDto);

        Assertions.assertNotNull(directorDto.getId());
        Assertions.assertNotNull(directorDto.getName());

        Assertions.assertEquals(entityDirector.getId(), directorDto.getId());
        Assertions.assertEquals(entityDirector.getName(), directorDto.getName());
    }
    @Test
    void ConvertDtoToEntityTest(){
        DirectorDto dtoDirector = new DirectorDto();
        dtoDirector.setId(2L);
        dtoDirector.setName("Alex");

        Director entityDirector = directorMapper.toEntity(dtoDirector);
        Assertions.assertNotNull(entityDirector);

        Assertions.assertNotNull(dtoDirector.getId());
        Assertions.assertNotNull(dtoDirector.getName());

        Assertions.assertEquals(entityDirector.getId(), dtoDirector.getId());
        Assertions.assertEquals(entityDirector.getName(), dtoDirector.getName());
    }

    @Test
    void convertEntityListToDtoList(){
        List<Director> entityList = new ArrayList<>();
        entityList.add(new Director(1L,"Abay"));
        entityList.add(new Director(2L,"Ytreq"));
        entityList.add(new Director(3L,"qwer"));

        List<DirectorDto> dtoList = directorMapper.toDtoList(entityList);

        Assertions.assertNotNull(dtoList);
        Assertions.assertNotEquals(0, dtoList.size());
        Assertions.assertEquals(entityList.size(), dtoList.size());

        for (int i = 0; i<entityList.size();i++){
            Director entityDirector = entityList.get(i);
            DirectorDto dtoDirector = directorMapper.toDto(entityDirector);

            Assertions.assertNotNull(dtoDirector);

            Assertions.assertNotNull(dtoDirector.getId());
            Assertions.assertNotNull(dtoDirector.getName());

            Assertions.assertEquals(entityDirector.getId(), dtoDirector.getId());
            Assertions.assertEquals(entityDirector.getName(), dtoDirector.getName());

        }
    }
}