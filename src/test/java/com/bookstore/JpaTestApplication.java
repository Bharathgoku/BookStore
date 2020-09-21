package com.bookstore;

import com.bookstore.config.RestTemplateConfig;
import com.bookstore.controller.MediaCoverageController;
import com.bookstore.external.MediaCoverageApiManager;
import com.bookstore.service.MediaCoverageService;
import com.bookstore.util.HttpRequestUtil;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;

@PropertySource(value = "classpath:application-test.properties", encoding = "UTF-8")
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
    classes = {MediaCoverageService.class, MediaCoverageApiManager.class, HttpRequestUtil.class, RestTemplateConfig.class, MediaCoverageController.class}),basePackages = {"com.bookstore.*"})
@SpringBootApplication(exclude = {MediaCoverageService.class, MediaCoverageApiManager.class, HttpRequestUtil.class,RestTemplateConfig.class, MediaCoverageController.class},scanBasePackages = "com.bookstore.*")
public class JpaTestApplication {

}
