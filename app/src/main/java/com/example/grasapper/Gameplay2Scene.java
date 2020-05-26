package com.example.grasapper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Build;
import android.view.MotionEvent;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;


public class Gameplay2Scene implements Scene
{
  // public ArrayList<Integer> kolejka = new ArrayList<>();
   public ArrayList<Bitmap> bitmaps = new ArrayList<>();
   public ArrayList<Sprite> lista = new ArrayList<>();
   public ArrayList<Integer> coordinates = new ArrayList<>();
    public ArrayList<Bitmap> Win2Bitmaps = new ArrayList<>();
    public ArrayList<Sprite> Win2Array = new ArrayList<>();

    Sprite background;
    Sprite blank;
    Sprite board;
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

    NextLevelButton toNextScene;
    WarringButton toCurrentScene;

    static boolean PLAY;
    boolean animationPlaying;
    int action;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public Gameplay2Scene(Context context)
    {
        Bitmap backgroundImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.gameplay_screen);
        Bitmap toMenuButtonImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.manu);
        Bitmap toHelpButtonImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.info);
        Bitmap toCodeButtonImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.code);
        Bitmap playButtonImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.play);
        Bitmap replayButtonImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.reverse);
        Bitmap listImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.list);
        Bitmap boardImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.poziom2);
        Bitmap DefeatImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.defeat3);
        Bitmap WinImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.win3);
        Bitmap WinImageButton = BitmapFactory.decodeResource(context.getResources(),R.drawable.win1);
        Bitmap DefeatImageButton = BitmapFactory.decodeResource(context.getResources(),R.drawable.defeat1);


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
        board = new Sprite(boardImage, 30, 110, 854, 1005);

        //gameButtony
        button1step = new GameButton(context, img1step, 150, 1170, 100, 120, 0);
        button3step = new GameButton(context, img3step, 420, 1170, 100, 120, 1);
        button4step = new GameButton(context, img4step, 690, 1170, 100, 120, 2);
        buttonJump = new GameButton(context, imgJump, 150, 1370, 120, 120, 3);
        buttonLeft = new GameButton(context, imgLeft, 410, 1370, 115, 120, 4);
        buttonRight = new GameButton(context, imgRight, 690, 1370, 115, 120, 5);
        buttonCut = new GameButton(context, imgCut, 420, 1570, 116, 120, 6);
        buttonFlag = new GameButton(context, imgFlag, 690, 1570, 104, 120, 7);
        licznik = new Text(context,"0/8", 940, 279, R.font.a_b, Color.WHITE, 57, 1020);

        toNextScene = new NextLevelButton(context, WinImageButton,  265, 950, 550, 223, 10);
        toCurrentScene = new WarringButton(context, DefeatImageButton,  265, 950, 550, 203);
        toNextScene.setInactive();
        toCurrentScene.setInactive();

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
            Win2Array.add(blank);
        }

        Win2Bitmaps.add(WinImage);
        Win2Bitmaps.add(DefeatImage);

        //x, y
        coordinates.add(970);
        coordinates.add(370);

        PLAY = false;
        animationPlaying = false;
        action = 0;
    }

    @Override
    public void update()
    {
        toMenuButton.update();
        toHelpButton.update();
        toCodeButton.update();
        playButton.update(kolejka2,2,Win2Array,Win2Bitmaps, toNextScene, toCurrentScene);
        replayButton.update(kolejka2,licznik,bitmaps, lista,coordinates,8);
        button1step.update(kolejka2,licznik,bitmaps, lista,coordinates,8);
        button3step.update(kolejka2,licznik,bitmaps, lista,coordinates,8);
        button4step.update(kolejka2,licznik,bitmaps, lista,coordinates,8);
        buttonJump.update(kolejka2,licznik,bitmaps, lista,coordinates,8);
        buttonLeft.update(kolejka2,licznik,bitmaps, lista,coordinates,8);
        buttonRight.update(kolejka2,licznik,bitmaps, lista,coordinates,8);
        buttonCut.update(kolejka2,licznik,bitmaps, lista,coordinates,8);
        buttonFlag.update(kolejka2,licznik,bitmaps, lista,coordinates,8);
        licznik.update();
        toNextScene.update(Win2Array.get(0));
        toCurrentScene.update(Win2Array.get(0));
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
        board.draw(canvas);

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
        Win2Array.get(0).draw(canvas);
        toNextScene.draw(canvas);
        toCurrentScene.draw(canvas);
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

        toNextScene.reciveTouch(event);
        toCurrentScene.reciveTouch(event);
    }

    @Override
    public void terminate()
    {

    }

}
