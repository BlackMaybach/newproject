package com.example.newproject.ui.registration


import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newproject.repository.Repository
import com.example.newproject.ui.api.models.Register
import com.example.newproject.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class RegistrationViewModel : ViewModel() {

    private val repository = Repository()

    var registration: MutableLiveData<String> = MutableLiveData()

    fun postNumber(number: Register, action: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.sendNumber(number).let {
                if (it.code() == 200) {
                    registration.postValue(it.body())
                    action.invoke()
                } else {
                    registration.postValue(it.errorBody()?.string())
                }
            }
        }
    }


}