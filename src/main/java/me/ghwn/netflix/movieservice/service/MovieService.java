package me.ghwn.netflix.movieservice.service;

import me.ghwn.netflix.movieservice.dto.MovieDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MovieService {

    Page<MovieDto> getMovies(Pageable pageable);

    MovieDto getMovieByMovieId(Long movieId);

}
