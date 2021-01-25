package com.debin.challengechip.breeds.data.repository

import com.debin.challengechip.breeds.data.datasource.IBreedsDataSource
import com.debin.challengechip.breeds.data.mappers.dogbreedmapper.DogBreedEntityMapper
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
    private lateinit var entityMapper: DogBreedEntityMapper

    @Before
    fun setUp() {
        mockDataSource = mock()
        entityMapper = mock()
        breedsRepository = BreedsRepository(mockDataSource, entityMapper)
    }

    @Test
    fun verifyGetBreedRepository_calls_getBreedAsync() {
        val breeds = BreedFactory.makeBreedResponse()
        val breedsEntity = BreedFactory.makeBreedResponseEntity()
        Mockito.`when`(mockDataSource.getBreedsAsync()).thenReturn(Single.just(breedsEntity))
        Mockito.`when`(entityMapper.mapFromRemote(breedsEntity)).thenReturn(breeds)
        breedsRepository.getBreeds()
        verify(mockDataSource).getBreedsAsync()
    }

    @Test
    fun getBreedRepositoryComplete_without_errors() {
        val breeds = BreedFactory.makeBreedResponse()
        val breedsEntity = BreedFactory.makeBreedResponseEntity()
        Mockito.`when`(mockDataSource.getBreedsAsync()).thenReturn(Single.just(breedsEntity))
        Mockito.`when`(entityMapper.mapFromRemote(breedsEntity)).thenReturn(breeds)
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
        val breedsEntity = BreedFactory.makeBreedResponseEntity()
        Mockito.`when`(mockDataSource.getBreedsAsync()).thenReturn(Single.just(breedsEntity))
        Mockito.`when`(entityMapper.mapFromRemote(breedsEntity)).thenReturn(breeds)
        val testObserver = breedsRepository.getBreeds().toObservable().test()
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertNoTimeout()
        testObserver.assertValue(breeds)
    }

}