package com.racanaa.services.account.persistance.manager;

import com.racanaa.services.account.constant.ApiConstant;
import com.racanaa.services.account.dto.ListResponseDto;
import com.racanaa.services.account.exception.DataNotFoundException;
import com.racanaa.services.account.persistance.model.SystemConfig;
import com.racanaa.services.account.persistance.repository.SystemConfigRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;

@Transactional
@Slf4j
@Component("SystemConfigManager")
public class SystemConfigManager {
    @Autowired
    private SystemConfigRepository systemConfigRepository;

    public ListResponseDto<SystemConfig> list(int offset, int limit, String sortBy, String sortOrder) {
        long totalCount = systemConfigRepository.count();
        Sort sort = (sortBy != null) ? Sort.by(sortBy) : Sort.by(ApiConstant.DEFAULT_SORT_BY);
        String sortO = (sortOrder != null && sortOrder.equalsIgnoreCase(ApiConstant.SORT_ORDER_ASC)) ? sortOrder : ApiConstant.DEFAULT_SORT_ORDER;
        if (sortO.equalsIgnoreCase(ApiConstant.SORT_ORDER_DESC)) {
            sort = sort.descending();
        } else {
            sort = sort.ascending();
        }
        Pageable paginate =
                PageRequest.of(offset - 1, limit, sort);
        Page<SystemConfig> page = systemConfigRepository.findAll(paginate);
        log.info(page.getContent().toString());
        Iterable<SystemConfig> accounts = page.getContent();
        return new ListResponseDto<>(accounts, totalCount, offset, limit, null, null);
    }

    public SystemConfig getById(String id) {
        return systemConfigRepository.findById(id).orElseThrow(
                () ->
                        new DataNotFoundException("System config not found with name: " + id)
        );
    }

    public String getByName(String name) {
        SystemConfig config = systemConfigRepository.findByName(name);
        return config.getValue();
    }

    public void deleteById(String id) {
        SystemConfig systemConfig = systemConfigRepository.findById(id).orElseThrow(
                () ->
                        new DataNotFoundException("System config not found with name: " + id)
        );
        systemConfigRepository.deleteById(systemConfig.getName());
    }

    public SystemConfig create(SystemConfig systemConfig) {
        systemConfig.setDateCreated(new Date());
        systemConfig.setDateUpdated(new Date());
        return systemConfigRepository.save(systemConfig);
    }


    public String getValue(String name) {
        SystemConfig systemConfig = getById(name);
        return systemConfig != null ? systemConfig.getValue() : null;
    }

    public String getString(String name) {
        SystemConfig systemConfig = getById(name);
        return systemConfig.getValue();
    }

    public String getString(String name, String defaultValue) {
        SystemConfig systemConfig = getById(name);
        return systemConfig != null ? systemConfig.getValue() : defaultValue;
    }

    /**
     * Fetches the boolean system config from db.
     *
     * @param name name of the config
     * @return the boolean value
     */
    public Boolean getBoolean(String name) {
        Boolean typeValue = null;
        String value = this.getValue(name);
        if (value != null) {
            typeValue = value.equalsIgnoreCase("true") || value.equalsIgnoreCase("t")
                    || value.equalsIgnoreCase("yes") || value.equalsIgnoreCase("y")
                    || value.equalsIgnoreCase("1");
        }
        return typeValue;
    }

    /**
     * Fetches the boolean system config from db. If the config is not present, return the default value.
     *
     * @param name       name of the config
     * @param defaultVal the default boolean value
     * @return the boolean value
     */
    public Boolean getBoolean(String name, Boolean defaultVal) {
        Boolean typeValue;
        String value = this.getValue(name);
        if (value != null) {
            typeValue = value.equalsIgnoreCase("true") || value.equalsIgnoreCase("t")
                    || value.equalsIgnoreCase("yes") || value.equalsIgnoreCase("y")
                    || value.equalsIgnoreCase("1");
        } else {
            return defaultVal;
        }
        return typeValue;
    }

    /**
     * Fetches the system config with multiple integers in value as an array.
     *
     * @param value     name of system config
     * @param separator character by with the list is separated
     * @return an array of integers
     */
    public Integer[] getIntegerArray(String value, String separator) {
        String strList = getValue(value);
        if (strList == null) {
            return new Integer[0];
        }
        int[] intArr = Arrays.stream(strList.split(separator)).map(String::trim).mapToInt(Integer::parseInt)
                .toArray();
        return Arrays.stream(intArr).boxed().toArray(Integer[]::new);
    }

    public int getIntValue(String name, int defaultValue) {
        SystemConfig systemConfig = getById(name);
        return systemConfig != null ? Integer.parseInt(systemConfig.getValue()) : defaultValue;
    }

}
