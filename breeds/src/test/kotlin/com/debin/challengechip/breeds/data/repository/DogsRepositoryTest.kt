package com.debin.challengechip.breeds.data.repository

import com.debin.challengechip.breeds.data.datasource.IDogsDataSource
import com.debin.challengechip.breeds.data.mappers.dogmapper.DogEntityMapper
import com.debin.challengechip.breeds.utils.BreedFactory
import com.debin.challengechip.breeds.utils.DataFactory
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito

@RunWith(JUnit4::class)
class DogsRepositoryTest {

    private lateinit var mockDataSource: IDogsDataSource
    private lateinit var dogsRepository: DogsRepository
    private lateinit var entityMapper: DogEntityMapper

    @Before
    fun setUp() {
        mockDataSource = mock()
        entityMapper = mock()
        dogsRepository = DogsRepository(mockDataSource, entityMapper)
    }

    @Test
    fun verifyGetDogsRepository_calls_getBreedAsync() {
        val dogResponse = BreedFactory.makeDogResponse()
        val dogEntity = BreedFactory.makeDogResponseEntity()
        Mockito.`when`(entityMapper.mapFromRemote(dogEntity)).thenReturn(dogResponse)
        Mockito.`when`(mockDataSource.getDogAsync(any())).thenReturn(Single.just(dogEntity))
        dogsRepository.getDogs(DataFactory.getBreedName()).test()
        verify(mockDataSource).getDogAsync(any())

    }

    @Test
    fun getDogsRepositoryComplete_without_errors() {
        val dogResponse = BreedFactory.makeDogResponse()
        val dogEntity = BreedFactory.makeDogResponseEntity()
        Mockito.`when`(mockDataSource.getDogAsync(any())).thenReturn(Single.just(dogEntity))
        Mockito.`when`(entityMapper.mapFromRemote(dogEntity)).thenReturn(dogResponse)
        val testOfServer = dogsRepository.getDogs(DataFactory.getBreedName()).toObservable().test()
        testOfServer.assertComplete()
        testOfServer.assertNoErrors()
        testOfServer.assertNoTimeout()
    }

    @Test
    fun getDogsRepositoryComplete_with_errors() {
        val error = BreedFactory.makeDogResponseError()
        Mockito.`when`(mockDataSource.getDogAsync(any())).thenReturn(Single.error(error))
        val testOfServer = dogsRepository.getDogs(DataFactory.getBreedName()).toObservable().test()
        testOfServer.assertError(error)
    }

    @Test
    fun getDogsRepository_returns_data() {
        val dogResponse = BreedFactory.makeDogResponse()
        val dogEntity = BreedFactory.makeDogResponseEntity()
        Mockito.`when`(mockDataSource.getDogAsync(any())).thenReturn(Single.just(dogEntity))
        Mockito.`when`(entityMapper.mapFromRemote(dogEntity)).thenReturn(dogResponse)
        val testOfServer = dogsRepository.getDogs(DataFactory.getBreedName()).toObservable().test()
        testOfServer.assertComplete()
        testOfServer.assertNoErrors()
        testOfServer.assertNoTimeout()
        testOfServer.assertValue(dogResponse)
    }
}