package kr.co.example.codingtest_wirebarley.di

import androidx.lifecycle.ViewModel
import dagger.MapKey
import javax.inject.Qualifier
import javax.inject.Scope
import kotlin.reflect.KClass

@MapKey
@Target(AnnotationTarget.FUNCTION)
annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ActivityScope

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationContext