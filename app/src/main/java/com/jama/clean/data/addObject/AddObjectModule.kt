package com.jama.clean.data.addObject

import com.jama.clean.data.addObject.remote.api.AddObjectService
import com.jama.clean.data.addObject.remote.models.request.AddObjectRequest
import com.jama.clean.data.addObject.repository.AddObjectRepositoryImpl
import com.jama.clean.data.common.module.NetworkModule
import com.jama.clean.domain.addObject.AddObjectRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class AddObjectModule {
    @Singleton
    @Provides
    fun provideAddObjectService(retrofit: Retrofit):AddObjectService{
        return retrofit.create(AddObjectService::class.java)
    }
    @Singleton
    @Provides
    fun provideAddObjectRepository(
        addObjectService: AddObjectService
    ):AddObjectRepository{
        return AddObjectRepositoryImpl(addObjectService)
    }
}