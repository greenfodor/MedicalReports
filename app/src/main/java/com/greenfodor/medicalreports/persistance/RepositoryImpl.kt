package com.greenfodor.medicalreports.persistance

import com.greenfodor.medicalreports.networking.AppApi
import org.koin.core.KoinComponent

class RepositoryImpl(private val appApi: AppApi) : Repository, KoinComponent {


}