package mahmoud.baziriazi;

import java.util.Random;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class playasan extends Activity {
    String[] backcolors=new String[]{"#00e0ff","#f07c05","#d6cf23"};

    TextView tvadad1;  // adade aval
    TextView tvadad2;	// adade 2vom
    TextView tvadad3;	// adade 3vom
    TextView tvplus;	// +
    TextView tvresult;	// result
    public Button btntrue;
    public Button btnfalse;
    int adad;
    boolean bool;
    public int[] myrandomnumbers=new int[2];
    public int result;
    public int count=0;
    private ProgressBar customProgress;
    public int myProgress=0;
    public CountDownTimer mCountDownTimer;
    public int counter=1; // baraye inke az dafeye dovom b bad progress bar kar konad
    public int etmam=1; // baraye inke 2 bar methode payan() seda zade nashavad

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playasan);
        RelativeLayout rl = (RelativeLayout)findViewById(R.id.relativelayout);
        tvadad1=(TextView) findViewById(R.id.textView1);
        tvadad2=(TextView) findViewById(R.id.textView2);
        tvadad3=(TextView) findViewById(R.id.textView3);
        btntrue = (Button) findViewById(R.id.buttontrue);
        customProgress =(ProgressBar)findViewById(R.id.customProgress);

        // baraye range backgroud
        int min = 0;
        int max = 2;
        Random backcolorrandom = new Random();
        int i1 = backcolorrandom.nextInt(max - min + 1) + min;
        rl.setBackgroundColor(Color.parseColor(backcolors[i1]));

        trueorfalse();
    }

    protected void trueorfalse(){

        bool=booleanrandom();
        if(bool==true) maketrue_ebarat();
        else           makefalse_ebarat();


    }


    // BUTTON TRUE
    public void btntrue(View view){


        try{
            mCountDownTimer.cancel();
            customProgress.setProgress(0);
        }
        catch(Exception e){
            e.printStackTrace();
        }

        finally{

            if(result==myrandomnumbers[0]+myrandomnumbers[1]){

                count++;
                // continue
                trueorfalse();
                myprogressbar();
            }
            else {
                if(etmam==1){
                    payan();
                    etmam=2;
                }

            }
        }

    }

    // BUTTON FALSE
    public void btnfalse(View view){


        try{
            mCountDownTimer.cancel();
            customProgress.setProgress(0);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            if(result!=myrandomnumbers[0]+myrandomnumbers[1]){
                count++;
                //continue
                trueorfalse();
                myprogressbar();
            }
            else{
                if(etmam==1){
                    payan();
                    etmam=2;
                }
            }
        }

    }

    public void payan(){
        // namayesh emtiaz va az sar giri
        try{
            String emtiaz=String.valueOf(count);
            Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(500);
            // mire b activitie result va natije ra ha mibarad

            Intent result_intent = new Intent(getApplicationContext(),Result.class);
            result_intent.putExtra("emtiaz",emtiaz);
            startActivity(result_intent);
            onDestroy();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

    public void maketrue_ebarat(){

        for(int i=0;i<=1;i++){

            myrandomnumbers[i]=random();

        }
        result=myrandomnumbers[0]+myrandomnumbers[1];
        tvadad1.setText(String.valueOf(myrandomnumbers[0]));
        tvadad2.setText(String.valueOf(myrandomnumbers[1]));
        tvadad3.setText(String.valueOf(result));


    }

    public void makefalse_ebarat(){
        // ijade 2 adade random va zakhire dar arraye

        for(int i=0;i<=1;i++){

            myrandomnumbers[i]=random();

        }
        // set the int array to text views
        tvadad1.setText(String.valueOf(myrandomnumbers[0]));
        tvadad2.setText(String.valueOf(myrandomnumbers[1]));
        int min=myrandomnumbers[0]+myrandomnumbers[1];
        int tmp;

        Random random=new Random();
        tmp=random.nextInt(100);

        if(tmp<30){
            // adade result kamtar az jame adaad bashad
            int x=min-2;
            result=random.nextInt(min-x)+x;
        }
        else{
            // adade result bisgtar az jame adaad basha
            int x=min+3;
            result=random.nextInt(x-min)+min;
        }
        tvadad3.setText(""+result);
    }





    public int random(){

        Random random = new Random();
        adad = random.nextInt(10 - 1) + 1;
        return adad;
    }

    public boolean booleanrandom(){
        Random random = new Random();
        adad = random.nextInt(100);
        if(adad<50)
            return true;
        else
            return false;
    }

    public void myprogressbar(){
        myProgress=0;
        mCountDownTimer=new CountDownTimer(2000,90) {

            @Override
            public void onTick(long millisUntilFinished) {
                // TODO Auto-generated method stub
                myProgress++;
                customProgress.setProgress(myProgress);
            }

            @Override
            public void onFinish() {
                // TODO Auto-generated method stub
                //Toast.makeText(getApplicationContext(), "finished", 1000).show();
                if(etmam==1) {
                    payan();
                    etmam=2;
                }
            }
        };
        mCountDownTimer.start();


    }


}

// vaghti user ham eshtebah miznad va ham inke timer tamoom mishe , nabayad jofteshoon  payan() ro seda bezanan

