package com.greenfodor.medicalreports.di

import com.google.gson.Gson
import com.greenfodor.medicalreports.BuildConfig
import com.greenfodor.medicalreports.networking.AppApi
import com.greenfodor.medicalreports.networking.factories.NullOnEmptyConverterFactory
import com.greenfodor.medicalreports.networking.interceptors.CustomHttpLogging
import com.greenfodor.medicalreports.networking.interceptors.ErrorInterceptor
import com.greenfodor.medicalreports.networking.interceptors.SessionInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { provideDefaultOkHttpClient() }
    single { provideRetrofit(client = get()) }
    single { provideApi(get()) }
}

fun provideDefaultOkHttpClient(): OkHttpClient {
    val okHttpClient = OkHttpClient.Builder()

    okHttpClient.addInterceptor(SessionInterceptor())
    okHttpClient.addNetworkInterceptor(ErrorInterceptor())

    if (BuildConfig.DEBUG) {
        okHttpClient.addInterceptor(HttpLoggingInterceptor(CustomHttpLogging()).apply { level = HttpLoggingInterceptor.Level.BODY })
    }

    return okHttpClient.build()
}

fun provideRetrofit(client: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_ENDPOINT)
        .client(client)
        .addConverterFactory(NullOnEmptyConverterFactory())
        .addConverterFactory(GsonConverterFactory.create(Gson()))
        .build()
}

fun provideApi(retrofit: Retrofit): AppApi = retrofit.create(AppApi::class.java)