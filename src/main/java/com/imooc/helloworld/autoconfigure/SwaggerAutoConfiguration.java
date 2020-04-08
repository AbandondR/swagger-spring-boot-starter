package com.imooc.helloworld.autoconfigure;

import com.google.common.base.Predicates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.util.StopWatch;
import org.springframework.util.StringUtils;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * swagger API 文档 自动配置
 *
 * @author Yu
 * @since 2020/3/27
 */

@Configuration
@ConditionalOnClass(ApiInfo.class)
@AutoConfigureAfter(WebMvcAutoConfiguration.class)
@EnableConfigurationProperties({SwaggerProperty.class})
@Profile("swagger")
@EnableSwagger2
public class SwaggerAutoConfiguration {

    private final static Logger LOGGER = LoggerFactory.getLogger(SwaggerAutoConfiguration.class);

    private final SwaggerProperty swaggerProperty;

    public SwaggerAutoConfiguration(ObjectProvider<SwaggerProperty> swaggerPropertyProvider) {
        this.swaggerProperty = swaggerPropertyProvider.getIfAvailable();
    }

    @Bean("docket")
    public Docket createRestDocApi() {
        LOGGER.info("======== starting swagger =======");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        ApiInfo apiInfo = new ApiInfoBuilder()
                .title(swaggerProperty.getTitle())
                .description(swaggerProperty.getDescription())
                .version(swaggerProperty.getVersion())
                .contact(swaggerProperty.getContact().getContact())
                .license(swaggerProperty.getLicense())
                .licenseUrl(swaggerProperty.getLicenseUrl())
                .termsOfServiceUrl(swaggerProperty.getTermsOfServiceUrl())
                .extensions(Arrays.asList(swaggerProperty.getVendorExtensions()))
                .build();


        boolean hasPaths = swaggerProperty.getPaths().length > 0;
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .enable(swaggerProperty.isEnable())
                .enableUrlTemplating(swaggerProperty.isEnableUrlTemplating())
                .select()
                .apis(StringUtils.isEmpty(swaggerProperty.getBasePackages()) ? RequestHandlerSelectors.any() : RequestHandlerSelectors.basePackage(swaggerProperty.getBasePackages()))
                .paths( !hasPaths ? PathSelectors.any() : Predicates.or(Arrays.stream(swaggerProperty.getPaths()).map(PathSelectors::ant).collect(Collectors.toList())))
                .build();
        stopWatch.stop();
        LOGGER.info("============= end swagger documentation----{} seconds ===========",stopWatch.getTotalTimeSeconds());
        return docket;
    }


}
