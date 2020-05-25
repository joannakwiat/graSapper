package com.example.grasapper;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
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

    int zmianatextu=0;

    int width;
    int height;

    int size;

    float widthRatio;
    float heightRatio;

    int color;

    TextPaint paint;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public Text(Context context, String text, int x, int y, int font, int color, int fontSize, int width)
    {
        float screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        float screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

        this.widthRatio = screenWidth/1080;
        this.heightRatio = screenHeight/1794;
        this.size=fontSize;
        this.text = text;
        this.x = (int)(x*widthRatio);
        this.y = (int)(y*heightRatio);
        this.width = (int)(width*widthRatio);

        this.color = color;

        paint = new TextPaint();
        Typeface typeface = ResourcesCompat.getFont(context, font);
        paint.setTypeface(typeface);
        paint.setColor(color);
        paint.setTextSize(fontSize);

        staticLayoutBuild = StaticLayout.Builder.obtain(text, 0,text.length(),paint,(int)(width*this.widthRatio));
        staticLayout = staticLayoutBuild.build();
        this.height = staticLayout.getHeight();
    }

    public void set(int x, int MaxIloscRuchow) {
        if (x == MaxIloscRuchow && zmianatextu == 0) {
            this.paint.setTextSize(this.size - 7);
            this.color = Color.RED;
            zmianatextu = 1;
        } else if (x != MaxIloscRuchow && zmianatextu == 1)
        {
            this.paint.setTextSize(this.size);
            this.color = Color.WHITE;
            zmianatextu = 0;
        }
        this.text = String.valueOf(x) + " / " + MaxIloscRuchow;
    }


    public void update()
    {

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void draw(Canvas canvas)
    {
        canvas.save();
        canvas.translate(x,y);
        paint.setColor(color);
        staticLayoutBuild = StaticLayout.Builder.obtain(text, 0,text.length(),paint,(int)(width*widthRatio));
        staticLayout = staticLayoutBuild.build();
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