package com.example.testlist3

import android.util.Log
import androidx.paging.PositionalDataSource

/**
 * messages всегда передается один и тот же
 */
internal class MessagesDataSource(private val messages: ArrayList<MessageTestModel>) : PositionalDataSource<MessageTestModel>() {

    companion object {
        const val TAG = "chat_tag"
    }

    init {
        Log.e(TAG, "MessagesDataSource init()")
    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<MessageTestModel>) {
        Log.e(TAG,"MessagesDataSource loadInitData()")
        callback.onResult(messages.subList(0, 10), 0)
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<MessageTestModel>) {
        Log.e(TAG,"MessagesDataSource loadAfter()")
        val startPosition = params.startPosition
        val endPosition = startPosition + params.loadSize
        if(endPosition <= messages.size) {
            callback.onResult(messages.subList(startPosition, endPosition))
        } else {
            callback.onResult(ArrayList())
        }
    }
}

