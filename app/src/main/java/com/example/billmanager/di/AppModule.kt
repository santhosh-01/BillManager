package com.example.billmanager.di

import android.app.Application
import com.example.billmanager.BillManagerApp
import com.example.billmanager.db.BillManagerDatabase
import com.example.billmanager.repository.QuantityRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideBillManagerDatabase(app: Application): BillManagerDatabase {
        return BillManagerDatabase.getInstance(app)
    }

    @Singleton
    @Provides
    fun provideNewsRepository(db: BillManagerDatabase): QuantityRepository {
        return QuantityRepository(db)
    }

}