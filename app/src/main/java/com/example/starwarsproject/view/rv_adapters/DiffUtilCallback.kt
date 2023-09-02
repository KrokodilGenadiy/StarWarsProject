package com.example.starwarsproject.view.rv_adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.starwarsproject.data.entity.Hero
import com.example.starwarsproject.data.entity.Planet
import com.example.starwarsproject.data.entity.Spaceship

class DiffUtilCallback(private val oldList: List<Any>, private val newList: List<Any>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldPos: Int, newPos: Int): Boolean {
        val oldItem = oldList[oldPos]
        val newItem = newList[newPos]
        return oldItem::class == newItem::class && when (oldItem) {
            is Hero -> oldItem.name == (newItem as Hero).name
            is Spaceship -> oldItem.name == (newItem as Spaceship).name
            is Planet -> oldItem.name == (newItem as Planet).name
            else -> false
        }
    }

    override fun areContentsTheSame(oldPos: Int, newPos: Int): Boolean {
        return oldList[oldPos] == newList[newPos]
    }
}
