/**
 * 
 */
package com.yls.bus.sys.swagger;

import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * swagger2 ≈‰÷√
 * @author Lian Shan Yang
 *
 */
@EnableSwagger2
public class SwaggerCfg {

    @Bean  
    public Docket createRestApi() {  
        return new Docket(DocumentationType.SWAGGER_2)  
                .apiInfo(apiInfo())  
                .select()  
                .apis(RequestHandlerSelectors.any())  
                .paths(PathSelectors.any())  
                .build(); 
    }

	private ApiInfo apiInfo() {  
        return new ApiInfoBuilder()  
                .title("Project_d API Doc")  
                .termsOfServiceUrl("https://github.com/yls705048720/project_d.git")  
                .contact(new Contact("Lianshan Yang", "https://github.com/yls705048720/project_d.git", "705048720@qq.com"))  
                .version("1.0.0")  
                .build();  
    } 
}
