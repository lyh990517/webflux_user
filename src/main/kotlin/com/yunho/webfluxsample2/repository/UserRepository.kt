package com.yunho.webfluxsample2.repository

import com.yunho.webfluxsample2.entity.UserEntity
import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty


@Repository
interface UserRepository : R2dbcRepository<UserEntity, Long>