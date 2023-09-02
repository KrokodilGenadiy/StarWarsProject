package com.example.starwarsproject.view.fragments.home_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.starwarsproject.R
import com.example.starwarsproject.base.BaseFragment
import com.example.starwarsproject.databinding.FragmentHomeBinding
import com.example.starwarsproject.view.rv_adapters.MainAdapter
import com.zaus_app.moviefrumy_20.view.rv_adaptes.TopSpacingItemDecoration
import com.example.starwarsproject.base.Result
import com.example.starwarsproject.view.fragments.filters_fragment.FiltersFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentHomeBinding
    {
        return FragmentHomeBinding.inflate(inflater,container,false)
    }
    private val viewModel: HomeFragmentViewModel by viewModels()
    private val mainAdapter: MainAdapter by lazy { MainAdapter(viewModel) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        binding.mainRecycler.apply {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(requireContext())
            val decorator = TopSpacingItemDecoration(8)
            addItemDecoration(decorator)
        }
        initSearch()
    }

    private fun observeData(query: String) {
        lifecycleScope.launch {
            viewModel.getData(query).collect { result ->
                when (result) {
                    is Result.Loading -> {
                        // Show loading state
                    }
                    is Result.Success -> {
                        val data = result.data.results
                        if (data.isNotEmpty()) {
                            mainAdapter.setItems(data as List<Any>)
                        }

                    }
                    is Result.Error -> {
                        val errorMessage = result.message
                        // Handle error
                    }
                }
            }
        }
    }

    private fun initToolbar() {
        binding.toolbarMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.filters -> {
                    val bottomSheetFragment = FiltersFragment()
                    bottomSheetFragment.show(
                        requireActivity().supportFragmentManager,
                        "BottomSheet"
                    )
                    true
                }
                else -> false
            }
        }
    }

    private fun initSearch() {
        binding.searchView.setOnClickListener {
            binding.searchView.isIconified = false
        }
        binding.searchView.setOnQueryTextListener(
            DebouncingQueryTextListener(
                this.lifecycle
            ) { newText ->
                newText?.let {
                    if (it.isNotEmpty() && it.length > 1) {
                        observeData(it)
                    }
                }
            }
        )
    }

    internal class DebouncingQueryTextListener(
        lifecycle: Lifecycle,
        private val onDebouncingQueryTextChange: (String?) -> Unit
    ) : SearchView.OnQueryTextListener {
        private var debouncePeriod: Long = 500
        private val coroutineScope = lifecycle.coroutineScope
        private var searchJob: Job? = null

        override fun onQueryTextSubmit(query: String?): Boolean {
            return false
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            searchJob?.cancel()
            searchJob = coroutineScope.launch {
                newText?.let {
                    delay(debouncePeriod)
                    onDebouncingQueryTextChange(newText)
                }
            }
            return false
        }
    }
}