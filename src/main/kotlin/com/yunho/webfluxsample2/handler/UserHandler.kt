package com.yunho.webfluxsample2.handler

import com.yunho.webfluxsample2.service.UserService
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class UserHandler(private val userService: UserService) {
    fun getUserById(serverRequest: ServerRequest): Mono<ServerResponse> {
        val userId = serverRequest.pathVariables()["id"]?.toLong() ?: 1

        return userService.getUserById(userId).flatMap {
            ServerResponse.ok().bodyValue(it)
        }
    }

    fun getUsers(serverRequest: ServerRequest): Mono<ServerResponse> {
        return userService.getUsers().flatMap {
            ServerResponse.ok().bodyValue(it)
        }
    }
}