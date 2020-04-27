package com.example.grasapper;

import android.graphics.Canvas;
import android.view.MotionEvent;

public interface Scene
{
    public void update();
    public void draw(Canvas canvas);
    public void reciveTouch(MotionEvent event);
    public void terminate();
}
