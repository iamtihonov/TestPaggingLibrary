package com.example.testlist3

import android.util.Log
import androidx.recyclerview.widget.DiffUtil

class MessageDiffUtilCallback : DiffUtil.ItemCallback<MessageModel>() {

    companion object {
        const val CHAT_TAG = "chat_tag"
    }

    override fun areItemsTheSame(oldItem: MessageModel, newItem: MessageModel): Boolean {
        val equals = oldItem.position == newItem.position
        Log.e(CHAT_TAG, "MessageDiffUtilCallback areItemsTheSame = $equals")
        return equals
    }

    override fun areContentsTheSame(oldItem: MessageModel, newItem: MessageModel): Boolean {
        val equals = oldItem.position == newItem.position
        Log.e(CHAT_TAG, "MessageDiffUtilCallback (equals = $equals")
        return equals
    }
}