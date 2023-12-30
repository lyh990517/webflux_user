package com.yunho.webfluxsample2.entity

import lombok.Data
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Data
@Table("Auth")
data class AuthEntity(
        @Id
        val userid: Long?,
        val token: String
)
