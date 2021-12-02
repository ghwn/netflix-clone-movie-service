package me.ghwn.netflix.movieservice.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MovieTest {

    @Test
    void equality() {
        Movie movie1 = new Movie();
        movie1.setId(1L);

        Movie movie2 = new Movie();
        movie2.setId(1L);

        assertThat(movie1.equals(movie2)).isTrue();
    }

}
