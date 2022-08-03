package com.example.newproject.ui.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newproject.repository.Repository
import com.example.newproject.ui.api.models.photo.PhotosResponse
import com.example.newproject.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfilePhotoViewModel: ViewModel() {

    val repository = Repository()

    var actions: MutableLiveData<Resource<PhotosResponse>> = MutableLiveData()
    private val listOfImages = arrayListOf<String>()

    fun takeID() {
        viewModelScope.launch(Dispatchers.IO) {

            repository.getPhotosIds().let {
                // code 200
                if (it.isSuccessful) {
                    
                    it.body()?.userFilesId?.forEach { id ->
                        repository.getImageFromId(id).body()?.fileName.let { base64 ->
                            if(base64 != null) {
                                listOfImages.add(
                                    base64
                                )
                            }
                        }
                    }
                    actions.postValue(Resource.success(it.body()))

                } else {
                    actions.postValue(Resource.error("${it.errorBody()?.string()}", null))
                }
            }
        }
    }
}