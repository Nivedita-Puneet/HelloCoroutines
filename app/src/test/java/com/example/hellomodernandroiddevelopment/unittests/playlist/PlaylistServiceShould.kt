package com.example.hellomodernandroiddevelopment.unittests.playlist

import com.example.hellomodernandroiddevelopment.playlists.PlayListService
import com.example.hellomodernandroiddevelopment.playlists.Playlist
import com.example.hellomodernandroiddevelopment.playlists.PlaylistAPI
import com.example.hellomodernandroiddevelopment.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import java.lang.RuntimeException

class PlaylistServiceShould:BaseUnitTest() {

    private val playListAPI: PlaylistAPI = mock()
    private lateinit var service:PlayListService
    private val playLists:List<Playlist> = mock()

    @Test
    fun getPlayListsFromAPI() = runBlockingTest {

        service = PlayListService(playListAPI)
        service.fetchPlayLists().first()

        verify(playListAPI, times(1)).fetchAllPlaylists()

    }

    @Test
    fun convertValuesToFlowResultAndEmitThem() = runBlockingTest {
        whenever(playListAPI.fetchAllPlaylists()).thenReturn(playLists)
        service = PlayListService(api = playListAPI)
        assertEquals(Result.success(playLists), service.fetchPlayLists().first())
    }

    @Test
    fun emitErrorResultsWhenNetworkFails() = runBlockingTest {
        whenever(playListAPI.fetchAllPlaylists()).thenThrow(RuntimeException("To the Backend developers"))
        service = PlayListService(playListAPI)
        assertEquals("Something went wrong", service.fetchPlayLists().first().exceptionOrNull()?.message)
    }

}