package com.example.grasapper;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.view.MotionEvent;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;

public class SceneManager
{
    private ArrayList<Scene> scanes = new ArrayList<>();
    public static int ACTIVE_SCANE;
    public static int PREVIOUSE_SCENE;
    MusicManager manager;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public SceneManager(Context context)
    {
        manager = new MusicManager(context);

        ACTIVE_SCANE = 0;
        PREVIOUSE_SCENE = 0;
        scanes.add(new TitleScene(context)); //0
        scanes.add(new GameplayScene(context)); //1
        scanes.add(new MenuScene(context)); //2
        scanes.add(new HelpScene(context)); //3
        scanes.add(new CodeScene(context)); //4
        scanes.add(new Help2Scene(context)); //5
        scanes.add(new Help3Scene(context)); //6
        scanes.add(new StartScene(context)); //7
        scanes.add(new MenuScene2(context)); //8
        scanes.add(new Gameplay2Scene(context)); //9
        scanes.add(new Gameplay3Scene(context)); //10
        scanes.add(new AnimationTestScene(context));
    }

    public void update()
    {
        scanes.get(ACTIVE_SCANE).update();
        manager.update();
    }

    public void draw(Canvas canvas)
    {
        scanes.get(ACTIVE_SCANE).draw(canvas);
    }

    public void reciveTouch(MotionEvent event)
    {
        scanes.get(ACTIVE_SCANE).reciveTouch(event);
    }

}
