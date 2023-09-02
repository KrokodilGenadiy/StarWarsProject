package com.example.starwarsproject.view.rv_adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsproject.data.entity.SectionItem
import com.example.starwarsproject.databinding.SectionItemBinding
import com.example.starwarsproject.view.rv_viewholders.SectionViewHolder

class SectionItemAdapter : RecyclerView.Adapter<SectionViewHolder>() {
    private val sectionItems: MutableList<SectionItem<Any>> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SectionItemBinding.inflate(inflater, parent, false)
        return SectionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SectionViewHolder, position: Int) {
        val sectionItem = sectionItems[position]
        holder.bind(sectionItem)
    }

    override fun getItemCount(): Int {
        return sectionItems.size
    }

    fun setData(newData: List<SectionItem<Any>>) {
        val diffResult = DiffUtil.calculateDiff(
            SectionItemDiffCallback(sectionItems, newData)
        )
        sectionItems.clear()
        sectionItems.addAll(newData)
        diffResult.dispatchUpdatesTo(this)
    }
}
