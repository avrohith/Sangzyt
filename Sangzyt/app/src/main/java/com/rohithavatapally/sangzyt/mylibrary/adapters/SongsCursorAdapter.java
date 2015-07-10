package com.rohithavatapally.sangzyt.mylibrary.adapters;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.support.v4.widget.ResourceCursorAdapter;
import android.view.View;
import android.widget.TextView;

import com.rohithavatapally.sangzyt.R;

/**
 * Created by RohithAvatapally on 7/4/15.
 */
public class SongsCursorAdapter extends ResourceCursorAdapter {

    public SongsCursorAdapter(Context context, int layout, Cursor c, int flags) {
        super(context, layout, c, flags);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView songTitle = (TextView) view.findViewById(R.id.song_listview_row_title_textview);
        songTitle.setText(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE)));

        TextView songId = (TextView) view.findViewById(R.id.song_listview_row_artist_textview);
        songId.setText(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)));
    }

    @Override
    protected void onContentChanged() {
        super.onContentChanged();
    }

    @Override
    public long getItemId(int position) {
        final Cursor cursor = getCursor();
        cursor.moveToPosition(position);
        long trackId = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media._ID));
        return trackId;
    }
}
