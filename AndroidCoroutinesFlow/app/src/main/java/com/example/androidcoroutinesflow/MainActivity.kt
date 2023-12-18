package com.example.androidcoroutinesflow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidcoroutinesflow.databinding.ActivityMainBinding
import com.example.androidcoroutinesflow.view.NewsListAdapter
import com.example.androidcoroutinesflow.viewModel.ListViewModel

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: ListViewModel
    private val newsListAdapter = NewsListAdapter()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[ListViewModel::class.java]


        binding.newsList.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = newsListAdapter
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.newsArticles.observe(this, Observer { article ->
            binding.loadingView.visibility = View.GONE
            binding.newsList.visibility = View.VISIBLE
            newsListAdapter.onAddNewsItem(article)
            binding.newsList.smoothScrollToPosition(0)
        })
    }
}