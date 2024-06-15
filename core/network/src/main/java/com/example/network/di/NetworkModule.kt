package com.example.network.di

import com.example.network.TicketsApi
import com.example.network.retrofit.TicketsApiImpl
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

@Module
interface NetworkModule{
    @Binds
    fun bindTicketsApiImpl_to_TicketsApi(input: TicketsApiImpl): TicketsApi

    companion object{
        private val json = Json {
            coerceInputValues = true
            ignoreUnknownKeys = true
        }

        @Provides
        fun providesRetrofit(): Retrofit {
            return Retrofit
                .Builder()
                .baseUrl("https://run.mocky.io/v3/")
                .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
                .build()
        }
    }
}