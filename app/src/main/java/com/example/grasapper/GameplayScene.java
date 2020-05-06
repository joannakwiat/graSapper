package com.example.grasapper;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.Log;
import android.view.MotionEvent;

import androidx.annotation.RequiresApi;


public class GameplayScene implements Scene
{
    Sprite background;
    NavigationButton toMenuButton;
    NavigationButton toHelpButton;
    NavigationButton toCodeButton;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public GameplayScene(Context context)
    {
        Bitmap backgroundImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.gameplay_screen);
        Bitmap toMenuButtonImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.manu);
        Bitmap toHelpButtonImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.info);
        Bitmap toCodeButtonImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.code);

        background = new Sprite(backgroundImage, 0,0,1080,1794);
        toMenuButton = new NavigationButton(context, toMenuButtonImage, 925, 1100, 150, 150, 2);
        toHelpButton = new NavigationButton(context, toHelpButtonImage, 925, 1300, 150, 150, 3);
        toCodeButton = new NavigationButton(context, toCodeButtonImage, 900, 1500, 280, 294, 4);
    }

    @Override
    public void update()
    {
        toMenuButton.update();
        toHelpButton.update();
        toCodeButton.update();
    }

    @Override
    public void draw(Canvas canvas)
    {

        background.draw(canvas);
        toMenuButton.draw(canvas);
        toHelpButton.draw(canvas);
        toCodeButton.draw(canvas);
    }

    @Override
    public void reciveTouch(MotionEvent event)
    {
        toMenuButton.reciveTouch(event);
        toHelpButton.reciveTouch(event);
        toCodeButton.reciveTouch(event);
    }

    @Override
    public void terminate()
    {

    }


}
