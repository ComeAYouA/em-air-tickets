package com.example.tickets.di

import com.example.tickets.TicketsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(dependencies = [TicketsDependencies::class])
internal interface TicketsComponent {

    fun inject(ticketsFragment: TicketsFragment)

    @Component.Builder
    interface Builder{
        fun deps(deps: TicketsDependencies): Builder
        fun build(): TicketsComponent
    }
}