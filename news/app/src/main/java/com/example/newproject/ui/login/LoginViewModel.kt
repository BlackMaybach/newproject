package com.example.newproject.ui.login

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

class LoginViewModel : ViewModel() {

    private val repository = Repository()
    var loginData : MutableLiveData<Resource<LoginResponse>?> = MutableLiveData()
    val shared_pref = SharedPreference(App.instance.applicationContext)
    fun getLogin(login: AccountLogin) {
        loginData.postValue(Resource.loading(null))
        viewModelScope.launch(Dispatchers.IO) {
            repository.sendLogin(login).let {
                // code 200
                if(it.isSuccessful && it.code() == 200) {
                    loginData.postValue(Resource.success(it.body()))
                    shared_pref.userToken = it.body()?.accessToken
                } else {
                    loginData.postValue(Resource.error("${it.errorBody()?.string()}", null))
                }
            }
        }
    }

    fun clear() {
        loginData.postValue(null)
    }

}