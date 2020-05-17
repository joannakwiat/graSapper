package com.example.grasapper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;

public class TitleScene implements Scene
{
    TitleScreenButton button;

    public TitleScene(Context context)
    {
        Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.title_screen);
        button = new TitleScreenButton(context, bmp, 0, 0, 1080, 1794, 7, 1);
    }

    @Override
    public void update()
    {
        button.update();
    }

    @Override
    public void draw(Canvas canvas)
    {
        button.draw(canvas);
    }

    @Override
    public void reciveTouch(MotionEvent event)
    {
        button.reciveTouch(event);
    }

    @Override
    public void terminate()
    {

    }
}
