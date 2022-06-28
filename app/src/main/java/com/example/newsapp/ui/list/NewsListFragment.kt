package com.example.newsapp.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.newsapp.data.model.NewsDTO
import com.example.newsapp.databinding.FragmentNewsListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewsListFragment : Fragment() {

    private val viewModel: NewsListViewModel by viewModels()

    private var newsListAdapter: NewsListAdapter? = null

    private var binding: FragmentNewsListBinding? = null

    private val args: NewsListFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentNewsListBinding.inflate(inflater, container, false)
        return binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getNews(args.query)
        lifecycleScope.launch {
            viewModel.state.collect {
                when (it) {
                    is NewsListViewState.Success -> {
                        binding?.loading?.isVisible = false
                        it.data?.let { newsDTO ->
                            if (newsDTO.totalResults == 0) {
                                binding?.tvError?.isVisible = true
                            } else {
                                newsListAdapter = NewsListAdapter(newsDTO.articles)
                                binding?.rvNews?.adapter = newsListAdapter
                            }
                        }
                    }

                    is NewsListViewState.Error -> {
                        binding?.loading?.isVisible = false
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }

                    is NewsListViewState.Loading -> {
                        binding?.loading?.isVisible = true
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }


}