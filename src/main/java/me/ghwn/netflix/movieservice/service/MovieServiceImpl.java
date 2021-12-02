package me.ghwn.netflix.movieservice.service;

import lombok.RequiredArgsConstructor;
import me.ghwn.netflix.movieservice.dto.MovieDto;
import me.ghwn.netflix.movieservice.repository.MovieRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final ModelMapper modelMapper;

    @Override
    public Page<MovieDto> getMovies(Pageable pageable) {
        return movieRepository.findAll(pageable)
                .map(movie -> modelMapper.map(movie, MovieDto.class));
    }

    @Override
    public MovieDto getMovieByMovieId(Long movieId) {
        return movieRepository.findByMovieId(movieId)
                .map(movie -> modelMapper.map(movie, MovieDto.class))
                .orElseThrow(() -> new IllegalArgumentException(String.format("Movie ID %d not found", movieId)));
    }

}
