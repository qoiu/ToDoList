package com.github.qoiu.todolist.domain

import com.github.qoiu.todolist.domain.entities.CategoryData
import com.github.qoiu.todolist.domain.entities.ResultData

interface CategoryRepository : Repository<CategoryData> {
    suspend fun fetch(): ResultData<CategoryData>
}