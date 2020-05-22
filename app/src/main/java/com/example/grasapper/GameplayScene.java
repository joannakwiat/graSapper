package com.example.grasapper;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.Log;
import android.view.MotionEvent;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;


public class GameplayScene implements Scene
{
  // public ArrayList<Integer> kolejka = new ArrayList<>();
   public ArrayList<Bitmap> bitmaps = new ArrayList<>();
   public ArrayList<Sprite> lista = new ArrayList<>();
   public ArrayList<Integer> coordinates = new ArrayList<>();

    Sprite background;
    Sprite blank;
    NavigationButton toMenuButton;
    NavigationButton toHelpButton;
    NavigationButton toCodeButton;

    //gameButton
   GameButton button1step;
   GameButton button3step;
   GameButton button4step;
   GameButton buttonJump;
   GameButton buttonLeft;
   GameButton buttonRight;
   GameButton buttonCut;
   GameButton buttonFlag;
   Text licznik;
   PlayButton playButton;
   GameButton replayButton;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public GameplayScene(Context context)
    {
        Bitmap backgroundImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.gameplay_screen);
        Bitmap toMenuButtonImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.manu);
        Bitmap toHelpButtonImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.info);
        Bitmap toCodeButtonImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.code);
        Bitmap playButtonImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.play);
        Bitmap replayButtonImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.reverse);
        Bitmap listImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.list);

        //gameButtony - img
        Bitmap img1step = BitmapFactory.decodeResource(context.getResources(),R.drawable.krok1);
        Bitmap img3step = BitmapFactory.decodeResource(context.getResources(),R.drawable.krok3);
        Bitmap img4step = BitmapFactory.decodeResource(context.getResources(),R.drawable.krok4);
        Bitmap imgJump = BitmapFactory.decodeResource(context.getResources(),R.drawable.strzalka_skok);
        Bitmap imgLeft = BitmapFactory.decodeResource(context.getResources(),R.drawable.strzalka_lewo);
        Bitmap imgRight = BitmapFactory.decodeResource(context.getResources(),R.drawable.strzalka_prawo);
        Bitmap imgCut = BitmapFactory.decodeResource(context.getResources(),R.drawable.ic_cut);
        Bitmap imgFlag = BitmapFactory.decodeResource(context.getResources(),R.drawable.bomb_bialaflaga);

        background = new Sprite(backgroundImage, 0,0,1080,1794);
        blank = new Sprite(listImage, 925,300,1,1);
        toMenuButton = new NavigationButton(context, toMenuButtonImage, 925, 1100, 150, 150, 2);
        toHelpButton = new NavigationButton(context, toHelpButtonImage, 925, 1300, 150, 150, 3);
        toCodeButton = new NavigationButton(context, toCodeButtonImage, 900, 1500, 194, 294, 4);
        playButton = new PlayButton(context, playButtonImage, 740, 80, 104, 120, false);
        replayButton = new GameButton(context, replayButtonImage, 910, 90, 96, 100, 8);

        //gameButtony
        button1step = new GameButton(context, img1step, 150, 1170, 100, 120, 0);
        button3step = new GameButton(context, img3step, 420, 1170, 100, 120, 1);
        button4step = new GameButton(context, img4step, 690, 1170, 100, 120, 2);
        buttonJump = new GameButton(context, imgJump, 150, 1370, 120, 120, 3);
        buttonLeft = new GameButton(context, imgLeft, 410, 1370, 115, 120, 4);
        buttonRight = new GameButton(context, imgRight, 690, 1370, 115, 120, 5);
        buttonCut = new GameButton(context, imgCut, 420, 1570, 116, 120, 6);
        buttonFlag = new GameButton(context, imgFlag, 690, 1570, 104, 120, 7);
        licznik = new Text(context,"0/10", 933, 279, R.font.a_b, Color.WHITE, 57, 1020);

        bitmaps.add(img1step);
        bitmaps.add(img3step);
        bitmaps.add(img4step);
        bitmaps.add(imgJump);
        bitmaps.add(imgLeft);
        bitmaps.add(imgRight);
        bitmaps.add(imgCut);
        bitmaps.add(imgFlag);
        bitmaps.add(listImage);

        for(int i=0; i<10; i++){
            lista.add(blank);
        }

        //x, y
        coordinates.add(970);
        coordinates.add(370);
    }

    @Override
    public void update()
    {
        toMenuButton.update();
        toHelpButton.update();
        toCodeButton.update();
        playButton.update();
        replayButton.update(kolejka,licznik,bitmaps, lista,coordinates);
        button1step.update(kolejka,licznik,bitmaps, lista,coordinates);
        button3step.update(kolejka,licznik,bitmaps, lista,coordinates);
        button4step.update(kolejka,licznik,bitmaps, lista,coordinates);
        buttonJump.update(kolejka,licznik,bitmaps, lista,coordinates);
        buttonLeft.update(kolejka,licznik,bitmaps, lista,coordinates);
        buttonRight.update(kolejka,licznik,bitmaps, lista,coordinates);
        buttonCut.update(kolejka,licznik,bitmaps, lista,coordinates);
        buttonFlag.update(kolejka,licznik,bitmaps, lista,coordinates);
        licznik.update();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void draw(Canvas canvas)
    {
        background.draw(canvas);
        toMenuButton.draw(canvas);
        toHelpButton.draw(canvas);
        toCodeButton.draw(canvas);
        playButton.draw(canvas);
        replayButton.draw(canvas);

        button1step.draw(canvas);
        button3step.draw(canvas);
        button4step.draw(canvas);
        buttonJump.draw(canvas);
        buttonLeft.draw(canvas);
        buttonRight.draw(canvas);
        buttonCut.draw(canvas);
        buttonFlag.draw(canvas);
        licznik.draw(canvas);
        for(int i=0; i<lista.size(); i++){
            lista.get(i).draw(canvas);
        }
    }

    @Override
    public void reciveTouch(MotionEvent event)
    {
        toMenuButton.reciveTouch(event);
        toHelpButton.reciveTouch(event);
        toCodeButton.reciveTouch(event);
        playButton.reciveTouch(event);
        replayButton.reciveTouch(event);

        button1step.reciveTouch(event);
        button3step.reciveTouch(event);
        button4step.reciveTouch(event);
        buttonJump.reciveTouch(event);
        buttonLeft.reciveTouch(event);
        buttonRight.reciveTouch(event);
        buttonCut.reciveTouch(event);
        buttonFlag.reciveTouch(event);
    }

    @Override
    public void terminate()
    {

    }


}
