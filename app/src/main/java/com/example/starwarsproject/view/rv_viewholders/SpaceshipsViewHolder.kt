package com.example.starwarsproject.view.rv_viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsproject.R
import com.example.starwarsproject.data.entity.Hero
import com.example.starwarsproject.data.entity.Spaceship
import com.example.starwarsproject.databinding.SpaceshipItemBinding

class SpaceshipsViewHolder(private val binding: SpaceshipItemBinding,private val insertSpaceship: (Spaceship) -> Unit) : RecyclerView.ViewHolder(binding.root) {
    fun bind(spaceship: Spaceship) {
        with(binding) {
            name.text = "Name: "+spaceship.name
            model.text = "Model: "+spaceship.model
            manufacturer.text = "Manufacturer: "+spaceship.manufacturer
            passengers.text = "Spaceship: "+spaceship.passengers
            likeButton.setOnClickListener {
                likeButton.setImageDrawable(root.resources.getDrawable(R.drawable.ic_favorite))
                insertSpaceship(spaceship)
            }
        }
    }
}