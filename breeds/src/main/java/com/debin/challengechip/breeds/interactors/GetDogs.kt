package com.debin.challengechip.breeds.interactors

import com.debin.challengechip.breeds.domain.executor.PostExecutionThread
import com.debin.challengechip.breeds.domain.executor.ThreadExecutor
import com.debin.challengechip.breeds.domain.Dog
import com.debin.challengechip.breeds.domain.repository.IDogsRepository
import com.debin.challengechip.breeds.domain.utils.OpenForTesting
import io.reactivex.Single

@OpenForTesting
open class GetDogs(private val dogsRepository: IDogsRepository,
                   threadExecutor: ThreadExecutor,
                   postExecutionThread: PostExecutionThread
) : SingleUseCase<Dog, String, String>(threadExecutor, postExecutionThread){
    public override fun buildUseCaseObservable(breedName: String?, arg2: String?): Single<Dog> {
        return dogsRepository.getDogs(breedName)
    }

}