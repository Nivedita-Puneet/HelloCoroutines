package com.example.hellomodernandroiddevelopment.unittests.playlist

import com.example.hellomodernandroiddevelopment.playlists.PlayListService
import com.example.hellomodernandroiddevelopment.playlists.Playlist
import com.example.hellomodernandroiddevelopment.playlists.PlaylistRepository
import com.example.hellomodernandroiddevelopment.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class PlaylistRepositoryShould:BaseUnitTest() {

    private val service: PlayListService = mock()
    private val playlists = mock<List<Playlist>>()
    private val exception = RuntimeException("Something went wrong")

    @Test
    fun getPlaylistsFromService() = runBlockingTest {

        val repository = PlaylistRepository(service)
        repository.getPlayLists()
        verify(service, times(1)).fetchPlayLists()
    }

    @Test
    fun emitPlayListFromService() = runBlockingTest {
        val repository = mockSuccessfulCase()
        assertEquals(playlists, repository.getPlayLists().first().getOrNull())
    }

    @Test
    fun propagateErrors() = runBlockingTest {
        val repository = mockFailureCase()
        assertEquals(exception, repository.getPlayLists().first().exceptionOrNull())
    }


    private suspend fun mockSuccessfulCase(): PlaylistRepository {
        whenever(service.fetchPlayLists()).thenReturn(
            flow {
                emit(Result.success(playlists))
            }
        )
        return PlaylistRepository(service)
    }

    private suspend fun mockFailureCase(): PlaylistRepository {
        whenever(service.fetchPlayLists()).thenReturn(
            flow {
                emit(Result.failure<List<Playlist>>(exception))
            }
        )
        return PlaylistRepository(service)
    }


}