package com.example.newproject.ui.registration


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newproject.App
import com.example.newproject.repository.Repository
import com.example.newproject.ui.api.models.AccountLogin
import com.example.newproject.ui.api.models.LoginResponse
import com.example.newproject.utils.Resource
import com.example.newproject.utils.SharedPreference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SmsViewModel: ViewModel() {

    private val repository = Repository()
    var smsCode: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()

    fun getSmsCodeFragment(account: AccountLogin) {
        smsCode.postValue(Resource.loading(null))
        viewModelScope.launch(Dispatchers.IO) {
            repository.sendSms(account).let {
                // code 200
                if(it.isSuccessful) {
                    smsCode.postValue(Resource.success(it.body()))
                } else {
                    smsCode.postValue(Resource.error("${it.errorBody()?.string()}", null))
                }
            }
        }
    }

}