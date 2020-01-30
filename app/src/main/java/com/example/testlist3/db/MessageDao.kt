package com.example.testlist3.db

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface MessageDao {
    @Query("SELECT * FROM messages ORDER BY position ASC")
    fun selectPaged(): DataSource.Factory<Int, MessageModel>

    @Insert
    fun insert(message: ArrayList<MessageModel>)

    @Insert
    fun insert(message: MessageModel)

    @Update
    fun update(message: MessageModel)

    @Query("DELETE FROM messages")
    fun nukeTable()
}