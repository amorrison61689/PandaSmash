package com.amorr.pandasmash;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Looper;
import android.view.View;

/**
 * Created by amorr on 8/3/2017.
 */

public class GameScreen extends View implements Runnable
{
    PandaSmash mainclass;
    Bitmap gamescreen;
    Bitmap img;
    Bitmap giantpanda;
    Bitmap redpanda;
    Bitmap polar;
    Bitmap empty;
    int i;
    float scalex;
    float scaley;




    public GameScreen(Context c)
    {
        super(c);
        mainclass = (PandaSmash)c;

        //load in bitmaps
        giantpanda = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.panda), 38, 38, false);
        redpanda = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.redpanda), 38, 38, false);
        polar = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.polar), 38, 38, false);
        empty = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.empty), 38, 38, false);
        gamescreen = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.game_screen), 180, 200, false);


    }


    protected void onDraw(Canvas g)
    {
        scalex = (float)(g.getWidth()/180);
        scaley = (float)(g.getHeight()/200);
        g.scale(scalex, scaley);

        int bit;
        Paint paint = new Paint();
        g.drawBitmap(gamescreen, 0, 0, paint);



        for (i = 0; i < 8; i++)
        {
            bit = mainclass.pandapos.get(i).image;
            if (bit < 5) {
                img = empty;
            } else if (bit == 5) {
                img = giantpanda;
            } else if (bit == 6) {
                img = redpanda;
            } else if (bit == 7) {
                img = polar;
            }
            g.drawBitmap(img, mainclass.pandapos.get(i).x, mainclass.pandapos.get(i).y, paint);

        }
        paint.setColor(Color.WHITE);
        g.drawRect(65,190,125,200, paint);
        paint.setColor(Color.BLACK);
        g.drawText("Score: " + mainclass.counter, 70, 200, paint);


    }


    @Override
    public void run()
    {
        mainclass.setContentView(this);
    }
}
