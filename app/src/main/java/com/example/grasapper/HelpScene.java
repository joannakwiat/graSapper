package com.example.grasapper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Build;
import android.view.MotionEvent;

import androidx.annotation.RequiresApi;

public class HelpScene implements Scene
{
    Sprite background;
    NavigationButton back;
    Text text;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public HelpScene(Context context)
    {
        Bitmap backgroundImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.help_screen);
        Bitmap backImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.back);

        background = new Sprite(backgroundImage, 0,0,1080,1794);
        back = new NavigationButton(backImage, 50,50,61, 105, 1);
        text = new Text(context, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed tempus ex nec eros ultricies, eu aliquam nibh condimentum. Mauris suscipit eros mattis mi commodo molestie. In porta justo ut mauris consequat tincidunt. Phasellus a tellus purus. Integer hendrerit est vel dui placerat commodo. Nulla non vestibulum nibh, non gravida eros. Sed justo massa, pretium a interdum ut, condimentum eu lacus. ",
                30, 650, R.font.a_b, Color.BLACK, 60, 1020);
    }

    @Override
    public void update()
    {
        back.update();
    }

    @Override
    public void draw(Canvas canvas)
    {
        background.draw(canvas);
        back.draw(canvas);
        text.draw(canvas);
    }

    @Override
    public void reciveTouch(MotionEvent event)
    {
        back.reciveTouch(event);
        switch (event.getAction())
        {
            case MotionEvent.ACTION_MOVE:
            {

            }
        }
    }

    @Override
    public void terminate()
    {

    }
}
