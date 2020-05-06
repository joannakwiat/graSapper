package com.example.grasapper;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.view.MotionEvent;

public class Button
{

    //sprite
    Bitmap bitmap;
    MediaPlayer click;

    //koordynaty poczatkowe (lewy górny róg)
    int x;
    int y;

    //szerokośc i wysokość
    int width;
    int height;

    int activeMusicToChange;

    boolean clicked;
    Paint paint;

    Button ()
    {

    }

    Button (Context context, Bitmap bitmap, int x, int y)
    {
        click = MediaPlayer.create(context, R.raw.click);

        float screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        float screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

        float widthRatio = screenWidth/1080;
        float heightRatio = screenHeight/1794;

        this.activeMusicToChange = -1;

        this.x = (int)(x*widthRatio);
        this.y = (int)(y*heightRatio);
        this.width = bitmap.getWidth();
        this.height = bitmap.getHeight();
        this.clicked = false;
        this.bitmap = bitmap;

        paint = new Paint();
    }

    Button (Context context, Bitmap bitmap, int x, int y, int width, int height)
    {
        click = MediaPlayer.create(context, R.raw.click);

        float screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        float screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

        float widthRatio = screenWidth/1080;
        float heightRatio = screenHeight/1794;

        this.activeMusicToChange = -1;

        this.x = (int)(x*widthRatio);
        this.y = (int)(y*heightRatio);
        this.width = (int)(width*widthRatio);
        this.height = (int)(height*heightRatio);
        this.clicked = false;
        this.bitmap = Bitmap.createScaledBitmap(bitmap, this.width, this.height, true);

        paint = new Paint();
    }

    Button (Context context, Bitmap bitmap, int x, int y, int width, int height, int changeActiveMusic)
    {
        click = MediaPlayer.create(context, R.raw.click);

        float screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        float screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

        float widthRatio = screenWidth/1080;
        float heightRatio = screenHeight/1794;

        this.activeMusicToChange = changeActiveMusic;

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
            click.start();
            ColorFilter filter = new LightingColorFilter(Color.WHITE, 0);
            paint.setColorFilter(filter);
            if (activeMusicToChange != -1)
            {
                MusicManager.CHANGE = true;
                MusicManager.ACTIVE_MUSIC = activeMusicToChange;
            }
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

    NavigationButton (Context context, Bitmap bitmap, int x, int y, int width, int height, int sceneNumber)
    {
        super(context,bitmap,x,y,width,height);
        this.numberOfScene = sceneNumber;

    }

    NavigationButton (Context context, Bitmap bitmap, int x, int y, int width, int height, int sceneNumber, int changeActiveMusic)
    {
        super(context,bitmap,x,y,width,height,changeActiveMusic);
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
    TitleScreenButton (Context context, Bitmap bitmap, int x, int y, int width, int height, int sceneNumber)
    {
        super(context,bitmap,x,y,width,height,sceneNumber);
    }

    TitleScreenButton (Context context, Bitmap bitmap, int x, int y, int width, int height, int sceneNumber, int changeActiveMusic)
    {
        super(context,bitmap,x,y,width,height, sceneNumber, changeActiveMusic);
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

