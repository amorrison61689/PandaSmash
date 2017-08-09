package com.amorr.pandasmash;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by amorr on 8/9/2017.
 */

public class StartScreen extends View
{
    PandaSmash mainclass;
    Bitmap startscreen;
    float scalex;
    float scaley;


    public StartScreen (Context c)
    {
        super(c);
        mainclass = (PandaSmash)c;
        startscreen = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.start_screen), 180, 200, false);
    }
    protected void onDraw(Canvas g)
    {
        scalex = (float)(g.getWidth()/180);
        scaley = (float)(g.getHeight()/200);
        g.scale(scalex, scaley);


        Paint paint = new Paint();
        g.drawBitmap(startscreen, 0, 0, paint);




    }
}
