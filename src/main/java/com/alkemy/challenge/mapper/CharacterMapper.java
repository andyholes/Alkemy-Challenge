package com.alkemy.challenge.mapper;

import com.alkemy.challenge.domain.CharacterEntity;
import com.alkemy.challenge.domain.MovieEntity;
import com.alkemy.challenge.dto.CharacterDTO;
import com.alkemy.challenge.dto.CharacterBasicDTO;
import com.alkemy.challenge.dto.MovieDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CharacterMapper {

    @Autowired
    private MovieMapper movieMapper;

    public CharacterEntity characterDTO2Entity(CharacterDTO dto){
        CharacterEntity entity = new CharacterEntity();
        entity.setId(dto.getId());
        entity.setImage(dto.getImage());
        entity.setName(dto.getName());
        entity.setAge(dto.getAge());
        entity.setWeight(dto.getWeight());
        entity.setHistory(dto.getHistory());
        if(dto.getMovies() != null) {
            Set<MovieEntity> movies = this.movieMapper.movieDTOSet2Entity(dto.getMovies());
            entity.setMovies(movies);
        }
        return entity;
    }

    public CharacterDTO characterEntity2DTO(CharacterEntity entity, boolean loadMovies) {
        CharacterDTO dto = new CharacterDTO();
        dto.setId(entity.getId());
        dto.setImage(entity.getImage());
        dto.setName(entity.getName());
        dto.setAge(entity.getAge());
        dto.setWeight(entity.getWeight());
        dto.setHistory(entity.getHistory());
        if (loadMovies){
            Set<MovieDTO> movieDTOS = this.movieMapper.movieEntitySet2DTOSet(entity.getMovies(), false);
            dto.setMovies(movieDTOS);
        }
        return dto;
    }

    public Set<CharacterEntity> characterDTOSet2Entity(Set<CharacterDTO> dtos){
        Set<CharacterEntity> entities = new HashSet<>();
        for (CharacterDTO dto : dtos){
            entities.add(this.characterDTO2Entity(dto));
        }
        return entities;
    }

    public Set<CharacterDTO> characterEntitySet2DTOSet(Collection<CharacterEntity> entities, boolean loadMovies){
        Set<CharacterDTO> dtos = new HashSet<>();
        for (CharacterEntity entity : entities){
            dtos.add(this.characterEntity2DTO(entity, loadMovies));
        }
        return dtos;
    }

    public Set<CharacterBasicDTO> characterEntitySet2BasicDTOList(Collection<CharacterEntity> entities, boolean loadMovies){
        Set<CharacterBasicDTO> dtos = new HashSet<>();
        CharacterBasicDTO filterDTO;
        for (CharacterEntity entity : entities){
            filterDTO = new CharacterBasicDTO();
            filterDTO.setImage(entity.getImage());
            filterDTO.setName(entity.getName());
            dtos.add(filterDTO);
        }
        return dtos;
    }

    public CharacterEntity characterBasicDTO2Entity (CharacterBasicDTO dto){
        CharacterEntity entity = new CharacterEntity();
        entity.setImage(dto.getImage());
        entity.setName(dto.getName());
        return entity;
    }

    public CharacterBasicDTO characterEntity2BasicDTO (CharacterEntity entity){
        CharacterBasicDTO dto = new CharacterBasicDTO();
        dto.setImage(entity.getImage());
        dto.setName(entity.getName());
        return dto;
    }

    public List<CharacterBasicDTO> characterEntityList2BasicDTOList(Collection<CharacterEntity> entities){
        List<CharacterBasicDTO> dtos = new ArrayList<>();
        for (CharacterEntity entity : entities){
            dtos.add(this.characterEntity2BasicDTO(entity));
        }
        return dtos;
    }

}
