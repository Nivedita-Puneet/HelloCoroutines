package com.example.hellomodernandroiddevelopment.playlists

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.lang.RuntimeException

class PlayListService(private val api:PlaylistAPI) {
    suspend fun fetchPlayLists() : Flow<Result<List<Playlist>>> {

        return flow{
            emit(Result.success(api.fetchAllPlaylists()))
        }.catch {
            emit(Result.failure(RuntimeException("Something went wrong")))
        }

    }
}