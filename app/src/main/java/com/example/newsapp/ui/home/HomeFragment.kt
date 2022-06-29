package com.example.newsapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.newsapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val categories = listOf("business", "entertainment", "general", "health", "science", "sports", "technology")

    private var homeAdapter: HomeAdapter? = null

    private var binding: FragmentHomeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeAdapter = HomeAdapter(categories) {
            val action = HomeFragmentDirections.actionHomeFragmentToNewsListFragment(it,Source.CATEGORY)
            findNavController().navigate(action)
        }

        binding?.rvCategories?.layoutManager = GridLayoutManager(requireContext(), 2)
        binding?.rvCategories?.adapter = homeAdapter

        binding?.btnSearch?.setOnClickListener {
            val query = binding?.etSearch?.text.toString()
            if (query.isNotEmpty()) {
                val action = HomeFragmentDirections.actionHomeFragmentToNewsListFragment(query, Source.SEARCH)
                findNavController().navigate(action)
            }
        }

    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}

enum class Source{
    SEARCH,
    CATEGORY
}

