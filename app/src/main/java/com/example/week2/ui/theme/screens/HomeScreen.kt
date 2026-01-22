package com.example.week2.ui.theme.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.week2.domain.Task
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HomeScreen(taskViewModel: TaskViewModel = viewModel()) {

    val visibleTasks = if (taskViewModel.doneTasks) {
        taskViewModel.tasks.filter { it.done }
    } else {
        taskViewModel.tasks
    }


    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text(
                text = "My Tasks",
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Row(
                modifier = Modifier.padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(onClick = {
                    taskViewModel.sortByDueDate()
                }, colors = ButtonDefaults.buttonColors(containerColor = Color.Magenta)) {
                    Text(text = "Sort by due date")
                }
                Button(onClick = {
                    taskViewModel.filterByDone()
                }, colors = ButtonDefaults.buttonColors(containerColor = Color.Magenta)) {
                    Text(if (taskViewModel.doneTasks) "Show all" else "Show done")
                }
            }
            Column {
                var text by remember { mutableStateOf("") }
                OutlinedTextField(
                    value = text,
                    singleLine = true,
                    onValueChange = { text = it },
                    label = { Text("Task") }
                )
                Button(onClick = {
                    val newTask = Task(
                        id = taskViewModel.tasks.size + 1,
                        title = text,
                        description = "New task added!",
                        priority = 6,
                        dueDate = "2026-01-06",
                        done = false
                    )
                    taskViewModel.addTask(newTask)
                    text = ""

                }, colors = ButtonDefaults.buttonColors(containerColor = Color.Magenta)) {
                    Text(text = "Add task")
                }
            }
            LazyColumn() {
                items(items = visibleTasks) { item ->
                    TaskRow(
                        task = item,
                        onToggleDone = {
                            taskViewModel.toggleDone(item.id)
                        },
                        onDelete = {
                            taskViewModel.removeTask(item.id)
                        }
                    )
                }
            }
        }
    }
}



@Composable
fun TaskRow(
    task: Task,
    onToggleDone: () -> Unit,
    onDelete: () -> Unit
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(8.dp)
    ) {
        Checkbox(
            checked = task.done,
            onCheckedChange = { onToggleDone() }
        )

        Column(modifier = Modifier
            .weight(1f)
            .padding(start = 8.dp)
        )
        {
            Text(text = task.title)
            Text(text = task.dueDate)
        }

        Button(onClick = onDelete,
            colors = ButtonDefaults.buttonColors(containerColor = Color.Magenta)) {
            Text("Delete")
        }
    }
}


