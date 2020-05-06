package com.example.grasapper;

import android.content.Context;
import android.graphics.Canvas;
import android.media.MediaPlayer;

import java.util.ArrayList;

public class MusicManager
{
    MediaPlayer player;
    private ArrayList<MediaPlayer> musicList = new ArrayList<>();

    public static boolean CHANGE;
    public static int ACTIVE_MUSIC;

    MusicManager(Context context)
    {
        CHANGE = false;
        ACTIVE_MUSIC = 0;

        MediaPlayer titleScreen = MediaPlayer.create(context, R.raw.titlescreen);
        MediaPlayer gameplay = MediaPlayer.create(context, R.raw.gameplay);

        musicList.add(titleScreen);
        musicList.add(gameplay);

        player = musicList.get(ACTIVE_MUSIC);
        player.start();
        player.setLooping(true);
    }

    public void update()
    {
        if (CHANGE)
        {
            player.stop();
            player.prepareAsync();
            player = musicList.get(ACTIVE_MUSIC);
            player.start();
            player.setLooping(true);
            CHANGE = false;
        }
    }

}
