package com.example.home.di

import com.example.home.Factory
import com.example.home.HomeFragment
import com.example.home.di.deps.HomeDependencies
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(dependencies = [HomeDependencies::class])
internal interface HomeComponent {

    fun inject(homeFragment: HomeFragment)

    fun ticketsViewModelFactory(): Factory

    @Component.Builder
    interface Builder{
        fun deps(deps: HomeDependencies): Builder
        fun build(): HomeComponent
    }
}