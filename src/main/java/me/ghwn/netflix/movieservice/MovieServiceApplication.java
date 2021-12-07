package me.ghwn.netflix.movieservice;

import lombok.RequiredArgsConstructor;
import me.ghwn.netflix.movieservice.entity.Movie;
import me.ghwn.netflix.movieservice.repository.MovieRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@EnableJpaAuditing
@SpringBootApplication
@EnableEurekaClient
public class MovieServiceApplication implements CommandLineRunner {

    private final MovieRepository movieRepository;

    public static void main(String[] args) {
        SpringApplication.run(MovieServiceApplication.class, args);
    }

    @Override
    public void run(String... args) {
        List<Movie> movies = List.of(
                new Movie(null, 1L, "0114709", "862", "Toy Story (1995)", 1995, Set.of("Adventure", "Animation", "Children", "Comedy", "Fantasy")),
                new Movie(null, 2L, "0113497", "8844", "Jumanji (1995)", 1995, Set.of("Adventure", "Children", "Fantasy")),
                new Movie(null, 3L, "0113228", "15602", "Grumpier Old Men (1995)", 1995, Set.of("Comedy", "Romance")),
                new Movie(null, 296L, "110912", "680", "Pulp Fiction (1994)", 1994, Set.of("Comedy", "Crime", "Drama", "Thriller")),
                new Movie(null, 68157L, "361748", "16869", "Inglourious Basterds (2009)", 1994, Set.of("Action", "Drama", "War")),
                new Movie(null, 99114L, "1853728", "68718", "Django Unchained (2012)", 2012, Set.of("Action", "Drama", "Western"))
        );
        movieRepository.saveAll(movies);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
