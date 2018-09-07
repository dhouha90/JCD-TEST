package com.example.dchikhaoui.monmap.view.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dchikhaoui.monmap.Model.Bike
import com.example.dchikhaoui.monmap.R
import com.example.dchikhaoui.monmap.adaptater.BikeListAdaptater

class BikeListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_bike_list, container, false)
        var recyclerview = rootView.findViewById(R.id.bike_list) as RecyclerView
        recyclerview.layoutManager = LinearLayoutManager(activity)
        recyclerview.adapter = BikeListAdaptater()
        // BikeListAdaptater().addData()
        //add recycle view to viewModel
        BikeListAdaptater().addData(Bike("test","test"))
        return rootView
    }

    fun createInstance(bundle: Bundle): BikeListFragment {
        val fragment = BikeListFragment()
        fragment.setArguments(bundle)
        return fragment
    }
}