package me.ghwn.netflix.movieservice.controller;

import me.ghwn.netflix.movieservice.entity.Movie;
import me.ghwn.netflix.movieservice.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class MovieControllerTest {

    @Autowired MovieRepository movieRepository;
    @Autowired MockMvc mockMvc;

    List<Movie> movies;

    @BeforeEach
    void beforeEach() {
        movieRepository.deleteAll();
        List<Movie> movies = List.of(
                new Movie(null, 1L, "0114709", "862", "Toy Story (1995)", 1995, Set.of("Adventure", "Animation", "Children", "Comedy", "Fantasy")),
                new Movie(null, 2L, "0113497", "8844", "Jumanji (1995)", 1995, Set.of("Adventure", "Children", "Fantasy")),
                new Movie(null, 3L, "0113228", "15602", "Grumpier Old Men (1995)", 1995, Set.of("Comedy", "Romance")),
                new Movie(null, 296L, "110912", "680", "Pulp Fiction (1994)", 1994, Set.of("Comedy", "Crime", "Drama", "Thriller")),
                new Movie(null, 68157L, "361748", "16869", "Inglourious Basterds (2009)", 1994, Set.of("Comedy", "Crime", "Drama", "Thriller")),
                new Movie(null, 99114L, "1853728", "68718", "Django Unchained (2012)", 2012, Set.of("Action", "Drama", "Western"))
        );
        this.movies = movies.stream()
                .map(movie -> movieRepository.save(movie))
                .collect(Collectors.toList());
    }

    @DisplayName("Get movie list successfully")
    @Test
    void getMovieList() throws Exception {
        mockMvc.perform(get("/api/v1/movies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("movies").exists())
                .andExpect(jsonPath("movies.length()").value(Math.min(this.movies.size(), 20)))
                .andExpect(jsonPath("movies[*].id").exists())
                .andExpect(jsonPath("movies[*].movieId").exists())
                .andExpect(jsonPath("movies[*].imdbId").exists())
                .andExpect(jsonPath("movies[*].tmdbId").exists())
                .andExpect(jsonPath("movies[*].title").exists())
                .andExpect(jsonPath("movies[*].releaseYear").exists())
                .andExpect(jsonPath("movies[*].genres").exists())
                .andExpect(jsonPath("movies[*].createdAt", notNullValue()))
                .andExpect(jsonPath("movies[*].updatedAt", notNullValue()))
                .andDo(print());
    }

    @DisplayName("Get a movie successfully")
    @Test
    void getMovieDetail() throws Exception {
        Movie movie = movies.get(0);
        mockMvc.perform(get("/api/v1/movies/{movieId}", movie.getMovieId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("movieId").value(movie.getMovieId()))
                .andExpect(jsonPath("imdbId").exists())
                .andExpect(jsonPath("tmdbId").exists())
                .andExpect(jsonPath("title").exists())
                .andExpect(jsonPath("releaseYear").exists())
                .andExpect(jsonPath("genres").exists())
                .andExpect(jsonPath("createdAt", notNullValue()))
                .andExpect(jsonPath("updatedAt", notNullValue()))
                .andDo(print());
    }

}
