package com.alkemy.challenge.service.impl;

import com.alkemy.challenge.domain.CharacterEntity;
import com.alkemy.challenge.domain.MovieEntity;
import com.alkemy.challenge.dto.*;
import com.alkemy.challenge.mapper.CharacterMapper;
import com.alkemy.challenge.repository.CharacterRepository;
import com.alkemy.challenge.repository.specifications.CharacterSpecification;
import com.alkemy.challenge.service.CharacterService;
import com.alkemy.challenge.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class CharacterServiceImpl implements CharacterService {

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private CharacterMapper characterMapper;

    @Autowired
    private CharacterSpecification characterSpecification;

    @Transactional
    @Override
    public CharacterDTO save(CharacterDTO dto) {
        CharacterEntity entity = characterMapper.characterDTO2Entity(dto);
        CharacterEntity savedEntity = characterRepository.save(entity);
        CharacterDTO result = characterMapper.characterEntity2DTO(savedEntity, false);
        return result;
    }

    @Override
    public List<CharacterBasicDTO> getByFilters(String name, Integer age, List<Long> movies){
        CharacterFilterDTO filterDTO = new CharacterFilterDTO(name, age, movies);
        List<CharacterEntity> entities = characterRepository.findAll(characterSpecification.getByFilters(filterDTO));
        List<CharacterBasicDTO> dtos = characterMapper.characterEntityList2BasicDTOList(entities);
        return dtos;
    }

    @Override
    public void delete(Long id) {
        this.characterRepository.deleteById(id);
    }
}
