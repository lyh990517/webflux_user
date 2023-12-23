package com.yunho.webfluxsample2.repository

import com.yunho.webfluxsample2.entity.UserEntity
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty


@Repository
class UserRepository {
    private val users = mutableListOf(UserEntity(0, "yunho", 0), UserEntity(1, "hello", 1))

    fun getUserById(id: Int): Mono<UserEntity> {
        return Mono.just(users.first { it.id == id })
            .switchIfEmpty {
                Mono.error(RuntimeException())
            }
    }

    fun getUsers(): Mono<List<UserEntity>> {
        return Mono.just(users.toList()).switchIfEmpty {
            Mono.error(RuntimeException())
        }
    }
}