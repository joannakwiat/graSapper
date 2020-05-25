package com.example.grasapper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;

public class CodeScene implements Scene
{
    Sprite background;
    Sprite start;
    Sprite krok1;
    Sprite krok3;
    Sprite krok4;
    Sprite lewo;
    Sprite prawo;
    Sprite skok;
    Sprite cut;
    Sprite rozbroj;
    NavigationButton back;

    public CodeScene(Context context)
    {
        Bitmap backgroundImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.code_screen);
        Bitmap backImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.back);
        Bitmap startImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.start);
        Bitmap krok1Image = BitmapFactory.decodeResource(context.getResources(),R.drawable.krok_1);
        Bitmap krok3Image = BitmapFactory.decodeResource(context.getResources(),R.drawable.krok_3);
        Bitmap krok4Image = BitmapFactory.decodeResource(context.getResources(),R.drawable.krok_4);
        Bitmap lewoImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.lewo);
        Bitmap prawoImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.prawo);
        Bitmap skokImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.skok);
        Bitmap cutImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.kabel);
        Bitmap rozbrojImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.rozbroj);

        background = new Sprite(backgroundImage, 0,0,1080,1794);
        back = new NavigationButton(context, backImage, 80,80,61, 105, 1);
        start = new Sprite(startImage, 290,290, startImage.getWidth()/2, startImage.getHeight()/2);
    }

    @Override
    public void update() { back.update(); }

    @Override
    public void draw(Canvas canvas)
    {
        background.draw(canvas);
        back.draw(canvas);
        start.draw(canvas);
        krok1.draw(canvas);
        krok3.draw(canvas);
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
