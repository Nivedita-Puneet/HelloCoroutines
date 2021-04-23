package com.example.hellomodernandroiddevelopment.playlists

import retrofit2.http.GET


interface PlaylistAPI {

    @GET("music")
    suspend fun fetchAllPlaylists(): List<Playlist>
}
