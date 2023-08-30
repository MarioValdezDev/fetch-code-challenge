package mx.mariovaldez.fetchcodechallenge.home.domain.usecases

import kotlinx.coroutines.withContext
import mx.mariovaldez.fetchcodechallenge.domain.dispatchers.DefaultDispatcherProvider
import mx.mariovaldez.fetchcodechallenge.home.data.repository.HomeRepository
import mx.mariovaldez.fetchcodechallenge.home.presentation.models.HiringUI
import javax.inject.Inject

internal class GetHiring @Inject constructor(
    private val repository: HomeRepository,
    private val defaultDispatcherProvider: DefaultDispatcherProvider
) {

    suspend operator fun invoke(): Map<Int, List<HiringUI>> = withContext(defaultDispatcherProvider.default) {
        repository.getHiring()
    }
}
