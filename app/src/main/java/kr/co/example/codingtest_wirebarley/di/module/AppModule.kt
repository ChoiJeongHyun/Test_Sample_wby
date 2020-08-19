package kr.co.example.codingtest_wirebarley.di.module

import android.app.Application
import dagger.Module
import dagger.Provides
import kr.co.example.codingtest_wirebarley.api.ApiManager
import kr.co.example.codingtest_wirebarley.di.ApplicationContext
import kr.co.example.codingtest_wirebarley.repository.AppRepository
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    @ApplicationContext
    fun provideContext(application: Application) = application

    @Provides
    @Singleton
    fun provideAppRepository(apiManager: ApiManager) = AppRepository(apiManager)

    @Provides
    @Singleton
    fun provideApiManager() = ApiManager()

}