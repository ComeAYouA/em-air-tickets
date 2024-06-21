package com.example.tickets.di

import com.example.tickets.Factory
import com.example.tickets.TicketsFragment
import com.example.tickets.di.deps.TicketsDependencies
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(dependencies = [TicketsDependencies::class])
internal interface TicketsComponent {
    fun inject(fragment: TicketsFragment)

    fun ticketsViewModelFactory(): Factory

    @Component.Builder
    interface Builder{
        fun deps(deps: TicketsDependencies): Builder
        fun build(): TicketsComponent
    }
}