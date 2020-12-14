package com.debin.challengechip.suite

import com.debin.challengechip.presentation.BreedsViewModelTest
import com.debin.challengechip.presentation.DogsViewModelTest
import com.debin.challengechip.remote.BreedDataSourceImplTest
import com.debin.challengechip.remote.DogsDataSourceImplTest
import org.junit.runner.RunWith
import org.junit.runners.Suite


@RunWith(Suite::class)
@Suite.SuiteClasses(
    BreedDataSourceImplTest::class,
    DogsDataSourceImplTest::class,
    BreedsViewModelTest::class,
    DogsViewModelTest::class
)
class DogBreedSuite