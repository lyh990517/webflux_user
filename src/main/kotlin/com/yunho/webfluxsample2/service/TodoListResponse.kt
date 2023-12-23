package com.yunho.webfluxsample2.service

import com.yunho.webfluxsample2.entity.Todo
import lombok.Data

@Data
data class TodoListResponse(
    val list : List<Todo>
)
