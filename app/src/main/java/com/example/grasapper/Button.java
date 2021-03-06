package com.example.grasapper;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.os.Build;
import android.view.Menu;
import android.view.MotionEvent;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.io.IOException;
import java.io.OutputStreamWriter;
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
    boolean active;
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
        active = true;

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
        active = true;

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
        active = true;

        paint = new Paint();
    }

    public void setInactive()
    {
        active = false;
    }

    public void setActive()
    {
        active = true;
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
        if (active)
        {
            if (clicked) {
                click.start();
                ColorFilter filter = new LightingColorFilter(Color.WHITE, 0);
                paint.setColorFilter(filter);
                if (activeMusicToChange != -1) {
                    MusicManager.CHANGE = true;
                    MusicManager.ACTIVE_MUSIC = activeMusicToChange;
                }
                clicked = false;
            }
        }
    }

    public void reciveTouch(MotionEvent event)
    {
        if (active)
        {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN: {
                    if (isClicked(event.getX(), event.getY())) {
                        ColorFilter filter = new LightingColorFilter(Color.GRAY, 0);
                        paint.setColorFilter(filter);

                    }
                    break;
                }
                case MotionEvent.ACTION_MOVE: {
                    if (!isClicked(event.getX(), event.getY())) {
                        ColorFilter filter = new LightingColorFilter(Color.WHITE, 0);
                        paint.setColorFilter(filter);
                    }
                    break;
                }
                case MotionEvent.ACTION_UP: {
                    setClicked(isClicked(event.getX(), event.getY()));
                    break;
                }
            }
        }
    }

    public void draw(Canvas canvas)
    {
        if (active)
        {
            canvas.drawBitmap(bitmap, this.x, this.y, paint);
        }
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
        if (active)
        {
            if (clicked) {
                SceneManager.PREVIOUSE_SCENE = SceneManager.ACTIVE_SCANE;
                SceneManager.ACTIVE_SCANE = numberOfScene;
                super.update();
            }
        }
    }
}

class LevelButton extends NavigationButton
{
    int numberOfLevel;
    Bitmap boardImage;

    LevelButton()
    {

    }

    LevelButton (Context context, Bitmap bitmap, int x, int y, int width, int height, int sceneNumber, int levelNumber)
    {
        super(context, bitmap, x, y, width, height, sceneNumber);
        numberOfLevel = levelNumber;
    }

    LevelButton (Context context, Bitmap bitmap, int x, int y, int width, int height, int sceneNumber, int changeActiveMusic,  int levelNumber)
    {
        super(context,bitmap,x,y,width,height, sceneNumber, changeActiveMusic);
        numberOfLevel = levelNumber;

    }

    public void update()
    {
        if (active)
        {
            if (clicked) {
                GameplayScene.LEVEL = numberOfLevel;
                GameplayScene.ACTIVE = true;
                super.update();
            }
        }
    }
}

class BackButton extends Button
{
    int numberOfScene;

    BackButton()
    {

    }

    BackButton (Context context, Bitmap bitmap, int x, int y, int width, int height)
    {
        super(context,bitmap,x,y,width,height);
    }

    BackButton (Context context, Bitmap bitmap, int x, int y, int width, int height, int changeActiveMusic)
    {
        super(context,bitmap,x,y,width,height,changeActiveMusic);
    }

    public void update()
    {
        if (active)
        {
            if (clicked) {
                SceneManager.ACTIVE_SCANE = SceneManager.PREVIOUSE_SCENE;
                super.update();
            }
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

    public void update(ArrayList<Integer> kolejka, Text text, ArrayList<Bitmap> bitmaps, ArrayList<Sprite> lista, ArrayList<Integer> coor, int MaxIloscRuchow, Context context)
    {
        if (active)
        {
            Sprite blank = new Sprite(bitmaps.get(8), 925, 300, 1, 1);
            Sprite pom;
            int x = coor.get(0);
            int y = coor.get(1);
            if (clicked) {
                if (buttonType == 8) {
                    //reset
                    Log.i("Clicked", "RESET");
                    kolejka.clear();
                    lista.clear();
                    CodeScene.codeList.clear();
                    int codeX = 290;
                    int codeY = 290;
                    Bitmap codeBlank = BitmapFactory.decodeResource(context.getResources(),R.drawable.blank);
                    for (int i = 0; i < 11; i++) {
                        lista.add(blank);
                        Sprite blankSprite = new Sprite(codeBlank, codeX,codeY,500,90);
                        CodeScene.codeList.add(blankSprite);
                        codeY += 90;
                    }
                    Bitmap startImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.start);
                    CodeScene.codeList.set(0, new Sprite(startImage, 290,290, 500, 90));
                    coor.clear();
                    coor.add(970);
                    coor.add(370);
                    Log.i("list", kolejka.toString());
                }
                if (kolejka.size() > MaxIloscRuchow - 1) {
                    Log.i("ERROR", "ZA DUZA KOLEJKA");
                } else {
                    if (buttonType == 0) {
                        //1step
                        Log.i("size kolejki", text.text);
                        Log.i("Clicked", "0");
                        kolejka.add(0);
                        Bitmap krok1Image = BitmapFactory.decodeResource(context.getResources(),R.drawable.krok_1);
                        CodeScene.codeList.set(kolejka.size(), new Sprite(krok1Image, CodeScene.codeList.get(kolejka.size()).x, CodeScene.codeList.get(kolejka.size()).y, 500,90));
                        pom = new Sprite(bitmaps.get(0), x, y, bitmaps.get(0).getWidth() / 4, bitmaps.get(0).getHeight() / 4);
                        lista.add(pom);
                        Log.i("list", kolejka.toString());
                        coor.clear();
                        coor.add(x);
                        coor.add(y + 70);
                    }
                    if (buttonType == 1) {
                        //3step
                        Log.i("Clicked", "1");
                        kolejka.add(1);
                        Bitmap krok3Image = BitmapFactory.decodeResource(context.getResources(),R.drawable.krok_3);
                        CodeScene.codeList.set(kolejka.size(), new Sprite(krok3Image, CodeScene.codeList.get(kolejka.size()).x, CodeScene.codeList.get(kolejka.size()).y, 500,90));
                        pom = new Sprite(bitmaps.get(1), x, y, bitmaps.get(1).getWidth() / 4, bitmaps.get(1).getHeight() / 4);
                        lista.add(pom);
                        Log.i("list", kolejka.toString());
                        coor.clear();
                        coor.add(x);
                        coor.add(y + 70);
                    }
                    if (buttonType == 2) {
                        //4step
                        Log.i("Clicked", "2");
                        kolejka.add(2);
                        Bitmap krok4Image = BitmapFactory.decodeResource(context.getResources(),R.drawable.krok_4);
                        CodeScene.codeList.set(kolejka.size(), new Sprite(krok4Image, CodeScene.codeList.get(kolejka.size()).x, CodeScene.codeList.get(kolejka.size()).y, 500,90));
                        pom = new Sprite(bitmaps.get(2), x, y, bitmaps.get(2).getWidth() / 4, bitmaps.get(2).getHeight() / 4);
                        lista.add(pom);
                        Log.i("list", kolejka.toString());
                        coor.clear();
                        coor.add(x);
                        coor.add(y + 70);
                    }
                    if (buttonType == 3) {
                        //jump
                        Log.i("Clicked", "3");
                        kolejka.add(3);
                        Bitmap skokImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.skok);
                        CodeScene.codeList.set(kolejka.size(), new Sprite(skokImage, CodeScene.codeList.get(kolejka.size()).x, CodeScene.codeList.get(kolejka.size()).y, 200,90));
                        pom = new Sprite(bitmaps.get(3), x, y, bitmaps.get(3).getWidth() / 5, bitmaps.get(3).getHeight() / 5);
                        lista.add(pom);
                        Log.i("list", kolejka.toString());
                        coor.clear();
                        coor.add(x);
                        coor.add(y + 60);
                    }
                    if (buttonType == 4) {
                        //left
                        Log.i("Clicked", "4");
                        kolejka.add(4);
                        Bitmap lewoImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.lewo);
                        CodeScene.codeList.set(kolejka.size(), new Sprite(lewoImage, CodeScene.codeList.get(kolejka.size()).x, CodeScene.codeList.get(kolejka.size()).y, 500,90));
                        pom = new Sprite(bitmaps.get(4), x, y, bitmaps.get(4).getWidth() / 5, bitmaps.get(4).getHeight() / 5);
                        lista.add(pom);
                        Log.i("list", kolejka.toString());
                        coor.clear();
                        coor.add(x);
                        coor.add(y + 60);
                    }
                    if (buttonType == 5) {
                        //right
                        Log.i("Clicked", "5");
                        kolejka.add(5);
                        Bitmap prawoImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.prawo);
                        CodeScene.codeList.set(kolejka.size(), new Sprite(prawoImage, CodeScene.codeList.get(kolejka.size()).x, CodeScene.codeList.get(kolejka.size()).y, 500,90));
                        pom = new Sprite(bitmaps.get(5), x, y, bitmaps.get(5).getWidth() / 5, bitmaps.get(5).getHeight() / 5);
                        lista.add(pom);
                        Log.i("list", kolejka.toString());
                        coor.clear();
                        coor.add(x);
                        coor.add(y + 60);
                    }
                    if (buttonType == 6) {
                        //cut
                        Log.i("Clicked", "6");
                        kolejka.add(6);
                        Bitmap cutImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.kabel);
                        CodeScene.codeList.set(kolejka.size(), new Sprite(cutImage, CodeScene.codeList.get(kolejka.size()).x, CodeScene.codeList.get(kolejka.size()).y, 500,90));
                        pom = new Sprite(bitmaps.get(6), x, y, bitmaps.get(6).getWidth() / 4, bitmaps.get(6).getHeight() / 4);
                        lista.add(pom);
                        Log.i("list", kolejka.toString());
                        coor.clear();
                        coor.add(x);
                        coor.add(y + 70);
                    }
                    if (buttonType == 7) {
                        //flag
                        Log.i("Clicked", "7");
                        kolejka.add(7);
                        Bitmap rozbrojImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.rozbroj);
                        CodeScene.codeList.set(kolejka.size(), new Sprite(rozbrojImage, CodeScene.codeList.get(kolejka.size()).x, CodeScene.codeList.get(kolejka.size()).y, 300,90));
                        pom = new Sprite(bitmaps.get(7), x, y, bitmaps.get(7).getWidth() / 4, bitmaps.get(7).getHeight() / 4);
                        lista.add(pom);
                        Log.i("list", kolejka.toString());
                        coor.clear();
                        coor.add(x);
                        coor.add(y + 70);
                    }
                }
                text.set(kolejka.size(), MaxIloscRuchow);
                super.update();
            }
        }
    }
}

class PlayButton extends Button {
    boolean isRepley;

    PlayButton() {

    }

    PlayButton(Context context, Bitmap bitmap, int x, int y, int width, int height, boolean isRepley) {
        super(context, bitmap, x, y, width, height);
        this.isRepley = isRepley;
    }

    PlayButton(Context context, Bitmap bitmap, int x, int y, int width, int height, boolean isRepley, int changeActiveMusic) {
        super(context, bitmap, x, y, width, height, changeActiveMusic);
        this.isRepley = isRepley;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void update(Context context, ArrayList<Integer> kolejka, int SceneNumber, ArrayList<Sprite> img, ArrayList<Bitmap> bitmaps, Button toNext, Button toCurrent) {
        if (active) {
            Sprite pom;
            if (clicked) {
                if (SceneNumber == 1)//poziom1
                {
                    if ((kolejka.get(0) == 5) && (kolejka.get(1) == 0) && (kolejka.get(2) == 4) && (kolejka.get(3) == 1) && (kolejka.get(4) == 4) && (kolejka.get(5) == 7)) {
                        pom = new Sprite(bitmaps.get(0), 0, 0, 1080, 1794);
                        toNext.setActive();
                        img.clear();
                        img.add(pom);
                        if (StartScene.savelevelbomb < 2) {
                            StartScene.savelevel = "2";
                            StartScene.savelevelbomb = 2;
                            try {
                                OutputStreamWriter outputStreamWriter1 = new OutputStreamWriter(context.openFileOutput("save.txt", Context.MODE_PRIVATE));
                                outputStreamWriter1.write("2");
                                outputStreamWriter1.close();
                                Log.i("SAVE", StartScene.savelevel);
                            } catch (IOException e) {
                                Log.i("Exception", e.toString());
                            }
                            Bitmap bombUnlockedImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.bomb_takiczerwony);
                            LevelButton bomb2x = new LevelButton(context, bombUnlockedImage, 437,933,205, 205, 1,2);
                            StartScene.bomb2=bomb2x;
                            Text text2x= new Text(context,"2",517,975,R.font.a_b,Color.BLACK,97,1020);
                            StartScene.text2=text2x;
                        }


                    } else {
                        pom = new Sprite(bitmaps.get(1), 0, 0, 1080, 1794);
                        toCurrent.setActive();
                        img.clear();
                        img.add(pom);
                    }
                }
                if (SceneNumber == 2)//poziom2
                {
                    if ((kolejka.get(0) == 5) && (kolejka.get(1) == 6) && (kolejka.get(2) == 1) && (kolejka.get(3) == 4) && (kolejka.get(4) == 2) && (kolejka.get(5) == 4) && (kolejka.get(6) == 6) && (kolejka.get(7) == 6)) {
                        pom = new Sprite(bitmaps.get(0), 0, 0, 1080, 1794);
                        toNext.setActive();
                        img.clear();
                        img.add(pom);
                        if (StartScene.savelevelbomb < 3) {
                            StartScene.savelevel = "3";
                            StartScene.savelevelbomb = 3;
                            try {
                                OutputStreamWriter outputStreamWriter1 = new OutputStreamWriter(context.openFileOutput("save.txt", Context.MODE_PRIVATE));
                                outputStreamWriter1.write("3");
                                outputStreamWriter1.close();
                                Log.i("SAVE", StartScene.savelevel);
                            } catch (IOException e) {
                                Log.i("Exception", e.toString());
                            }
                            Bitmap bombUnlockedImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.bomb_takiczerwony);
                            LevelButton bomb3x = new LevelButton(context, bombUnlockedImage, 707, 933, 205, 205, 1, 3);
                            StartScene.bomb3=bomb3x;
                            Text text3x = new Text(context, "3", 787, 975, R.font.a_b, Color.BLACK, 97, 1020);
                            StartScene.text3=text3x;
                        }
                    } else {
                        pom = new Sprite(bitmaps.get(1), 0, 0, 1080, 1794);
                        toCurrent.setActive();
                        img.clear();
                        img.add(pom);
                    }
                }
                if (SceneNumber == 3)//poziom3
                {
                    if ((kolejka.get(0) == 1) && (kolejka.get(1) == 5) && (kolejka.get(2) == 0) && (kolejka.get(3) == 0) && (kolejka.get(4) == 5) && (kolejka.get(5) == 7)) {
                        pom = new Sprite(bitmaps.get(0), 0, 0, 1080, 1794);
                        toNext.setActive();
                        img.clear();
                        img.add(pom);
                        if (StartScene.savelevelbomb < 4)
                        {
                            StartScene.savelevel = "4";
                            StartScene.savelevelbomb = 4;
                            try {
                                OutputStreamWriter outputStreamWriter1 = new OutputStreamWriter(context.openFileOutput("save.txt", Context.MODE_PRIVATE));
                                outputStreamWriter1.write("4");
                                outputStreamWriter1.close();
                                Log.i("SAVE", StartScene.savelevel);
                            } catch (IOException e) {
                                Log.i("Exception", e.toString());
                            }
                            Bitmap bombUnlockedImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.bomb_takiczerwony);
                            LevelButton bomb4x = new LevelButton(context, bombUnlockedImage, 167,1198,205, 205, 1,4);
                            StartScene.bomb4=bomb4x;
                            Text text4x=new Text(context,"4",247,1240 ,R.font.a_b,Color.BLACK,97,1020);
                            StartScene.text4=text4x;

                        }
                    } else {
                        pom = new Sprite(bitmaps.get(1), 0, 0, 1080, 1794);
                        toCurrent.setActive();
                        img.clear();
                        img.add(pom);
                    }

                }
                if (SceneNumber == 4)//poziom4
                {
                    if ((kolejka.get(0) == 0) && (kolejka.get(1) == 4) && (kolejka.get(2) == 0) && (kolejka.get(3) == 3) && (kolejka.get(4) == 4) && (kolejka.get(5) == 7)) {
                        pom = new Sprite(bitmaps.get(0), 0, 0, 1080, 1794);
                        toNext.setActive();
                        img.clear();
                        img.add(pom);
                        if (StartScene.savelevelbomb < 5) {
                            StartScene.savelevel = "5";
                            StartScene.savelevelbomb = 5;
                            try {
                                OutputStreamWriter outputStreamWriter1 = new OutputStreamWriter(context.openFileOutput("save.txt", Context.MODE_PRIVATE));
                                outputStreamWriter1.write("5");
                                outputStreamWriter1.close();
                                Log.i("SAVE", StartScene.savelevel);
                            } catch (IOException e) {
                                Log.i("Exception", e.toString());
                            }
                            Bitmap bombUnlockedImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.bomb_takiczerwony);
                            LevelButton bomb5x = new LevelButton(context, bombUnlockedImage, 437,1198,205, 205, 1,5);
                            StartScene.bomb5=bomb5x;
                            Text text5x = new Text(context,"5",517,1240 ,R.font.a_b,Color.BLACK,97,1020);
                            StartScene.text5=text5x;
                        }
                    } else {
                        pom = new Sprite(bitmaps.get(1), 0, 0, 1080, 1794);
                        toCurrent.setActive();
                        img.clear();
                        img.add(pom);
                    }
                }
                if (SceneNumber == 5)//poziom5
                {
                    if ((kolejka.get(0) == 0) && (kolejka.get(1) == 3) && (kolejka.get(2) == 4) && (kolejka.get(3) == 0) && (kolejka.get(4) == 0) && (kolejka.get(5) == 4) && (kolejka.get(6) == 6) && (kolejka.get(7) == 0) && (kolejka.get(8) == 7)) {
                        pom = new Sprite(bitmaps.get(0), 0, 0, 1080, 1794);
                        toNext.setActive();
                        img.clear();
                        img.add(pom);
                        if (StartScene.savelevelbomb < 6) {
                            StartScene.savelevel = "6";
                            StartScene.savelevelbomb = 6;
                            try {
                                OutputStreamWriter outputStreamWriter1 = new OutputStreamWriter(context.openFileOutput("save.txt", Context.MODE_PRIVATE));
                                outputStreamWriter1.write("6");
                                outputStreamWriter1.close();
                                Log.i("SAVE", StartScene.savelevel);
                            } catch (IOException e) {
                                Log.i("Exception", e.toString());
                            }
                            Bitmap bombUnlockedImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.bomb_takiczerwony);
                            LevelButton bomb6x = new LevelButton(context, bombUnlockedImage, 707,1198,205, 205, 1,6);
                            StartScene.bomb6=bomb6x;
                            Text text6x= new Text(context,"6",787,1240 ,R.font.a_b,Color.BLACK,97,1020);
                            StartScene.text6=text6x;

                        }
                    } else {
                        pom = new Sprite(bitmaps.get(1), 0, 0, 1080, 1794);
                        toCurrent.setActive();
                        img.clear();
                        img.add(pom);
                    }
                }
                if (SceneNumber == 6)//poziom6
                {
                    if ((kolejka.get(0) == 4) && (kolejka.get(1) == 2) && (kolejka.get(2) == 5) && (kolejka.get(3) == 1) && (kolejka.get(4) == 3) && (kolejka.get(5) == 1) && (kolejka.get(6) == 5) && (kolejka.get(7) == 0) && (kolejka.get(8) == 0) && (kolejka.get(9) == 7)) {
                        pom = new Sprite(bitmaps.get(0), 0, 0, 1080, 1794);
                        toNext.setActive();
                        img.clear();
                        img.add(pom);
                    } else {
                        pom = new Sprite(bitmaps.get(1), 0, 0, 1080, 1794);
                        toCurrent.setActive();
                        img.clear();
                        img.add(pom);
                    }
                }
                super.update();
            }
        }
    }
}

class NextLevelButton extends Button
{
    int nextLevel;

    NextLevelButton()
    {

    }

    NextLevelButton (Context context, Bitmap bitmap, int x, int y, int width, int height, int nextLevel)
    {
        super(context,bitmap,x,y,width,height);
        this.nextLevel = nextLevel;
    }

    public void update(Sprite sprite)
    {
        if (active)
        {
            if (clicked)
            {
                super.update();
                active = false;
                sprite.setInactive();
                GameplayScene.LEVEL = nextLevel;
                GameplayScene.ACTIVE = true;
            }
        }
    }
}

class WarringButton extends Button
{
    WarringButton()
    {

    }

    WarringButton (Context context, Bitmap bitmap, int x, int y, int width, int height)
    {
        super(context,bitmap,x,y,width,height);
    }

    public void update(Sprite sprite)
    {
        if (active)
        {
            if (clicked)
            {
                super.update();
                active = false;
                sprite.setInactive();
            }
        }
    }
}