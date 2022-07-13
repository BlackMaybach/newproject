package com.example.newproject.ui.registration

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newproject.repository.Repository
import com.example.newproject.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AgreementViewModel: ViewModel() {
    private val repository = Repository()
    var agreementText: MutableLiveData<Resource<String>> = MutableLiveData()

    fun getAgreementText() {
        viewModelScope.launch(Dispatchers.IO) {
            //отправялем полученный номер в repository
            repository.takeText().let {
                // code 200
                if(it.isSuccessful) {
                    agreementText.postValue(Resource.success(it.body()))
                } else {
                    agreementText.postValue(Resource.error("${it.errorBody()?.string()}", null))
                }
            }

        }
    }

}