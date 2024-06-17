package com.example.search.di

import com.example.search.ModalSearchFragment
import com.example.search.di.deps.SearchDependencies
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(dependencies = [SearchDependencies::class])
internal interface SearchComponent {

    fun inject(homeFragment: ModalSearchFragment)

    @Component.Builder
    interface Builder{
        fun deps(deps: SearchDependencies): Builder
        fun build(): SearchComponent
    }
}