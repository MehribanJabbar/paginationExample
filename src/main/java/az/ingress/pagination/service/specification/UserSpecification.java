package az.ingress.pagination.service.specification;

import az.ingress.pagination.entity.UserEntity;
import az.ingress.pagination.model.criteria.UserCriteria;
import az.ingress.pagination.util.PredicateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import static az.ingress.pagination.model.constants.CriteriaConstants.*;
import static az.ingress.pagination.util.PredicateUtil.applyLikePattern;

@RequiredArgsConstructor
public class UserSpecification implements Specification<UserEntity> {

    private final UserCriteria userCriteria;

    @Override
    public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        var predicates = PredicateUtil.builder()
                .addNullSafety(userCriteria.getBirthplace(),
                        birthPlace -> cb.like(root.get(BIRTH_PLACE), applyLikePattern(birthPlace))
                )
                .addNullSafety(userCriteria.getAgeFrom(), ageFrom -> cb.greaterThanOrEqualTo(root.get(AGE), ageFrom))
                .addNullSafety(userCriteria.getAgeTo(), ageTo -> cb.lessThanOrEqualTo(root.get(AGE), ageTo))
                .addNullSafety(userCriteria.getCreatedDate(), createdDate -> cb.greaterThanOrEqualTo(root.get(CREATED_AT), createdDate))
                .build();
        return cb.and(predicates);
    }
}
