package com.example.grasapper;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class MainThread extends Thread
{
    //pola
    private SurfaceHolder surfaceHolder;
    private CoronaEngine coronaEngine;
    private boolean running;
    public static Canvas canvas;

    //metody
    public MainThread(SurfaceHolder surfaceHolder, CoronaEngine coronaEngine)
    {
        super();
        this.surfaceHolder = surfaceHolder;
        this.coronaEngine = coronaEngine;
    }

    @Override
    public void run()
    {
        while (running)
        {
            canvas = null;

            try
            {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder)
                {
                    this.coronaEngine.update();
                    this.coronaEngine.draw(canvas);
                }
            } catch (Exception e)
            {

            }
            finally
            {
                if (canvas != null)
                {
                    try
                    {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

    public void setRunning(boolean isRunning)
    {
        running = isRunning;
    }
}
