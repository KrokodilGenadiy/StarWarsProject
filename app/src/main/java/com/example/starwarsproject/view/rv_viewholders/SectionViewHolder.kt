package com.example.starwarsproject.view.rv_viewholders

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsproject.data.entity.Hero
import com.example.starwarsproject.data.entity.Planet
import com.example.starwarsproject.data.entity.SectionItem
import com.example.starwarsproject.data.entity.Spaceship
import com.example.starwarsproject.databinding.SectionItemBinding
import com.example.starwarsproject.view.rv_adapters.MainAdapter
import com.zaus_app.moviefrumy_20.view.rv_adaptes.TopSpacingItemDecoration

class SectionViewHolder(private val binding: SectionItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(sectionItem: SectionItem<Any>) {
        with(binding) {
            headline.text = sectionItem.headline
            val itemAdapter = when (sectionItem.headline) {
                "Characters" -> {
                    val result = MainAdapter(null)
                    result.setItems(sectionItem.items as List<Hero>)
                    result
                }

                "Spaceships" -> {
                    val result = MainAdapter(null)
                    result.setItems(sectionItem.items as List<Spaceship>)
                    result
                }

                "Planets" -> {
                    val result = MainAdapter(null)
                    result.setItems(sectionItem.items as List<Planet>)
                    result
                }

                else -> {
                    throw IllegalArgumentException("Wrong section type")
                }
            }
            recycler.layoutManager = LinearLayoutManager(binding.root.context)
            recycler.addItemDecoration(TopSpacingItemDecoration(8))
            recycler.adapter = itemAdapter
        }

    }
}