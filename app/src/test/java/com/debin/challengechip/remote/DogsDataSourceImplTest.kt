package com.debin.challengechip.remote


import com.debin.challengechip.breeds.domain.Dog
import com.debin.challengechip.utils.BreedFactory
import com.debin.challengechip.framework.network.ApiService
import com.debin.challengechip.framework.remote.DogsDataSourceImpl
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
class DogsDataSourceImplTest {
    private lateinit var mockApiService: ApiService
    private lateinit var dogsDataSourceImpl: DogsDataSourceImpl

    @Before
    fun setUp() {
        mockApiService = mock()
        dogsDataSourceImpl = DogsDataSourceImpl(mockApiService)
    }

    @Test
    fun verifyGetDogAsyncCalled() {
        dogsDataSourceImpl.getDogAsync(DataFactory.getBreedName())
        verify(mockApiService).getDogs(any())
    }

    @Test
    fun getBreedRepositoryComplete_without_errors() {
        val dogs = BreedFactory.makeDogResponse()
        stubWheneverThenReturn(Single.just(dogs))
        val testObserver = dogsDataSourceImpl.getDogAsync(DataFactory.getBreedName()).test()
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertNoTimeout()
    }

    @Test
    fun getBreedRepositoryComplete_with_errors() {
        val error = BreedFactory.makeDogResponseError()
        stubWheneverThenReturn(Single.error(error))
        val testObserver = dogsDataSourceImpl.getDogAsync(DataFactory.getBreedName()).test()
        testObserver.assertError(error)
    }

    @Test
    fun getBreedRepository_returns_data() {
        val dogs = BreedFactory.makeDogResponse()
        stubWheneverThenReturn(Single.just(dogs))
        val testObserver = dogsDataSourceImpl.getDogAsync(DataFactory.getBreedName()).test()
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertNoTimeout()
        testObserver.assertValue(dogs)
    }

    private fun stubWheneverThenReturn(single: Single<Dog>) {
        whenever(mockApiService.getDogs(any())).thenReturn(single)
    }
}