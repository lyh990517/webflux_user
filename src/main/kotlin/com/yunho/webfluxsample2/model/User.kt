package com.yunho.webfluxsample2.model

import lombok.Data

@Data
data class User(
    val id: Long,
    val name: String,
    val todo: Todo
)
