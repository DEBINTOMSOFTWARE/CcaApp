package com.debin.challengechip.presentation.breeds

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.debin.challengechip.breeds.domain.Dog
import com.debin.challengechip.breeds.domain.DogBreed
import com.debin.challengechip.breeds.domain.utils.OpenForTesting
import com.debin.challengechip.breeds.interactors.GetBreeds
import com.debin.challengechip.breeds.interactors.GetDogs
import com.debin.challengechip.framework.utils.Resource
import io.reactivex.observers.DisposableSingleObserver

private const val TAG = "BreedsViewModel"

@OpenForTesting
open class BreedsViewModel(private val getBreeds: GetBreeds) : ViewModel() {

    val progress = MutableLiveData<Int>()
    val loadingError = MutableLiveData<String>()
    private val _breeds = MutableLiveData<Resource<List<String>>>()
    private val _dogs = MutableLiveData<Resource<List<String>>>()
    val breeds : LiveData<Resource<List<String>>>
    get() = _breeds
    val dogs : LiveData<Resource<List<String>>>
    get() = _dogs

     init {
         progress.value = 8
         loadingError.value = ""
         getBreeds()
         println(TAG + "init is called")
    }

    open fun getBreeds() {
        progress.value = 0
        getBreeds.execute(BreedSubscriber())
    }

//    fun getDogs(breedName : String) {
//        progress.value = 0
//        getDogs.execute(DogsSubscriber(),breedName)
//    }

    open private inner class BreedSubscriber : DisposableSingleObserver<DogBreed>() {
        override fun onSuccess(breeds: DogBreed) {
           _breeds.value = Resource.Success(breeds.message)
            Log.i(TAG, "Success")
            progress.value = 8
        }

        override fun onError(e: Throwable) {
            _breeds.value = Resource.Error(e.message)
            loadingError.postValue(e.message)
            Log.i(TAG, "Failure")
            progress.value = 8
        }
    }

//    private inner class DogsSubscriber : DisposableSingleObserver<Dog>() {
//        override fun onSuccess(dog: Dog) {
//            _dogs.postValue(Resource.Success(dog.message))
//            Log.i(TAG, "Success")
//            progress.value = 8
//        }
//
//        override fun onError(e: Throwable) {
//            _dogs.postValue(Resource.Error(e.message))
//            loadingError.postValue(e.message)
//            Log.i(TAG, "Failure")
//            progress.value = 8
//        }
//
//    }

    override fun onCleared() {
        super.onCleared()
        Log.i(TAG, "Distroy")
        getBreeds.dispose()
       // getDogs.dispose()
    }


}