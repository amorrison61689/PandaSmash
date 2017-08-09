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

public class GameOverScreen extends View implements Runnable
{
    PandaSmash mainclass;
    Bitmap gameoverscreen;
    float scalex;
    float scaley;
    public GameOverScreen (Context c)
    {
        super(c);
        mainclass = (PandaSmash)c;
        gameoverscreen = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.gameoverscreen), 180, 200, false);
    }

    protected void onDraw(Canvas g)
    {
        scalex = (float) (g.getWidth() / 180);
        scaley = (float) (g.getHeight() / 200);
        g.scale(scalex, scaley);


        Paint paint = new Paint();
        g.drawBitmap(gameoverscreen, 0, 0, paint);

        paint.setColor(Color.BLACK);
        g.drawRect(5,40,173,190, paint);
        paint.setColor(Color.WHITE);
        g.drawText("Score: " + mainclass.counter, 65, 100, paint);
    }

    //this is called by PandaSmash to put the end screen after game is over
    @Override
    public void run()
    {
        mainclass.setContentView(this);
    }

}
