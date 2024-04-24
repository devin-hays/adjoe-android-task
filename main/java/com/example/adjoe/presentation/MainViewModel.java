package com.example.adjoe.presentation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.adjoe.domain.Album;
import com.example.adjoe.domain.FetchAlbumsTaskExecutor;

import java.util.List;

public class MainViewModel extends ViewModel {
    private MutableLiveData<List<Album>> albumsLiveData = new MutableLiveData<>();

    public LiveData<List<Album>> getAlbums() {
        return albumsLiveData;
    }

    public MainViewModel() {
        super();
        fetchAlbums();
    }

    public void fetchAlbums() {
        new FetchAlbumsTaskExecutor(albums -> {
            albumsLiveData.setValue(albums);
        }).execute();
    }
}
