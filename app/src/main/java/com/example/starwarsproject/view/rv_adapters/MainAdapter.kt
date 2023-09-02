package com.example.starwarsproject.view.rv_adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsproject.data.entity.Hero
import com.example.starwarsproject.data.entity.Planet
import com.example.starwarsproject.data.entity.Spaceship
import com.example.starwarsproject.databinding.CharacterItemBinding
import com.example.starwarsproject.databinding.PlanetItemBinding
import com.example.starwarsproject.databinding.SpaceshipItemBinding
import com.example.starwarsproject.view.fragments.home_fragment.HomeFragmentViewModel
import com.example.starwarsproject.view.rv_viewholders.HeroesViewHolder
import com.example.starwarsproject.view.rv_viewholders.PlanetsViewHolder
import com.example.starwarsproject.view.rv_viewholders.SpaceshipsViewHolder

class MainAdapter(private val viewModel: HomeFragmentViewModel?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: List<Any> = emptyList()

    fun setItems(newItems: List<Any>) {
        val diffResult = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = items.size
            override fun getNewListSize(): Int = newItems.size

            override fun areItemsTheSame(oldPos: Int, newPos: Int): Boolean {
                val oldItem = items[oldPos]
                val newItem = newItems[newPos]
                return when {
                    oldItem is Hero && newItem is Hero -> oldItem.name == newItem.name
                    oldItem is Spaceship && newItem is Spaceship -> oldItem.name == newItem.name
                    oldItem is Planet && newItem is Planet -> oldItem.name == newItem.name
                    else -> false
                }
            }

            override fun areContentsTheSame(oldPos: Int, newPos: Int): Boolean {
                return items[oldPos] == newItems[newPos]
            }
        })
        items = newItems
        diffResult.dispatchUpdatesTo(this)
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is Hero -> VIEW_TYPE_1
            is Spaceship -> VIEW_TYPE_2
            is Planet -> VIEW_TYPE_3
            else -> throw IllegalArgumentException("Invalid item type")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_1 -> {
                val binding = CharacterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                HeroesViewHolder(binding) { hero ->
                    viewModel?.insertHero(hero)
                }
            }
            VIEW_TYPE_2 -> {
                val binding = SpaceshipItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                SpaceshipsViewHolder(binding) { spaceship ->
                    viewModel?.insertSpaceship(spaceship)
                }
            }
            VIEW_TYPE_3 -> {
                val binding = PlanetItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                PlanetsViewHolder(binding) { planet ->
                    viewModel?.insertPlanet(planet)
                }
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        when (holder) {
            is HeroesViewHolder -> {
                holder.bind(item as Hero)
            }
            is SpaceshipsViewHolder -> {
                holder.bind(item as Spaceship)
            }
            is PlanetsViewHolder -> {
                holder.bind(item as Planet)
            }
        }
    }

    companion object {
        private const val VIEW_TYPE_1 = 1
        private const val VIEW_TYPE_2 = 2
        private const val VIEW_TYPE_3 = 3
    }
}