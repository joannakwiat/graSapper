package com.example.grasapper;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
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
    int width;
    int height;
    int currentFrame;
    private int secondsPassed;

    float seconds;
    private float spritePerFrame;
    private float movePerFrame;
    private float xChangePerFrame;
    private float yChangePerFrame;

    float angle;
    float angleChange;

    private long lastFrameSprite;
    private long lastFrameMove;

    boolean isPlaying;
    boolean contiunes;
    boolean loop;

    int counter;

    Bitmap frame;

    Animation()
    {

    }

    Animation(ArrayList<Bitmap> frames, int startX, int startY, int endX, int endY, float seconds)
    {
        this.frames = frames;
        this.startX = startX;
        this.startY = startY;
        this.x = startX;
        this.y = startY;
        this.endX = endX;
        this.endY = endY;
        this.seconds = seconds;
        this.spritePerFrame = 40.0f/frames.size();
        this.movePerFrame = 1.0f/40;
        this.currentFrame = 0;
        this.secondsPassed = 0;
        this.lastFrameSprite = System.currentTimeMillis();
        this.lastFrameMove = System.currentTimeMillis();
        this.xChangePerFrame = (endX - startX) * (this.movePerFrame / seconds);
        this.yChangePerFrame = (endY - startY) * (this.movePerFrame / seconds);

        this.angle = 0;
        this.angleChange = 0;

        this.counter = 0;
        this.contiunes = false;
        this.isPlaying = false;
        this.loop = false;
    }

    Animation(ArrayList<Bitmap> frames, int startX, int startY, int endX, int endY, float seconds, int width, int height)
    {
        for(int i=0;i<frames.size();i++) {
            this.frames.add(Bitmap.createScaledBitmap(frames.get(i), width, height, true));
        }
        this.startX = startX;
        this.startY = startY;
        this.x = startX;
        this.y = startY;
        this.endX = endX;
        this.endY = endY;
        this.seconds = seconds;
        this.width=width;
        this.height=height;
        this.spritePerFrame = 40.0f/frames.size();
        this.movePerFrame = 1.0f/40;
        this.currentFrame = 0;
        this.secondsPassed = 0;
        this.lastFrameSprite = System.currentTimeMillis();
        this.lastFrameMove = System.currentTimeMillis();
        this.xChangePerFrame = (endX - startX) * (this.movePerFrame / seconds);
        this.yChangePerFrame = (endY - startY) * (this.movePerFrame / seconds);

        this.angle = 0;
        this.angleChange = 0;

        this.counter = 0;
        this.contiunes = false;
        this.isPlaying = false;
        this.loop = false;
    }

    Animation(ArrayList<Bitmap> frames, int startX, int startY, int endX, int endY, float seconds, int width, int height, float angle)
    {
        for(int i=0;i<frames.size();i++) {
            this.frames.add(Bitmap.createScaledBitmap(frames.get(i), width, height, true));
        }
        this.startX = startX;
        this.startY = startY;
        this.x = startX;
        this.y = startY;
        this.endX = endX;
        this.endY = endY;
        this.seconds = seconds;
        this.width=width;
        this.height=height;
        this.spritePerFrame = 40.0f/frames.size();
        this.movePerFrame = 1.0f/40;
        this.currentFrame = 0;
        this.secondsPassed = 0;
        this.lastFrameSprite = System.currentTimeMillis();
        this.lastFrameMove = System.currentTimeMillis();
        this.xChangePerFrame = (endX - startX) * (this.movePerFrame / seconds);
        this.yChangePerFrame = (endY - startY) * (this.movePerFrame / seconds);

        this.angle = 0;
        this.angleChange = angle * (this.movePerFrame / seconds);

        this.counter = 0;
        this.contiunes = false;
        this.isPlaying = false;
        this.loop = false;
    }

    public void Play()
    {
        isPlaying = true;
        contiunes = true;
    }

    public void Stop()
    {
        isPlaying = false;
        contiunes = false;
    }

    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(frame, x, y, null);
    }

    public void update()
    {
        if (!isPlaying)
        {
            return;
        }

        if (System.currentTimeMillis() - lastFrameMove > movePerFrame *1000 )
        {
            counter++;
            if (counter >= spritePerFrame)
            {
                counter = 0;
                currentFrame++;
                if (currentFrame >= frames.size())
                {
                    currentFrame = 0;
                }
            }
            x += xChangePerFrame;
            y += yChangePerFrame;
            angle += angleChange;
            frame = RotateBitmap(frames.get(currentFrame), angle);

            if (xChangePerFrame < 0)
            {
                if (yChangePerFrame < 0)
                {
                    if (x <= endX && y <= endY)
                    {
                        contiunes = false;
                    }
                }
                else
                {
                    if (x <= endX && y >= endY)
                    {
                        contiunes = false;
                    }
                }
            }
            else
            {
                if (yChangePerFrame < 0)
                {
                    if (x >= endX && y <= endY)
                    {
                        contiunes = false;
                    }
                }
                else
                {
                    if (x >= endX && y >= endY)
                    {
                        contiunes = false;
                    }
                }
            }

            if (!contiunes)
            {
                if (!loop)
                {
                    isPlaying = false;
                }
                else
                {
                    x = startX;
                    y = startY;
                }
            }
            lastFrameMove = System.currentTimeMillis();
        }
    }

    public Bitmap RotateBitmap(Bitmap source, float angle)
    {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }
}
