package run.nya.toutiao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket buildDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("run.nya.toutiao.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(buildApiInf());
    }

    private ApiInfo buildApiInf() {
        return new ApiInfoBuilder()
                .title("TouTiao API Document with RESTful")
                .version("1.0.0")
                .contact(new Contact("HAIZAKURA", "https://nya.run", "i@nya.run"))
                .build();
    }

}
