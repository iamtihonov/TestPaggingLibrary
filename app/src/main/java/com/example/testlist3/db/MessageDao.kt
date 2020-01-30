package com.example.testlist3.db

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface MessageDao {
    @Query("SELECT * FROM messages ORDER BY position ASC")
    fun selectPaged(): DataSource.Factory<Int, MessageModel>

    @Insert
    fun insert(message: MessageModel)

    @Query("DELETE FROM messages")
    fun nukeTable()
}