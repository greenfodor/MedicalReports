package com.greenfodor.medicalreports.di

import com.greenfodor.medicalreports.persistance.Repository
import com.greenfodor.medicalreports.persistance.RepositoryImpl
import com.greenfodor.medicalreports.presentation.startup.LoginViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<Repository> { RepositoryImpl(get()) }

    viewModel { LoginViewModel(get()) }
}