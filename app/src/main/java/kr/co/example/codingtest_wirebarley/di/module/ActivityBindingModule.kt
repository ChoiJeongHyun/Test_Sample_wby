package kr.co.example.codingtest_wirebarley.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import kr.co.example.codingtest_wirebarley.di.ActivityScope
import kr.co.example.codingtest_wirebarley.views.MainActivity

@Module
abstract class ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity

}