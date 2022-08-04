package me.dio.repositoryapp.data.di

import android.util.Log
import com.google.gson.GsonBuilder
import me.dio.repositoryapp.data.repositories.RepoRepository
import me.dio.repositoryapp.data.repositories.RepoRepositoryImpl
import me.dio.repositoryapp.data.services.GitHubService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataModule {

    private const val DK_HTTP = "Okhttp"

    fun load() {
        loadKoinModules(networkModels() + repositoriesModule())
    }

    private fun networkModels(): Module {
        return module {
            single {
                val interceptor = HttpLoggingInterceptor() {
                    Log.e(DK_HTTP, it)
                }
                interceptor.level = HttpLoggingInterceptor.Level.BODY

                OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()
            }

            single {
                GsonConverterFactory.create(GsonBuilder().create())
            }

            single {
                createService<GitHubService>(get(), get())
            }
        }
    }

    private fun repositoriesModule(): Module {
        return module {
            single<RepoRepository> { RepoRepositoryImpl(get()) }
        }
    }

    private inline fun <reified T> createService(
        client: OkHttpClient,
        factory: GsonConverterFactory
    ): T {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .build().create(T::class.java)
    }
}