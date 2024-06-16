package com.example.emair.di

import android.app.Application
import com.example.data.CitiesFlowUtil
import com.example.network.TicketsApi
import com.example.network.di.NetworkModule
import com.example.tickets.di.deps.TicketsDependencies
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface ApplicationComponent: TicketsDependencies {
    override val ticketsApi: TicketsApi
    override val citiesFlowUtil: CitiesFlowUtil

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): ApplicationComponent
    }
}