package com.example.hellomodernandroiddevelopment.playlists

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PlaylistRepository @Inject constructor(private val service: PlayListService){
    suspend fun getPlayLists(): Flow<Result<List<Playlist>>> {
        return service.fetchPlayLists()
    }
}