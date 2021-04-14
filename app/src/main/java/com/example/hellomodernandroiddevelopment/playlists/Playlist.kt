package com.example.hellomodernandroiddevelopment.playlists

import com.example.hellomodernandroiddevelopment.R

data class Playlist(
    val id: String,
    val name: String,
    val category: String,
    val image: Int = R.mipmap.playlist
)