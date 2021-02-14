package org.kodluyoruz.mybank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                //.apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build();
    }
        /*
      @Bean
        public Docket docket() {
          return new Docket(DocumentationType.OAS_30)
              .apiInfo(new ApiInfoBuilder()
                  .title("Note API")
                  .description("A CRUD API to demonstrate Springfox 3 integration")
                  .version("0.0.1-SNAPSHOT")
                  .license("MIT")
                  .licenseUrl("https://opensource.org/licenses/MIT")
                  .build())
              .tags(new Tag("Note", "Endpoints for CRUD operations on notes"))
              .select().apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
              .build();
        }

         */


}