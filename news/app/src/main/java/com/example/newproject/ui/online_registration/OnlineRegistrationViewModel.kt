package com.example.newproject.ui.online_registration

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newproject.repository.Repository
import com.example.newproject.ui.api.models.references.getReferences
import com.example.newproject.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OnlineRegistrationViewModel: ViewModel() {
    private val repository = Repository()
    var references : MutableLiveData<Resource<getReferences>> = MutableLiveData()

    fun getReferences() {
        viewModelScope.launch(Dispatchers.IO) {
            //отправялем полученный номер в repository
            repository.takeReferences().let {
                // code 200
                if(it.isSuccessful) {
                    references.postValue(Resource.success(it.body()))
                } else {
                    references.postValue(Resource.error("${it.errorBody()?.string()}", null))
                }
            }

        }
    }

}