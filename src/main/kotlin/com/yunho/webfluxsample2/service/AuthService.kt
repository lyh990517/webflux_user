package com.yunho.webfluxsample2.service

import com.yunho.webfluxsample2.entity.AuthEntity
import com.yunho.webfluxsample2.repository.AuthRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

@Service
class AuthService(private val repository: AuthRepository) {
    fun getNameByToken(token: String): Mono<AuthEntity> {
        return repository.findAll().filter { it.token == token }.toMono()
    }

    fun registerToken(userId: Long): Mono<AuthEntity> {
        return repository.save(AuthEntity(userId,""))
    }
}