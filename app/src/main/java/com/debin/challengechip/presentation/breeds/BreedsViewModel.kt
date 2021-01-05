package com.debin.challengechip.presentation.breeds

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.debin.challengechip.breeds.domain.Dog
import com.debin.challengechip.breeds.domain.DogBreed
import com.debin.challengechip.breeds.domain.utils.OpenForTesting
import com.debin.challengechip.breeds.interactors.GetBreeds
import com.debin.challengechip.breeds.interactors.GetDogs
import com.debin.challengechip.framework.utils.Resource
import com.debin.challengechip.framework.utils.ResponseListener
import io.reactivex.observers.DisposableSingleObserver

private const val TAG = "BreedsViewModel"

@OpenForTesting
class BreedsViewModel(private val getBreeds: GetBreeds) : ViewModel() {

    val loadingError = MutableLiveData<String>()
    private val _breeds = MutableLiveData<Resource<List<String>>>()
    private val _dogs = MutableLiveData<Resource<List<String>>>()
    val breeds : LiveData<Resource<List<String>>>
    get() = _breeds
    val dogs : LiveData<Resource<List<String>>>
    get() = _dogs
    lateinit var responseListener: ResponseListener

     init {
         loadingError.value = ""
         getBreeds()
         println(TAG + "init is called")
    }

    fun getBreeds() {
        println(TAG + "getBreeds")
        getBreeds.execute(BreedSubscriber())
    }


    private inner class BreedSubscriber : DisposableSingleObserver<DogBreed>() {
        override fun onSuccess(breeds: DogBreed) {
           _breeds.value = Resource.Success(breeds.message)
            Log.i(TAG, "Success")
        }

        override fun onError(e: Throwable) {
            _breeds.value = Resource.Error(e.message)
            loadingError.postValue(e.message)
            Log.i(TAG, "Failure")
        }
    }

    override fun onCleared() {
        //super.onCleared()
        Log.i(TAG, "Destroy")
        getBreeds.dispose()
    }


}