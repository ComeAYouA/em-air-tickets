package com.example.emair.di

import android.app.Application
import com.example.search.di.deps.SearchComponentDependenciesProvider
import com.example.search.di.deps.SearchDependencies
import com.example.home.di.deps.HomeComponentDependenciesProvider
import com.example.home.di.deps.HomeDependencies
import com.example.offer.di.deps.OffersComponentDependenciesProvider
import com.example.offer.di.deps.OffersDependencies
import com.example.tickets.di.deps.TicketsComponentDependenciesProvider
import com.example.tickets.di.deps.TicketsDependencies

internal class EMAirApp: Application(),
    HomeComponentDependenciesProvider,
    SearchComponentDependenciesProvider,
    OffersComponentDependenciesProvider,
    TicketsComponentDependenciesProvider
{
    private val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent
            .builder()
            .application(this)
            .context(this.applicationContext)
            .build()
    }

    override fun getHomeComponentDependencies(): HomeDependencies = applicationComponent
    override fun getTicketsComponentDependencies(): TicketsDependencies = applicationComponent
    override fun getSearchComponentDependencies(): SearchDependencies = applicationComponent
    override fun getOffersComponentDependencies(): OffersDependencies = applicationComponent

}