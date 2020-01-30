package com.example.testlist3

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil

/**
 * Адаптер отвечающий за отображение исполнителей
 */
class MessagesAdapter : PagedListAdapter<MessageModel, MessageHolder> {

    companion object {
        const val TAG = "testList"
    }

    @Suppress("ConvertSecondaryConstructorToPrimary")
    constructor(diffUtilCallback: DiffUtil.ItemCallback<MessageModel>)
            : super(diffUtilCallback)

    @Suppress("CascadeIf")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_chat, parent, false)
        return MessageHolder(itemView)
    }

    override fun onBindViewHolder(messageItemHolder: MessageHolder, position: Int) {
        Log.d(TAG, "ChatsAdapter onBindViewHolder pos = $position")
        val item = getItem(position)

        if (item != null) {
            messageItemHolder.bind(item)
        }
    }
}