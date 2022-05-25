package com.github.qoiu.todolist.presentation.entity

sealed class UiElement : UiElementMapper.Ui {
    private val illegalStateException =
        IllegalStateException("Wrong mapper for class ${this.javaClass.simpleName}")

    override fun <T> map(mapper: UiElementToDomainMapper.Category): T {
        throw illegalStateException
    }

    override fun <T> map(mapper: UiElementToDomainMapper.Task): T {
        throw illegalStateException
    }

    data class Category(
        val id: Int,
        val name: String,
        private val completed: Boolean,
        val completion: Double
    ) : UiElement() {
        override fun <T> map(mapper: UiElementToDomainMapper.Category): T =
            mapper.map(id, name, completed, completion)

        fun update(name: String? = null) = Category(id, name ?: this.name, completed, completion)
    }

    data class Task(
        private val id: Int,
        private val list: String,
        val listId: Int,
        val name: String,
        val completed: Boolean
    ) : UiElement() {
        override fun <T> map(mapper: UiElementToDomainMapper.Task): T =
            mapper.map(id, list, listId, name, completed)

        fun update(
            id: Int? = null,
            name: String? = null,
            completed: Boolean? = null
        ) = Task(
            id ?: this.id,
            list, listId,
            name ?: this.name,
            completed ?: this.completed
        )

        fun comlete() = Task(id, list, listId, name, true)
    }

    object NewCategory : UiElement()
    object NewTask : UiElement()
}
