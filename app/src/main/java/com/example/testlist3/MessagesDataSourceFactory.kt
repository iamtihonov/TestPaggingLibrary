package com.example.testlist3

import androidx.paging.DataSource as DataSource1

/**
 * Создает и пересоздает MessagesDataSource по необходимости
 */
internal class MessagesDataSourceFactory : DataSource1.Factory<Int, MessageModel>() {

    var dataSource: MessagesDataSource? = null

    /**
     * Что бы всегда использовать одни и те же объекты
     */
    private val messages = ArrayList<MessageModel>()

    init {
        for(index in 0..39) {
            messages.add(MessageModel(index.toString(), index))
        }
    }

    override fun create(): DataSource1<Int, MessageModel>? {
        dataSource = MessagesDataSource(messages)
        return dataSource
    }
}