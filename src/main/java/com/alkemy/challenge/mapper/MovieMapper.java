package com.alkemy.challenge.mapper;

import com.alkemy.challenge.domain.CharacterEntity;
import com.alkemy.challenge.domain.MovieEntity;
import com.alkemy.challenge.dto.CharacterDTO;
import com.alkemy.challenge.dto.MovieBasicDTO;
import com.alkemy.challenge.dto.MovieDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class MovieMapper {

    @Autowired
    private CharacterMapper characterMapper;

    public MovieEntity movieDTO2Entity (MovieDTO dto){
        MovieEntity entity = new MovieEntity();
        entity.setId(dto.getId());
        entity.setImage(dto.getImage());
        entity.setTitle(dto.getTitle());
        entity.setDate(this.string2LocalDate(dto.getDate()));
        entity.setScore(dto.getScore());
        entity.setGenreId(dto.getGenreId());
        if(dto.getCharacters() != null){
            Set<CharacterEntity> characters = this.characterMapper.characterDTOSet2Entity(dto.getCharacters());
            entity.setCharacters(characters);
        }
        return entity;
    }

    public MovieDTO movieEntity2DTO (MovieEntity entity, boolean loadCharacters){
        MovieDTO dto = new MovieDTO();
        dto.setId(entity.getId());
        dto.setImage(entity.getImage());
        dto.setTitle(entity.getTitle());
        dto.setDate(entity.getDate().toString());
        dto.setScore(entity.getScore());
        dto.setGenreId(entity.getGenreId());
        if(loadCharacters){
            Set<CharacterDTO> charactersDTO = this.characterMapper.characterEntitySet2DTOSet(entity.getCharacters(), false);
            dto.setCharacters(charactersDTO);
        }
        return dto;
    }

    private LocalDate string2LocalDate (String stringDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(stringDate, formatter);
        return date;
    }

    public Set<MovieEntity> movieDTOSet2Entity(Set<MovieDTO> dtos){
        Set<MovieEntity> entities = new HashSet<>();
        for (MovieDTO dto : dtos){
            entities.add(this.movieDTO2Entity(dto));
        }
        return entities;
    }

    public Set<MovieDTO> movieEntitySet2DTOSet(Collection<MovieEntity> entities, boolean loadCharacters){
        Set<MovieDTO> dtos = new HashSet<>();
        for (MovieEntity entity : entities){
            dtos.add(this.movieEntity2DTO(entity, loadCharacters));
        }
        return dtos;
    }

    public MovieEntity movieBasicDTO2Entity (MovieBasicDTO dto){
        MovieEntity entity = new MovieEntity();
        entity.setImage(dto.getImage());
        entity.setTitle(dto.getTitle());
        entity.setDate(this.string2LocalDate(dto.getDate()));
        return entity;
    }

    public MovieBasicDTO movieEntity2BasicDTO (MovieEntity entity){
        MovieBasicDTO dto = new MovieBasicDTO();
        dto.setImage(entity.getImage());
        dto.setTitle(entity.getTitle());
        dto.setDate(entity.getDate().toString());
        return dto;
    }

    public List<MovieBasicDTO> movieEntityList2BasicDTOList(Collection<MovieEntity> entities){
        List<MovieBasicDTO> dtos = new ArrayList<>();
        for (MovieEntity entity : entities){
            dtos.add(this.movieEntity2BasicDTO(entity));
        }
        return dtos;
    }

}
