package com.yunho.webfluxsample2.service

import com.yunho.webfluxsample2.entity.UserEntity
import com.yunho.webfluxsample2.model.Todo
import com.yunho.webfluxsample2.model.User
import com.yunho.webfluxsample2.model.response.TodoListResponse
import com.yunho.webfluxsample2.model.response.TodoResponse
import com.yunho.webfluxsample2.repository.UserRepository
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class UserService(private val userRepository: UserRepository) {

    private val webClient = WebClient.create("http://localhost:8081")

    fun getUserById(id: Long): Mono<User> {
        return userRepository.findById(id).flatMap { entity ->
            webClient
                    .get()
                    .uri("/api/{id}", mapOf("id" to id))
                    .retrieve()
                    .toEntity(TodoResponse::class.java)
                    .map {
                        it.body?.let { response -> Todo(response.id, response.content) }
                    }.map { todo ->
                        User(entity.id, entity.name, todo)
                    }
        }

    }

    fun getUsers(): Mono<List<User>> {
        return userRepository.findAll().collectList()
                .flatMap { list ->
                    webClient
                            .get()
                            .uri("/api")
                            .retrieve()
                            .toEntity(TodoListResponse::class.java)
                            .map {
                                it.body?.let { response ->
                                    list.map { entity ->
                                        User(entity.id, entity.name, response.list[entity.todo])
                                    }
                                }
                            }
                }
    }

    fun createUser(user: User): Mono<User> {
        userRepository.save(UserEntity(user.id, user.name, user.todo.id)).map {
            user
        }.subscribe()
        return Mono.just(user)
    }

    fun deleteUser(id: Long): Mono<Void> {
        return userRepository.deleteById(id)
    }
}