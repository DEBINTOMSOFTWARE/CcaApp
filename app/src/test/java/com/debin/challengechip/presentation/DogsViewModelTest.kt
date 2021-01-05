package com.debin.challengechip.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.SavedStateHandle
import com.debin.challengechip.breeds.domain.Dog
import com.debin.challengechip.breeds.interactors.GetDogs
import com.debin.challengechip.framework.utils.Resource
import com.debin.challengechip.presentation.dogs.DogsViewModel
import com.debin.challengechip.utils.BreedFactory
import com.debin.challengechip.utils.DataFactory
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
class DogsViewModelTest {
    private lateinit var mockGetDogsUseCase: GetDogs
    private lateinit var state : SavedStateHandle
    private lateinit var dogsViewModel : DogsViewModel

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        mockGetDogsUseCase = mock()
        state = SavedStateHandle()
        dogsViewModel = DogsViewModel(mockGetDogsUseCase, state)
    }

    @Test
    fun test_loading_is_emitted() {
        val dog = BreedFactory.makeDogResponse()
        stubWheneverThenReturn(Single.just(dog))
        dogsViewModel.getDogs(DataFactory.getBreedName())
        val mediatorLiveData = MediatorLiveData<Resource<Dog>>()
        mediatorLiveData.addSource(dogsViewModel.dogs) { result ->
            Assert.assertTrue(result is Resource.Loading)
        }
    }

    @Test
    fun test_error_is_emitted() {
        stubWheneverThenReturn(Single.error(RuntimeException()))
        dogsViewModel.getDogs(DataFactory.getBreedName())
        val mediatorLiveData = MediatorLiveData<Resource<Dog>>()
        mediatorLiveData.addSource(dogsViewModel.dogs) { result ->
            Assert.assertTrue(result is Resource.Error)
        }
    }

    @Test
    fun test_data_is_emitted() {
        val dog = BreedFactory.makeDogResponse()
        stubWheneverThenReturn(Single.just(dog))
        dogsViewModel.getDogs(DataFactory.getBreedName())
        val mediatorLiveData = MediatorLiveData<Resource<Dog>>()
        mediatorLiveData.addSource(dogsViewModel.dogs) { result ->
            Assert.assertTrue(result is Resource.Success)
        }
    }

    @Test
    fun test_data_emitted_is_DogBreed() {
        val dogs = BreedFactory.makeDogResponse()
        stubWheneverThenReturn(Single.just(dogs))
        dogsViewModel.getDogs(DataFactory.getBreedName())
        val mediatorLiveData = MediatorLiveData<Resource<Dog>>()
        mediatorLiveData.addSource(dogsViewModel.dogs) { result ->
            when(result) {
                is Resource.Success ->{
                    val breedResult = result.result
                    Assert.assertEquals(dogs, breedResult)
                }
            }
        }
    }

    private fun stubWheneverThenReturn(single: Single<Dog>) {
        whenever(mockGetDogsUseCase.buildUseCaseObservable(any(), any())).thenReturn(single)
    }
}