package com.example.grasapper;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Typeface;
import android.os.Build;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.view.MotionEvent;

import androidx.annotation.RequiresApi;
import androidx.core.content.res.ResourcesCompat;

public class TextButton
{
    //tekst
    String text;
    StaticLayout staticLayout;
    StaticLayout.Builder staticLayoutBuild;

    //koordynaty poczatkowe (lewy górny róg)
    int x;
    int y;

    //szerokośc i wysokość
    int width;
    int height;

    boolean clicked;
    TextPaint paint;

    TextButton ()
    {

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    TextButton (Context context, String text, int x, int y, int font, int color, int fontSize, int width)
    {
        float screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        float screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

        float widthRatio = screenWidth/1080;
        float heightRatio = screenHeight/1794;


        this.x = (int)(x*widthRatio);
        this.y = (int)(y*heightRatio);
        this.width = (int)(width*widthRatio);
        this.clicked = false;

        paint = new TextPaint();
        Typeface typeface = ResourcesCompat.getFont(context, font);
        paint.setTypeface(typeface);
        paint.setColor(color);
        paint.setTextSize(fontSize);

        staticLayoutBuild = StaticLayout.Builder.obtain(text, 0,text.length(),paint,(int)(width*widthRatio));
        staticLayout = staticLayoutBuild.build();
        this.height = staticLayout.getHeight();
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
        canvas.save();
        canvas.translate(x,y);
        staticLayout.draw(canvas);
        canvas.restore();
    }
}

class NavigationTextButton extends TextButton
{
    int numberOfScene;

    @RequiresApi(api = Build.VERSION_CODES.M)
    NavigationTextButton (Context context, String text, int x, int y, int font, int color, int fontSize, int width, int sceneNumber)
    {
        super(context,text,x,y,font,color,fontSize,width);
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

class ExitTextButton extends TextButton
{
    @RequiresApi(api = Build.VERSION_CODES.M)
    ExitTextButton (Context context, String text, int x, int y, int font, int color, int fontSize, int width)
    {
        super(context,text,x,y,font,color,fontSize,width);
    }

    public void update()
    {
        if (clicked)
        {
            System.exit(0);
        }

    }
}