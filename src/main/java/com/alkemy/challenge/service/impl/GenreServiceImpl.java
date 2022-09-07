package com.alkemy.challenge.service.impl;

import com.alkemy.challenge.domain.GenreEntity;
import com.alkemy.challenge.mapper.GenreMapper;
import com.alkemy.challenge.repository.GenreRepository;
import com.alkemy.challenge.dto.GenreDTO;
import com.alkemy.challenge.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private GenreMapper genreMapper;

    @Transactional
    @Override
    public GenreDTO save (GenreDTO dto) {
        GenreEntity entity = genreMapper.genreDTO2Entity(dto);
        GenreEntity savedEntity = genreRepository.save(entity);
        GenreDTO result = genreMapper.genreEntity2DTO(savedEntity);
        return result;
    }

    @Override
    public List<GenreDTO> getAllGenres() {
        List<GenreEntity> entities = genreRepository.findAll();
        List<GenreDTO> result = genreMapper.genreEntityList2DTOList(entities);
        return result;
    }
}