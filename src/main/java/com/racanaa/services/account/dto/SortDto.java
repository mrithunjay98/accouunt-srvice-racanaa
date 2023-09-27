package com.racanaa.services.account.dto;

import com.racanaa.services.account.persistance.criteria.SortCriteria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SortDto {
    private List<SortCriteria> criteria;
}