package com.example.testlist3

import android.util.Log
import androidx.recyclerview.widget.DiffUtil

class MessageDiffUtilCallback : DiffUtil.ItemCallback<MessageTestModel>() {

    companion object {
        const val CHAT_TAG = "chat_tag"
    }

    override fun areItemsTheSame(oldItem: MessageTestModel, newItem: MessageTestModel): Boolean {
        val equals = oldItem.position == newItem.position
        Log.e(CHAT_TAG, "MessageDiffUtilCallback areItemsTheSame = $equals")
        return equals
    }

    override fun areContentsTheSame(oldItem: MessageTestModel, newItem: MessageTestModel): Boolean {
        val equals = oldItem.position == newItem.position
        Log.e(CHAT_TAG, "MessageDiffUtilCallback (equals = $equals")
        return equals
    }
}