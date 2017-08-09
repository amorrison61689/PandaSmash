package com.amorr.pandasmash;

        import android.os.Handler;
        import android.os.Looper;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.MotionEvent;
        import android.view.View;

        import java.util.ArrayList;

public class PandaSmash extends AppCompatActivity implements View.OnTouchListener, Runnable
{
    GameScreen gamescreen;
    GameOverScreen gameoverscreen;
    StartScreen startscreen;
    public ArrayList<PandaPos> pandapos;
    int startx;
    int starty;
    protected int x, y;
    int counter =0;
    Handler handler;
    //PandaPos is a class of threads, the array list is an array of threads

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        pandapos = new ArrayList<PandaPos>();

        pandapos.add(new PandaPos(18,46,this));
        pandapos.add(new PandaPos(18,90,this));
        pandapos.add(new PandaPos(18,132,this));
        pandapos.add(new PandaPos(70,46,this));
        pandapos.add(new PandaPos(70,90,this));
        pandapos.add(new PandaPos(70,132,this));
        pandapos.add(new PandaPos(124,46,this));
        pandapos.add(new PandaPos(124,90,this));
        pandapos.add(new PandaPos(124,132,this));

        //this is the game screen
        gamescreen = new GameScreen(this);

        //this is the game over screen
        gameoverscreen = new GameOverScreen(this);

        startscreen = new StartScreen(this);
        //make visible
        setContentView(startscreen);

        handler = new Handler();

        gamescreen.setOnTouchListener(this);
        //this will change the pictures randomly from PandaPos
        for( int i = 0; i < 9; i++)
        {
            Thread pos;
            pos = new Thread(pandapos.get(i));
            pos.start();
        }

        //this thread makes the screens change and sets the time limits
        Thread pos;
        pos = new Thread(this);
        pos.start();
    }
    public void run()
    {
        Looper.prepare();
        long starttime =System.currentTimeMillis();

        //this will make the app sleep for 5 sec before changing to the gamescreen
        try
        {
            Thread.sleep(5000);
        }
        catch(InterruptedException e)
        {
        }
        handler.post(gamescreen);

        while (true)
        {
            try
            {
                Thread.sleep(2500); //this is how long you have to click the icon
            }
            catch(InterruptedException e)
            {
            }
            //****the program keeps crashing here****
            if (System.currentTimeMillis()-starttime > 30000)
            {
                //this calls the thread in gameoverscreen
                handler.post(gameoverscreen);

                break;
           }
        }
    }


    @Override
    public boolean onTouch(View v, MotionEvent event)
    {
        if (event.getAction()==MotionEvent.ACTION_DOWN)
        {
            startx = (int) ((event.getX()) / gamescreen.scalex);
            starty = (int) ((event.getY()) / gamescreen.scaley);


            for (int j = 0; j < 9; j++)
            {
                if (pandapos.get(j).clicked(startx - (38 / 2), starty - (38 / 2)) == true)
                {
                   if (pandapos.get(j).image == 5)
                    {
                        counter = counter + 1;
                    }
                    if (pandapos.get(j).image == 6)
                    {
                        counter = counter + 2;
                    }
                   if (pandapos.get(j).image == 7)
                    {
                        counter = counter - 3;
                    }
                    //if the space is empty give 0 points
                    if (pandapos.get(j).image < 5)
                    {
                        counter = counter + 0;
                    }
                }
            }
        }
        return true;
    }
}
