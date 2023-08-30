package mx.mariovaldez.fetchcodechallenge.data.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mx.mariovaldez.fetchcodechallenge.BuildConfig
import mx.mariovaldez.fetchcodechallenge.data.remote.api.ApiServiceFactory
import mx.mariovaldez.fetchcodechallenge.data.remote.models.HeadersInterceptor
import mx.mariovaldez.fetchcodechallenge.data.remote.models.ResponseCallAdapterFactory
import mx.mariovaldez.fetchcodechallenge.data.remote.services.ApiServices
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal object NetworkModule {

    private const val READ_TIMEOUT: Long = 120
    private const val CONNECT_TIMEOUT: Long = 120

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder().disableHtmlEscaping().create()

    @Singleton
    @Provides
    fun provideOkHttpClient(
        headersInterceptor: HeadersInterceptor
    ): okhttp3.OkHttpClient = OkHttpClient().newBuilder().apply {
        readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            addInterceptor(
                HttpLoggingInterceptor { message ->
                    Timber.d(message)
                }.apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
        }
        addInterceptor(headersInterceptor)
    }.build()

    @Singleton
    @Provides
    fun providesRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson,
        responseCallAdapterFactory: ResponseCallAdapterFactory
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addCallAdapterFactory(responseCallAdapterFactory)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Singleton
    @Provides
    fun providesApiServices(
        apiServiceFactory: ApiServiceFactory
    ): ApiServices = apiServiceFactory.createApiService(
        ApiServices::class.java
    )
}
