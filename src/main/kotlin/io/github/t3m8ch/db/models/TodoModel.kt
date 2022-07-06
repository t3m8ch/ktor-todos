package io.github.t3m8ch.db.models

data class TodoModel(val id: Int, var text: String, var isCompleted: Boolean) {
    override fun equals(other: Any?): Boolean {
        if (other is TodoModel) {
            return id == other.id
        }

        return super.equals(other)
    }

    override fun hashCode(): Int {
        return id
    }
}
