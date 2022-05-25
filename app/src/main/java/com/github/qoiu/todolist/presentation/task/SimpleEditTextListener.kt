package com.github.qoiu.todolist.presentation.task

import android.text.Editable
import android.text.TextWatcher

class SimpleEditTextListener(private val after: (s: Editable?) -> Unit) : TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

    override fun afterTextChanged(s: Editable?) {
        after.invoke(s)
    }
}