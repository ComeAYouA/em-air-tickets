package com.example.emair.di

import android.app.Application
import com.example.data.CitiesFlowUtil
import com.example.network.TicketsApi
import com.example.network.di.NetworkModule
import com.example.search.ModalSearchFragment
import com.example.search.di.deps.SearchDependencies
import com.example.home.di.deps.HomeDependencies
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.BindsInstance
import dagger.Component
import dagger.Provides
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
abstract class ApplicationComponent: HomeDependencies, SearchDependencies {
    abstract override val ticketsApi: TicketsApi
    abstract override val citiesFlowUtil: CitiesFlowUtil

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): ApplicationComponent
    }
}