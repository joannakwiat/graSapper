package com.example.grasapper;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;

import java.util.ArrayList;

public class Sprite
{
    //sprite
    Bitmap bitmap;

    //koordynaty poczatkowe (lewy górny róg)
    int x;
    int y;

    //szerokośc i wysokość
    int width;
    int height;

    boolean active;

    Sprite (Bitmap bitmap, int x, int y)
    {
        float screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        float screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

        float widthRatio = screenWidth/1080;
        float heightRatio = screenHeight/1794;

        this.x = (int)(x*widthRatio);
        this.y = (int)(y*heightRatio);
        this.width = (int)(width*widthRatio);
        this.height = (int)(height*heightRatio);
        this.bitmap = Bitmap.createScaledBitmap(bitmap, width, height, true);
        active = true;
    }

    Sprite (Bitmap bitmap, int x, int y, int width, int height)
    {
        float screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        float screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

        float widthRatio = screenWidth/1080;
        float heightRatio = screenHeight/1794;

        this.x = (int)(x*widthRatio);
        this.y = (int)(y*heightRatio);
        this.width = (int)(width*widthRatio);
        this.height = (int)(height*heightRatio);
        this.bitmap = Bitmap.createScaledBitmap(bitmap, this.width, this.height, true);
        active = true;
    }

    public void setInactive()
    {
        active = false;
    }

    public void setActive()
    {
        active = true;
    }

    public void update()
    {
        if (active)
        {

        }
    }

    public void draw(Canvas canvas)
    {
        if (active)
        {
            canvas.drawBitmap(bitmap, x, y, null);
        }
    }

}
