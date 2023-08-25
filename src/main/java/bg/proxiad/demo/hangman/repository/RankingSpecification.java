package bg.proxiad.demo.hangman.repository;

import bg.proxiad.demo.hangman.model.Ranking;
import jakarta.persistence.Entity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class RankingSpecification implements Specification<Ranking> {

    private SearchCriteria criteria;

    public RankingSpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<Ranking> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (criteria.getOperation().equalsIgnoreCase(":")) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return criteriaBuilder.like(
                        root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
            } else {
                return criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        } else if (criteria.getOperation().equalsIgnoreCase(">")) {
            //criteriaBuilder.function("INTERVAL", String.class, root.get(criteria.getKey()));
            return criteriaBuilder.greaterThan(root.get(criteria.getKey()), (LocalDate) criteria.getValue());
        }

        return null;
    }
}