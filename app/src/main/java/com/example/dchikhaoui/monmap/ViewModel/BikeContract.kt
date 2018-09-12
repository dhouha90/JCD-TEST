package com.example.dchikhaoui.monmap.ViewModel

import com.example.dchikhaoui.monmap.databinding.ActivityDetailBikeBinding

interface BikeContract {
    fun getBikeList()
    fun detaileBike(mNameContract: String, mBikeId: Int, mActivityDetailBikeBinding: ActivityDetailBikeBinding)
}