package com.example.chibbistest.ai.hits

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chibbistest.App
import com.example.chibbistest.R
import com.example.chibbistest.ai.ViewModelFactory
import com.example.chibbistest.ai.hits.adapter.HitsAdapter
import com.example.chibbistest.ai.hits.adapter.HitsDiffUtilCallback
import com.example.chibbistest.databinding.FragmentHitsBinding
import kotlinx.coroutines.launch

class HitsFragment : Fragment() {

    lateinit var binding: FragmentHitsBinding
    private lateinit var viewModel: HitsViewModel
    private val viewModelFactory: ViewModelFactory =
        App.viewModelFactoryComponent.getViewModelFactory()
    private val hitsAdapter = HitsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHitsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[HitsViewModel::class.java]

        initHitsRecyclerView()
        lifecycleScope.launch {
            updateHitsRecyclerView()
        }

    }

    private fun initHitsRecyclerView() {
        binding.hitsRecyclerView.apply {
            adapter = hitsAdapter
            layoutManager = LinearLayoutManager(context).also {
                it.onRestoreInstanceState(it.onSaveInstanceState())
            }
        }
    }

    private suspend fun updateHitsRecyclerView() {
        val newHitsList = viewModel.getHits()
        val hitsDiffUtilCallback =
            HitsDiffUtilCallback(hitsAdapter.items, newHitsList)
        val result = DiffUtil.calculateDiff(hitsDiffUtilCallback)
        val recyclerViewState: Parcelable? =
            binding.hitsRecyclerView.layoutManager?.onSaveInstanceState()
        hitsAdapter.items = newHitsList
        result.dispatchUpdatesTo(hitsAdapter)
        binding.hitsRecyclerView.layoutManager?.onRestoreInstanceState(recyclerViewState)
    }

}