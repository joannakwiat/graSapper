package com.example.grasapper;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Build;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.Log;
import android.view.MotionEvent;

import androidx.annotation.RequiresApi;
import androidx.core.content.res.ResourcesCompat;

import java.io.IOException;
import java.io.OutputStreamWriter;

public class TextButton
{
    //tekst
    String text;
    StaticLayout staticLayout;
    StaticLayout.Builder staticLayoutBuild;

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
    TextPaint paint;

    TextButton ()
    {

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    TextButton (Context context, String text, int x, int y, int font, int color, int fontSize, int width)
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
        this.clicked = false;
        active = true;

        paint = new TextPaint();
        Typeface typeface = ResourcesCompat.getFont(context, font);
        paint.setTypeface(typeface);
        paint.setColor(color);
        paint.setTextSize(fontSize);

        staticLayoutBuild = StaticLayout.Builder.obtain(text, 0,text.length(),paint,(int)(width*widthRatio));
        staticLayout = staticLayoutBuild.build();
        this.height = staticLayout.getHeight();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    TextButton (Context context, String text, int x, int y, int font, int color, int fontSize, int width, int changeActiveMusic)
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
        this.clicked = false;
        active = true;

        paint = new TextPaint();
        Typeface typeface = ResourcesCompat.getFont(context, font);
        paint.setTypeface(typeface);
        paint.setColor(color);
        paint.setTextSize(fontSize);

        staticLayoutBuild = StaticLayout.Builder.obtain(text, 0,text.length(),paint,(int)(width*widthRatio));
        staticLayout = staticLayoutBuild.build();
        this.height = staticLayout.getHeight();
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
            canvas.save();
            canvas.translate(x, y);
            staticLayout.draw(canvas);
            canvas.restore();
        }
    }
}

class NavigationTextButton extends TextButton
{
    int numberOfScene;

    @RequiresApi(api = Build.VERSION_CODES.M)
    NavigationTextButton (Context context, String text, int x, int y, int font, int color, int fontSize, int width, int sceneNumber)
    {
        super(context,text,x,y,font,color,fontSize,width);
        this.numberOfScene = sceneNumber;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    NavigationTextButton (Context context, String text, int x, int y, int font, int color, int fontSize, int width, int sceneNumber, int changeActiveMusic)
    {
        super(context,text,x,y,font,color,fontSize,width, changeActiveMusic);
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

class BackTextButton extends TextButton
{
    int numberOfScene;

    @RequiresApi(api = Build.VERSION_CODES.M)
    BackTextButton (Context context, String text, int x, int y, int font, int color, int fontSize, int width)
    {
        super(context,text,x,y,font,color,fontSize,width);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    BackTextButton (Context context, String text, int x, int y, int font, int color, int fontSize, int width, int changeActiveMusic)
    {
        super(context,text,x,y,font,color,fontSize,width, changeActiveMusic);
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

class ExitTextButton extends TextButton
{
    @RequiresApi(api = Build.VERSION_CODES.M)
    ExitTextButton (Context context, String text, int x, int y, int font, int color, int fontSize, int width)
    {
        super(context,text,x,y,font,color,fontSize,width);
    }

    public void update()
    {
        if (active)
        {
            if (clicked) {
                System.exit(0);
            }
        }
    }
}
class ResetSaveTextButton extends TextButton
{
    @RequiresApi(api = Build.VERSION_CODES.M)
    ResetSaveTextButton (Context context, String text, int x, int y, int font, int color, int fontSize, int width)
    {
        super(context,text,x,y,font,color,fontSize,width);
    }

    public void update(Context context)
    {
        if (active)
        {
            if (clicked) {
                try {
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("save.txt", Context.MODE_PRIVATE));
                    outputStreamWriter.write("1");
                    outputStreamWriter.close();
                }catch(IOException e) {Log.i("SAVE", "ERROR");}
                Log.i("SAVE", "Plik nie istnieje!");
                StartScene.savelevel="1";
                StartScene.savelevelbomb=1;
                Bitmap bombImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.bomb_takiczarny);
                LevelButton bomb2x = new LevelButton(context, bombImage, 437,933,205,  205, 1,2);
                LevelButton bomb3x = new LevelButton(context, bombImage, 707,933, 205, 205, 1,3);
                LevelButton bomb4x = new LevelButton(context, bombImage, 167,1198,205, 205, 1,4);
                LevelButton bomb5x = new LevelButton(context, bombImage, 437,1198,205, 205, 1,5);
                LevelButton bomb6x = new LevelButton(context, bombImage, 707,1198,205, 205, 1,6);
                Text text2x=  new Text(context,"2",517,975  ,R.font.a_b,Color.WHITE,97,1020);
                Text text3x=  new Text(context,"3",787,975  ,R.font.a_b,Color.WHITE,97,1020);
                Text text4x=  new Text(context,"4",247,1240 ,R.font.a_b,Color.WHITE,97,1020);
                Text text5x=  new Text(context,"5",517,1240 ,R.font.a_b,Color.WHITE,97,1020);
                Text text6x=  new Text(context,"6",787,1240 ,R.font.a_b,Color.WHITE,97,1020);
                StartScene.text2=text2x;
                StartScene.text3=text3x;
                StartScene.text4=text4x;
                StartScene.text5=text5x;
                StartScene.text6=text6x;
                StartScene.bomb2=bomb2x;
                StartScene.bomb3=bomb3x;
                StartScene.bomb4=bomb4x;
                StartScene.bomb5=bomb5x;
                StartScene.bomb6=bomb6x;
                SceneManager.ACTIVE_SCANE = SceneManager.PREVIOUSE_SCENE;
                super.update();

            }
        }
    }
}