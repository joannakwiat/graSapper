package com.example.grasapper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Build;
import android.view.MotionEvent;

import androidx.annotation.RequiresApi;

public class Help3Scene implements Scene
{

    Sprite background;
    NavigationButton back;
    NavigationButton back2;
    Text text;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public Help3Scene(Context context)
    {
        Bitmap backgroundImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.help_screen2);
        Bitmap backImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.back);
        Bitmap back2Image = BitmapFactory.decodeResource(context.getResources(),R.drawable.back2);

        background = new Sprite(backgroundImage, 0,0,1080,1794);
        back = new NavigationButton(backImage, 50,50,61, 105, 1);
        text = new Text(context, "Na planszy będą umieszczone bomby, które będziesz miał za zadanie rozbroić. Aby to zrobić konieczne będzie dostanie się do bomby i wykonanie instrukcji rozbrajania. Bomba wybuchnie, jeśli na nią wejdziesz! \n\n\n Do każdej bomby dociera jeden lub kilka kabli, które po wejściu Twojej postaci na nie, doprowadza w konsekwencji do wybuchu bomby. Aby do tego nie doszło można wykonać instrukcję przecinania. Kable mogą być przecięte tylko i wyłącznie w z góry ustalonej kolejności, co oznacza, że przecięcie złego kabla również doprowadzi do wybuchu bomby.",
                30, 250, R.font.a_b, Color.BLACK, 60, 1020);
        back2 = new NavigationButton(backImage, 100,1639,61, 105, 5);
    }

    public Help3Scene() {
    }

    @Override
    public void update()
    {
        back.update();
        back2.update();
    }

    @Override
    public void draw(Canvas canvas)
    {
        background.draw(canvas);
        back.draw(canvas);
        back2.draw(canvas);
        text.draw(canvas);
    }

    @Override
    public void reciveTouch(MotionEvent event)
    {
        back.reciveTouch(event);
        back2.reciveTouch(event);
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
