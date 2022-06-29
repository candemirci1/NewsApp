package com.example.newsapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.databinding.ItemCategoryBinding

class HomeAdapter(
    private val categories: List<String>,
    private val onClick: (String) -> Unit
): RecyclerView.Adapter<HomeAdapter.CategoryViewHolder>() {

    class CategoryViewHolder (val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val currentCategory = categories[position]
        holder.binding.apply {

            tvCategory.text = currentCategory

            root.setOnClickListener {
                onClick.invoke(currentCategory)
            }

        }
    }

    override fun getItemCount(): Int {
        return categories.size
    }

}