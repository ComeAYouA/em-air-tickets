package com.example.emair.di

import android.app.Application
import com.example.tickets.di.TicketsComponentDependenciesProvider
import com.example.tickets.di.TicketsDependencies

class EMAirApp: Application(), TicketsComponentDependenciesProvider{

    val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent
            .builder()
            .application(this)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        //appComponent = DaggerAppComponent.factory().create(this)
    }

    override fun getTicketsComponentDependencies(): TicketsDependencies {
        return applicationComponent
    }

}