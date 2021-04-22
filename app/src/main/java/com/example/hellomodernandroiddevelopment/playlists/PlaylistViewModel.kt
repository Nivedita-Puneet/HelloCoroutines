package com.example.hellomodernandroiddevelopment.playlists

import androidx.lifecycle.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PlaylistViewModel(private val repository: PlaylistRepository): ViewModel() {
    val playlists= liveData {
        emitSource(repository.getPlayLists().asLiveData())
    }

}