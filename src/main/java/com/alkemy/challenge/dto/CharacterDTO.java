package com.alkemy.challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CharacterDTO {

    private Long id;

    private String image;

    private String name;

    private Integer age;

    private Float weight;

    private String history;

    private Set<MovieDTO> movies;
}
