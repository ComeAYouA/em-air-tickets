package com.example.offer.di

import com.example.offer.Factory
import com.example.offer.OffersFragment
import com.example.offer.di.deps.OffersDependencies
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(dependencies = [OffersDependencies::class])
internal interface OffersComponent {

    fun inject(fragment: OffersFragment)

    fun offersViewModelFactory(): Factory

    @Component.Builder
    interface Builder{
        fun deps(deps: OffersDependencies): Builder
        fun build(): OffersComponent
    }
}