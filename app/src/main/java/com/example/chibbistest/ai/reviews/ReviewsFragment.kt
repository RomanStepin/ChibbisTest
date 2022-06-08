package com.example.chibbistest.ai.reviews

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chibbistest.App
import com.example.chibbistest.R
import com.example.chibbistest.ai.ViewModelFactory
import com.example.chibbistest.ai.reviews.adapter.ReviewsAdapter
import com.example.chibbistest.ai.reviews.adapter.ReviewsDiffUtilCallback
import com.example.chibbistest.databinding.FragmentReviewsBinding
import com.example.chibbistest.getRangeSpannableString
import com.example.chibbistest.models.Review
import kotlinx.coroutines.launch

class ReviewsFragment : Fragment() {

    private lateinit var binding: FragmentReviewsBinding
    private lateinit var viewModel: ReviewsViewModel
    private val viewModelFactory: ViewModelFactory =
        App.viewModelFactoryComponent.getViewModelFactory()
    private val reviewsAdapter = ReviewsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReviewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[ReviewsViewModel::class.java]

        initReviewsRecyclerView()
        lifecycleScope.launch {
            val newReviewsList = viewModel.getReviews()
            val newReviewsString = "${newReviewsList.size} ${getString(R.string.total_reviews)}"
            val newReviewsSpannableString = newReviewsString.getRangeSpannableString(
                0,
                newReviewsList.size.toString().length,
                2F
            )
            binding.reviewsAllCount.text = newReviewsSpannableString
            updateReviewsRecyclerView(newReviewsList)
        }
    }

    private fun initReviewsRecyclerView() {
        binding.reviewsRecyclerView.apply {
            adapter = reviewsAdapter
            layoutManager = LinearLayoutManager(context).also {
                it.onRestoreInstanceState(it.onSaveInstanceState())
            }
        }
    }

    private fun updateReviewsRecyclerView(newReviewsList: List<Review>) {
        val reviewsDiffUtilCallback =
            ReviewsDiffUtilCallback(reviewsAdapter.items, newReviewsList)
        val result = DiffUtil.calculateDiff(reviewsDiffUtilCallback)
        val recyclerViewState: Parcelable? =
            binding.reviewsRecyclerView.layoutManager?.onSaveInstanceState()
        reviewsAdapter.items = newReviewsList
        result.dispatchUpdatesTo(reviewsAdapter)
        binding.reviewsRecyclerView.layoutManager?.onRestoreInstanceState(recyclerViewState)
    }

}