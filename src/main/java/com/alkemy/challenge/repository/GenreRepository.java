package com.alkemy.challenge.repository;

import com.alkemy.challenge.domain.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<GenreEntity, Long> {

    GenreEntity getByName(String name);
}
