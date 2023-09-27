package com.racanaa.services.account.controller;

import com.racanaa.services.account.constant.ApiConstant;
import com.racanaa.services.account.dto.ListResponseDto;
import com.racanaa.services.account.persistance.model.SystemConfig;
import com.racanaa.services.account.service.SystemConfigService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import static com.racanaa.services.account.constant.ApiConstant.API_CONTEXT;

@Slf4j
@RestController
@Tag(name = "System Configurations", description = "Manage system configurations")
@Component("SystemConfigController")
@RequestMapping(API_CONTEXT + "/configs")
public class SystemConfigController {

    @Autowired
    SystemConfigService systemConfigService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ListResponseDto<SystemConfig>> list(
            @RequestParam(value = ApiConstant.PARAM_OFFSET_NAME,
                    defaultValue = ApiConstant.PARAM_OFFSET_DEFAULT,
                    required = false) int offset,
            @RequestParam(value = ApiConstant.PARAM_LIMIT_NAME,
                    defaultValue = ApiConstant.PARAM_LIMIT_DEFAULT,
                    required = false) int limit,
            @RequestParam(value = ApiConstant.DEFAULT_SORT_FIELD_NAME,
                    defaultValue = ApiConstant.DEFAULT_SORT_BY,
                    required = false) String sortBy,
            @RequestParam(value = ApiConstant.DEFAULT_SORT_ORDER,
                    defaultValue = ApiConstant.DEFAULT_SORT_ORDER,
                    required = false) String sortDir) {
        return new ResponseEntity<>(systemConfigService.list(offset, limit, sortBy, sortDir), HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET, path = "/name/{name}")
    public ResponseEntity<String> getByName(@PathVariable String name) {
        String config = systemConfigService.getString(name);
        return new ResponseEntity<>(config, HttpStatus.OK);
    }

    /**
     * Get user by id
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<SystemConfig> getById(@PathVariable String id) {
        return ResponseEntity.ok(systemConfigService.getById(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<SystemConfig> create(@RequestBody SystemConfig systemConfig) {
        return new ResponseEntity<>(systemConfigService.create(systemConfig), HttpStatus.CREATED);
    }
}
