package com.alkemy.challenge.service.impl;

import com.alkemy.challenge.domain.MovieEntity;
import com.alkemy.challenge.dto.MovieBasicDTO;
import com.alkemy.challenge.dto.MovieFilterDTO;
import com.alkemy.challenge.mapper.MovieMapper;
import com.alkemy.challenge.dto.MovieDTO;
import com.alkemy.challenge.repository.MovieRepository;
import com.alkemy.challenge.repository.specifications.MovieSpecification;
import com.alkemy.challenge.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieMapper movieMapper;

    @Autowired
    private MovieSpecification movieSpecification;

    @Transactional
    @Override
    public MovieDTO save (MovieDTO dto) {
        MovieEntity entity = movieMapper.movieDTO2Entity(dto);
        MovieEntity savedEntity = movieRepository.save(entity);
        MovieDTO result = movieMapper.movieEntity2DTO(savedEntity, false);
        return result;
    }

    @Override
    public List<MovieBasicDTO> getByFilters(String name, Long genre, String order){
         MovieFilterDTO filterDTO = new MovieFilterDTO(name, genre, order);
         List<MovieEntity> entities = movieRepository.findAll(movieSpecification.getByFilters(filterDTO));
         List<MovieBasicDTO> dtos = movieMapper.movieEntityList2BasicDTOList(entities);
         return dtos;
    }


    @Override
    public void delete (Long id){
        movieRepository.deleteById(id);
    }
}
