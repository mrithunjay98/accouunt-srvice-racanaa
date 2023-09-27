package com.racanaa.services.account.dto;

import com.racanaa.services.account.persistance.criteria.SearchCriteria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchDto {
    private List<SearchCriteria> criteria;
    private String dataOption;
}