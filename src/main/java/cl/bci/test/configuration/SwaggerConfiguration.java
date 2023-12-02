package cl.bci.test.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@EnableSwagger2 // http://localhost:8080/swagger-ui.html
@Configuration
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .select()
                .apis(RequestHandlerSelectors.basePackage("cl.bci.test.controller"))
                .paths(PathSelectors.any())
                .build()
                .genericModelSubstitutes(ResponseEntity.class)
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .tags( new springfox.documentation.service.Tag("User", "Gestión de usuarios"));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("BCI-Test")
                .description("Microservicios Backend que controla la gestión de usuarios")
                .license("")
                .licenseUrl("")
                .termsOfServiceUrl("")
                .version("1.0.0")
                .build();
    }
}
