package com.example.projectsoftware.api;

import com.example.projectsoftware.dto.MovieDto;
import com.example.projectsoftware.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/movies")
public class MovieApi {

    private final MovieService movieService;

    public MovieApi(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(movieService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addMovie(@RequestBody MovieDto movieDto){
        movieService.addMovie(movieDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMovie(@PathVariable("id") Long id,
                                         @RequestBody MovieDto movieDto){
        movieService.updateMovie(id, movieDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable("id") Long id){
        movieService.deleteMovie(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

