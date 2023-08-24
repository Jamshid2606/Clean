package com.jama.clean.data.singleObject

import com.jama.clean.data.common.module.NetworkModule
import com.jama.clean.data.singleObject.remote.api.SingleObjectService
import com.jama.clean.data.singleObject.repository.SingleObjectRepositoryImpl
import com.jama.clean.domain.singleObject.SingleObjectRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class SingleObjectModule {
    @Singleton
    @Provides
    fun provideGetSingleObjectService(retrofit: Retrofit):SingleObjectService{
        return retrofit.create(SingleObjectService::class.java)
    }
    @Provides
    @Singleton
    fun provideSingleObjectRepository(
        getSingleObjectService: SingleObjectService
    ):SingleObjectRepository{
        return SingleObjectRepositoryImpl(getSingleObjectService)
    }
}