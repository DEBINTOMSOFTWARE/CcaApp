package com.debin.challengechip.breeds.data.repository

import com.debin.challengechip.breeds.data.datasource.IBreedsDataSource
import com.debin.challengechip.breeds.domain.DogBreed
import com.debin.challengechip.breeds.utils.BreedFactory
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Before

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito

@RunWith(JUnit4::class)
class BreedsRepositoryTest {

    private lateinit var mockDataSource: IBreedsDataSource
    private lateinit var breedsRepository: BreedsRepository

    @Before
    fun setUp() {
        mockDataSource = mock()
        breedsRepository = BreedsRepository(mockDataSource)
    }

    @Test
    fun verifyGetBreedRepository_calls_getBreedAsync() {
        breedsRepository.getBreeds()
        verify(mockDataSource).getBreedsAsync()
    }

    @Test
    fun getBreedRepositoryComplete_without_errors() {
        val breeds = BreedFactory.makeBreedResponse()
        Mockito.`when`(mockDataSource.getBreedsAsync()).thenReturn(Single.just(breeds))
        val testObserver = breedsRepository.getBreeds().toObservable().test()
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertNoTimeout()
    }

    @Test
    fun getBreedRepositoryComplete_with_errors() {
        val error = BreedFactory.makeBreedResponseError()
        Mockito.`when`(mockDataSource.getBreedsAsync()).thenReturn(Single.error(error))
        val testObserver = breedsRepository.getBreeds().toObservable().test()
        testObserver.assertError(error)
    }

    @Test
    fun getBreedRepository_returns_data() {
        val breeds = BreedFactory.makeBreedResponse()
        Mockito.`when`(mockDataSource.getBreedsAsync()).thenReturn(Single.just(breeds))
        val testObserver = breedsRepository.getBreeds().toObservable().test()
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertNoTimeout()
        testObserver.assertValue(breeds)
    }

}