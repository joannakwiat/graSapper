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

    @RequiresApi(api = Build.VERSION_CODES.M)
    public SceneManager(Context context)
    {
        ACTIVE_SCANE = 0;
        scanes.add(new TitleScene(context));
        scanes.add(new GameplayScene(context));
        scanes.add(new MenuScene(context));
        scanes.add(new HelpScene(context));
        scanes.add(new CodeScene(context));
    }

    public void update()
    {
        scanes.get(ACTIVE_SCANE).update();
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
