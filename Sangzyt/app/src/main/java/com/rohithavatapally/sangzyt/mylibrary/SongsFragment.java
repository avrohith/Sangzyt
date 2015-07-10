package com.rohithavatapally.sangzyt.mylibrary;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.rohithavatapally.sangzyt.R;
import com.rohithavatapally.sangzyt.mylibrary.adapters.SongsCursorAdapter;
import com.rohithavatapally.sangzyt.playback.PlaybackService;

/**
 * Created by RohithAvatapally on 6/27/15.
 */
public class SongsFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private final static int SONG_URL_LOADER = 1;
    private ProgressBar progressBar;
    private ListView listView;
    private SongsCursorAdapter songsCursorAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        songsCursorAdapter = new SongsCursorAdapter(getActivity(), R.layout.song_listview_row, null, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_songs, null);

        progressBar = (ProgressBar) view.findViewById(R.id.song_fragment_progress_bar);

        listView = (ListView) view.findViewById(R.id.songs_fragment_listview);
        listView.setAdapter(songsCursorAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                long trackId = parent.getItemIdAtPosition(position);
                Intent intent = new Intent(getActivity(), PlaybackService.class);
                intent.putExtra("trackid", trackId);
                getActivity().startService(intent);
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        getLoaderManager().initLoader(SONG_URL_LOADER, null, this);
        listView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id){
            case SONG_URL_LOADER:
                Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                return new CursorLoader(getActivity(), uri, null, null, null, null);

            default:return null;
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        listView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        songsCursorAdapter.changeCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        songsCursorAdapter.changeCursor(null);
    }
}
