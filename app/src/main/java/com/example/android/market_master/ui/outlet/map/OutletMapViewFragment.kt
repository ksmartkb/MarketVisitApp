package com.example.android.market_master.ui.outlet.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.android.market_master.R
import com.example.android.market_master.domain.Outlet
import com.example.android.market_master.models.OutletViewModel
import com.example.android.market_master.utils.DataState
import com.example.android.market_master.utils.DialogyAlert
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_outlet_map_view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OutletMapViewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OutletMapViewFragment : Fragment(),GoogleMap.OnMarkerClickListener{


    private lateinit var viewModel: OutletViewModel
    private lateinit var dataState: DataState<List<Outlet>>

    private var mapReady  = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        val rootView =  inflater.inflate(R.layout.fragment_outlet_map_view, container, false)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(callback)
        return rootView
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = activity?.run {
            ViewModelProvider(this).get(OutletViewModel::class.java)
        }?: throw Exception("Invalid Activity")

        //mMap = googleMap
        mapReady = true

    }


    private val callback = OnMapReadyCallback { mMaps ->
        viewModel.dataState.observe(viewLifecycleOwner, Observer { dataState ->
            when(dataState){

                is DataState.Success<List<Outlet>> -> {
                    displayProgressBar(false)

                    dataState.data.map {
                        outlet ->

                        val marker = LatLng(outlet.latitude.toDouble(),outlet.longitude.toDouble())
                        //Log.e("Testeee",outlet.latitude)
                        mMaps.addMarker(MarkerOptions().position(marker).title(outlet.outletName))
                        mMaps.moveCamera(CameraUpdateFactory.newLatLng(marker))


                    }

                }
                is DataState.Error -> {
                    displayProgressBar(false)
                    displayError(dataState.exception.message)
                }
                is DataState.Loading -> {
                    displayProgressBar(true)
                }
                else -> {

                }
            }
        })
    }
    private fun displayProgressBar(isDisplayed: Boolean){
        progress_bar_map.visibility = if(isDisplayed) View.VISIBLE else View.GONE
    }

    private fun displayError(message: String?){
        if(message != null) text_map.text = message else text_map.text = "Unknown error."
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        val clickCount = marker.tag as? Int
        val alert = DialogyAlert()

        // Check if a click count was set, then display the click count.
        clickCount?.let {
            val newClickCount = it + 1
            marker.tag = newClickCount



            alert.showDialog(activity, "${marker.title} !!!")
        }
        return false


    }


}