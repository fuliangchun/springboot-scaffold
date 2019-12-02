package com.springboot.scaffold.config;

import com.fasterxml.classmate.TypeResolver;
import com.springboot.scaffold.common.ExceptionVO;
import com.springboot.scaffold.common.FieldExceptionVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

/**
 * @author FULIANGCHUN288
 * @description
 * @date 2019年12月02日 09:56
 * @since 1.0.0
 **/
@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
@Slf4j
public class SwaggerConfiguration implements ApplicationListener<WebServerInitializedEvent> {

    @Bean
    public Docket createWebApi() {

        ModelRef errorModel = new ModelRef("ExceptionVO");

        List<ResponseMessage> responseMessages = Arrays.asList(
                new ResponseMessageBuilder().code(409).message("Conflict")
                        .responseModel(new ModelRef("FieldExceptionVO")).build(),
                new ResponseMessageBuilder().code(401).message("Unauthorized").responseModel(null).build(),
                new ResponseMessageBuilder().code(403).message("Forbiddon").responseModel(null).build(),
                new ResponseMessageBuilder().code(404).message("Not Found").responseModel(errorModel).build(),
                new ResponseMessageBuilder().code(500).message("Intenal Error").responseModel(errorModel).build());

        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.springboot.scaffold"))
                .paths(PathSelectors.any())
                .build().groupName("web接口")
                .apiInfo(apiInfo()).useDefaultResponseMessages(false)
                .additionalModels(typeResolver.resolve(FieldExceptionVO.class), typeResolver.resolve(ExceptionVO.class))
                .globalResponseMessage(RequestMethod.POST, responseMessages)
                .globalResponseMessage(RequestMethod.PUT, responseMessages)
                .globalResponseMessage(RequestMethod.GET, responseMessages)
                .globalResponseMessage(RequestMethod.DELETE, responseMessages);

    }

    @Autowired
    private TypeResolver typeResolver;

    private ApiInfo apiInfo() {
        Contact contact = new Contact("", "www.baidu.com", "xxxx");
        return new ApiInfoBuilder().title("scaffold api").contact(contact).description("XX系统 接口文档").build();

    }

    private String getAddress() {
        String hostAddress;
        try {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            hostAddress = "127.0.0.1";
        }
        return hostAddress;
    }


    @Override
    public void onApplicationEvent(WebServerInitializedEvent webServerInitializedEvent) {
        int serverPort = webServerInitializedEvent.getWebServer().getPort();
        log.info("swagger访问地址:http://{}:{}/swagger-ui.html",getAddress(),serverPort);
        System.err.println("swagger访问地址:http://" + getAddress() + ":" + serverPort + "/swagger-ui.html");
    }

}
