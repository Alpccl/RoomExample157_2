package com.crisspian.roomexample157_2.model

import androidx.lifecycle.LiveData
import androidx.room.*
import java.util.concurrent.Flow

@Dao
interface TaskDao {

    // Inserta una tarea
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task : TaskEntity)

    // Inserta un listado de tareas
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTask(listTask: List<TaskEntity>)

    //Actualizar una tarea
    @Update
    suspend fun updateTask(task: TaskEntity)

    //Borrar una tarea
    @Delete
    suspend fun deleteTask(task: TaskEntity)

    // Borra todos los elementos de la tabla
    @Query("DELETE FROM task_table")
    suspend fun deleteAll()

    // Traer todos los elementos de la tabla (LEER)
    @Query("SELECT * FROM task_table ORDER BY id DESC")
    fun getAllTask() : LiveData<List<TaskEntity>>

    // Trae una tarea buscada por titulo y limita a 1 respuesta
    @Query("SELECT * FROM task_table WHERE title = :title LIMIT 1")
    fun getTaskByTitle(title: String) : LiveData<TaskEntity>

    //Trae una tarea por ID
    @Query("SELECT * FROM task_table WHERE id = :id")
    fun getTaskByID(id: Int) : LiveData<TaskEntity>

}