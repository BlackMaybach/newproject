package com.example.newproject.ui.registration


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newproject.repository.Repository
import com.example.newproject.ui.api.models.AccountRegister
import com.example.newproject.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class RegistrationViewModel : ViewModel() {

    private val repository = Repository()

    var registration: MutableLiveData<Resource<String>> = MutableLiveData()

//    fun postNumber(number: Register, action: () -> Unit) {
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.sendNumber(number).let {
//                if (it.code() == 200) {
//                    registration.postValue(it.body())
//                    action.invoke()
//                } else {
//                    registration.postValue(it.errorBody()?.string())
//                }
//            }
//        }
//    }

    fun getNumberFragment(number: AccountRegister) {
        registration.postValue(Resource.loading(null))

        viewModelScope.launch(Dispatchers.IO) {
            //отправялем полученный номер в repository
            repository.sendNumber(number).let {
                // code 200
                if (it.isSuccessful) {
                    registration.postValue(Resource.success(it.body()))
                } else {
                    registration.postValue(Resource.error("${it.errorBody()?.string()}", null))
                }
            }

        }
    }
}