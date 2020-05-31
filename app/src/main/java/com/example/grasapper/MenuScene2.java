package com.example.grasapper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Build;
import android.view.MotionEvent;

import androidx.annotation.RequiresApi;

public class MenuScene2 implements Scene
{
    Sprite background;
    NavigationTextButton toGame;
    ResetSaveTextButton reset;
    ExitTextButton saveAndExit;
    Context conn;
    @RequiresApi(api = Build.VERSION_CODES.M)
    public MenuScene2(Context context)
    {
        conn=context;
        Bitmap backgroundImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.manu_screen);
        background = new Sprite(backgroundImage, 0,0,1080,1794);
        toGame = new NavigationTextButton(context,"Reasume", 300, 800, R.font.a_b, Color.BLACK, 80, 1000, 7);
        reset = new ResetSaveTextButton(context,"Reset Save", 300, 1000, R.font.a_b, Color.BLACK, 80, 1000);
        saveAndExit = new ExitTextButton(context,"Exit the Game", 300, 1200, R.font.a_b, Color.BLACK, 80, 1000);

    }

    @Override
    public void update()
    {
        toGame.update();
        saveAndExit.update();
        reset.update(conn);
    }

    @Override
    public void draw(Canvas canvas)
    {
        background.draw(canvas);
        toGame.draw(canvas);
        reset.draw(canvas);
        saveAndExit.draw(canvas);
    }

    @Override
    public void reciveTouch(MotionEvent event)
    {
        toGame.reciveTouch(event);
        saveAndExit.reciveTouch(event);
        reset.reciveTouch(event);
    }

    @Override
    public void terminate()
    {

    }
}
