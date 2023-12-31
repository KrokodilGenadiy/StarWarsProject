package com.example.starwarsproject.view.rv_adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.starwarsproject.data.entity.SectionItem

class SectionItemDiffCallback(
    private val oldList: List<SectionItem<Any>>,
    private val newList: List<SectionItem<Any>>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return true
    }
}