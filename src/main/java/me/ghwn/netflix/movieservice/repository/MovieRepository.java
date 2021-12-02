package me.ghwn.netflix.movieservice.repository;

import me.ghwn.netflix.movieservice.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    Optional<Movie> findByMovieId(Long movieId);

}
