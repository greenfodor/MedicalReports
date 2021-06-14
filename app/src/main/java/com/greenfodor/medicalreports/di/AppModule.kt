package com.greenfodor.medicalreports.di

import com.greenfodor.medicalreports.persistance.Repository
import com.greenfodor.medicalreports.persistance.RepositoryImpl
import com.greenfodor.medicalreports.presentation.home.viewmodels.GetPatientViewModel
import com.greenfodor.medicalreports.presentation.home.viewmodels.RegisterPatientViewModel
import com.greenfodor.medicalreports.presentation.startup.viewmodels.LoginViewModel
import com.greenfodor.medicalreports.presentation.startup.viewmodels.RegisterViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<Repository> { RepositoryImpl(get()) }

    viewModel { LoginViewModel(get()) }

    viewModel { RegisterViewModel(get()) }

    viewModel { RegisterPatientViewModel(get()) }

    viewModel { GetPatientViewModel(get()) }
}