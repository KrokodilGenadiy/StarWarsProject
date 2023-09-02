package com.example.starwarsproject.view.rv_viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsproject.R
import com.example.starwarsproject.data.entity.Planet
import com.example.starwarsproject.data.entity.Spaceship
import com.example.starwarsproject.databinding.PlanetItemBinding
import com.example.starwarsproject.databinding.SpaceshipItemBinding

class PlanetsViewHolder(private val binding: PlanetItemBinding, private val insertSpaceship: (Planet) -> Unit) : RecyclerView.ViewHolder(binding.root) {
    fun bind(planet: Planet) {
        with(binding) {
            name.text = "Name: "+planet.name
            diameter.text = "Model: "+planet.diameter
            population.text = "Manufacturer: "+planet.population
            likeButton.setOnClickListener {
                likeButton.setImageDrawable(root.resources.getDrawable(R.drawable.ic_favorite))
                insertSpaceship(planet)
            }
        }
    }
}