package com.example.starwarsproject.view.rv_viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsproject.R
import com.example.starwarsproject.data.entity.Hero
import com.example.starwarsproject.databinding.CharacterItemBinding

class HeroesViewHolder(private val binding: CharacterItemBinding, private val insertHeroCallback: (Hero) -> Unit) : RecyclerView.ViewHolder(binding.root) {
    fun bind(character: Hero) {
        with(binding) {
            name.text = "Name: "+character.name
            gender.text = "Gender: "+character.gender
            spaceShipCount.text = "Spaceship count: "+character.starships.count().toString()
            likeButton.setOnClickListener {
                likeButton.setImageDrawable(root.resources.getDrawable(R.drawable.ic_favorite))
                insertHeroCallback(character)
            }
        }
    }
}