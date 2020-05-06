package com.example.grasapper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Build;
import android.view.MotionEvent;

import androidx.annotation.RequiresApi;

public class HelpScene implements Scene
{
    Sprite background;
    NavigationButton back;
    NavigationButton next;
    Text text;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public HelpScene(Context context)
    {
        Bitmap backgroundImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.help_screen);
        Bitmap backImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.back);
        Bitmap back2Image = BitmapFactory.decodeResource(context.getResources(),R.drawable.back2);

        background = new Sprite(backgroundImage, 0,0,1080,1794);
        back = new NavigationButton(context, backImage, 50,50,61, 105, 1);
        text = new Text(context, "Twoim zadaniem będzie pokierowanie postacią w taki sposób, aby rozbroić wszystkie bomby, jednocześnie ich nie detonując oraz omijać nierozcięte kable. Sterowanie postacią odbywa się przy pomocy układania listy poleceń, którą Twoja postać ma wykonać, aby zrealizować swoje zadanie. Ilość instrukcji jest ograniczona!",
                30, 700, R.font.a_b, Color.BLACK, 60, 1020);
        next = new NavigationButton(context, back2Image, 919,1639,61, 105, 5);
    }

    public HelpScene() {
    }

    @Override
    public void update()
    {
        back.update();
        next.update();
    }

    @Override
    public void draw(Canvas canvas)
    {
        background.draw(canvas);
        back.draw(canvas);
        next.draw(canvas);
        text.draw(canvas);
    }

    @Override
    public void reciveTouch(MotionEvent event)
    {
        back.reciveTouch(event);
        next.reciveTouch(event);
        switch (event.getAction())
        {
            case MotionEvent.ACTION_MOVE:
            {

            }
        }
    }

    @Override
    public void terminate()
    {

    }
}

