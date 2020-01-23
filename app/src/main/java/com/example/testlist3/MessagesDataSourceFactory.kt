package com.example.testlist3

import androidx.paging.DataSource as DataSource1

/**
 * Создает и пересоздает MessagesDataSource по необходимости
 */
internal class MessagesDataSourceFactory : DataSource1.Factory<MessageTestModel, MessageTestModel>() {

    var dataSource: MessagesDataSource? = null

    override fun create(): DataSource1<MessageTestModel, MessageTestModel>? {
        dataSource = MessagesDataSource()
        return dataSource
    }
}