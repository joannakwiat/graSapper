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

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class StartScene implements Scene
{

    Sprite background;
    static LevelButton bomb1,bomb2,bomb3,bomb4,bomb5,bomb6;
    NavigationButton manu;
    static Text text1,text2,text3,text4,text5,text6;
    Text text;
    public static String savelevel;
    public static int savelevelbomb;
    ArrayList<Bitmap> animationFrames = new ArrayList<>();
    Animation animation;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public StartScene(Context context)
    {
        Bitmap frame1 = BitmapFactory.decodeResource(context.getResources(),R.drawable.title_screen);

        try {
            File f = context.getFileStreamPath("save.txt");
            if (!f.exists())
            {
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("save.txt", Context.MODE_PRIVATE));
                outputStreamWriter.write("1");
                outputStreamWriter.close();
                Log.i("SAVE", "Plik nie istnieje!");
                savelevel="1";
            }
            else {

                    InputStream inputStream = context.openFileInput("save.txt");
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    savelevel = bufferedReader.readLine();
                    bufferedReader.close();
                    Log.i("SAVE",savelevel);
            }

        }
        catch (IOException e) {
            Log.i("Exception", e.toString());
        }
        Log.i("SAVE",savelevel);


        animationFrames.add(frame1);
        animation = new Animation(animationFrames, 0, 0, 0, -1113, 0.4f, 1080,1794);
        animation.Play();

        Bitmap backgroundImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.start_screen);
        Bitmap bombImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.bomb_takiczarny);
        Bitmap bombUnlockedImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.bomb_takiczerwony);
        Bitmap menuImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.manu_takiczerwony);

         savelevelbomb=0;
            savelevelbomb=Integer.parseInt(savelevel);




        background = new Sprite(backgroundImage, 0,0,1080,1794);
        bomb1 = new LevelButton(context, bombUnlockedImage, 167,933,205, 205, 1, 1);
        text1 = new Text(context,"1",247,975,R.font.a_b,Color.BLACK,97,1020);
        if(savelevelbomb<2)
        {
            bomb2 = new LevelButton(context, bombImage, 437,933,205, 205, 1,2);
            text2 = new Text(context,"2",517,975,R.font.a_b,Color.WHITE,97,1020);

        }
        else
        {
            bomb2 = new LevelButton(context, bombUnlockedImage, 437,933,205, 205, 1,2);
            text2 = new Text(context,"2",517,975,R.font.a_b,Color.BLACK,97,1020);
        }
        if(savelevelbomb<3)
        {
            bomb3 = new LevelButton(context, bombImage, 707, 933, 205, 205, 1, 3);
            text3 = new Text(context, "3", 787, 975, R.font.a_b, Color.WHITE, 97, 1020);
        }
        else
        {
            bomb3 = new LevelButton(context, bombUnlockedImage, 707, 933, 205, 205, 1, 3);
            text3 = new Text(context, "3", 787, 975, R.font.a_b, Color.BLACK, 97, 1020);
        }
        if(savelevelbomb<4)
        {
            bomb4 = new LevelButton(context, bombImage, 167,1198,205, 205, 1,4);
            text4 = new Text(context,"4",247,1240 ,R.font.a_b,Color.WHITE,97,1020);
        }
        else
        {
            bomb4 = new LevelButton(context, bombUnlockedImage, 167,1198,205, 205, 1,4);
            text4 = new Text(context,"4",247,1240 ,R.font.a_b,Color.BLACK,97,1020);
        }
        if(savelevelbomb<5)
        {
            bomb5 = new LevelButton(context, bombImage, 437,1198,205, 205, 1,5);
            text5 = new Text(context,"5",517,1240 ,R.font.a_b,Color.WHITE,97,1020);
        }
        else
        {
            bomb5 = new LevelButton(context, bombUnlockedImage, 437,1198,205, 205, 1,5);
            text5 = new Text(context,"5",517,1240 ,R.font.a_b,Color.BLACK,97,1020);
        }
        if(savelevelbomb<6)
        {
            bomb6 = new LevelButton(context, bombImage, 707,1198,205, 205, 1,6);
            text6 = new Text(context,"6",787,1240 ,R.font.a_b,Color.WHITE,97,1020);
        }
        else
        {
            bomb6 = new LevelButton(context, bombUnlockedImage, 707,1198,205, 205, 1,6);
            text6 = new Text(context,"6",787,1240 ,R.font.a_b,Color.BLACK,97,1020);
        }
        text = new Text(context, "Wybór planszy",
                227, 770, R.font.a_b, Color.BLACK, 97, 1020);
        manu = new NavigationButton(context, menuImage, 451,1470,179, 179, 8);
    }

    public StartScene() {
    }

    @Override
    public void update()
    {
        animation.update();
        bomb1.update();
        bomb2.update();
        bomb3.update();
        bomb4.update();
        bomb5.update();
        bomb6.update();
        manu.update();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void draw(Canvas canvas)
    {
        background.draw(canvas);
        bomb1.draw(canvas);
        bomb2.draw(canvas);
        bomb3.draw(canvas);
        bomb4.draw(canvas);
        bomb5.draw(canvas);
        bomb6.draw(canvas);
        text.draw(canvas);
        text1.draw(canvas);
        text2.draw(canvas);
        text3.draw(canvas);
        text4.draw(canvas);
        text5.draw(canvas);
        text6.draw(canvas);
        manu.draw(canvas);
        animation.draw(canvas);
    }

    @Override
    public void reciveTouch(MotionEvent event)
    {
        bomb1.reciveTouch(event);
        if(savelevelbomb>=2)
            bomb2.reciveTouch(event);
        if(savelevelbomb>=3)
            bomb3.reciveTouch(event);
        if(savelevelbomb>=4)
            bomb4.reciveTouch(event);
        if(savelevelbomb>=5)
            bomb5.reciveTouch(event);
        if(savelevelbomb>=6)
            bomb6.reciveTouch(event);
        manu.reciveTouch(event);
    }

    @Override
    public void terminate()
    {

    }
}
