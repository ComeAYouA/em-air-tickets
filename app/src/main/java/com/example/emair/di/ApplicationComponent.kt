package com.example.emair.di

import android.app.Application
import android.content.Context
import com.example.data.DataFlowUtil
import com.example.network.TicketsApi
import com.example.network.di.NetworkModule
import com.example.search.di.deps.SearchDependencies
import com.example.home.di.deps.HomeDependencies
import com.example.offer.di.deps.OffersDependencies
import com.example.tickets.di.deps.TicketsDependencies
import dagger.BindsInstance
import dagger.Component
import dagger.Provides
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
internal interface ApplicationComponent:
    HomeDependencies,
    SearchDependencies,
    OffersDependencies,
    TicketsDependencies
{
    override val ticketsApi: TicketsApi
    override val dataFlowUtil: DataFlowUtil

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application: Application): Builder
        @BindsInstance
        fun context(context: Context): Builder

        fun build(): ApplicationComponent
    }
}