package com.racanaa.services.account.persistance.criteria;

import io.jsonwebtoken.lang.Collections;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

/**
 * Search specification builder
 *
 * @param <T>
 * @author Manoahr
 * @since 20/Sep/2023
 */
@Slf4j
public class SearchSpecificationBuilder<T> {

    private final List<SearchCriteria> params;

    public SearchSpecificationBuilder() {
        this.params = new ArrayList<>();
    }

    public final SearchSpecificationBuilder<T> with(String key, String operation, Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public final SearchSpecificationBuilder<T> with(SearchCriteria searchCriteria) {
        params.add(searchCriteria);
        return this;
    }

    /**
     * Returns the search specification built using the search criteria
     *
     * @return
     */
    public Specification<T> build() {
        if (Collections.isEmpty(params)) {
            return null;
        }
        Specification<T> result = new SearchSpecification(params.get(0));
        for (int idx = 1; idx < params.size(); idx++) {
            SearchCriteria criteria = params.get(idx);
            result = SearchOperation.getDataOption(criteria.getDataOption()) == SearchOperation.ALL
                             ? Specification.where(result).and(new SearchSpecification(criteria))
                             : Specification.where(result).or(new SearchSpecification(criteria));
        }
        return result;
    }
}