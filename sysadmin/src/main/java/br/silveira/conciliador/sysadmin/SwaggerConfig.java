package br.silveira.conciliador.sysadmin;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig{

	 
	 @Bean
	    public Docket api() {
	        return new Docket(DocumentationType.SWAGGER_2)
	                .select()
	                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
	                .build()
	                .apiInfo(metaData());
	    }

	    private ApiInfo metaData() {
	        return new ApiInfoBuilder()
	                .title("Conciliador de Market Place")
	                .description("Orders API REST")
	                .version("1.0")
	                .contact(new Contact("Silveira Tech", "http://conciliador.silveiratech.com.br", "gusilviera@silveiratech.com.br"))
	                .license("Silveira Tech Brazil")
	                .licenseUrl("http:///")
	                .build();
	    }
}
