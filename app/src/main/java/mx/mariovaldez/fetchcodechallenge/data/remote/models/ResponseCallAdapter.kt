package mx.mariovaldez.fetchcodechallenge.data.remote.models

import mx.mariovaldez.fetchcodechallenge.data.remote.mappers.ClientExceptionMapper
import mx.mariovaldez.fetchcodechallenge.data.remote.models.ResponseCall
import retrofit2.Call
import retrofit2.CallAdapter

internal class ResponseCallAdapter constructor(
    private val delegateAdapter: CallAdapter<Any, Call<*>>,
    private val clientExceptionMapper: ClientExceptionMapper
) : CallAdapter<Any, Call<*>> by delegateAdapter {

    override fun adapt(call: Call<Any>): Call<*> =
        delegateAdapter.adapt(ResponseCall(call, clientExceptionMapper))
}
