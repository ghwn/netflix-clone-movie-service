package me.ghwn.netflix.movieservice.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Design based on MovieLens dataset
 */
@EqualsAndHashCode(of = "id", callSuper = false)
@AllArgsConstructor
@Getter @Setter @NoArgsConstructor
@Entity
public class Movie extends TimestampedEntity {

    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private Long movieId;

    private String imdbId;

    private String tmdbId;

    private String title;

    private int releaseYear;

    @ElementCollection(fetch = FetchType.LAZY)
    private Set<String> genres = new HashSet<>();

}
