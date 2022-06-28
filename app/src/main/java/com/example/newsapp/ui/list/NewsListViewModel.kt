package com.example.newsapp.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.model.NewsDTO
import com.example.newsapp.data.repository.NewsRepository
import com.example.newsapp.data.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    val state = MutableStateFlow<NewsListViewState>(NewsListViewState.Loading)

     fun getNews(query: String) {
        viewModelScope.launch {
            newsRepository.getNews(query).let {
                when (it) {
                    is Response.Loading -> state.value = NewsListViewState.Loading
                    is Response.Success -> state.value = NewsListViewState.Success(it.data)
                    is Response.Error -> state.value = NewsListViewState.Error(it.message.orEmpty())
                }
            }
        }
    }


}


sealed class NewsListViewState {
    object Loading : NewsListViewState()

    data class Success(
        val data: NewsDTO?
    ) : NewsListViewState()

    data class Error(
        val message: String
    ) : NewsListViewState()
}