package com.yunho.webfluxsample2.entity

import lombok.Data
import org.springframework.data.relational.core.mapping.Table

@Data
@Table("USER")
data class UserEntity(
    val id: Int,
    val name: String,
    val todo: Int
)
