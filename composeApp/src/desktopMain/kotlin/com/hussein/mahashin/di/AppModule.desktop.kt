package com.hussein.mahashin.di

import com.hussein.mahashin.database.getDatabaseBuilder
import org.koin.core.module.Module
import org.koin.dsl.module

actual val targetModule = module{
    single { getDatabaseBuilder(context = get()) }
}