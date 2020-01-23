package com.example.testlist3

import android.util.Log
import androidx.paging.ItemKeyedDataSource

internal class MessagesDataSource : ItemKeyedDataSource<MessageTestModel, MessageTestModel>() {

    companion object {
        const val TAG = "testBug2"
    }

    init {
        Log.e(TAG, "MessagesDataSource init()")
    }

    override fun loadInitial(params: LoadInitialParams<MessageTestModel>, callback: LoadInitialCallback<MessageTestModel>) {
        Log.e(TAG,"MessagesDataSource loadInitData()")
        val result = ArrayList<MessageTestModel>()
        for(index in 0..9) {
            result.add(MessageTestModel(index.toString(), index))
        }

        callback.onResult(result)
    }

    override fun loadAfter(params: LoadParams<MessageTestModel>, callback: LoadCallback<MessageTestModel>) {
        Log.e(TAG,"MessagesDataSource loadAfter()")
        val result = ArrayList<MessageTestModel>()
        val key = params.key.position

        if(key < 40) {
            for (index in (key + 1)..(key + 10)) {
                result.add(MessageTestModel(index.toString(), index))
            }
        }

        callback.onResult(result)
    }

    override fun loadBefore(params: LoadParams<MessageTestModel>, callback: LoadCallback<MessageTestModel>) {
        //Реализация не нужна
    }

    override fun getKey(item: MessageTestModel): MessageTestModel {
        return item
    }
}

