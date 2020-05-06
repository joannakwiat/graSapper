package com.example.grasapper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;

public class AnimationTestScene implements Scene
{
    ArrayList<Bitmap> animationFrames = new ArrayList<>();
    Animation animation;

    public AnimationTestScene(Context context)
    {
        Bitmap frame1 = BitmapFactory.decodeResource(context.getResources(),R.drawable.test);
        Bitmap frame2 = BitmapFactory.decodeResource(context.getResources(),R.drawable.test2);

        animationFrames.add(frame1);
        animationFrames.add(frame2);

        animation = new Animation(animationFrames, 100, 100, 400, 100, 4);

        animation.Play();
    }

    public void draw(Canvas canvas)
    {
        animation.draw(canvas);
    }

    public void update()
    {
        animation.update();
    }

    public void reciveTouch(MotionEvent event)
    {

    }

    public void terminate()
    {

    }
}
