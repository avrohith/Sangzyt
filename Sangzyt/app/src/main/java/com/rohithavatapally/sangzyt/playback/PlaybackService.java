package com.rohithavatapally.sangzyt.playback;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ContentUris;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.os.PowerManager;
import android.provider.MediaStore;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.NotificationCompat;

import com.rohithavatapally.sangzyt.MainActivity;
import com.rohithavatapally.sangzyt.R;
import com.rohithavatapally.sangzyt.SecondActivity;
import com.rohithavatapally.sangzyt.ThirdActivity;

import java.io.IOException;

/**
 * Created by RohithAvatapally on 7/4/15.
 */
public class PlaybackService extends Service implements MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener {

    private MediaPlayer mediaPlayer = null;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        initMediaPlayer();

        long trackId = intent.getLongExtra("trackid", 0);
        playSong(trackId);

        return START_NOT_STICKY;
    }

    private void initMediaPlayer(){
        // Create an instance of MediaPlayer
        // only if it does not exist
        if (mediaPlayer == null){
            mediaPlayer = new MediaPlayer();
        }
        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.setOnErrorListener(this);
        mediaPlayer.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);
    }

    private void playSong(final long trackId){
        try {
            Uri uri = ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, trackId);
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setDataSource(getApplicationContext(), uri);
            mediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        Notification notification = setUpNotification("Song");
        startForeground(123, notification);
        mp.start();
    }

    private Notification setUpNotification(String songName){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("My Notification");
        builder.setContentText("Hello World");

        Intent resultantIntent = new Intent(this, MainActivity.class);
        Intent secondActivity  = new Intent(this, SecondActivity.class);
        Intent thirdActivity = new Intent(this, ThirdActivity.class);

        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(this);
        taskStackBuilder.addParentStack(MainActivity.class);
        taskStackBuilder.addNextIntent(resultantIntent);
        taskStackBuilder.addNextIntent(secondActivity);
        taskStackBuilder.addNextIntent(thirdActivity);

        PendingIntent pendingIntent = taskStackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);

        return builder.build();
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        return false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
