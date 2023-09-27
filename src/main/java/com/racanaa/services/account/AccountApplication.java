package com.racanaa.services.account;

import com.racanaa.services.account.constant.ApiConstant;
import com.racanaa.services.account.persistance.manager.SystemConfigManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Arrays;

/**
 * Spring boot application
 *
 * @author Manohar
 * @since 1.0
 */
@EnableWebMvc
@EnableJpaAuditing
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class AccountApplication implements ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(AccountApplication.class);
    @Autowired
    private SystemConfigManager systemConfigManager;
    @Autowired
    private ApplicationContext context;

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(AccountApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        initApplicationData();
    }

    /**
     * Initialize DB configurations for the application setup
     */
    public void initApplicationData() {
        logger.info("Service started: " +
                            systemConfigManager.getString(ApiConstant.DB_SERVICE_NAME_CONFIG_KEY,
                                    ApiConstant.DEFAULT_SERVICE_NAME));
        if (systemConfigManager.getBoolean(ApiConstant.DB_INSPECT_BEANS_CONFIG_KEY)) {
            logBeansInitiated(context);
        }
    }

    /**
     * Log Beans initialized by spring boot for debugging purpose. This can be enabled dynamically using the system
     * config
     *
     * @param ctx
     */
    public static void logBeansInitiated(ApplicationContext ctx) {
        logger.info("Let's inspect the beans provided by Spring Boot.....");
        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            logger.info(beanName);
        }
    }

}
