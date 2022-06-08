package com.example.chibbistest.ai.restaurants

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chibbistest.App
import com.example.chibbistest.ai.ViewModelFactory
import com.example.chibbistest.ai.restaurants.adapter.RestaurantsDiffUtilCallback
import com.example.chibbistest.ai.restaurants.adapter.RestaurantsAdapter
import com.example.chibbistest.databinding.FragmentRestaurantsBinding
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import ru.ldralighieri.corbind.widget.textChanges


class RestaurantsFragment : Fragment() {

    private lateinit var binding: FragmentRestaurantsBinding
    private lateinit var viewModel: RestaurantsViewModel
    private val viewModelFactory: ViewModelFactory =
        App.viewModelFactoryComponent.getViewModelFactory()
    private val restaurantsAdapter = RestaurantsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRestaurantsBinding.inflate(inflater, container, false)
        return binding.root
    }

    @OptIn(FlowPreview::class)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[RestaurantsViewModel::class.java]

        initRestaurantsRecyclerView()
        initRestaurantsSearch()

        lifecycleScope.launch {
            updateRestaurantsRecyclerView()
        }

    }

    private fun initRestaurantsRecyclerView() {
        binding.restaurantsRecyclerView.apply {
            adapter = restaurantsAdapter
            layoutManager = LinearLayoutManager(context).also {
                it.onRestoreInstanceState(it.onSaveInstanceState())
            }
        }
    }

    @OptIn(FlowPreview::class)
    private fun initRestaurantsSearch() {
        binding.restaurantsSearch.textChanges()
            .debounce(250)
            .onEach {
                updateRestaurantsRecyclerView(it.toString())
            }
            .flowWithLifecycle(lifecycle)
            .launchIn(lifecycleScope)
    }

    private suspend fun updateRestaurantsRecyclerView(query: String = "") {
        val newRestaurantsList = viewModel.getRestaurants(query)
        val restaurantDiffUtilCallback =
            RestaurantsDiffUtilCallback(restaurantsAdapter.items, newRestaurantsList)
        val result = DiffUtil.calculateDiff(restaurantDiffUtilCallback)
        val recyclerViewState: Parcelable? =
            binding.restaurantsRecyclerView.layoutManager?.onSaveInstanceState()
        restaurantsAdapter.items = newRestaurantsList
        result.dispatchUpdatesTo(restaurantsAdapter)
        binding.restaurantsRecyclerView.layoutManager?.onRestoreInstanceState(recyclerViewState)
    }
}