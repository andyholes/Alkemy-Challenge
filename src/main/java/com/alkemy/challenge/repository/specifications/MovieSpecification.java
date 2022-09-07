package com.alkemy.challenge.repository.specifications;

import com.alkemy.challenge.domain.MovieEntity;
import com.alkemy.challenge.dto.MovieFilterDTO;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class MovieSpecification {

    public Specification<MovieEntity> getByFilters(MovieFilterDTO filterDTO){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasLength(filterDTO.getName())){
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("title")),
                                "%" + filterDTO.getName().toLowerCase() + "%"
                        )
                );
            }

            if (filterDTO.getGenre() != null && 0 < filterDTO.getGenre()){
                predicates.add(
                        criteriaBuilder.equal(
                                root.get("genreId"),
                                filterDTO.getGenre()
                        )
                );
            }

            query.distinct(true);

            String orderByField = "title";
            query.orderBy(
                    filterDTO.isASC() ?
                            criteriaBuilder.asc(root.get(orderByField)) :
                            criteriaBuilder.desc(root.get(orderByField))
            );

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
