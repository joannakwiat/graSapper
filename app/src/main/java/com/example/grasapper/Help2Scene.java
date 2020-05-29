package com.example.grasapper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Build;
import android.view.MotionEvent;

import androidx.annotation.RequiresApi;

public class Help2Scene implements Scene
{

    Sprite background;
    Sprite img1;
    Sprite img2;
    Sprite img3;
    Sprite img4;
    Sprite img5;
    Sprite img6;
    Sprite img7;
    Sprite img8;
    NavigationButton back;
    NavigationButton back2;
    NavigationButton next;
    Text text;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public Help2Scene(Context context)
    {
        Bitmap backgroundImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.help_screen2);
        Bitmap backImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.back);
        Bitmap back2Image = BitmapFactory.decodeResource(context.getResources(),R.drawable.back2);
        Bitmap IMG1 = BitmapFactory.decodeResource(context.getResources(),R.drawable.krok1);
        Bitmap IMG2 = BitmapFactory.decodeResource(context.getResources(),R.drawable.krok3);
        Bitmap IMG3 = BitmapFactory.decodeResource(context.getResources(),R.drawable.krok4);
        Bitmap IMG4 = BitmapFactory.decodeResource(context.getResources(),R.drawable.strzalka_skok);
        Bitmap IMG5 = BitmapFactory.decodeResource(context.getResources(),R.drawable.strzalka_prawo);
        Bitmap IMG6 = BitmapFactory.decodeResource(context.getResources(),R.drawable.strzalka_lewo);
        Bitmap IMG7 = BitmapFactory.decodeResource(context.getResources(),R.drawable.ic_cut);
        Bitmap IMG8 = BitmapFactory.decodeResource(context.getResources(),R.drawable.bomb_bialaflaga);


        background = new Sprite(backgroundImage, 0,0,1080,1794);
        img1 = new Sprite(IMG1, 100,200, 85, 100);
        img2 = new Sprite(IMG2, 100,350, 85, 100);
        img3 = new Sprite(IMG3, 100,500, 85, 100);
        img4 = new Sprite(IMG4, 100,645, 99, 99);
        img5 = new Sprite(IMG5, 100,790, 95, 100);
        img6 = new Sprite(IMG6, 100,940, 95, 100);
        img7 = new Sprite(IMG7, 100,1100, 83, 87);
        img8 = new Sprite(IMG8, 100,1235, 86, 99);
        back = new NavigationButton(context, backImage, 50,50,61, 105, 1);
        text = new Text(context, "Zrób 1 krok do przodu \n\nZrób 3 kroki do przodu\n\nZrób 4 kroki do przodu\n\nSkocz\n\nSkręć w prawo\n\nSkręć w lewo\n\nPrzetnij przewód\n\nRozbrój bombę",
                300, 220, R.font.a_b, Color.BLACK, 60, 1020);
        back2 = new NavigationButton(context, backImage, 100,1639,61, 105, 3);
        next = new NavigationButton(context, back2Image, 919,1639,61, 105, 6);
    }

    public Help2Scene() {
    }

    @Override
    public void update()
    {
        back.update();
        back2.update();
        next.update();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void draw(Canvas canvas)
    {
        background.draw(canvas);
        back.draw(canvas);
        back2.draw(canvas);
        next.draw(canvas);
        text.draw(canvas);
        img1.draw(canvas);
        img2.draw(canvas);
        img3.draw(canvas);
        img4.draw(canvas);
        img5.draw(canvas);
        img6.draw(canvas);
        img7.draw(canvas);
        img8.draw(canvas);
    }

    @Override
    public void reciveTouch(MotionEvent event)
    {
        back.reciveTouch(event);
        back2.reciveTouch(event);
        next.reciveTouch(event);
    }

    @Override
    public void terminate()
    {

    }
}
