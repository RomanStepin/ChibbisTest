package com.example.chibbistest.ai.hits

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.chibbistest.data.Repository
import com.example.chibbistest.models.Hit

class HitsViewModel(private val repository: Repository) : ViewModel() {

    suspend fun getHits(): List<Hit> {
        return repository.getHits()
    }
}