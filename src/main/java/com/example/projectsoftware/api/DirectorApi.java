package com.example.projectsoftware.api;

import com.example.projectsoftware.dto.DirectorDto;
import com.example.projectsoftware.mapper.DirectorMapper;
import com.example.projectsoftware.model.Director;
import com.example.projectsoftware.repository.DirectorRepository;
import com.example.projectsoftware.service.DirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/directors")
@RequiredArgsConstructor
public class DirectorApi {

    private final DirectorService directorService;
    private final DirectorMapper directorMapper;
    private final DirectorRepository directorRepository;

    @GetMapping
    public List<DirectorDto> getAll() {
        List<Director> directorEntityList = directorRepository.findAll();
        return directorMapper.toDtoList(directorEntityList);
    }

    @PostMapping
    public ResponseEntity<?> addDirector(@RequestBody DirectorDto directorDto){
        directorService.addDirector(directorDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDirector(@PathVariable("id") Long id,
                                            @RequestBody DirectorDto directorDto){
        directorService.updateDirector(id, directorDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDirector(@PathVariable("id") Long id){
        directorService.deleteDirector(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
