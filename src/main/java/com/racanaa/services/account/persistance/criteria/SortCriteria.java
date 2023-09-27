package com.racanaa.services.account.persistance.criteria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Sort criteria to perform sort on a model/entity
 *
 * @author Manohar
 * @since 20/Sep/2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SortCriteria {
    private String by;
    private String order;
}