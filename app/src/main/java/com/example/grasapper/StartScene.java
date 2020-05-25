package com.example.grasapper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Build;
import android.view.MotionEvent;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;

public class StartScene implements Scene
{

    Sprite background;
    NavigationButton bomb1,bomb2,bomb3,bomb4,bomb5,bomb6,manu;
    NavigationTextButton text1,text2,text3,text4,text5,text6;
    Text text;

    ArrayList<Bitmap> animationFrames = new ArrayList<>();
    Animation animation;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public StartScene(Context context)
    {
        Bitmap frame1 = BitmapFactory.decodeResource(context.getResources(),R.drawable.title_screen);
        Bitmap backgroundImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.start_screen);
        Bitmap bombImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.bomb_takiczarny);
        Bitmap bombUnlockedImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.bomb_takiczerwony);
        Bitmap menuImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.manu_takiczerwony);

        animationFrames.add(frame1);
        animation = new Animation(animationFrames, 0, 0, 0, -1113, 0.4f, 1080,1794);
        animation.Play();
        background = new Sprite(backgroundImage, 0,0,1080,1794);
        bomb1 = new NavigationButton(context, bombUnlockedImage, 167,933,205, 205, 1);
        text1 = new NavigationTextButton(context,"1",247,975,R.font.a_b,Color.BLACK,97,1020,1);
        bomb2 = new NavigationButton(context, bombImage, 437,933,205, 205, 9);
        text2 = new NavigationTextButton(context,"2",517,975,R.font.a_b,Color.WHITE,97,1020,9);
        bomb3 = new NavigationButton(context, bombImage, 707,933,205, 205, 10);
        text3 = new NavigationTextButton(context,"3",787,975,R.font.a_b,Color.WHITE,97,1020,10);
        bomb4 = new NavigationButton(context, bombImage, 167,1198,205, 205, 1);
        text4 = new NavigationTextButton(context,"4",247,1240 ,R.font.a_b,Color.WHITE,97,1020,1);
        bomb5 = new NavigationButton(context, bombImage, 437,1198,205, 205, 1);
        text5 = new NavigationTextButton(context,"5",517,1240 ,R.font.a_b,Color.WHITE,97,1020,1);
        bomb6 = new NavigationButton(context, bombImage, 707,1198,205, 205, 1);
        text6 = new NavigationTextButton(context,"6",787,1240 ,R.font.a_b,Color.WHITE,97,1020,1);
        text = new Text(context, "Wybór planszy",
                227, 770, R.font.a_b, Color.BLACK, 97, 1020);
        manu = new NavigationButton(context, menuImage, 451,1470,179, 179, 8);
    }

    public StartScene() {
    }

    @Override
    public void update()
    {
        bomb1.update();
        bomb2.update();
        bomb3.update();
        bomb4.update();
        bomb5.update();
        bomb6.update();
        text1.update();
        text2.update();
        text3.update();
        text4.update();
        text5.update();
        text6.update();
        manu.update();
        animation.update();
    }

    @Override
    public void draw(Canvas canvas)
    {

        background.draw(canvas);
        bomb1.draw(canvas);
        bomb2.draw(canvas);
        bomb3.draw(canvas);
        bomb4.draw(canvas);
        bomb5.draw(canvas);
        bomb6.draw(canvas);
        text.draw(canvas);
        text1.draw(canvas);
        text2.draw(canvas);
        text3.draw(canvas);
        text4.draw(canvas);
        text5.draw(canvas);
        text6.draw(canvas);
        manu.draw(canvas);
        animation.draw(canvas);
    }

    @Override
    public void reciveTouch(MotionEvent event)
    {
        bomb1.reciveTouch(event);
        switch (event.getAction())
        {
            case MotionEvent.ACTION_MOVE:
            {

            }
        }
        bomb2.reciveTouch(event);
        switch (event.getAction())
        {
            case MotionEvent.ACTION_MOVE:
            {

            }
        }
        bomb3.reciveTouch(event);
        switch (event.getAction())
        {
            case MotionEvent.ACTION_MOVE:
            {

            }
        }
        bomb4.reciveTouch(event);
        switch (event.getAction())
        {
            case MotionEvent.ACTION_MOVE:
            {

            }
        }
        bomb5.reciveTouch(event);
        switch (event.getAction())
        {
            case MotionEvent.ACTION_MOVE:
            {

            }
        }
        bomb6.reciveTouch(event);
        switch (event.getAction())
        {
            case MotionEvent.ACTION_MOVE:
            {

            }
        }
        manu.reciveTouch(event);
}

    @Override
    public void terminate()
    {

    }
}
