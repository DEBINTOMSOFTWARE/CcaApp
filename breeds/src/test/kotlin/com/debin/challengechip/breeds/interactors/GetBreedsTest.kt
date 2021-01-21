package com.debin.challengechip.breeds.interactors

import com.debin.challengechip.breeds.domain.executor.PostExecutionThread
import com.debin.challengechip.breeds.domain.executor.ThreadExecutor
import com.debin.challengechip.breeds.domain.DogBreed
import com.debin.challengechip.breeds.domain.repository.IBreedsRepository
import com.debin.challengechip.breeds.utils.BreedFactory
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
class GetBreedsTest {
    private lateinit var getBreeds: GetBreeds
    private lateinit var mockThreadExecutor: ThreadExecutor
    private lateinit var mockPostExecutionThread: PostExecutionThread
    private lateinit var mockGetBreedInfoRepository : IBreedsRepository

    @Before
    fun setUp() {
        mockThreadExecutor = mock()
        mockPostExecutionThread = mock()
        mockGetBreedInfoRepository = mock()
        getBreeds = GetBreeds(mockGetBreedInfoRepository, mockThreadExecutor, mockPostExecutionThread)
    }

    @Test
    fun verifyGetBreed_use_case_call_getBreeds() {
        getBreeds.buildUseCaseObservable(null, null)
        verify(mockGetBreedInfoRepository).getBreeds()
    }

    @Test
    fun checkGetBreed_use_case_complete_without_errors() {
        stubWheneverThenReturn(Single.just(BreedFactory.makeBreedResponse()))
        val testObserver = getBreeds.buildUseCaseObservable(null, null).test()
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertNoTimeout()
    }

    @Test
    fun checkGetBreed_use_case_complete_with_errors() {
        val error = BreedFactory.makeBreedResponseError()
        stubWheneverThenReturn(Single.error(error))
        val testObserver = getBreeds.buildUseCaseObservable(null, null).test()
        testObserver.assertError(error)


    }
    @Test
    fun checkGetBreed_use_case_returns_data() {
        val breed = BreedFactory.makeBreedResponse()
        stubWheneverThenReturn(Single.just(breed))
        val testObserver = getBreeds.buildUseCaseObservable(null, null).test()
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertNoTimeout()
        testObserver.assertValue(breed)
    }

    @After
    fun tearDown() {
        getBreeds.dispose()
    }

    private fun stubWheneverThenReturn(single: Single<DogBreed>) {
       whenever(mockGetBreedInfoRepository.getBreeds()).thenReturn(single)
    }
}