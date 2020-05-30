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
    public static ArrayList<Sprite> codeList = new ArrayList<>();

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
    BackButton back;
    public CodeScene(Context context)
    {
        Bitmap backgroundImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.code_screen);
        Bitmap backImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.back);
        Bitmap startImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.start);
        Bitmap blank = BitmapFactory.decodeResource(context.getResources(),R.drawable.blank);

        background = new Sprite(backgroundImage, 0,0,1080,1794);
        back = new BackButton(context, backImage, 80,80,61, 105);
        start = new Sprite(startImage, 290,290, 500, 90);

        codeList.add(start);
        int x = 290;
        int y = 380;
        for (int i = 1; i < 11; i++)
        {
            Sprite blankSprite = new Sprite(blank, x,y,500,90);
            codeList.add(blankSprite);
            y += 90;
        }
    }

    @Override
    public void update() { back.update(); }

    @Override
    public void draw(Canvas canvas)
    {
        background.draw(canvas);
        back.draw(canvas);
        for (int i = 0; i < GameplayScene.kolejka.size() + 1; i++)
        {
            codeList.get(i).draw(canvas);
        }
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
