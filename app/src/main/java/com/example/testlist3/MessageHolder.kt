package com.example.testlist3

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_chat.view.*


/**
 * ViewHolder для item_chat_me_text_message.xml
 */
class MessageHolder(private val containerView: View) : RecyclerView.ViewHolder(containerView) {

    fun bind(item: MessageModel) {
        val message = item as? MessageModel
        containerView.apply {
            textMessage.text = message?.text ?: "other"
        }
    }
}
