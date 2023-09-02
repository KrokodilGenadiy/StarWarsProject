package com.example.starwarsproject.view.fragments.favorites_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.starwarsproject.base.BaseFragment
import com.example.starwarsproject.databinding.FragmentFavoritesBinding
import com.example.starwarsproject.view.rv_adapters.SectionItemAdapter
import com.zaus_app.moviefrumy_20.view.rv_adaptes.TopSpacingItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoritesFragment : BaseFragment<FragmentFavoritesBinding>() {
    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentFavoritesBinding
    {
        return FragmentFavoritesBinding.inflate(inflater,container,false)
    }
    private val viewModel: FavoritesViewModel by viewModels()
    private val favoritesAdapter = SectionItemAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            favoritesRecycler.apply {
                adapter = favoritesAdapter
                layoutManager = LinearLayoutManager(requireContext())
                val decorator = TopSpacingItemDecoration(8)
                addItemDecoration(decorator)
            }
        }
        observeData()
    }

    private fun observeData() {
        lifecycleScope.launch {
            viewModel.getData().collect { sections ->
                favoritesAdapter.setData(sections)
            }
        }
    }

}