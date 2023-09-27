package com.racanaa.services.account.dto.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.racanaa.services.account.dto.SortDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Sort criteria converter to convert SortDto to string
 *
 * @author Manohar
 * @since 20/Sep/2023
 */
@Slf4j
@Component
public class SortCriteriaConverter implements Converter<String, SortDto> {

    @Override
    public SortDto convert(String from) {
        SortDto dto = null;
        try {
             dto = new ObjectMapper().readValue(from, SortDto.class);
        }catch(Exception e){
            e.printStackTrace();
        }
        return dto;
    }
}
