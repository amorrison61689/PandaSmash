package com.amorr.pandasmash;

import android.os.Looper;

/**
 * Created by amorr on 8/3/2017.
 */

//thread values

public class PandaPos implements Runnable
{
    protected int x, y;
    int image;
    PandaSmash mainclass;


    public PandaPos(int x, int y, PandaSmash m) //constructor
    {
        this.x=x;
        this.y=y;
        mainclass = (PandaSmash)m;

        image = (int)(Math.random()*8);
    }

    public boolean clicked(int startx, int starty)
    {
        if((startx-x)*(startx-x)+(starty-y)*(starty-y) < (15*15))
            return true;
        else
            return false;
    }
    public void run()
    {
        Looper.prepare();

        while (true)
        {

            image = (int)(Math.random()*8);


            mainclass.gamescreen.postInvalidate();

            try
            {
                Thread.sleep((int)(5000*Math.random())); //this is how long you have to click the icon
            }
            catch(InterruptedException e)
            {
            }

        }
    }

}
