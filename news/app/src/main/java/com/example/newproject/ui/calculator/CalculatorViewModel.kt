package com.example.newproject.ui.calculator

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newproject.repository.Repository
import com.example.newproject.ui.api.models.creditCalculator.GetCalculation
import com.example.newproject.ui.api.models.creditCalculator.PostCalculation
import com.example.newproject.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CalculatorViewModel: ViewModel() {

    val repository = Repository()

    var info: MutableLiveData<Resource<GetCalculation>> = MutableLiveData()

    fun sendInfo(calculation: PostCalculation) {
        info.postValue(Resource.loading(null))
        viewModelScope.launch(Dispatchers.IO) {
            repository.sendCalculation(calculation).let {
                // code 200
                if(it.isSuccessful) {
                    info.postValue(Resource.success(it.body()))
                } else {
                    info.postValue(Resource.error("${it.errorBody()?.string()}", null))
                }
            }
        }
    }
}