package com.yunho.webfluxsample2.filter

import com.yunho.webfluxsample2.auth.Authentication
import com.yunho.webfluxsample2.service.AuthService
import org.springframework.http.HttpStatus
import org.springframework.security.core.context.ReactiveSecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono

@Component
class SecurityFilter(private val authService: AuthService): WebFilter {
    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        val token = exchange.request.headers.getFirst("token") ?: "not exist"
        return authService.getNameByToken(token).map{
            Authentication(it.token)
        }
            .flatMap { auth ->
                chain.filter(exchange)
                    .contextWrite{ context ->
                        val newContext = ReactiveSecurityContextHolder.withAuthentication(auth)
                        context.putAll(newContext)
                    }
            }
    }
}