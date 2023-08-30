package mx.mariovaldez.fetchcodechallenge.data.remote.services

import mx.mariovaldez.fetchcodechallenge.home.data.models.HiringRequest
import retrofit2.http.GET

internal interface ApiServices {

    @GET("hiring.json")
    suspend fun getHiring(): List<HiringRequest>
}
