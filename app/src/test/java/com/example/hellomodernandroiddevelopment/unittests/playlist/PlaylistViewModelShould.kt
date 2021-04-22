package com.example.hellomodernandroiddevelopment.unittests.playlist

import com.example.hellomodernandroiddevelopment.playlists.Playlist
import com.example.hellomodernandroiddevelopment.playlists.PlaylistRepository
import com.example.hellomodernandroiddevelopment.playlists.PlaylistViewModel
import com.example.hellomodernandroiddevelopment.utils.BaseUnitTest
import com.example.hellomodernandroiddevelopment.utils.getValueForTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import java.lang.RuntimeException

class PlaylistViewModelShould: BaseUnitTest() {


    private val repository: PlaylistRepository = mock()
    private val playlists = mock<List<Playlist>>()
    private val expected = Result.success(playlists)
    private val exception = RuntimeException("Something went wrong")

    private fun mockSuccessfulCase(): PlaylistViewModel {
        runBlocking {

            whenever(repository.getPlayLists()).thenReturn(flow
            {
                emit(expected)
            })
        }
        return PlaylistViewModel(repository)
    }
    @Test
    fun getPlaylistsFromRepository() = runBlockingTest {
        val viewModel = mockSuccessfulCase()
        viewModel.playlists.getValueForTest()
        verify(repository, times(1)).getPlayLists()
    }

     @Test
     fun emitErrorWhenReceivedError(){
         runBlocking {
             whenever(repository.getPlayLists()).thenReturn(
                 flow {
                     emit(Result.failure<List<Playlist>>(exception))
                 }
             )

             val playlistViewModel = PlaylistViewModel(repository)
             assertEquals(exception, playlistViewModel.playlists?.getValueForTest()?.exceptionOrNull())
         }
     }

    @Test
    fun emitPlaylistsFromRepository() = runBlockingTest {

        val viewModel = mockSuccessfulCase()
        assertEquals(expected, viewModel.playlists.getValueForTest())
    }


}