package com.alkemy.challenge.service;

import com.alkemy.challenge.dto.GenreDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface GenreService {

    GenreDTO save(GenreDTO genre);

    List<GenreDTO> getAllGenres();


}
