package com.alkemy.challenge.service;

import com.alkemy.challenge.dto.CharacterBasicDTO;
import com.alkemy.challenge.dto.CharacterDTO;
import com.alkemy.challenge.dto.MovieBasicDTO;
import com.alkemy.challenge.dto.MovieDTO;

import java.util.List;
import java.util.Set;

public interface CharacterService {

    CharacterDTO save(CharacterDTO character);

    List<CharacterBasicDTO> getByFilters(String name, Integer age, List<Long> moviesId);

    void delete(Long id);
}
