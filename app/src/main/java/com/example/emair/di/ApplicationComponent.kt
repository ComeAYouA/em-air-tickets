package com.example.emair.di

import android.app.Application
import com.example.network.TicketsApi
import com.example.network.di.NetworkModule
import com.example.tickets.di.TicketsDependencies
import dagger.BindsInstance
import dagger.Component

@Component(modules = [NetworkModule::class])
interface ApplicationComponent: TicketsDependencies{
    override val ticketsApi: TicketsApi

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): ApplicationComponent
    }
}