package com.hussein.mahashin.di

import com.hussein.mahashin.core.data.getRoomDatabase
import com.hussein.mahashin.presentation.home.OrderViewModel
import com.hussein.mahashin.presentation.orderdetails.DetailsViewModel
import com.hussein.mahashin.presentation.products.ProductScreen
import com.hussein.mahashin.presentation.products.ProductViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module


expect val targetModule: Module
val appModule = module {
    single { getRoomDatabase(get()) }
    viewModelOf(::OrderViewModel)
    viewModelOf(::DetailsViewModel)
    viewModel { ProductViewModel(get(),get()) }
}
/*
fun initKoin(
    config : (KoinApplication.() -> Unit)? = null
)
{
    startKoin{
        config?.invoke(this)
        modules(targetModule , appModule)
    }
}*/
