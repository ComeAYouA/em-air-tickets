package com.example.emair.di

import android.app.Application
import com.example.search.di.deps.SearchComponentDependenciesProvider
import com.example.search.di.deps.SearchDependencies
import com.example.home.di.deps.HomeComponentDependenciesProvider
import com.example.home.di.deps.HomeDependencies

class EMAirApp: Application(),
    HomeComponentDependenciesProvider,
    SearchComponentDependenciesProvider {

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

    override fun getHomeComponentDependencies(): HomeDependencies {
        return applicationComponent
    }
    override fun getSearchComponentDependencies(): SearchDependencies {
        return applicationComponent
    }

}