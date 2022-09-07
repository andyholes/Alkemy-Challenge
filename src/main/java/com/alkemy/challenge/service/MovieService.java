package com.alkemy.challenge.service;

import com.alkemy.challenge.domain.MovieEntity;
import com.alkemy.challenge.dto.MovieBasicDTO;
import com.alkemy.challenge.dto.MovieDTO;

import java.util.List;
import java.util.Set;

public interface MovieService {

    MovieDTO save(MovieDTO movie);

    List<MovieBasicDTO> getByFilters(String name, Long genre, String order);

    void delete(Long id);
}
