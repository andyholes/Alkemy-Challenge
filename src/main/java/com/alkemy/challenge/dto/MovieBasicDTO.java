package com.alkemy.challenge.dto;

import com.alkemy.challenge.domain.GenreEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieBasicDTO {

    private String image;

    private String title;

    private String date;
}
