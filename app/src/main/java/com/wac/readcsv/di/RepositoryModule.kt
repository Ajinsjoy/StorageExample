package com.wac.readcsv.di

import com.wac.readcsv.csvreader.data.repository.DetailsRepositoryImpl
import com.wac.readcsv.csvreader.domain.repository.DetailsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindDetailsRepository(detailsRepositoryImpl: DetailsRepositoryImpl): DetailsRepository
}