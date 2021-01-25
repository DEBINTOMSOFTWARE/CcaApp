package com.debin.challengechip.remote

import com.debin.challengechip.breeds.data.model.DogBreedEntity
import com.debin.challengechip.breeds.domain.Dog
import com.debin.challengechip.breeds.domain.DogBreed
import com.debin.challengechip.framework.network.ApiService
import com.debin.challengechip.framework.remote.BreedDataSourceImpl
import com.debin.challengechip.framework.remote.DogsDataSourceImpl
import com.debin.challengechip.utils.BreedFactory
import com.debin.challengechip.utils.DataFactory
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class BreedDataSourceImplTest {
    private lateinit var mockApiService: ApiService
    private lateinit var breedsDataSourceImpl: BreedDataSourceImpl

    @Before
    fun setUp() {
        mockApiService = mock()
        breedsDataSourceImpl = BreedDataSourceImpl(mockApiService)
    }

    @Test
    fun verifyGetDogAsyncCalled() {
        breedsDataSourceImpl.getBreedsAsync()
        verify(mockApiService).getBreeds()
    }

    @Test
    fun getBreedRepositoryComplete_without_errors() {
        val breed = BreedFactory.makeBreedResponseEntity()
        stubWheneverThenReturn(Single.just(breed))
        val testObserver = breedsDataSourceImpl.getBreedsAsync().toObservable().test()
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertNoTimeout()
    }

    @Test
    fun getBreedRepositoryComplete_with_errors() {
        val error = BreedFactory.makeBreedResponseError()
        stubWheneverThenReturn(Single.error(error))
        val testObserver = breedsDataSourceImpl.getBreedsAsync().toObservable().test()
        testObserver.assertError(error)
    }

    @Test
    fun getBreedRepository_returns_data() {
        val breed = BreedFactory.makeBreedResponseEntity()
        stubWheneverThenReturn(Single.just(breed))
        val testObserver = breedsDataSourceImpl.getBreedsAsync().toObservable().test()
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertNoTimeout()
        testObserver.assertValue(breed)
    }

    private fun stubWheneverThenReturn(single: Single<DogBreedEntity>) {
        whenever(mockApiService.getBreeds()).thenReturn(single)
    }
}