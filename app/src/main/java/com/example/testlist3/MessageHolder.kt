package com.example.testlist3

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.testlist3.db.MessageModel
import kotlinx.android.synthetic.main.item_chat.view.*


/**
 * ViewHolder для item_chat_me_text_message.xml
 */
class MessageHolder(private val containerView: View) : RecyclerView.ViewHolder(containerView) {

    fun bind(message: MessageModel) {
        Log.e(MainActivity.CHAT_TAG, "MessageHolder bind(), text = " + message.text)
        containerView.apply {
            textMessage.text = message.text
        }
    }
}
