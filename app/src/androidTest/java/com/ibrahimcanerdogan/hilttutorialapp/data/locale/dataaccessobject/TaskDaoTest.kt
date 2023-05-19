package com.ibrahimcanerdogan.hilttutorialapp.data.locale.dataaccessobject

import android.app.Application
import android.provider.ContactsContract.Data
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.ibrahimcanerdogan.hilttutorialapp.data.locale.database.DatabaseService
import com.ibrahimcanerdogan.hilttutorialapp.data.locale.entities.TaskLocalEntity
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TaskDaoTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var databaseService : DatabaseService
    private lateinit var dao: TaskDao

    @Before
    fun setup() {
        databaseService = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            DatabaseService::class.java
        ).allowMainThreadQueries().build()

        dao = databaseService.taskDao()
    }

    @After
    fun tearDown() {
        databaseService.close()
    }

    @Test
    fun given_task_when_insert_then_return_should_contain_task_list() = runBlockingTest {
        val item = TaskLocalEntity(
            id = 1,
            userId = 1,
            title = "test title 1",
            body = "test body 1",
            note = "test note 1",
            status = "STARTED",
            createdAt = "",
            updatedAt = ""
        )

        val result = dao.insert(item)
        val itemList = dao.getTasks()

        assertThat(result).isEqualTo(1)
        assertThat(itemList).contains(item)
    }

    @Test
    fun given_task_list_when_insert_then_return_should_contain_task() = runBlockingTest {
        val taskList = listOf(TaskLocalEntity(
            id = 1,
            userId = 1,
            title = "test title 1",
            body = "test body 1",
            note = "test note 1",
            status = "STARTED",
            createdAt = "",
            updatedAt = ""
        ),TaskLocalEntity(
            id = 2,
            userId = 1,
            title = "test title 2",
            body = "test body 2",
            note = "test note 2",
            status = "STARTED",
            createdAt = "",
            updatedAt = ""
        ))

        val result = dao.insertMany(taskList)
        val itemList = dao.getTasks()

        assertThat(result.size).isEqualTo(2)
        assertThat(itemList).contains(TaskLocalEntity(
            id = 2,
            userId = 1,
            title = "test title 2",
            body = "test body 2",
            note = "test note 2",
            status = "STARTED",
            createdAt = "",
            updatedAt = ""
        ))
        assertThat(itemList.size).isEqualTo(taskList.size)
    }
}