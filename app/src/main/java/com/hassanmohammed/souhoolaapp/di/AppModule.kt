package com.hassanmohammed.souhoolaapp.di

import com.hassanmohammed.souhoolaapp.data.datasource.local.FlikerPhotoLocalDataSource
import com.hassanmohammed.souhoolaapp.data.datasource.local.FlikerPhotoLocaleDataSourceImpl
import com.hassanmohammed.souhoolaapp.data.datasource.remote.FlikerPhotoRemoteDataSource
import com.hassanmohammed.souhoolaapp.data.datasource.remote.FlikerPhotoRemoteDataSourceImpl
import com.hassanmohammed.souhoolaapp.data.db.FlikerDatabase
import com.hassanmohammed.souhoolaapp.data.db.FlikerPhotoDao
import com.hassanmohammed.souhoolaapp.data.remote.FlikerService
import com.hassanmohammed.souhoolaapp.data.resporitory.FlikerRepository
import com.hassanmohammed.souhoolaapp.data.resporitory.FlikerRepositoryImpl
import com.hassanmohammed.souhoolaapp.domain.usecases.GetFlikerPhotoFromApiUseCase
import com.hassanmohammed.souhoolaapp.domain.usecases.GetFlikerPhotoFromApiUseCaseImpl
import com.hassanmohammed.souhoolaapp.domain.usecases.GetFlikerPhotoFromDatabaseUseCase
import com.hassanmohammed.souhoolaapp.domain.usecases.GetFlikerPhotoFromDatabaseUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    @Singleton
    fun provideFlikerPhotoLocaleDataSource(flikerPhotoDao: FlikerPhotoDao): FlikerPhotoLocalDataSource {
        return FlikerPhotoLocaleDataSourceImpl(flikerPhotoDao)
    }

    @Provides
    @Singleton
    fun provideFlikerPhotoRemoteLocalDataSource(
        api: FlikerService,
        db: FlikerDatabase
    ): FlikerPhotoRemoteDataSource {
        return FlikerPhotoRemoteDataSourceImpl(api, db)
    }

    @Provides
    @Singleton
    fun provideFlikerRepository(
        localDataSource: FlikerPhotoLocalDataSource,
        remoteDataSource: FlikerPhotoRemoteDataSource
    ): FlikerRepository {
        return FlikerRepositoryImpl(localDataSource, remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideGetFlikerPhotoFromApiUseCase(repository: FlikerRepository): GetFlikerPhotoFromApiUseCase {
        return GetFlikerPhotoFromApiUseCaseImpl(repository)
    }

    @Provides
    @Singleton
    fun provideFlikerPhotoFromDatabaseUseCase(repository: FlikerRepository): GetFlikerPhotoFromDatabaseUseCase {
        return GetFlikerPhotoFromDatabaseUseCaseImpl(repository)
    }
}