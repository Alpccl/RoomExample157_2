package com.crisspian.roomexample157_2.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crisspian.roomexample157_2.model.TaskDataBase
import com.crisspian.roomexample157_2.model.TaskEntity
import com.crisspian.roomexample157_2.model.TaskRepository
import kotlinx.coroutines.launch


class TaskViewModel(application: Application) : AndroidViewModel(application) {

    // variable que representa al repositorio
    private val repository: TaskRepository
    //LiveData que expone la info del modelo.
    val allTask : LiveData<List<TaskEntity>>

    init {
        val taskDao = TaskDataBase.getDataBase(application).getTaskDao()
        repository = TaskRepository(taskDao)
        allTask = repository.listAllTask
    }

    // Aqui manejamos la coroutina
     fun insertTask(task: TaskEntity) = viewModelScope.launch {
        repository.insertTask(task)
    }


}