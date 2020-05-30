package com.example.grasapper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.MotionEvent;

import androidx.annotation.RequiresApi;

import java.io.*;
import java.util.ArrayList;




public class GameplayScene implements Scene
{
    public static int LEVEL;
    public static int MAX_NUMBER_OF_MOVES;
    public int nextLevel;


    public static boolean ACTIVE;

    // public ArrayList<Integer> kolejka = new ArrayList<>();
    public ArrayList<Bitmap> bitmaps = new ArrayList<>();
    public ArrayList<Sprite> lista = new ArrayList<>();
    public ArrayList<Integer> coordinates = new ArrayList<>();
    public ArrayList<Bitmap> WinBitmaps = new ArrayList<>();
    public ArrayList<Sprite> WinArray = new ArrayList<>();

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

    Context con;

    Bitmap boardImage;
    Bitmap WinImageButton;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public GameplayScene(Context context)
    {
        con = context;

        Bitmap backgroundImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.gameplay_screen);
        Bitmap toMenuButtonImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.manu);
        Bitmap toHelpButtonImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.info);
        Bitmap toCodeButtonImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.code);
        Bitmap playButtonImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.play);
        Bitmap replayButtonImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.reverse);
        Bitmap listImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.list);
        Bitmap WinImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.win3);
        Bitmap DefeatImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.defeat3);
        WinImageButton = BitmapFactory.decodeResource(context.getResources(),R.drawable.win1);
        Bitmap DefeatImageButton = BitmapFactory.decodeResource(context.getResources(),R.drawable.defeat1);
        boardImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.poziom1);

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
        licznik = new Text(context,"0/" + MAX_NUMBER_OF_MOVES, 940, 279, R.font.a_b, Color.WHITE, 57, 1020);

        toNextScene = new NextLevelButton(context, WinImageButton,  265, 950, 550, 223, nextLevel);
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
            WinArray.add(blank);
        }

        WinBitmaps.add(WinImage);
        WinBitmaps.add(DefeatImage);

        //x, y
        coordinates.add(970);
        coordinates.add(370);
    }

    @Override
    public void update()
    {
        gameplaySceneActive(con);
        toMenuButton.update();
        toHelpButton.update();
        toCodeButton.update();
        playButton.update(con,kolejka,LEVEL,WinArray,WinBitmaps, toNextScene, toCurrentScene);
        replayButton.update(kolejka,licznik,bitmaps, lista,coordinates, MAX_NUMBER_OF_MOVES);
        button1step.update(kolejka,licznik,bitmaps, lista,coordinates, MAX_NUMBER_OF_MOVES);
        button3step.update(kolejka,licznik,bitmaps, lista,coordinates, MAX_NUMBER_OF_MOVES);
        button4step.update(kolejka,licznik,bitmaps, lista,coordinates, MAX_NUMBER_OF_MOVES);
        buttonJump.update(kolejka,licznik,bitmaps, lista,coordinates, MAX_NUMBER_OF_MOVES);
        buttonLeft.update(kolejka,licznik,bitmaps, lista,coordinates, MAX_NUMBER_OF_MOVES);
        buttonRight.update(kolejka,licznik,bitmaps, lista,coordinates, MAX_NUMBER_OF_MOVES);
        buttonCut.update(kolejka,licznik,bitmaps, lista,coordinates, MAX_NUMBER_OF_MOVES);
        buttonFlag.update(kolejka,licznik,bitmaps, lista,coordinates, MAX_NUMBER_OF_MOVES);
        licznik.update();
        toNextScene.update(WinArray.get(0));
        toCurrentScene.update(WinArray.get(0));
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
        WinArray.get(0).draw(canvas);
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

    public void gameplaySceneActive(Context context)
    {
        if (ACTIVE)
        {
            Log.i("KEK", LEVEL + "");
            ACTIVE = false;

            kolejka.clear();
            lista.clear();

            for(int i=0; i<10; i++)
            {
                lista.add(blank);
            }

            coordinates.clear();
            coordinates.add(970);
            coordinates.add(370);

            licznik.color = Color.WHITE;

            switch (LEVEL)
            {
                case 1:
                {
                    boardImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.poziom3);
                    board = new Sprite(boardImage, 30, 110, 854, 1005);
                    MAX_NUMBER_OF_MOVES = 6;
                    licznik.text = "0/" + MAX_NUMBER_OF_MOVES;
                    nextLevel = 2;
                    break;
                }
                case 2:
                {
                    boardImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.poziom2);
                    board = new Sprite(boardImage, 30, 110, 854, 1005);
                    MAX_NUMBER_OF_MOVES = 8;
                    licznik.text = "0/" + MAX_NUMBER_OF_MOVES;
                    nextLevel = 3;
                    break;
                }
                case 3:
                {
                    boardImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.poziom1);
                    board = new Sprite(boardImage, 30, 110, 854, 1005);
                    MAX_NUMBER_OF_MOVES = 6;
                    licznik.text = "0/" + MAX_NUMBER_OF_MOVES;
                    nextLevel = 4;
                    break;
                }
                case 4:
                {
                    boardImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.poziom4);
                    board = new Sprite(boardImage, 30, 110, 854, 1005);
                    MAX_NUMBER_OF_MOVES = 6;
                    licznik.text = "0/" + MAX_NUMBER_OF_MOVES;
                    nextLevel = 5;
                    break;
                }
                case 5:
                {
                    boardImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.poziom5);
                    board = new Sprite(boardImage, 30, 110, 854, 1005);
                    MAX_NUMBER_OF_MOVES = 9;
                    licznik.text = "0/" + MAX_NUMBER_OF_MOVES;
                    nextLevel = 6;
                    break;
                }
                case 6:
                {
                    boardImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.poziom6);
                    board = new Sprite(boardImage, 30, 110, 854, 1005);
                    MAX_NUMBER_OF_MOVES = 10;
                    licznik.text = "0/" + MAX_NUMBER_OF_MOVES;
                    nextLevel = 1;
                    break;
                }
            }
            toNextScene = new NextLevelButton(context, WinImageButton,  265, 950, 550, 223, nextLevel);
            toNextScene.setInactive();
        }
    }
}
