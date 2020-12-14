package com.debin.challengechip.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MediatorLiveData
import com.debin.challengechip.breeds.domain.DogBreed
import com.debin.challengechip.breeds.interactors.GetBreeds
import com.debin.challengechip.framework.utils.Resource
import com.debin.challengechip.presentation.breeds.BreedsViewModel
import com.debin.challengechip.utils.BreedFactory
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.lang.RuntimeException

@RunWith(JUnit4::class)
class BreedsViewModelTest {
    private lateinit var mockGetBreedsUseCases: GetBreeds
    private lateinit var breedsViewModel: BreedsViewModel

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        mockGetBreedsUseCases = mock()
        breedsViewModel = BreedsViewModel(mockGetBreedsUseCases)
    }

    @Test
    fun test_loading_is_emitted() {
        val breed = BreedFactory.makeBreedResponse()
        stubWheneverThenReturn(Single.just(breed))
        breedsViewModel.getBreeds()
        val mediatorLiveData = MediatorLiveData<Resource<DogBreed>>()
        mediatorLiveData.addSource(breedsViewModel.breeds) { result ->
           Assert.assertTrue(result is Resource.Loading)
        }
    }

    @Test
    fun test_error_is_emitted() {
      stubWheneverThenReturn(Single.error(RuntimeException()))
        breedsViewModel.getBreeds()
        val mediatorLiveData = MediatorLiveData<Resource<DogBreed>>()
        mediatorLiveData.addSource(breedsViewModel.breeds) { result ->
            Assert.assertTrue(result is Resource.Error)
        }
    }

    @Test
    fun test_data_is_emitted() {
        val breed = BreedFactory.makeBreedResponse()
        stubWheneverThenReturn(Single.just(breed))
        breedsViewModel.getBreeds()
        val mediatorLiveData = MediatorLiveData<Resource<DogBreed>>()
        mediatorLiveData.addSource(breedsViewModel.breeds) { result ->
            Assert.assertTrue(result is Resource.Success)
        }
    }

    @Test
    fun test_data_emitted_is_DogBreed() {
        val breed = BreedFactory.makeBreedResponse()
        stubWheneverThenReturn(Single.just(breed))
        breedsViewModel.getBreeds()
        val mediatorLiveData = MediatorLiveData<Resource<DogBreed>>()
        mediatorLiveData.addSource(breedsViewModel.breeds) { result ->
            when(result) {
               is Resource.Success ->{
                   val breedResult = result.result
                   Assert.assertEquals(breed, breedResult)
               }
            }
        }
    }

    private fun stubWheneverThenReturn(single: Single<DogBreed>) {
     whenever(mockGetBreedsUseCases.buildUseCaseObservable(any(), any())).thenReturn(single)
    }


}