package com.example.math_game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import java.util.EventListener;

public class Block implements EventListener {

    int x=0,y;
    Bitmap blockarea;


    Block(Resources res){
        blockarea = BitmapFactory.decodeResource(res,R.drawable.bublee);
        blockarea = Bitmap.createScaledBitmap(blockarea,350,1080,false);
    }
    Bitmap getBlockarea(){
        return blockarea;
    }

    Rect getcolshape(){
        return new Rect(x,y,x+450,2000);
    }

    public void setX(int x) {
        this.x = x;
    }
}