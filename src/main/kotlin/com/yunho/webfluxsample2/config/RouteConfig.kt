package com.yunho.webfluxsample2.config

import com.yunho.webfluxsample2.handler.UserHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.router


@Configuration
class RouteConfig(private val userHandler: UserHandler) {

    @Bean
    fun route() = router {
        "/users".nest {
            GET("", userHandler::getUsers)
            GET("/{id}", userHandler::getUserById)
            POST("/signup",userHandler::registerUser)
            DELETE("",userHandler::deleteUser)
        }
    }
}