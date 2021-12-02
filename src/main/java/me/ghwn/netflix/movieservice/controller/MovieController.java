package me.ghwn.netflix.movieservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.ghwn.netflix.movieservice.dto.MovieDto;
import me.ghwn.netflix.movieservice.service.MovieService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/movies")
@RestController
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<?> getMovieList(Pageable pageable) {
        Page<MovieDto> movies = movieService.getMovies(pageable);
        Map<String, Object> response = new HashMap<>();
        response.put("movies", movies.getContent());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<MovieDto> getMovie(@PathVariable Long movieId) {
        MovieDto movie = movieService.getMovieByMovieId(movieId);
        return ResponseEntity.ok(movie);
    }

}
