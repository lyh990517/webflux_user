package com.yunho.webfluxsample2.service

import lombok.Data

@Data
data class TodoResponse(
    val id: Int,
    val content: String
)
