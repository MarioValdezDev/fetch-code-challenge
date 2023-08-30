package mx.mariovaldez.fetchcodechallenge.home.data.remote

import mx.mariovaldez.fetchcodechallenge.data.remote.services.ApiServices
import mx.mariovaldez.fetchcodechallenge.home.data.models.HiringRequest
import javax.inject.Inject

internal class RemoteDataSource @Inject constructor(
    private val apiServices: ApiServices
) {

    suspend fun getHiring(): List<HiringRequest> = apiServices.getHiring()

}