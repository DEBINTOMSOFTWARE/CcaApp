package com.debin.challengechip.utils

import com.debin.challengechip.breeds.data.model.DogBreedEntity
import com.debin.challengechip.breeds.data.model.DogEntity
import com.debin.challengechip.breeds.domain.Dog
import com.debin.challengechip.breeds.domain.DogBreed

class BreedFactory {

    companion object Factory {
        fun makeBreedResponse() : DogBreed {
            val breedList = listOf<String>("affenpinscher", "african", "airedale", "dalmatian")
            val dogBreeds = DogBreed(breedList, "success")
            return dogBreeds
        }

        fun makeBreedResponseEntity() : DogBreedEntity {
            val breedList = listOf<String>("affenpinscher", "african", "airedale", "dalmatian")
            val dogBreeds = DogBreedEntity(breedList, "success")
            return dogBreeds
        }

        fun makeBreedResponseError() : Throwable {
            return Throwable()
        }

        fun makeDogResponse() : Dog {
            val uriList = listOf<String>("https://images.dog.ceo/breeds/hound-afghan/n02088094_1003.jpg",
                "https://images.dog.ceo/breeds/hound-afghan/n02088094_1007.jpg",
                "https://images.dog.ceo/breeds/hound-afghan/n02088094_1023.jpg",
                "https://images.dog.ceo/breeds/hound-afghan/n02088094_10263.jpg"
            )
            val dogs = Dog(uriList, "success")
            return dogs
        }

        fun makeDogResponseEntity() : DogEntity {
            val uriList = listOf<String>("https://images.dog.ceo/breeds/hound-afghan/n02088094_1003.jpg",
                "https://images.dog.ceo/breeds/hound-afghan/n02088094_1007.jpg",
                "https://images.dog.ceo/breeds/hound-afghan/n02088094_1023.jpg",
                "https://images.dog.ceo/breeds/hound-afghan/n02088094_10263.jpg"
            )
            val dogs = DogEntity(uriList, "success")
            return dogs
        }

        fun makeDogResponseError() : Throwable {
            return Throwable()
        }

        fun makeDogResponseList() : List<String> {
            val dogList = listOf<String>("https://images.dog.ceo/breeds/hound-afghan/n02088094_1003.jpg",
                "https://images.dog.ceo/breeds/hound-afghan/n02088094_1007.jpg",
                "https://images.dog.ceo/breeds/hound-afghan/n02088094_1023.jpg",
                "https://images.dog.ceo/breeds/hound-afghan/n02088094_10263.jpg")
            return dogList
        }
    }
}