package com.example.android.market_master.models

import androidx.lifecycle.*
import com.example.android.market_master.domain.Outlet
import com.example.android.market_master.repository.OutletRepository
import com.example.android.market_master.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OutletViewModel

@Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val outletRepository: OutletRepository,

    ):ViewModel()
{
    private val _dataState:MutableLiveData<DataState<List<Outlet>>> = MutableLiveData()

    val dataState: LiveData<DataState<List<Outlet>>>
    get() = _dataState

    fun setOuletStateEvent(outletStateEvent: OutletStateEvent){
        viewModelScope.launch {
            when(outletStateEvent){
                is OutletStateEvent.GetOutletsEvent ->{
                    outletRepository.getOutlets()
                        .onEach {dataState ->
                        _dataState.value = dataState

                        }
                        .launchIn(viewModelScope)
                }

                is OutletStateEvent.None ->{
                    //todo
                }
            }
        }
    }




}
sealed class OutletStateEvent{

    object GetOutletsEvent: OutletStateEvent()
    object None: OutletStateEvent()

}