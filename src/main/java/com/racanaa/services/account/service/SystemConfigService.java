package com.racanaa.services.account.service;

import com.racanaa.services.account.dto.ListResponseDto;
import com.racanaa.services.account.persistance.manager.SystemConfigManager;
import com.racanaa.services.account.persistance.model.SystemConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component("SystemConfigService")
public class SystemConfigService {
    @Autowired
    private SystemConfigManager systemConfigManager;

    public ListResponseDto<SystemConfig> list(int offset, int limit, String sortBy, String sortOrder) {
        return systemConfigManager.list(offset, limit, sortBy, sortOrder);
    }

    public SystemConfig getById(String id) {
        return systemConfigManager.getById(id);
    }

    public SystemConfig deleteById(String id) {
        return systemConfigManager.getById(id);
    }

    public SystemConfig create(SystemConfig systemConfig) {
        return systemConfigManager.create(systemConfig);
    }

    public String getString(String name) {
        return systemConfigManager.getByName(name);
    }

}
