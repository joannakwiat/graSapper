package com.example.grasapper;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.os.Build;
import android.text.StaticLayout;
import android.text.TextPaint;

import androidx.annotation.RequiresApi;
import androidx.core.content.res.ResourcesCompat;

public class Text
{
    //tekst
    String text;
    StaticLayout staticLayout;
    StaticLayout.Builder staticLayoutBuild;

    //koordynaty poczatkowe (lewy górny róg)
    int x;
    int y;

    int width;
    int height;

    TextPaint paint;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public Text(Context context, String text, int x, int y, int font, int color, int fontSize, int width)
    {
        float screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        float screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

        float widthRatio = screenWidth/1080;
        float heightRatio = screenHeight/1794;

        this.text = text;
        this.x = (int)(x*widthRatio);
        this.y = (int)(y*heightRatio);
        this.width = (int)(width*widthRatio);

        paint = new TextPaint();
        Typeface typeface = ResourcesCompat.getFont(context, font);
        paint.setTypeface(typeface);
        paint.setColor(color);
        paint.setTextSize(fontSize);

        staticLayoutBuild = StaticLayout.Builder.obtain(text, 0,text.length(),paint,(int)(width*widthRatio));
        staticLayout = staticLayoutBuild.build();
        this.height = staticLayout.getHeight();
    }

    public void update()
    {

    }

    public void draw(Canvas canvas)
    {
        canvas.save();
        canvas.translate(x,y);
        staticLayout.draw(canvas);
        canvas.restore();
    }
}

class ScrollText extends Text
{
    @RequiresApi(api = Build.VERSION_CODES.M)
    ScrollText(Context context, String text, int x, int y, int font, int color, int fontSize, int width)
    {
        super(context, text, x, y, font, color, fontSize, width);
    }

    public void update()
    {

    }
}