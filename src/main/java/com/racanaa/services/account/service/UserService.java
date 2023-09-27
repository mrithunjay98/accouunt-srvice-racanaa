package com.racanaa.services.account.service;

import com.racanaa.services.account.dto.ListResponseDto;
import com.racanaa.services.account.dto.SearchDto;
import com.racanaa.services.account.dto.SortDto;
import com.racanaa.services.account.dto.response.UserResponseDto;
import com.racanaa.services.account.persistance.criteria.SearchCriteria;
import com.racanaa.services.account.persistance.criteria.SearchSpecificationBuilder;
import com.racanaa.services.account.persistance.manager.UserManager;
import com.racanaa.services.account.persistance.model.User;
import com.racanaa.services.account.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.List;

@Slf4j
@Component("UserService")
public class UserService {
    @Autowired
    private UserManager userManager;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    ApplicationContext context;

    /**
     * Returns a list of accounts based on the search criteria. Sorts data if Sort criteria is provided,
     * otherwise sorts by a default criteria that is dateUpdated desc.
     *
     * @param offset page number
     * @param limit  page size
     * @param sort   the sort criteria
     * @return ListResponseDto<AccountResponseDto> list response dto of AccountResponseDto's
     */
    public ListResponseDto<UserResponseDto> list(int offset, int limit, SortDto sort) {
        Pageable pageable =
                PageRequest.of(offset - 1, limit, ObjectUtil.getSortObject(sort));
        Page<User> page = userManager.list(pageable);
        Type listType = new TypeToken<List<UserResponseDto>>() {
        }.getType();
        List<UserResponseDto> userResponseDtoList = modelMapper.map(page.getContent(), listType);
        sort = (sort == null) ? ObjectUtil.getDefaultSortDto() : sort;
        return new ListResponseDto<>(userResponseDtoList, page.getTotalElements(), offset, limit, sort);
    }

    /**
     * Returns a list of users based on the search criteria. Sorts data if Sort criteria is provided,
     * otherwise sorts by a default criteria that is dateUpdated desc.
     *
     * @param offset page number
     * @param limit  page size
     * @param sort   the sort criteria
     * @param search the search criteria
     * @return ListResponseDto<UserResponseDto> list response dto of UserResponseDto's
     * @see SortDto
     * @see com.racanaa.services.account.persistance.criteria.SortCriteria
     * @see SearchDto
     * @see com.racanaa.services.account.persistance.criteria.SearchCriteria
     */
    public ListResponseDto<UserResponseDto> search(int offset, int limit, SortDto sort, SearchDto search) {
        if (search == null) {
            return list(offset, limit, sort);
        }
        SearchSpecificationBuilder<User> builder = new SearchSpecificationBuilder<User>();
        List<SearchCriteria> criteriaList = search.getCriteria();
        if (criteriaList != null) {
            criteriaList.forEach(x ->
            {
                x.setDataOption(search.getDataOption());
                builder.with(x);
            });
        }
        Pageable pageable = PageRequest.of(offset - 1, limit, ObjectUtil.getSortObject(sort));
        Page<User> page = userManager.search(builder, pageable);
        Type listType = new TypeToken<List<UserResponseDto>>() {
        }.getType();
        List<UserResponseDto> userResponseDtoList = modelMapper.map(page.getContent(), listType);
        sort = (sort == null) ? ObjectUtil.getDefaultSortDto() : sort;
        return new ListResponseDto<>(userResponseDtoList, page.getTotalElements(), offset, limit, sort, search);
    }


//    public User createUser(User u) {
//        u.setDateCreated(new Date());
//        u.setDateUpdated(new Date());
//        return userRepository.save(u);
//    }
}
