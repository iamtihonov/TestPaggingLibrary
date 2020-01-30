package com.example.testlist3

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_chat.*
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    companion object {
        const val CHAT_TAG = "chat_tag"
    }

    private lateinit var factory: MessagesDataSourceFactory
    private lateinit var messagesAdapter: MessagesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        initViews()
    }

    private fun initViews() {
        messagesAdapter = MessagesAdapter(MessageDiffUtilCallback())
        val layoutManager = LinearLayoutManager(this)
        factory = MessagesDataSourceFactory()

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
            showToast()
            factory.dataSource?.invalidate()
        }
    }

    private fun showToast() {
        Toast.makeText(this, "Update clicked", Toast.LENGTH_LONG).show()
    }
}
