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
    BackTextButton toGame;
    NavigationTextButton choose;
    ExitTextButton exit;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public MenuScene(Context context)
    {
        Bitmap backgroundImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.manu_screen);

        background = new Sprite(backgroundImage, 0,0,1080,1794);
        toGame = new BackTextButton(context,"Back to Game", 300, 800, R.font.a_b, Color.BLACK, 80, 1000);
        choose = new NavigationTextButton(context,"Choose level", 300, 1000, R.font.a_b, Color.BLACK, 80, 1000, 7);
        exit = new ExitTextButton(context,"Save and exit", 300, 1200, R.font.a_b, Color.BLACK, 80, 1000);
    }

    @Override
    public void update()
    {
        toGame.update();
        choose.update();
        exit.update();
    }

    @Override
    public void draw(Canvas canvas)
    {
        background.draw(canvas);
        toGame.draw(canvas);
        choose.draw(canvas);
        exit.draw(canvas);
    }

    @Override
    public void reciveTouch(MotionEvent event)
    {
        toGame.reciveTouch(event);
        choose.reciveTouch(event);
        exit.reciveTouch(event);
    }

    @Override
    public void terminate()
    {

    }
}
