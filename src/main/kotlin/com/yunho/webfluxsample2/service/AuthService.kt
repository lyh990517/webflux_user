package com.yunho.webfluxsample2.service

import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class AuthService {

    private val token = mapOf("yunho" to 0, "hello" to 1)
    fun getNameByToken(id: String) : Mono<Int>{
        return Mono.just(token[id] ?: 0)
    }
}