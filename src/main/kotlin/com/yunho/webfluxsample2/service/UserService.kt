package com.yunho.webfluxsample2.service

import com.yunho.webfluxsample2.entity.Todo
import com.yunho.webfluxsample2.entity.User
import com.yunho.webfluxsample2.repository.UserRepository
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
@Service
class UserService(private val userRepository: UserRepository) {

    private val webClient = WebClient.create("http://localhost:8081")

    fun getUserById(id: Int): Mono<User> {
        return userRepository.getUserById(id).flatMap { entity ->
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
        return userRepository.getUsers()
            .flatMap { list ->
                webClient
                    .get()
                    .uri("/api")
                    .retrieve()
                    .toEntity(TodoListResponse::class.java)
                    .map {
                        it.body?.let { response ->
                            list.map { entity ->
                                User(entity.id,entity.name,response.list[entity.todo])
                            }
                        }
                    }
            }
    }
}