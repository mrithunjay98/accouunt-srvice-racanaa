package com.racanaa.services.account.persistance.criteria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Search criteria to perform search on a model/entity
 *
 * @author Manohar
 * @since 20/Sep/2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchCriteria {
    private String filterKey;
    private Object value;
    private String operation;
    private String dataOption;
    public SearchCriteria(String filterKey, String operation, Object value){
        super();
        this.filterKey = filterKey;
        this.operation = operation;
        this.value = value;
    }
}