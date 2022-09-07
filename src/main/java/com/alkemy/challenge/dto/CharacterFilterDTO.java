package com.alkemy.challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CharacterFilterDTO {

    private String name;

    private Integer age;

    private List<Long> moviesId;
}
