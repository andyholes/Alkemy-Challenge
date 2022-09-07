package com.alkemy.challenge.repository.specifications;

import com.alkemy.challenge.domain.CharacterEntity;
import com.alkemy.challenge.domain.MovieEntity;
import com.alkemy.challenge.dto.CharacterFilterDTO;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class CharacterSpecification {

    public Specification<CharacterEntity> getByFilters(CharacterFilterDTO filterDTO){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasLength(filterDTO.getName())){
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("name")),
                                "%" + filterDTO.getName().toLowerCase() + "%"
                        )
                );
            }

            if (filterDTO.getAge() != null && 0 < filterDTO.getAge()){
                predicates.add(
                        criteriaBuilder.equal(
                                root.get("age"),
                                filterDTO.getAge()
                        )
                );
            }

            if (filterDTO.getAge() != null && 0 < filterDTO.getAge()){
                predicates.add(
                        criteriaBuilder.equal(
                                root.get("age"), filterDTO.getAge()
                        )
                );
            }

            if (!CollectionUtils.isEmpty(filterDTO.getMoviesId())){
                Join<MovieEntity, CharacterEntity> join = root.join("movies", JoinType.INNER);
                Expression<String> moviesId = join.get("id");
                predicates.add(moviesId.in(filterDTO.getMoviesId()));
            }

            query.distinct(true);

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
