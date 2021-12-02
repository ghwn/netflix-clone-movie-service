package me.ghwn.netflix.movieservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@Getter @Setter @NoArgsConstructor
public class MovieDto {

    private Long id;

    private Long movieId;

    private String imdbId;

    private String tmdbId;

    private String title;

    private int releaseYear;

    private Set<String> genres;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
