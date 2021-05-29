package com.greenfodor.medicalreports.di

import com.greenfodor.medicalreports.persistance.Repository
import com.greenfodor.medicalreports.persistance.RepositoryImpl
import org.koin.dsl.module

val appModule = module {
    single<Repository> { RepositoryImpl(get()) }
}