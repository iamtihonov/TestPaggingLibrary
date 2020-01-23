package com.example.testlist3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_chat_details.*
import java.util.*
import java.util.concurrent.Executors
import kotlin.concurrent.schedule
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {

    companion object {
        const val CHAT_TAG = "chat_tag"
    }

    private lateinit var observer: RecyclerView.AdapterDataObserver
    private lateinit var factory: MessagesDataSourceFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_chat_details)
        initViews()
        Timer().schedule(10000) {
            factory.dataSource?.invalidate()
        }
    }

    private fun initViews() {
        val messagesAdapter = TestMessagesAdapter(MessageDiffUtilCallback())
        val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        factory = MessagesDataSourceFactory()

        recycleView.apply {
            this.layoutManager = layoutManager
            adapter = messagesAdapter
            setHasFixedSize(true)
        }

        observer = object : RecyclerView.AdapterDataObserver() {
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                Log.e(CHAT_TAG,"ChatDetailsFragment onItemRangeInserted, positionStart = $positionStart")
            }
        }
        messagesAdapter.registerAdapterDataObserver(observer)

        val config = PagedList.Config.Builder()
            .setPageSize(10)
            .setInitialLoadSizeHint(30)
            .build()
        val pagedListLiveData = LivePagedListBuilder(factory, config)
            .setFetchExecutor(Executors.newSingleThreadExecutor())
            .build()

        pagedListLiveData.observe(this, Observer<PagedList<MessageTestModel>?> { pagedList ->
            Log.e(CHAT_TAG,"ChatDetailsFragment submit PagedList")
            messagesAdapter.submitList(pagedList)
        })
    }
}
