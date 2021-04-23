package com.example.hellomodernandroiddevelopment.playlists

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.example.hellomodernandroiddevelopment.R
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class PlaylistFragment : Fragment() {

    lateinit var viewModel: PlaylistViewModel
    lateinit var viewModelFactory: PlaylistViewModelFactory
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://5b9eabe1-72ad-4eda-b37f-3180c1d925e4.mock.pstmn.io/").
        client(OkHttpClient()).addConverterFactory(GsonConverterFactory.create()).build()

    private val api = retrofit.create(PlaylistAPI::class.java)
    private val service = PlayListService(api)
    private val repository:PlaylistRepository = PlaylistRepository(service)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)

        setuViewModel()
        viewModel.playlists.observe(this as LifecycleOwner, { playlists ->

            if(playlists.getOrNull() != null){
                setupList(view, playlists.getOrNull()!!)

            }else{
                //TODO
            }
        })

        return view
    }

    private fun setupList(
        view: View?,
        playlists: List<Playlist>
    ) {
        with(view as RecyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = MyPlaylistRecyclerViewAdapter(playlists)
        }
    }

    private fun setuViewModel() {
        viewModelFactory = PlaylistViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(PlaylistViewModel::class.java)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            PlaylistFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}