package com.example.testlist3

import android.util.Log
import androidx.paging.PositionalDataSource

internal class MessagesDataSource : PositionalDataSource<MessageTestModel>() {

    companion object {
        const val TAG = "testBug2"
    }

    init {
        Log.e(TAG, "MessagesDataSource init()")
    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<MessageTestModel>) {
        Log.e(TAG,"MessagesDataSource loadInitData()")
        val result = ArrayList<MessageTestModel>()
        for(index in 0..9) {
            result.add(MessageTestModel(index.toString(), index))
        }

        callback.onResult(result, 0)
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<MessageTestModel>) {
        Log.e(TAG,"MessagesDataSource loadAfter()")
        val result = ArrayList<MessageTestModel>()
        val startPosition = params.startPosition

        if(startPosition < 40) {
            for (index in (startPosition + 1)..(startPosition + 10)) {
                result.add(MessageTestModel(index.toString(), index))
            }
        }

        callback.onResult(result)
    }
}

