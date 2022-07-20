package com.example.newproject.ui.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newproject.repository.Repository
import com.example.newproject.ui.api.models.UserInfo.userInfo
import com.example.newproject.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileFormViewModel: ViewModel() {

    val repository = Repository()

    var infoFormUser: MutableLiveData<Resource<userInfo>> = MutableLiveData()

    fun getInfoUser() {
        viewModelScope.launch(Dispatchers.IO) {

            repository.takeUserInfo().let {
                // code 200
                if (it.isSuccessful) {
                    infoFormUser.postValue(Resource.success(it.body()))
                } else {
                    infoFormUser.postValue(Resource.error("${it.errorBody()?.string()}", null))
                }
            }
        }

    }

}