package com.yunho.webfluxsample2.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.WebSecurityConfigurer
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain


//@Configuration
//class SecurityConfig {
//
//    @Bean
//    fun springSecurityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
//        return http
//                .csrf().disable() // CSRF 비활성화
//                .build()
//    }
//}