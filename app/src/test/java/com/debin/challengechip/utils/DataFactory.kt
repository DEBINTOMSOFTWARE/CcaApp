package com.debin.challengechip.utils

class DataFactory {

    companion object Factory{
       fun getBreedName() : String {
           val breedName = listOf("affenpinscher", "african", "airedale", "dalmatian").random()
           return breedName
       }
    }
}