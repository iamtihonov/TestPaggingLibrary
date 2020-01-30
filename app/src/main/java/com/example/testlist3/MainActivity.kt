package com.example.testlist3

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.testlist3.db.AppDatabase
import com.example.testlist3.db.MessageModel
import com.facebook.stetho.Stetho
import kotlinx.android.synthetic.main.activity_chat.*
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    companion object {
        const val CHAT_TAG = "chat_tag"
    }

    private lateinit var factory: DataSource.Factory<Int, MessageModel>
    private lateinit var messagesAdapter: MessagesAdapter
    private lateinit var db: AppDatabase
    private val messages = ArrayList<MessageModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        Stetho.initializeWithDefaults(this)
        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database")
            .allowMainThreadQueries()
            .build()

        initTestData()
    }

    private fun initTestData() {
        db.messageDao().nukeTable()
        for (index in 0..39) {
            messages.add(MessageModel(index, index.toString()))
        }

        db.messageDao().insert(messages)

        initViews()
    }

    private fun initViews() {
        messagesAdapter = MessagesAdapter(MessageDiffUtilCallback())
        val layoutManager = LinearLayoutManager(this)
        factory = db.messageDao().selectPaged()

        recycleView.apply {
            this.layoutManager = layoutManager
            adapter = messagesAdapter
            setHasFixedSize(true)
        }

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(10)
            .setInitialLoadSizeHint(30)
            .build()
        val pagedListLiveData = LivePagedListBuilder(factory, config)
            .setFetchExecutor(Executors.newSingleThreadExecutor())
            .build()

        pagedListLiveData.observe(this, Observer<PagedList<MessageModel>?> { pagedList ->
            Log.e(CHAT_TAG, "ChatDetailsFragment submit PagedList")
            messagesAdapter.submitList(pagedList)
        })

        fab.setOnClickListener {
            val newPosition = messages.size
            val newMessage = MessageModel(newPosition, newPosition.toString())
            messages.add(newMessage)
            db.messageDao().insert(newMessage)
        }
    }
}
