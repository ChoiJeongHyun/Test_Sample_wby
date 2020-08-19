package kr.co.example.codingtest_wirebarley.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import kr.co.example.codingtest_wirebarley.base.BaseApplication
import kr.co.example.codingtest_wirebarley.di.module.ActivityBindingModule
import kr.co.example.codingtest_wirebarley.di.module.AppModule
import kr.co.example.codingtest_wirebarley.di.module.ViewModelModule
import javax.inject.Singleton


@Singleton
@Component(modules = [AndroidInjectionModule::class , AppModule::class , ViewModelModule::class, ActivityBindingModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(application: BaseApplication)
}