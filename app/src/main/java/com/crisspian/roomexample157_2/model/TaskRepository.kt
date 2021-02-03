package com.crisspian.roomexample157_2.model

import androidx.lifecycle.LiveData

class TaskRepository(private val taskDao: TaskDao) {

    //Este value va a contener Toda la info de la base de datos.
    val listAllTask : LiveData<List<TaskEntity>> = taskDao.getAllTask()

    suspend fun insertTask(task: TaskEntity) {
        taskDao.insertTask(task)
    }

    suspend fun updateTask(task: TaskEntity){
        taskDao.updateTask(task)
    }

    suspend fun deleteAll() {
        taskDao.deleteAll()
    }

    fun getTaskByID(id: Int): LiveData<TaskEntity> {
        return taskDao.getTaskByID(id)
    }

}