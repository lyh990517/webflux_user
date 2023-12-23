package com.yunho.webfluxsample2.entity

import lombok.Data

@Data
data class User(
    val id: Int,
    val name: String,
    val todo: Todo
)
