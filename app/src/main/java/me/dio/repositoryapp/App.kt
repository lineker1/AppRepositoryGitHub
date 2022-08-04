package me.dio.repositoryapp

import android.app.Application
import me.dio.repositoryapp.data.di.DataModule
import me.dio.repositoryapp.domain.di.DomainModule
import me.dio.repositoryapp.presentation.di.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
        }

        DataModule.load()
        DomainModule.load()
        PresentationModule.load()
    }
}