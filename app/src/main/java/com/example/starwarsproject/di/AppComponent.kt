package com.example.starwarsproject.di


import android.content.Context
import com.example.starwarsproject.di.modules.DatabaseModule
import com.example.starwarsproject.di.modules.DomainModule
import com.example.starwarsproject.di.modules.RemoteModule
import com.example.starwarsproject.view.fragments.filters_fragment.FiltersFragmentViewModel
import com.example.starwarsproject.view.fragments.home_fragment.HomeFragmentViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DatabaseModule::class,DomainModule::class,RemoteModule::class])
interface AppComponent {
    fun inject(viewModel: HomeFragmentViewModel)
    fun inject(filtersFragmentViewModel: FiltersFragmentViewModel)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun build(): AppComponent
    }
}