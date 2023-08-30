package mx.mariovaldez.fetchcodechallenge.home.data.repository

import mx.mariovaldez.fetchcodechallenge.home.data.models.HiringRequest
import mx.mariovaldez.fetchcodechallenge.home.data.remote.RemoteDataSource
import mx.mariovaldez.fetchcodechallenge.home.presentation.mappers.HiringUIMapper
import mx.mariovaldez.fetchcodechallenge.home.presentation.models.HiringUI
import javax.inject.Inject
import kotlin.streams.toList

internal class HomeRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val mapper: HiringUIMapper
) {

    suspend fun getHiring(): Map<Int, List<HiringUI>> {
        val remoteHiring = remoteDataSource.getHiring()
        // println(remoteHiring)

        val mapped: List<HiringRequest> = remoteHiring.stream().filter {
            !it.name.isNullOrEmpty()
        }.toList()

        return mapper.map(mapped).stream().toList()
            .sortedBy {
                it.listId
            }
            .sortedBy {
                it.name
            }
            .groupBy {
                it.listId
            }.toSortedMap()
    }
}
