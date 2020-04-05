package com.example.android.market_master.ui.outlet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.market_master.R
import com.example.android.market_master.adapters.OutletListAdapter
import com.example.android.market_master.domain.Outlet
import com.example.android.market_master.models.OutletViewModel
import com.example.android.market_master.utils.DataState
import com.example.android.market_master.utils.TopSpacingItemDecoration
import com.example.android.market_master.utils.DataStateListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_outlet_map_view.*
import kotlinx.android.synthetic.main.outlet_fragment_main.*

class OutletFragramentMain :Fragment(), OutletListAdapter.Interaction {
    override fun onItemSelected(position: Int, item: Outlet) {
        println("DEBUG: CLICKED: $position")
        println("DEBUG: CLICKED: $item")
    }

    lateinit var viewModel: OutletViewModel
    lateinit var dataStateHandler: DataStateListener

    lateinit var mainRecyclerAdapter: OutletListAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.outlet_fragment_main, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        viewModel = activity?.run {
            ViewModelProvider(this).get(OutletViewModel::class.java)
        }?: throw Exception("Invalid Activity")

        subscribeObservers()
        initRecyclerView()
    }


    private fun subscribeObservers(){
        viewModel.dataState.observe(viewLifecycleOwner, Observer { dataState ->
            when(dataState){
                is DataState.Success<List<Outlet>> -> {
                    //displayProgressBar(false)
                    showOutlets(dataState.data)
                }
//                is DataState.Error -> {
//                    displayProgressBar(false)
//                    displayError(dataState.exception.message)
//                }
//                is DataState.Loading -> {
//                    displayProgressBar(true)
//                }
            }
        })
    }

    private fun displayError(message: String?){
        if(message != null) text.text = message else text.text = "Unknown error."
    }

    private fun showOutlets(outlets: List<Outlet>){


        mainRecyclerAdapter.submitList(outlets)
//        val sb = StringBuilder()
//        for(outlet in outlets){
//            sb.append(outlet.outletName + "\n")
//        }
//        text.text = sb.toString()
    }

    private fun displayProgressBar(isDisplayed: Boolean){
        progress_bar.visibility = if(isDisplayed) View.VISIBLE else View.GONE
    }

    private fun initRecyclerView(){
        recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
            val topSpacingDecorator = TopSpacingItemDecoration(1)
            addItemDecoration(topSpacingDecorator)
            mainRecyclerAdapter = OutletListAdapter(this@OutletFragramentMain)
            adapter = mainRecyclerAdapter
        }
    }
}