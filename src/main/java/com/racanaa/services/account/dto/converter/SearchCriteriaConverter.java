package com.racanaa.services.account.dto.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.racanaa.services.account.dto.SearchDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Search criteria converter to convert SearchDto to string
 *
 * @author Manohar
 * @since 20/Sep/2023
 */
@Slf4j
@Component
public class SearchCriteriaConverter implements Converter<String, SearchDto> {

    @Override
    public SearchDto convert(String from) {
        SearchDto dto = null;
        try {
            dto = new ObjectMapper().readValue(from, SearchDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }
}

