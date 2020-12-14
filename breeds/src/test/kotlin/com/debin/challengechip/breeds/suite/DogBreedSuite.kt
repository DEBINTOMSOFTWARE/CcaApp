package com.debin.challengechip.breeds.suite

import com.debin.challengechip.breeds.data.repository.BreedsRepositoryTest
import com.debin.challengechip.breeds.data.repository.DogsRepositoryTest
import com.debin.challengechip.breeds.interactors.GetBreedsTest
import com.debin.challengechip.breeds.interactors.GetDogsTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    GetBreedsTest::class,
    GetDogsTest::class,
    BreedsRepositoryTest::class,
    DogsRepositoryTest::class
)

class DogBreedSuite