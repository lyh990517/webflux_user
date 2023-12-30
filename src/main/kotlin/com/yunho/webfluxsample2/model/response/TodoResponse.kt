package com.yunho.webfluxsample2.model.response

import lombok.Data

@Data
data class TodoResponse(
    val id: Int,
    val content: String
)
