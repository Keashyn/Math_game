package com.example.math_game;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import java.util.EventListener;

import static com.example.math_game.GameView.screenratX;
import static com.example.math_game.GameView.screenratY;


public class Bubble implements EventListener {

    public int speed = 20;
    public boolean gettap = true;
    int x=0,y , width,height;
    Bitmap bubble1,bubble2,bubble3,bubble4;
    String text;
    boolean bubstat = true;

//    int bubblecount = 1; //for animation

    Bubble(Resources res){

        bubble1 = BitmapFactory.decodeResource(res, R.drawable.buburu);
        bubble2 = BitmapFactory.decodeResource(res, R.drawable.buburu2);
        bubble3 = BitmapFactory.decodeResource(res, R.drawable.buburu3);
        bubble4 = BitmapFactory.decodeResource(res, R.drawable.buburu4);

        width = bubble1.getWidth();
        height = bubble1.getHeight();

        width /= 6;
        height /= 6;

        width += (int) (width * screenratX);
        height += (int) (height* screenratY);

        bubble1 = Bitmap.createScaledBitmap(bubble1,width,height,false);
        bubble2 = Bitmap.createScaledBitmap(bubble2,width,height,false);
        bubble3 = Bitmap.createScaledBitmap(bubble3,width,height,false);
        bubble4 = Bitmap.createScaledBitmap(bubble4,width,height,false);

        y= -height;

    }
    Bitmap getBubble1(){
//        if (bubblecount == 1) {
//            bubblecount++;
//            return bubbl1;
//        }
//
//        if (bubblecount == 2) {
//            bubblecount++;
//            return bubbl1;
//        }
//
//        if (bubblecount == 3) {
//            bubblecount++;
//            return bubbl1;
//        }
//
//        bubblecount = 1;
        //animation purpose

        return bubble1;
    }

    public Bitmap getBubble2() {
        return bubble2;
    }

    public Bitmap getBubble3() {
        return bubble3;
    }

    public Bitmap getBubble4() {
        return bubble4;
    }

    Rect getcolshape(){
        return new Rect(x,y,x+width+50,y+height+70);
    }

    public boolean isBubstat() {
        return bubstat;
    }

    public void setBubstat(boolean bubstat) {
        this.bubstat = bubstat;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

