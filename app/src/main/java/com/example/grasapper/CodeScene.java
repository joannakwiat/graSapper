package com.example.grasapper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;

public class CodeScene implements Scene
{
    Sprite background;
    NavigationButton back;

    public CodeScene(Context context)
    {
        Bitmap backgroundImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.code_screen);
        Bitmap backImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.back);

        background = new Sprite(backgroundImage, 0,0,1080,1794);
        back = new NavigationButton(context, backImage, 50,50,61, 105, 1);
    }

    @Override
    public void update()
    {
        back.update();
    }

    @Override
    public void draw(Canvas canvas)
    {
        background.draw(canvas);
        back.draw(canvas);
    }

    @Override
    public void reciveTouch(MotionEvent event)
    {
        back.reciveTouch(event);
    }

    @Override
    public void terminate()
    {

    }
}
