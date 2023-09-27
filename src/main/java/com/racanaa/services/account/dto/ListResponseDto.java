package com.racanaa.services.account.dto;

import lombok.Data;

@Data
public class ListResponseDto<T> {
    long total;
    int offset;
    int limit;

    private Iterable<T> data;

    // @JsonInclude(JsonInclude.Include.NON_NULL)
    private SearchDto search;

    // @JsonInclude(JsonInclude.Include.NON_NULL)
    private SortDto sort;


    public ListResponseDto(Iterable<T> data, long total, int offset, int limit) {
        this.total = total;
        this.offset = offset;
        this.limit = limit;
        this.data = data;
    }

    public ListResponseDto(Iterable<T> data, long total, int offset, int limit, SortDto sort) {
        this.total = total;
        this.offset = offset;
        this.limit = limit;
        this.data = data;
        this.sort = sort;
    }

    public ListResponseDto(Iterable<T> data, long total, int offset, int limit, SortDto sort, SearchDto search) {
        this.total = total;
        this.offset = offset;
        this.limit = limit;
        this.data = data;
        this.sort = sort;
        this.search = search;
    }

}
