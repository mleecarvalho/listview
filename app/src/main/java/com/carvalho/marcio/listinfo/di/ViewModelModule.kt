package com.carvalho.marcio.listinfo.di

import com.carvalho.marcio.listinfo.ui.features.login.LogInViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val viewModelModule = module {

    viewModel { LogInViewModel(get()) }

}