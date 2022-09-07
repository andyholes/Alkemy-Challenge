package com.alkemy.challenge.dto;

import com.alkemy.challenge.domain.GenreEntity;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {

    private Long id;

    private String image;

    private String title;

    private String date;

    private Integer score;

    private Set<CharacterDTO> characters;

    private GenreEntity genre;

    private Long genreId;
}
