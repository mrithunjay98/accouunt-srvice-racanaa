package com.racanaa.services.account.util;

import com.racanaa.services.account.constant.ApiConstant;
import com.racanaa.services.account.dto.SortDto;
import com.racanaa.services.account.persistance.criteria.SortCriteria;
import com.racanaa.services.account.security.apikey.ApiKey;
import com.racanaa.services.account.security.jwt.Token;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Sort;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Utility class for bean and dto manipulations
 *
 * @author Manohar
 * @since 25/Sep/2023
 */
@Slf4j
public class ObjectUtil {

    /**
     * Private constructor to avoid new()
     */
    private ObjectUtil() {
        throw new IllegalStateException(ApiConstant.UTILITY_CLASS_NO_CONSTRUCTOR);
    }

    /**
     * Returns the tenant id from API key stored in context
     *
     * @param context
     * @return String
     */
    public static String getTenantId(ApplicationContext context) {
        ApiKey apiKeyContext = context.getBean(ApiKey.class);
        if (apiKeyContext == null || apiKeyContext.getAccount() == null) {
            return null;
        }
        return apiKeyContext.getAccount().getTenantId();
    }

    /**
     * Returns the user id from the JWT token
     *
     * @param context
     * @return String
     */
    public static String getUserId(ApplicationContext context) {
        Token tokenContext = context.getBean(Token.class);
        if (tokenContext == null || tokenContext.getUser() == null) {
            return null;
        }
        return tokenContext.getUser().getId();
    }

    /**
     * Returns a Sort object used with JPA
     * If the SortDto is null it falls back to the default sort that sort by dateUpdated desc
     * <p>
     * Example:
     *
     * <code>
     * Sort sort = Sort.by("firstname").ascending()
     * .and(Sort.by("lastname").descending());
     * </code>
     *
     * @param sortDto
     * @return Sort
     */
    public static Sort getSortObject(SortDto sortDto) {
        if (sortDto == null || sortDto.getCriteria() == null || sortDto.getCriteria().isEmpty()) {
            return Sort.by(ApiConstant.DEFAULT_SORT_BY).descending();
        } else {
            Sort sort = null;
            for (int i = 0; i < sortDto.getCriteria().size(); i++) {
                SortCriteria criteria = sortDto.getCriteria().get(i);
                Sort eachSort = Sort.by(criteria.getBy());
                if (criteria.getOrder().equalsIgnoreCase(ApiConstant.SORT_ORDER_ASC)) {
                    eachSort = eachSort.ascending();
                } else {
                    eachSort = eachSort.descending();
                }

                if (sort == null) {
                    sort = eachSort;
                } else {
                    sort = sort.and(eachSort);
                }
            }
            return sort;
        }
    }

    /**
     * Returns a default sort dto object. The default sort that sort by dateUpdated desc.
     *
     * @return SortDto
     */
    public static SortDto getDefaultSortDto() {
        SortDto dto = new SortDto();
        List<SortCriteria> sortCriteriaList = new ArrayList<>();
        SortCriteria criteria = new SortCriteria(ApiConstant.DEFAULT_SORT_BY, ApiConstant.DEFAULT_SORT_ORDER);
        sortCriteriaList.add(criteria);
        dto.setCriteria(sortCriteriaList);
        return dto;
    }

    /**
     * Copies non null fields from source to destination object
     *
     * @param source      source object
     * @param destination destination object
     * @param <T>
     * @throws IllegalAccessException
     */
    public static <T> void copyNonNullFields(T source, T destination) {
        try {
            if (source == null || destination == null) return;
            if (!destination.getClass().equals(source.getClass())) {
                throw new IllegalArgumentException(destination.getClass() + " is of a different type than " + source.getClass());
            }
            final List<Field> fields = getAllFields(source.getClass());
            for (Field field : fields) {
                field.setAccessible(true);
                final Object fieldValue = field.get(source);
                if (fieldValue != null) {
                    field.set(destination, fieldValue);
                }
            }
        }catch (IllegalAccessException e){
            throw new IllegalArgumentException(destination.getClass() + " is of a different type than " + source.getClass());
        }
    }

    /**
     * Returns a list of all the fields of a class
     *
     * @param clazz class
     * @return List<Field>
     */
    private static List<Field> getAllFields(Class<?> clazz) {
        List<Field> fields = new ArrayList<>();
        do {
            Collections.addAll(fields, clazz.getDeclaredFields());
            clazz = clazz.getSuperclass();
        } while (clazz != null);
        return fields;
    }
}
