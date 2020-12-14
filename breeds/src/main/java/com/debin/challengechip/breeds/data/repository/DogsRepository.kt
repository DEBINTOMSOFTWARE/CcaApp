package com.debin.challengechip.breeds.data.repository

import com.debin.challengechip.breeds.data.datasource.IDogsDataSource
import com.debin.challengechip.breeds.domain.Dog
import com.debin.challengechip.breeds.domain.repository.IDogsRepository
import io.reactivex.Single

class DogsRepository(private val dataSource: IDogsDataSource) : IDogsRepository {
    override fun getDogs(breedName: String?): Single<Dog> {
        return dataSource.getDogAsync(breedName).map {
            val list = it.message
            it.message = getRandomList(list)
            it
        }
    }

    private fun getRandomList(result : List<String>) : List<String> {
        val resultList = ArrayList<String>()
        val randomList = ArrayList<String>()
        resultList.addAll(result)
        resultList.shuffle()
        for (i in resultList.takeLast(10)) {
            randomList.add(i)
        }
        return randomList
    }
}