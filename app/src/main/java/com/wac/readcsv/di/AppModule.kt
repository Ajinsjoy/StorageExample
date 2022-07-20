package com.wac.readcsv.di

import android.app.Application

import androidx.room.Room
import com.wac.readcsv.csvreader.data.data.local.CSVAppDatabase
import com.wac.readcsv.csvreader.data.repository.DetailsRepositoryImpl
import com.wac.readcsv.csvreader.domain.repository.DetailsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideCSVAppDatabase(application: Application) =
        Room.databaseBuilder(application, CSVAppDatabase::class.java, "csv_app_db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideCategoryDao(csvAppDatabase: CSVAppDatabase) =
        csvAppDatabase.detailsDao

}