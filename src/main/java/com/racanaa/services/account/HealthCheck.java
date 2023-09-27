package com.racanaa.services.account;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.racanaa.services.account.constant.ApiConstant.API_STATUS_RUNNING;

/**
 * Health check controller to check the api status
 *
 * @author Manohar
 * @since 20/Sep/2023
 */
@Slf4j
@RestController
@RequestMapping("/health")
public class HealthCheck {
    @RequestMapping(method = RequestMethod.GET)
    public String health() {
        return API_STATUS_RUNNING;
    }
}
