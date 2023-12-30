package com.yunho.webfluxsample2.model.response

import com.yunho.webfluxsample2.model.Todo
import lombok.Data

@Data
data class TodoListResponse(
    val list : List<Todo>
)
