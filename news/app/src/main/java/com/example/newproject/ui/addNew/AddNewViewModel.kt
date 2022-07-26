package com.example.newproject.ui.addNew

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newproject.repository.Repository
import com.example.newproject.ui.api.models.creditReferences.CreditReferences
import com.example.newproject.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddNewViewModel: ViewModel() {

    private val repository = Repository()
    var references : MutableLiveData<Resource<CreditReferences>> = MutableLiveData()


    fun getCreditReferences() {
        viewModelScope.launch(Dispatchers.IO) {

            repository.takeCreditReferences().let {
                if (it.isSuccessful) {
                    references.postValue(Resource.success(it.body()))
                } else {
                    references.postValue(Resource.error("${it.errorBody()?.string()}", null))
                }
            }
        }
    }
}