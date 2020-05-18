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
    ArrayList<Integer> kolejka = new ArrayList<>();

    Sprite background;
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
    }

    @Override
    public void update()
    {
        toMenuButton.update();
        toHelpButton.update();
        toCodeButton.update();
        playButton.update();
        replayButton.update(kolejka);

        button1step.update(kolejka);
        button3step.update(kolejka);
        button4step.update(kolejka);
        buttonJump.update(kolejka);
        buttonLeft.update(kolejka);
        buttonRight.update(kolejka);
        buttonCut.update(kolejka);
        buttonFlag.update(kolejka);
    }

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
