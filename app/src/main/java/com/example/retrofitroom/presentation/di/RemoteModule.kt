package com.example.retrofitroom.presentation.di

import com.example.retrofitroom.common.BASE_URL
import com.example.retrofitroom.data.db.remote.api.APIInterface
import com.example.retrofitroom.data.db.remote.api.MoviesAPI
import com.example.retrofitroom.data.db.repository.dataImpl.MoviesRemoteDataImpl
import com.example.retrofitroom.data.db.repository.dataSource.MoviesRemoteDataSource
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RemoteModule() {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder().apply {
            addInterceptor(interceptor)
        }.build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideAPIInterface(retrofit: Retrofit): APIInterface {
        return retrofit.create(MoviesAPI::class.java)
    }

    @Singleton
    @Provides
    fun providesRemoteMovieDataSource(service: APIInterface): MoviesRemoteDataSource {
        return MoviesRemoteDataImpl(service)
    }
}