package com.example.grasapper;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

import java.util.ArrayList;

public class Animation
{
    ArrayList<Bitmap> frames = new ArrayList<>();

    int startX;
    int startY;
    float x;
    float y;
    int endX;
    int endY;
    int currentFrame;
    private int secondsPassed;

    float seconds;
    private float spritePerFrame;
    private float movePerFrame;
    private float xChangePerFrame;
    private float yChangePerFrame;

    private long lastFrameSprite;
    private long lastFrameMove;

    boolean isPlaying;
    boolean loop;

    Animation()
    {

    }

    Animation(ArrayList<Bitmap> frames, int startX, int startY, int endX, int endY, int seconds)
    {
        this.frames = frames;
        this.startX = startX;
        this.startY = startY;
        this.x = startX;
        this.y = startY;
        this.endX = endX;
        this.endY = endY;
        this.seconds = seconds;
        this.spritePerFrame = 1.0f/frames.size();
        this.movePerFrame = 1.0f/30;
        this.currentFrame = 0;
        this.secondsPassed = 0;
        this.lastFrameSprite = System.currentTimeMillis();
        this.lastFrameMove = System.currentTimeMillis();
        this.xChangePerFrame = (endX - startX) * (this.movePerFrame / 4);
        this.yChangePerFrame = (endY - startY) * (this.movePerFrame / 4);


        this.isPlaying = false;
        this.loop = false;
    }

    public void Play()
    {
        isPlaying = true;
    }

    public void Stop()
    {
        isPlaying = false;
    }

    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(frames.get(currentFrame), x, y, null);
    }

    public void update()
    {
        if (!isPlaying)
        {
            return;
        }
        Log.i("LOLZ", System.currentTimeMillis() - lastFrameMove + " > " + movePerFrame *1000);
        if (System.currentTimeMillis() - lastFrameMove > movePerFrame *1000 )
        {
            Log.i("LOLZ", System.currentTimeMillis() - lastFrameMove + " > " + movePerFrame *1000);
            x += xChangePerFrame;
            y += yChangePerFrame;
            if (xChangePerFrame >= endX && yChangePerFrame >= endY)
            {
                Log.i("LOL", "LOL");
                x = startX;
                y = startY;
                if (!loop)
                {
                    isPlaying = false;
                }

            }
            lastFrameMove = System.currentTimeMillis();
        }

        if (System.currentTimeMillis() - lastFrameSprite > spritePerFrame *1000 )
        {
            Log.i("KEK", System.currentTimeMillis() - lastFrameSprite + " > " + spritePerFrame *1000);
            currentFrame++;
            if (currentFrame >= frames.size())
            {
                Log.i("LOL", "LOL");
                if (secondsPassed < seconds)
                {
                    currentFrame = 0;
                    secondsPassed++;
                }
                else
                {
                    currentFrame = 0;
                    secondsPassed = 0;
                    if (!loop)
                    {
                       isPlaying = false;
                    }
                }
            }
            lastFrameSprite = System.currentTimeMillis();
        }
    }
}
