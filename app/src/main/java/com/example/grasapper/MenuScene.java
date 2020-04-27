package com.example.grasapper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Build;
import android.view.MotionEvent;

import androidx.annotation.RequiresApi;

public class MenuScene implements Scene
{
    Sprite background;
    NavigationTextButton toGame;
    ExitTextButton saveAndExit;
    ExitTextButton exit;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public MenuScene(Context context)
    {
        Bitmap backgroundImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.manu_screen);

        background = new Sprite(backgroundImage, 0,0,1080,1794);
        toGame = new NavigationTextButton(context,"Back to Game", 300, 800, R.font.a_b, Color.BLACK, 80, 1000, 1);
        saveAndExit = new ExitTextButton(context,"Save and Exit", 300, 1000, R.font.a_b, Color.BLACK, 80, 1000);
        exit = new ExitTextButton(context,"Exit the Game", 300, 1200, R.font.a_b, Color.BLACK, 80, 1000);
    }

    @Override
    public void update()
    {
        toGame.update();
        saveAndExit.update();
        exit.update();
    }

    @Override
    public void draw(Canvas canvas)
    {
        background.draw(canvas);
        toGame.draw(canvas);
        saveAndExit.draw(canvas);
        exit.draw(canvas);
    }

    @Override
    public void reciveTouch(MotionEvent event)
    {
        toGame.reciveTouch(event);
        saveAndExit.reciveTouch(event);
        exit.reciveTouch(event);
    }

    @Override
    public void terminate()
    {

    }
}
