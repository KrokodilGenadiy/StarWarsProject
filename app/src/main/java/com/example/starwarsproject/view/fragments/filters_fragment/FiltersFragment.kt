package com.example.starwarsproject.view.fragments.filters_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.starwarsproject.R
import com.example.starwarsproject.databinding.FragmentFiltersBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FiltersFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentFiltersBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FiltersFragmentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFiltersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCategoryChooser()
        initConfirmButton()
    }

    private fun initCategoryChooser() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.currentCategory.collectLatest {
                binding.autoCompleteTextView.setText(viewModel.currentCategory.value)
            }
        }
        val categories = resources.getStringArray(R.array.categories)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.category_item, categories)
        binding.autoCompleteTextView.setAdapter(arrayAdapter)
        binding.autoCompleteTextView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                when (parent.getItemAtPosition(position).toString()) {
                    resources.getString(R.string.people) -> viewModel.saveDefaultCategory(
                        PEOPLE
                    )
                    resources.getString(R.string.spaceships) -> viewModel.saveDefaultCategory(
                        SPACESHIPS
                    )
                    resources.getString(R.string.planets) -> viewModel.saveDefaultCategory(
                        PLANETS
                    )
                }
            }
    }

    fun initConfirmButton() {
        //TODO need to add logic to refresh recycler on home page
        binding.confirmButton.setOnClickListener {
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val PEOPLE = "People"
        private const val SPACESHIPS = "Spaceships"
        private const val PLANETS = "Planets"
    }
}
