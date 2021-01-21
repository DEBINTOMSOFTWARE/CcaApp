package com.debin.challengechip.breeds.interactors

import com.debin.challengechip.breeds.domain.executor.PostExecutionThread
import com.debin.challengechip.breeds.domain.executor.ThreadExecutor
import com.debin.challengechip.breeds.domain.DogBreed
import com.debin.challengechip.breeds.domain.repository.IBreedsRepository
import com.debin.challengechip.breeds.domain.utils.OpenForTesting
import io.reactivex.Single

@OpenForTesting
open class GetBreeds(private val breedsRepository: IBreedsRepository,
                     threadExecutor: ThreadExecutor,
                     postExecutionThread: PostExecutionThread
) : SingleUseCase<DogBreed ,Int, String>(threadExecutor, postExecutionThread) {
    public override fun buildUseCaseObservable(params: Int?, arg2: String?): Single<DogBreed> {
        return breedsRepository.getBreeds()
    }
}
