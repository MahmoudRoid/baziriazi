package mahmoud.baziriazi;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends Activity {
    boolean doubleBackToExitPressedOnce=false;
    ImageView imgplay;
    ImageView imgexit;
    final CharSequence[] levels = {"ساده","متوسط","سخت"};
    int levelid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgexit=(ImageView)findViewById(R.id.imageexit);
        imgplay=(ImageView)findViewById(R.id.imageplay);


    }

    public void exitapp(View view){
        finish();
    }

    public void playactivityasan(){

        // goto second activity

        Intent myintent = new Intent(MainActivity.this,playasan.class);
        startActivity(myintent);

    }
    public void playactivitymotevaset(){

        // goto second activity

        Intent intent = new Intent(MainActivity.this,Playmotevaset.class);
        startActivity(intent);

    }
    public void   playactivitysakht(){

        // goto second activity

        Intent intent = new Intent(MainActivity.this,playsakht.class);
        startActivity(intent);
    }
    @SuppressLint("NewApi") public void setlevel(View view){
        // entekhabe noe level

        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this,AlertDialog.THEME_TRADITIONAL);
        alert.setInverseBackgroundForced(true);


        alert.setTitle("انتخاب سطح بازی");
        alert.setSingleChoiceItems(levels,-1, new

                DialogInterface.OnClickListener()

                {

                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        if(levels[which]=="ساده")

                        {

                            levelid=1;
                        }

                        else if (levels[which]=="متوسط")

                        {

                            levelid=2;

                        }
                        else if (levels[which]=="سخت")

                        {

                            levelid=3;

                        }

                    }


                });

        alert.setPositiveButton("همین خوبه", new DialogInterface.OnClickListener() {


            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                switch (levelid) {
                    case 1:
                        playactivityasan();
                        break;

                    case 2:
                        playactivitymotevaset();
                        break;
                    case 3:;
                        playactivitysakht();
                        break;
                }


            }
        });

        alert.show();

    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, getResources().getString(R.string.doubleclicktoexit), Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

}

