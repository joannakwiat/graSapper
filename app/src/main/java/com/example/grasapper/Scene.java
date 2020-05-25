package com.example.grasapper;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;

public interface Scene
{
    public static ArrayList<Integer> kolejka = new ArrayList<>();
    public static ArrayList<Integer> kolejka2 = new ArrayList<>();
    public static ArrayList<Integer> kolejka3 = new ArrayList<>();

    public void update();
    public void draw(Canvas canvas);
    public void reciveTouch(MotionEvent event);
    public void terminate();
}
