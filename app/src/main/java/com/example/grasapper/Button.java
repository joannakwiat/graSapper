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
import android.widget.Toast;
import android.util.Log;

import java.util.ArrayList;

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

class GameButton extends Button
{
    int buttonType;

    GameButton()
    {

    }

    GameButton (Context context, Bitmap bitmap, int x, int y, int width, int height, int type)
    {
        super(context,bitmap,x,y,width,height);
        this.buttonType = type;
    }

    GameButton (Context context, Bitmap bitmap, int x, int y, int width, int height, int type, int changeActiveMusic)
    {
        super(context,bitmap,x,y,width,height,changeActiveMusic);
        this.buttonType = type;
    }

    public void update()
    {

    }
    public void update(ArrayList<Integer> kolejka, Text text, ArrayList<Bitmap> bitmaps, ArrayList<Sprite> lista, ArrayList<Integer> coor)
    {
        Sprite blank = new Sprite(bitmaps.get(8), 925,300,1,1);
        Sprite pom;
        int x = coor.get(0);
        int y = coor.get(1);
        if (clicked)
        {
            if (buttonType == 8) {
                //reset
                Log.i("Clicked", "RESET");
                kolejka.clear();
                lista.clear();
                for(int i=0; i<10; i++){
                    lista.add(blank);
                }
                coor.clear();
                coor.add(970);
                coor.add(370);
                Log.i("list", kolejka.toString());
            }
            if(kolejka.size()>9)
            {
                Log.i("ERROR","ZA DUZA KOLEJKA");
            }
            else {
                if (buttonType == 0) {
                    //1step
                    Log.i("size kolejki", text.text);
                    Log.i("Clicked", "0");
                    kolejka.add(0);
                    pom = new Sprite(bitmaps.get(0),x,y,bitmaps.get(0).getWidth()/4,bitmaps.get(0).getHeight()/4);
                    lista.add(pom);
                    Log.i("list", kolejka.toString());
                    coor.clear();
                    coor.add(x);
                    coor.add(y+70);
                }
                if (buttonType == 1) {
                    //3step
                    Log.i("Clicked", "1");
                    kolejka.add(1);
                    pom = new Sprite(bitmaps.get(1),x,y,bitmaps.get(1).getWidth()/4,bitmaps.get(1).getHeight()/4);
                    lista.add(pom);
                    Log.i("list", kolejka.toString());
                    coor.clear();
                    coor.add(x);
                    coor.add(y+70);
                }
                if (buttonType == 2) {
                    //4step
                    Log.i("Clicked", "2");
                    kolejka.add(2);
                    pom = new Sprite(bitmaps.get(2),x,y,bitmaps.get(2).getWidth()/4,bitmaps.get(2).getHeight()/4);
                    lista.add(pom);
                    Log.i("list", kolejka.toString());
                    coor.clear();
                    coor.add(x);
                    coor.add(y+70);
                }
                if (buttonType == 3) {
                    //jump
                    Log.i("Clicked", "3");
                    kolejka.add(3);
                    pom = new Sprite(bitmaps.get(3),x,y,bitmaps.get(3).getWidth()/5,bitmaps.get(3).getHeight()/5);
                    lista.add(pom);
                    Log.i("list", kolejka.toString());
                    coor.clear();
                    coor.add(x);
                    coor.add(y+60);
                }
                if (buttonType == 4) {
                    //left
                    Log.i("Clicked", "4");
                    kolejka.add(4);
                    pom = new Sprite(bitmaps.get(4),x,y,bitmaps.get(4).getWidth()/5,bitmaps.get(4).getHeight()/5);
                    lista.add(pom);
                    Log.i("list", kolejka.toString());
                    coor.clear();
                    coor.add(x);
                    coor.add(y+60);
                }
                if (buttonType == 5) {
                    //right
                    Log.i("Clicked", "5");
                    kolejka.add(5);
                    pom = new Sprite(bitmaps.get(5),x,y,bitmaps.get(5).getWidth()/5,bitmaps.get(5).getHeight()/5);
                    lista.add(pom);
                    Log.i("list", kolejka.toString());
                    coor.clear();
                    coor.add(x);
                    coor.add(y+60);
                }
                if (buttonType == 6) {
                    //cut
                    Log.i("Clicked", "6");
                    kolejka.add(6);
                    pom = new Sprite(bitmaps.get(6),x,y,bitmaps.get(6).getWidth()/4,bitmaps.get(6).getHeight()/4);
                    lista.add(pom);
                    Log.i("list", kolejka.toString());
                    coor.clear();
                    coor.add(x);
                    coor.add(y+70);
                }
                if (buttonType == 7) {
                    //flag
                    Log.i("Clicked", "7");
                    kolejka.add(7);
                    pom = new Sprite(bitmaps.get(7),x,y,bitmaps.get(7).getWidth()/4,bitmaps.get(7).getHeight()/4);
                    lista.add(pom);
                    Log.i("list", kolejka.toString());
                    coor.clear();
                    coor.add(x);
                    coor.add(y+70);
                }
            }
            text.set(kolejka.size());
            super.update();
        }
    }
}

class PlayButton extends Button
{
    boolean isRepley;

    PlayButton()
    {

    }

    PlayButton (Context context, Bitmap bitmap, int x, int y, int width, int height, boolean isRepley)
    {
        super(context,bitmap,x,y,width,height);
        this.isRepley = isRepley;
    }

    PlayButton (Context context, Bitmap bitmap, int x, int y, int width, int height, boolean isRepley, int changeActiveMusic)
    {
        super(context,bitmap,x,y,width,height,changeActiveMusic);
        this.isRepley = isRepley;
    }

    public void update()
    {
        if (clicked)
        {
            if(isRepley==true){
                //TODO
                // Wyczyść liste komend
            }
            if(isRepley==false){
                //TODO
                // Aktywuje animacje chodzenia po planszy
                // Rozpoczyna sprawdzanie poprawności kombinacji z listy poleceń
            }

            super.update();
        }
    }
}