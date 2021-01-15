package com.debin.challengechip.presentation.dogs

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.debin.challengechip.breeds.domain.Dog
import com.debin.challengechip.breeds.domain.utils.OpenForTesting
import com.debin.challengechip.breeds.interactors.GetDogs
import com.debin.challengechip.framework.utils.Resource
import io.reactivex.observers.DisposableSingleObserver

private const val TAG = "DogsViewModel"

@OpenForTesting
open class DogsViewModel(private val getDogs: GetDogs,
                    private val state: SavedStateHandle) : ViewModel() {

    private val _dogs = MutableLiveData<Resource<List<String>>>()
    val dogs : LiveData<Resource<List<String>>>
        get() = _dogs
    val progress = MutableLiveData<Int>()
    val loadingError = MutableLiveData<String>()
    var _savedBreedName = MutableLiveData<String>()
    val savedBreedName : LiveData<String>
    get() = _savedBreedName

    init {
        progress.value = 8
        loadingError.value = ""
        _savedBreedName.value = ""
    }

    companion object {
        private val BREED_NAME = "breedName"
    }

    open fun getDogs(breedName : String) {
        progress.value = 0
        saveCurrentBreed(breedName)
        getDogs.execute(DogsSubscriber(),breedName)
        getCurrentBreed()
    }

    private fun saveCurrentBreed(breedName: String) {
        state[BREED_NAME] = breedName
    }

    private fun getCurrentBreed()  {
        _savedBreedName = state.getLiveData<String>(BREED_NAME)
    }

    private inner class DogsSubscriber : DisposableSingleObserver<Dog>() {
        override fun onSuccess(dog: Dog) {
            _dogs.value = Resource.Success(dog.message)
            progress.value = 8
        }

        override fun onError(e: Throwable) {
            _dogs.value = Resource.Error(e.message)
            loadingError.postValue(e.message)
            progress.value = 8
        }

    }

    override fun onCleared() {
        getDogs.dispose()
    }
}