package com.example.hellomodernandroiddevelopment.playlists

import kotlinx.coroutines.flow.Flow

class PlaylistRepository(private val service: PlayListService){
    suspend fun getPlayLists(): Flow<Result<List<Playlist>>> {
        return service.fetchPlayLists()
    }
}