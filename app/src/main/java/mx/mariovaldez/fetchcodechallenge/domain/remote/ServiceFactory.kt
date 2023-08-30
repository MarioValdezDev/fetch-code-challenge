package mx.mariovaldez.fetchcodechallenge.domain.remote

internal interface ServiceFactory {

    fun <T> createApiService(serviceClass: Class<T>): T
}
