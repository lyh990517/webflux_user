package com.yunho.webfluxsample2.handler

import com.yunho.webfluxsample2.entity.UserEntity
import com.yunho.webfluxsample2.model.User
import com.yunho.webfluxsample2.service.AuthService
import com.yunho.webfluxsample2.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class UserHandler(
        private val userService: UserService,
        private val authService: AuthService
) {
    fun getUserById(serverRequest: ServerRequest): Mono<ServerResponse> {
        val userId = serverRequest.pathVariables()["id"]?.toLong() ?: 1

        return userService.getUserById(userId).flatMap {
            ServerResponse.ok().bodyValue(it)
        }
    }

    fun getUsers(serverRequest: ServerRequest): Mono<ServerResponse> {
        println(2)
        return userService.getUsers().flatMap {
            ServerResponse.ok().bodyValue(it)
        }
    }

    fun registerUser(serverRequest: ServerRequest): Mono<ServerResponse> {
        println(1)
        return serverRequest
                .bodyToMono(User::class.java)
                .flatMap { user ->
                    userService
                            .createUser(user)
                            .flatMap { body ->
                                authService
                                        .registerToken(user.id)
                                        .flatMap { auth ->
                                            ServerResponse
                                                    .ok()
                                                    .bodyValue(mapOf(body to auth))
                                        }
                            }
                }
    }

    fun deleteUser(serverRequest: ServerRequest): Mono<ServerResponse> {
        val id = serverRequest.headers().firstHeader("id")?.toLong() ?: 0
        return userService.deleteUser(id).flatMap {
            ServerResponse.ok().bodyValue(id)
        }
    }
}