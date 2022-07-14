package com.example.newproject.ui.registration

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newproject.repository.Repository
import com.example.newproject.ui.api.models.AccountPassword
import com.example.newproject.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PasswordViewModel: ViewModel() {

    val repository = Repository()

    var pass: MutableLiveData<Resource<String>> = MutableLiveData()

    fun getPassword(password: AccountPassword) {
        pass.postValue(Resource.loading(null))
        viewModelScope.launch(Dispatchers.IO) {
            repository.sendPassword(password).let {
                // code 200
                if(it.isSuccessful) {
                    pass.postValue(Resource.success(it.body()))
                } else {
                    pass.postValue(Resource.error("${it.errorBody()?.string()}", null))
                }
            }
        }
    }

}