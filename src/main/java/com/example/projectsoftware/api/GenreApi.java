package com.example.projectsoftware.api;

import com.example.projectsoftware.dto.GenreDto;
import com.example.projectsoftware.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/genres")
@RequiredArgsConstructor
public class GenreApi {

    private final GenreService genreService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(genreService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody GenreDto genreDto) {
        genreService.addGenre(genreDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateGenre(@PathVariable("id") Long id,
                                         @RequestBody GenreDto genreDto) {
        genreService.updateGenre(id, genreDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGenre(@PathVariable Long id) {
        genreService.deleteGenre(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
