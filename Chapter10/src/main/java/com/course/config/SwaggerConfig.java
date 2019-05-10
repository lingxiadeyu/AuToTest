package com.course.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*
Swaggerui的配置类
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .pathMapping("/")
                .select()
                .paths(PathSelectors.regex("/.*"))
                .build();
    }
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder().title("后服务项目组接口文档")//接口文档的title
                .contact(new Contact("qinzhenxia","","610493912@qq.com"))
                .description("洗车、保养、罚单代缴、特权加油、加油充值接口文档")//接口文档的描述
                .build();

    }
}
