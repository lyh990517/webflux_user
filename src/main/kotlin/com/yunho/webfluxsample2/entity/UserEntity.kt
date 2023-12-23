package com.yunho.webfluxsample2.entity

import lombok.Data

@Data
data class UserEntity(
    val id: Int,
    val name: String,
    val todo: Int
)
