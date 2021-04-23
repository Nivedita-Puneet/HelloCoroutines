package com.example.hellomodernandroiddevelopment.playlists

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(FragmentComponent::class)
class PlaylistModule {

    @Provides
    fun playlistAPI(retrofit: Retrofit) = retrofit.create(PlaylistAPI::class.java)


    @Provides
    fun retrofit() = Retrofit.Builder()
        .baseUrl("https://5b9eabe1-72ad-4eda-b37f-3180c1d925e4.mock.pstmn.io/").
        client(OkHttpClient()).addConverterFactory(GsonConverterFactory.create()).build()

}