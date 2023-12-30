package com.yunho.webfluxsample2.repository

import com.yunho.webfluxsample2.entity.Auth
import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthRepository : R2dbcRepository<Auth, Long> {
}