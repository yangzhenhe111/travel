package com.cy.travels.config;

import com.cy.travels.model.entity.User;
import com.fasterxml.classmate.ResolvedType;
import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;


@Configuration //配置类
@EnableSwagger2// 开启Swagger2的自动配置
public class SwaggerConfig implements WebMvcConfigurer {

    @Bean
    public Docket docket(Environment environment) {
        // 设置要显示swagger的环境
        Profiles of = Profiles.of("local", "dev", "test");
        // 判断当前是否处于该环境
        // 通过 enable() 接收此参数判断是否要显示
        boolean b = environment.acceptsProfiles(of);


        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(b) //配置是否启用Swagger，如果是false，在浏览器将无法访问
                .select()// 通过.select()方法，去配置扫描接口,RequestHandlerSelectors配置如何扫描接口
                .apis(RequestHandlerSelectors.basePackage("com.cy.travels.controller"))
                // 配置如何通过path过滤,任意接口
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(addRequestHeader());
    }

    private List<Parameter> addRequestHeader(){
        User user = new User();
        user.setId(1L);
        user.setUsername("zhangsan");
        user.setPassword("123");

        ParameterBuilder parameterBuilder = new ParameterBuilder();
        List<Parameter> parameters = new ArrayList<>();
        parameterBuilder.name("header-user")//设置header的key，一定不要和接口的参数重名，否则在swagger里会产生混淆，无法使用
                .description("Swagger默认请求头")
                .modelRef(new ModelRef("String"))
                .parameterType("header")
                .defaultValue(new Gson().toJson(user))//添加默认值value
                .required(true)
                .build();
        parameters.add(parameterBuilder.build());

//        parameterBuilder.name("header")//设置header的key
//                .description("Swagger默认请求头")
//                .modelRef(new ModelRef("String"))
//                .parameterType("header")
//                .defaultValue(new Gson().toJson(user))//添加默认值value
//                .required(true)
//                .build();
//        parameters.add(parameterBuilder.build());
        return parameters;

    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("SpringBootDemo-API实时接口文档")
                .description("用于管理、查看、测试API")
                .version("v1.0.0")
                .build();
    }
}
