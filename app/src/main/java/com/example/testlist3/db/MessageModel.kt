package com.example.testlist3.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "messages")
class MessageModel (
     @ColumnInfo(name = "position") @PrimaryKey val position: Int,
     @ColumnInfo(name = "text") var text: String
)