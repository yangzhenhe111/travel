package com.cy.travels;

import com.cy.travels.controller.UserController;
import com.cy.travels.model.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.websocket.WsSession;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.context.WebApplicationContext;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.Resource;
import javax.websocket.Session;
import java.util.List;

@EnableSwagger2
@Slf4j
@MapperScan("com.cy.travels.dao")
@SpringBootApplication
public class TravelsApplication {

    public static void main(String[] args) {
        SpringApplication.run(TravelsApplication.class, args);
    }

}
