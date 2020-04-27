package com.example.grasapper;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

import androidx.annotation.RequiresApi;

public class CoronaEngine extends SurfaceView implements SurfaceHolder.Callback
{
    private MainThread thread;
    private SceneManager manager;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public CoronaEngine(Context context)
    {
        super(context);
        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this);
        manager = new SceneManager(context);
        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
    {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {
        boolean retry = true;
        while (retry)
        {
            try
            {
                thread.setRunning(false);
                thread.join();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        manager.reciveTouch(event);

        return true;
    }

    public void update()
    {
        manager.update();
    }

    @Override
    public void draw(Canvas canvas)
    {
        super.draw(canvas);
        if (canvas != null);
        {
            manager.draw(canvas);
        }

    }
}
