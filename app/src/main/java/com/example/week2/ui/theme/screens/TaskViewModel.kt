package com.example.week2.ui.theme.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.week2.domain.*


class TaskViewModel : ViewModel() {

    private var _tasks by mutableStateOf(listOf<Task>())
    private var _doneTasks by mutableStateOf(false)

    val tasks: List<Task> get() = _tasks
    val doneTasks: Boolean get() = _doneTasks

    init {
        _tasks = mockTasks
    }

    fun toggleDone(id: Int) {
        _tasks = _tasks.map {
            if (it.id == id) it.copy(done = !it.done)
            else it
        }
    }
    fun removeTask(id: Int) {
        _tasks = _tasks.filter { it.id != id }
    }
    fun addTask(task: Task) {
        _tasks = _tasks + task
    }
    fun filterByDone() {
        _doneTasks = !_doneTasks
    }
    fun sortByDueDate() {
        _tasks = _tasks.sortedBy { it.dueDate }
    }

}