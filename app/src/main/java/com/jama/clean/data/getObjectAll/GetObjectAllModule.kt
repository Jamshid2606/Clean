package com.jama.clean.data.getObjectAll

import com.jama.clean.data.common.module.NetworkModule
import com.jama.clean.data.getObjectAll.remote.api.GetObjectAllService
import com.jama.clean.data.getObjectAll.repository.GetObjectAllRepositoryImpl
import com.jama.clean.domain.getObjectAll.GetObjectAllRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class
GetObjectAllModule {
    @Singleton
    @Provides
    fun provideGetObjectAllService(retrofit: Retrofit):GetObjectAllService{
        return retrofit.create(GetObjectAllService::class.java)
    }
    @Singleton
    @Provides
    fun provideRegisterRepository(
        getObjectAllService: GetObjectAllService
    ): GetObjectAllRepository {
        return GetObjectAllRepositoryImpl(getObjectAllService)
    }
}