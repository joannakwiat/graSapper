package com.example.grasapper;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.view.MotionEvent;

public class Button
{

    //sprite
    Bitmap bitmap;

    //koordynaty poczatkowe (lewy górny róg)
    int x;
    int y;

    //szerokośc i wysokość
    int width;
    int height;

    boolean clicked;
    Paint paint;

    Button ()
    {

    }

    Button (Bitmap bitmap, int x, int y)
    {
        float screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        float screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

        float widthRatio = screenWidth/1080;
        float heightRatio = screenHeight/1794;


        this.x = (int)(x*widthRatio);
        this.y = (int)(y*heightRatio);
        this.width = bitmap.getWidth();
        this.height = bitmap.getHeight();
        this.clicked = false;
        this.bitmap = bitmap;

        paint = new Paint();
    }

    Button (Bitmap bitmap, int x, int y, int width, int height)
    {
        float screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        float screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

        float widthRatio = screenWidth/1080;
        float heightRatio = screenHeight/1794;


        this.x = (int)(x*widthRatio);
        this.y = (int)(y*heightRatio);
        this.width = (int)(width*widthRatio);
        this.height = (int)(height*heightRatio);
        this.clicked = false;
        this.bitmap = Bitmap.createScaledBitmap(bitmap, this.width, this.height, true);

        paint = new Paint();
    }

    public boolean isClicked(float clickedX, float clickedY)
    {
        if (clickedX >= x && clickedX <= (x + width) && clickedY >= y && clickedY <= (y + height))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void setClicked(boolean c)
    {
        clicked = c;
    }

    public void update()
    {
        if (clicked)
        {
            ColorFilter filter = new LightingColorFilter(Color.WHITE, 0);
            paint.setColorFilter(filter);
            clicked = false;
        }
    }

    public void reciveTouch(MotionEvent event)
    {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
            {
                if (isClicked(event.getX(), event.getY())) {
                    ColorFilter filter = new LightingColorFilter(Color.GRAY, 0);
                    paint.setColorFilter(filter);

                }
                break;
            }
            case MotionEvent.ACTION_MOVE:
            {
                if(!isClicked(event.getX(),event.getY()))
                {
                    ColorFilter filter = new LightingColorFilter(Color.WHITE, 0);
                    paint.setColorFilter(filter);
                }
                break;
            }
            case MotionEvent.ACTION_UP:
            {
                setClicked(isClicked(event.getX(), event.getY()));
                break;
            }
        }
    }

    public void draw(Canvas canvas)
    {
        canvas.drawBitmap( bitmap, this.x, this.y, paint);
    }
}

class NavigationButton extends Button
{
    int numberOfScene;

    NavigationButton()
    {

    }

    NavigationButton (Bitmap bitmap, int x, int y, int width, int height, int sceneNumber)
    {
        super(bitmap,x,y,width,height);
        this.numberOfScene = sceneNumber;

    }

    public void update()
    {

        if (clicked)
        {
            SceneManager.ACTIVE_SCANE = numberOfScene;
            super.update();
        }

    }
}

class TitleScreenButton extends NavigationButton
{
    TitleScreenButton (Bitmap bitmap, int x, int y, int width, int height, int sceneNumber)
    {
        super(bitmap,x,y,width,height,sceneNumber);
    }

    public void reciveTouch(MotionEvent event)
    {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_UP:
            {
                setClicked(isClicked(event.getX(), event.getY()));
                break;
            }
        }
    }
}

