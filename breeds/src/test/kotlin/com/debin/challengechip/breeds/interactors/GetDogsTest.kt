package com.debin.challengechip.breeds.interactors

import com.challenger.domain.executor.PostExecutionThread
import com.challenger.domain.executor.ThreadExecutor
import com.debin.challengechip.breeds.domain.Dog
import com.debin.challengechip.breeds.domain.repository.IDogsRepository
import com.debin.challengechip.breeds.utils.BreedFactory
import com.debin.challengechip.breeds.utils.DataFactory
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GetDogsTest {
    private lateinit var getDogs: GetDogs
    private lateinit var mockThreadExecutor: ThreadExecutor
    private lateinit var mockPostExecutionThread: PostExecutionThread
    private lateinit var mockGetDogInfoRepository : IDogsRepository

    @Before
    fun setUp() {
        mockThreadExecutor = mock()
        mockPostExecutionThread = mock()
        mockGetDogInfoRepository = mock()
        getDogs = GetDogs(mockGetDogInfoRepository, mockThreadExecutor, mockPostExecutionThread)
    }

    @Test
    fun verifyGetDogs_use_case_calls_getDops() {
        getDogs.buildUseCaseObservable(DataFactory.getBreedName(), null)
        verify(mockGetDogInfoRepository).getDogs(any())
    }

    @Test
    fun checkGetBreed_use_case_complete_without_errors() {
        stubWheneverThenReturn(Single.just(BreedFactory.makeDogResponse()))
        val testObserver = getDogs.buildUseCaseObservable(DataFactory.getBreedName(), null).test()
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertNoTimeout()
    }

    @Test
    fun checkGetBreed_use_case_complete_with_errors() {
        val error = BreedFactory.makeDogResponseError()
        stubWheneverThenReturn(Single.error(error))
        val testObserver = getDogs.buildUseCaseObservable(DataFactory.getBreedName(), null).test()
        testObserver.assertError(error)


    }
    @Test
    fun checkGetBreed_use_case_returns_data() {
        val dog = BreedFactory.makeDogResponse()
        stubWheneverThenReturn(Single.just(dog))
        val testObserver = getDogs.buildUseCaseObservable(DataFactory.getBreedName(), null).test()
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertNoTimeout()
        testObserver.assertValue(dog)
    }

    @After
    fun tearDown() {
        getDogs.dispose()
    }

    private fun stubWheneverThenReturn(single: Single<Dog>) {
        whenever(mockGetDogInfoRepository.getDogs(any())).thenReturn(single)
    }




}