package com.example.adjoe.domain;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.Collections;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FetchAlbumsTaskExecutor {

    private static final String ALBUMS_URL = "https://jsonplaceholder.typicode.com/albums";
    private final TaskListener listener;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor(); // or use newFixedThreadPool(n) for multiple threads
    private final Handler handler = new Handler(Looper.getMainLooper());

    public FetchAlbumsTaskExecutor(TaskListener listener) {
        this.listener = listener;
    }

    public void execute() {
        executorService.execute(() -> {
            List<Album> albumList = new ArrayList<>();

            try {
                URL urlObj = new URL(ALBUMS_URL);
                HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        stringBuilder.append(line).append('\n');
                    }
                    JSONArray jsonArray = new JSONArray(stringBuilder.toString());
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject albumJson = jsonArray.getJSONObject(i);
                        Album album = new Album(albumJson.getInt("id"), albumJson.getString("title"));
                        albumList.add(album);
                    }

                    Collections.sort(albumList, Comparator.comparingInt(Album::getId));
                } finally {
                    connection.disconnect();
                }
            } catch (Exception e) {
                Log.e("FetchAlbumsTask", "Error fetching albums", e);
            }

            handler.post(() -> listener.onTaskComplete(albumList));
        });
    }

    public interface TaskListener {
        void onTaskComplete(List<Album> albums);
    }
}
