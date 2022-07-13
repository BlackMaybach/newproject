package com.example.newproject.ui.registration


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newproject.repository.Repository
import com.example.newproject.ui.api.models.Otp
import com.example.newproject.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SmsViewModel: ViewModel() {

    val repository = Repository()

    var smsCode: MutableLiveData<Resource<String>> = MutableLiveData()

    fun getSmsCodeFragment(number: Int, code: String) {
        smsCode.postValue(Resource.loading(null))
        viewModelScope.launch(Dispatchers.IO) {
            repository.sendOtp(number,code).let {
                // code 200
                if(it.isSuccessful) {
                    smsCode.postValue(Resource.success(it.body().toString()))
                } else {
                    smsCode.postValue(Resource.error("${it.errorBody()?.string()}", null))
                }
            }
        }
    }

}