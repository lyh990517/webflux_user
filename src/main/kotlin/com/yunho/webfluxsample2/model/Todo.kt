package com.yunho.webfluxsample2.model

import lombok.Data

@Data
data class Todo(
    val id: Int,
    val content: String
)
